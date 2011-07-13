package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class CompraController {
    def springSecurityService
    def folioService
    
    static allowedMethods = [crea: "POST", update: "POST", elimina: "POST"]

    @Secured(['ROLE_EMP','ROLE_CCP','ROLE_DIRFIN','ROLE_COMPRAS'])
    def index = {
        redirect(action: "lista", params: params)
    }

  	@Secured(['ROLE_EMP','ROLE_CCP','ROLE_DIRFIN','ROLE_COMPRAS'])
    def lista = {
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
    	[compras: Compra.list(params), totalDeCompras: Compra.count()]
  	}

    def nueva = {
        def compra = new Compra(params)
        compra.folio = folioService.temporal()
        if (compra.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compra.folio])
            redirect(action: "edita", id: compra.id)
        }
        else {
            render(view: "nueva", model: [compra: compra])
        }
    }

    def ver = {
        def compra = Compra.get(params.id)
        if (!compra) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
            redirect(action: "lista")
        }
        else {
            [compra: compra]
        }
    }

	@Secured(['ROLE_EMP','ROLE_CCP','ROLE_DIRFIN','ROLE_COMPRAS'])
    def edita = {
        def compra = Compra.get(params.id)
        def permisos = permisos()
        if (!compra) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
            redirect(action: "lista")
        }
        else {
            return [compra: compra, permisos: permisos]
        }
    }

    def actualiza = {
	    params.total = calculaTotal(params)
        def compra = Compra.get(params.id)
        if (compra) {
            if (params.version) {
                def version = params.version.toLong()
                if (compra.version > version) {
                    compra.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'compra.label', default: 'Compra')] as Object[], "Another user has updated this Compra while you were editing")
                    render(view: "edita", model: [compra: compra])
                    return
                }
            }
            compra.properties = params
            
            compra.save(flush: true)
            if (!compra.hasErrors() && compra.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compra.folio])
                redirect(action: "edita", id: compra.id)
            }
            else {
                render(view: "edita", model: [compra: compra])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
            redirect(action: "lista")
        }
    }

    def elimina = {
    	  //params.total = calculaTotal(params)
        def compra = Compra.get(params.id)
        if (compra) {
            try {
                compra.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.folio])
            redirect(action: "lista")
        }
    }
    
    def calculaTotal = {
        println "calcular total"
        def articulos = Articulo.list()
        def total = 0
        for(def articulo in articulos){
            if(articulo.compra.id.toInteger() == params.id.toInteger()){
              total += articulo.total
            }
        }
        println "total " + total
        return total
    }
    
    def permisos = {
        //total de Permisos, 1 = Enviar, 2 = Aprobar/Rechazar, 3 = Comprar/Entregar, 4 = Todos
        def totalPermisos = 0
        def usuario = springSecurityService.currentUser
        if(SpringSecurityUtils.ifAnyGranted('ROLE_EMP')) {
            totalPermisos = 1
        }else if(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')){
            totalPermisos = 2
        }else if(SpringSecurityUtils.ifAnyGranted('ROLE_COMPRAS')){
            totalPermisos = 3
        }
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')){
        	  totalPermisos = 4
        }
        log.debug "totalPermisos = " +totalPermisos
        return totalPermisos
    }
    
    @Secured(['ROLE_EMP'])
    def enviar = {
    	if (SpringSecurityUtils.ifAnyGranted('ROLE_EMP')) {
		def compra = Compra.get(params.id)
		if (compra){
			if(compra.status.equals("CREADA")){
				compra.status = "ENVIADA"
				compra.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'compra.status.message5', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect(action: "lista")
			}
		}
		}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def aprobar = {
    	if (SpringSecurityUtils.ifAnyGranted('ROLE_CCP') || SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN')) {
		def compra = Compra.get(params.id)
		if (compra){
			if(compra.status.equals("ENVIADA") || compra.status.equals("RECHAZADA")){
				compra.status = "APROBADA"
				compra.save(flush:true)
				redirect(action: "lista")
			}
			else if (compra.status.equals("CREADA")){
				flash.message = message(code: 'compra.status.message1', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect(action: "lista")
			}
			else{
				flash.message = message(code: 'compra.status.message2', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect(action: "lista")
			}
		}
		}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def rechazar = {
    if (SpringSecurityUtils.ifAnyGranted('ROLE_CCP') || SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN')) {
		def compra = Compra.get(params.id)
		if (compra){
			if(compra.status.equals("ENVIADA")){
				compra.status = "RECHAZADA"
				compra.save(flush:true)
				redirect(action: "lista")
			}
			else if (compra.status.equals("CREADA")){
				flash.message = message(code: 'compra.status.message1', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect(action: "lista")
			}
			else{
				flash.message = message(code: 'compra.status.message2', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect(action: "lista")
			}
		}
		} 
	}
    
    @Secured(['ROLE_COMPRAS'])
    def comprar = {
    	if (SpringSecurityUtils.ifAnyGranted('ROLE_COMPRAS')) {
			def compra = Compra.get(params.id)
			if (compra){
				if(compra.status.equals("APROBADA")){
					compra.status = "COMPRADA"
					compra.save(flush:true)
					redirect(action: "lista")
				}
				else if (compra.status.equals("CREADA") || compra.status.equals("ENVIADA") || compra.status.equals("RECHAZADA")){
					flash.message = message(code: 'compra.status.message4', args: [message(code: 'compra.label', default: 'Compra'), params.id])
		            redirect(action: "lista")
				}
				else{
					flash.message = message(code: 'compra.status.message6', args: [message(code: 'compra.label', default: 'Compra'), params.id])
		            redirect(action: "lista")
				}
			}    
		}
    }
    
    @Secured(['ROLE_COMPRAS'])
    def entregar = {
    	if (SpringSecurityUtils.ifAnyGranted('ROLE_COMPRAS')) {
			def compra = Compra.get(params.id)
				if (compra){
					if (compra.status.equals("COMPRADA")){
						compra.status = "ENTREGADA"
						compra.save(flush:true)
						redirect(action: "lista")
					}
					else{
						flash.message = message(code: 'compra.status.message3', args: [message(code: 'compra.label', default: 'Compra'), params.id])
				        redirect(action: "lista")
					}
				} 
			}
		}
	}
