package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

class PermisoController {
	
	def springSecurityService
	def procesoService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[permisos: Permiso.list(params), totalDePermisos: Permiso.count()]
	}

    def create = {
        def permiso = new Permiso()
        permiso.properties = params
        return [permiso: permiso]
    }

    def crea = {
        def permiso = new Permiso(params)
        if (permiso.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'permiso.label', default: 'Permiso'), permiso.id])
            redirect(action: "ver", id: permiso.id)
        }
        else {
            render(view: "nuevo", model: [permiso: permiso])
        }
    }

    def ver = {
        def permiso = Permiso.get(params.id)
        if (!permiso) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
            redirect(action: "lista")
        }
        else {
            [permiso: permiso]
        }
    }

    def edita = {
        def permiso = Permiso.get(params.id)
        if (!permiso) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
            redirect(action: "lista")
        }
        else {
            return [permiso: permiso]
        }
    }

    def actualiza = {
        def permiso = Permiso.get(params.id)
        if (permiso) {
            if (params.version) {
                def version = params.version.toLong()
                if (permiso.version > version) {
                    
                    permiso.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'permiso.label', default: 'Permiso')] as Object[], "Another user has updated this Permiso while you were editing")
                    render(view: "edita", model: [permiso: permiso])
                    return
                }
            }
            permiso.properties = params
            if (!permiso.hasErrors() && permiso.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'permiso.label', default: 'Permiso'), permiso.id])
                redirect(action: "ver", id: permiso.id)
            }
            else {
                render(view: "edita", model: [permiso: permiso])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def permiso = Permiso.get(params.id)
        if (permiso) {
            try {
                permiso.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'permiso.label', default: 'Permiso'), params.id])
            redirect(action: "lista")
        }
    }
    
        
    @Secured(['ROLE_EMP'])
    def enviar = {
		def cheque = Cheque.get(params.id)
		if (cheque){
			if(cheque.status.equals("CREADA")){
				cheque = procesoService.enviar(cheque)
				cheque.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'cheque.status.message5', args: [message(code: 'cheque.label', default: 'cheque'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def aprobar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def cheque = Cheque.get(params.id)
			if (cheque){
				if(cheque.status.equals("ENVIADA") || cheque.status.equals("RECHAZADA")){
					cheque = procesoService.aprobar(cheque)
					cheque.save(flush:true)
					redirect(action: "lista")
				}
				else if (cheque.status.equals("CREADA")){
					flash.message = message(code: 'cheque.status.message1', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
			        redirect(action: "lista")
				}
				else{
					flash.message = message(code: 'cheque.status.message2', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
			        redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def rechazar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def cheque = Cheque.get(params.id)
			if (cheque){
			    //log.debug "observaciones $params.observaciones"
				if (cheque.observaciones != ""){
					if(cheque.status.equals("ENVIADA")){
						cheque = procesoService.rechazar(cheque)
						cheque.observaciones = params.observaciones
						cheque.save(flush:true)
						redirect(action: "lista")
					}
					else if (cheque.status.equals("CREADA")){
						flash.message = message(code: 'cheque.status.message1', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
			            redirect(action: "lista")
					}
					else{
						flash.message = message(code: 'cheque.status.message2', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
			            redirect(action: "lista")
					}
				}else{
				    flash.message = message(code: 'cheque.observaciones')
				    redirect(action: "edita", id: cheque.id)
				}
			}
	}
	
	@Secured(['ROLE_COMPRAS'])
    def cancelar = {
			def cheque = Cheque.get(params.id)
			if (cheque){
					cheque = procesoService.cancelar(cheque)
					cheque.save(flush:true)
					redirect(action: "lista")
			}
	}
    
}
