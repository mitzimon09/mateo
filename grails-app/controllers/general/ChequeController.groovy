package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

class ChequeController {

	def springSecurityService
	def procesoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[cheques: Cheque.list(params), totalDeCheques: Cheque.count()]
	}

    def nuevo = {
        def cheque = new Cheque()
        cheque.properties = params
        return [cheque: cheque]
    }

    def crea = {
        def cheque = new Cheque(params)
        if (cheque.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'cheque.label', default: 'Cheque'), cheque.id])
            redirect(action: "ver", id: cheque.id)
        }
        else {
            render(view: "nuevo", model: [cheque: cheque])
        }
    }

    def ver = {
        def cheque = Cheque.get(params.id)
        if (!cheque) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
            redirect(action: "lista")
        }
        else {
            [cheque: cheque]
        }
    }

    def edita = {
        def cheque = Cheque.get(params.id)
        if (!cheque) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
            redirect(action: "lista")
        }
        else {
            return [cheque: cheque]
        }
    }

    def actualiza = {
        def cheque = Cheque.get(params.id)
        if (cheque) {
            if (params.version) {
                def version = params.version.toLong()
                if (cheque.version > version) {
                    
                    cheque.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cheque.label', default: 'Cheque')] as Object[], "Another user has updated this Cheque while you were editing")
                    render(view: "edita", model: [cheque: cheque])
                    return
                }
            }
            cheque.properties = params
            if (!cheque.hasErrors() && cheque.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cheque.label', default: 'Cheque'), cheque.id])
                redirect(action: "ver", id: cheque.id)
            }
            else {
                render(view: "edita", model: [cheque: cheque])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def cheque = Cheque.get(params.id)
        if (cheque) {
            try {
                cheque.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
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
		//}
	}
	
	@Secured(['ROLE_COMPRAS'])
    def cancelar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN')) {
			def cheque = Cheque.get(params.id)
			if (cheque){
				//if (cheque.status.equals("COMPRADA")){
					cheque = procesoService.cancelar(cheque)
					cheque.save(flush:true)
					redirect(action: "lista")
				//}
				//else{
				//	flash.message = message(code: 'cheque.status.message3', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
				//    redirect(action: "lista")
				//}
			}
		//}
	}
    
}
