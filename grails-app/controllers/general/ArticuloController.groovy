package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class ArticuloController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[articulos: Articulo.list(params), totalDeArticulos: Articulo.count()]
	}

    def nuevo = {
        def articulo = new Articulo()
        articulo.properties = params
        return [articulo: articulo]
    }

    def crea = {
        params.total = params.cantidad.toInteger() * params.precioUnitario.toInteger()
        def articulo = new Articulo(params)
        if (articulo.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
            redirect(controller:"compra", action: "actualiza", id: articulo.compra.id)
        }
        else {
            render(view: "nuevo", model: [articulo: articulo])
        }
    }

    def ver = {
        def articulo = Articulo.get(params.id)
        if (!articulo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
        else {
            [articulo: articulo]
        }
    }

    def edita = {
        def articulo = Articulo.get(params.id)
        def permisos = permisos()
        if (!articulo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
        else {
            return [articulo: articulo, permisos: permisos]
        }
    }

    def actualiza = {
        def articulo = Articulo.get(params.id)
        articulo.total = articulo.cantidad.toInteger() * articulo.precioUnitario.toInteger()
       // log.debug "total " + params.total + " :) precio unitario " + params.precioUnitario + " :( cantidad " + params.cantidad
        
        if (articulo) {
            if (params.version) {
                def version = params.version.toLong()
                if (articulo.version > version) {
                    
                    articulo.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'articulo.label', default: 'Articulo')] as Object[], "Another user has updated this Articulo while you were editing")
                    render(view: "edit", model: [articulo: articulo])
                    return
                }
            }
            articulo.properties = params
            if (!articulo.hasErrors() && articulo.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect(controller:"compra", action: "actualiza", id: articulo.compra.id)
            }
            else {
                render(view: "edita", model: [articulo: articulo])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def articulo = Articulo.get(params.id)
        if (articulo) {
            try {
                articulo.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(controller:"articulo", action: "lista", id: articulo.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(action: "edita", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
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
        log.debug "totalPermisos = " + totalPermisos
        return totalPermisos
    }
    
        @Secured(['ROLE_COMPRAS'])
    def comprar = {
			def articulo = Articulo.get(params.id)
			if (articulo){
				if (articulo.status.equals("AGREGADO")){
					articulo.status = "COMPRADO"
					articulo.save(flush:true)
					redirect(controller: "compra", action: "completar", id: articulo.compra.id)
				}
				else {
					flash.message = message(code: 'articulo.status.message7', args: [message(code: 'articulo.label', default: 'articulo'), params.id])
			        redirect(action: "lista")
				}
				
			}
    }
    
    @Secured(['ROLE_EMP'])
    def entregar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_articuloS')) {
			def articulo = Articulo.get(params.id)
			if (articulo){
				if (articulo.status.equals("COMPRADO")){
					articulo.status = "ENTREGADO"
					articulo.save(flush:true)
					redirect(controller: "compra", action: "completar", id: articulo.compra.id)
				}
				else{
					flash.message = message(code: 'articulo.status.message8', args: [message(code: 'articulo.label', default: 'articulo'), params.id])
				    redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_COMPRAS'])
    def cancelar = {
			def articulo = Articulo.get(params.id)
			if (articulo){
				articulo.status = "CANCELADA"
				articulo.save(flush:true)
				redirect(controller: "compra", action: "completar", id: articulo.compra.id)
			}
	}
    
}
