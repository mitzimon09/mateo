package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class CompraController {
	/*
	Status:
	A) CREADA
	B) ENVIADA
	C) APROBADA
	D) RECHAZADA
	E) COMPRADA
	F) ENTREGADA
	G) CANCELADA	
	*/
    def springSecurityService
    def procesoServiceInterface
    
    static allowedMethods = [crea: "POST", update: "POST", elimina: "POST"]

    @Secured(['ROLE_EMP','ROLE_CCP','ROLE_DIRFIN','ROLE_COMPRAS'])
    def index = {
        redirect(action: "lista", params: params)
    }

  	@Secured(['ROLE_EMP','ROLE_CCP','ROLE_DIRFIN','ROLE_COMPRAS'])
    def lista = {
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
    	//def lista = listaPorRoles()
    	//log.debug "lista en lista = " + lista
    	//[compra: lista, totalDeCompras: lista.size()]
    	[compra: Compra.list(params), totalDeCompras: Compra.count()]
  	}
  	
  	def listaPorRoles = {
  	    def compras = Compra.list()
  	    def usuario = springSecurityService.currentUser
  	    def lista = []
  	    for(compra in compras){
  	        //rol CCP
  	        if(compra.status == "ENVIADA" && compra.total < 100 && compra.empesa.organizacion == usuario.empresa.organizacion){
      	        log.debug "ccp"
  	            lista << compra
            }
            //rol Compras
            if(compra.status == "APROBADA" && compra.empesa.organizacion == usuario.empresa.organizacion){
      	        log.debug "compras"
                lista << compra
            }
            //rol DirFin
            if(compra.status == "ENVIADA" || compra.status == "ESPECIAL" && compra.total > 100 && compra.empesa.organizacion == usuario.empresa.organizacion){
                log.debug "dirfin"
  	            lista << compra
            }
            //rol Emp
            if(compra.status == "CREADA" && compra.empresa == usuario.empresa){
                log.debug "emp"
  	            lista << compra
            }
  	    }
  	    return lista
  	}

    def nueva = {
        log.debug "empresa = " + springSecurityService.currentUser.empresa
        def compra = new Compra(params)
        compra.empresa = springSecurityService.currentUser.empresa
        if (compra.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
            redirect(action: "edita", id: compra.id)
        }
        else {
            render(view: "nueva", model: [compra: compra])
        }
    }

    def ver = {
        def compra = Compra.get(params.id)
        if (!compra) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
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
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
                redirect(action: "edita", id: compra.id)
            }
            else {
                render(view: "edita", model: [compra: compra])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
    	  //params.total = calculaTotal(params)
        def compra = Compra.get(params.id)
        if (compra) {
            try {
                compra.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
    }
    
    def calculaTotal = {
        //println "calcular total"
        def articulos = Articulo.list()
        def total = 0
        for(def articulo in articulos){
            if(articulo.compra.id.toInteger() == params.id.toInteger()){
              total += articulo.total
            }
        }
        log.debug "total " + total
        return total
    }
    
    def permisos = {
        //total de Permisos, 1 = Enviar, 2 = Aprobar/Rechazar, 3 = Comprar/Entregar, 4 = Todos
        def totalPermisos = 0
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
        //log.debug "totalPermisos = " + totalPermisos
        return totalPermisos
    }
    
    @Secured(['ROLE_EMP'])
    def enviar = {
    	//log.debug "user" + springSecurityService.currentUser
    	//log.debug "user" + springSecurityService.currentUser.authorities
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_EMP')) {
			def compra = Compra.get(params.id)
			if (compra){
				if(compra.status.equals("CREADA")){
				  log.debug "compra + " + compra
					compra = procesoServiceInterface.enviar(compra)
  				log.debug "compra + " + compra
					compra.save(flush:true)
					redirect(action: "lista")
				}
				else {
					flash.message = message(code: 'compra.status.message5', args: [message(code: 'compra.label', default: 'Compra'), params.id])
			        redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def aprobar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
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
		//}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def rechazar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def compra = Compra.get(params.id)
			if (compra){
			    //log.debug "observaciones $params.observaciones"
				if (compra.observaciones != ""){
					if(compra.status.equals("ENVIADA")){
						compra.status = "RECHAZADA"
						compra.observaciones = params.observaciones
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
				}else{
				    flash.message = message(code: 'compra.observaciones')
				    redirect(action: "edita", id: compra.id)
				}
			}
		//}
	}
    
    @Secured(['ROLE_COMPRAS'])
    def comprar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_COMPRAS')) {
			def compra = Compra.get(params.id)
			if (compra){
				if(compra.status.equals("APROBADA")){
					compra.status = "COMPRADA"
					compra.save(flush:true)
					redirect(controller: "compra", action: "lista", id: compra.id)
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
		//}
    }
    
    @Secured(['ROLE_COMPRAS'])
    def entregar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_COMPRAS')) {
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
		//}
    }
		
	@Secured(['ROLE_COMPRAS'])
    def cancelar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN')) {
			def compra = Compra.get(params.id)
			if (compra){
				//if (compra.status.equals("COMPRADA")){
					compra.status = "CANCELADA"
					compra.save(flush:true)
					redirect(action: "lista")
				//}
				//else{
				//	flash.message = message(code: 'compra.status.message3', args: [message(code: 'compra.label', default: 'Compra'), params.id])
				//    redirect(action: "lista")
				//}
			}
		//}
	}
}
