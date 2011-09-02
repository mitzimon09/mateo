package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class VacacionesController {

	def springSecurityService
	def procesoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[vacacionesList: Vacaciones.list(params), totalDevacaciones: Vacaciones.count()]
	}

    def nueva = {
        def vacaciones = new Vacaciones()
        vacaciones.properties = params
        return [vacaciones: vacaciones]
    }

    def crea = {
        def vacaciones = new Vacaciones(params)
        if (vacaciones.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), vacaciones.id])
            redirect(action: "ver", id: vacaciones.id)
        }
        else {
            render(view: "nueva", model: [vacaciones: vacaciones])
        }
    }

    def ver = {
        def vacaciones = Vacaciones.get(params.id)
        if (!vacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
        else {
            [vacaciones: vacaciones]
        }
    }

    def edita = {
        def vacaciones = Vacaciones.get(params.id)
        if (!vacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
        else {
            return [vacaciones: vacaciones]
        }
    }

    def actualiza = {
        def vacaciones = Vacaciones.get(params.id)
        if (vacaciones) {
            if (params.version) {
                def version = params.version.toLong()
                if (vacaciones.version > version) {
                    
                    vacaciones.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vacaciones.label', default: 'Vacaciones')] as Object[], "Another user has updated this Vacaciones while you were editing")
                    render(view: "edita", model: [vacaciones: vacaciones])
                    return
                }
            }
            vacaciones.properties = params
            if (!vacaciones.hasErrors() && vacaciones.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), vacaciones.id])
                redirect(action: "ver", id: vacaciones.id)
            }
            else {
                render(view: "edita", model: [vacaciones: vacaciones])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def vacaciones = Vacaciones.get(params.id)
        if (vacaciones) {
            try {
                vacaciones.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
    }
    
    @Secured(['ROLE_EMP'])
    def enviar = {
		def vacaciones = Vacaciones.get(params.id)
		if (vacaciones){
			if(vacaciones.status.equals("CREADA")){
				vacaciones = procesoService.enviar(vacaciones)
				vacaciones.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'vacaciones.status.message5', args: [message(code: 'vacaciones.label', default: 'vacaciones'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def aprobar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def vacaciones = Vacaciones.get(params.id)
			if (vacaciones){
				if(vacaciones.status.equals("ENVIADA") || vacaciones.status.equals("RECHAZADA")){
					vacaciones = procesoService.aprobar(vacaciones)
					vacaciones.save(flush:true)
					redirect(action: "lista")
				}
				else if (vacaciones.status.equals("CREADA")){
					flash.message = message(code: 'vacaciones.status.message1', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
			        redirect(action: "lista")
				}
				else{
					flash.message = message(code: 'vacaciones.status.message2', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
			        redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN'])
    def rechazar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def vacaciones = Vacaciones.get(params.id)
			if (vacaciones){
			    //log.debug "observaciones $params.observaciones"
				if (vacaciones.observaciones != ""){
					if(vacaciones.status.equals("ENVIADA")){
						vacaciones = procesoService.rechazar(vacaciones)
						vacaciones.observaciones = params.observaciones
						vacaciones.save(flush:true)
						redirect(action: "lista")
					}
					else if (vacaciones.status.equals("CREADA")){
						flash.message = message(code: 'vacaciones.status.message1', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
			            redirect(action: "lista")
					}
					else{
						flash.message = message(code: 'vacaciones.status.message2', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
			            redirect(action: "lista")
					}
				}else{
				    flash.message = message(code: 'vacaciones.observaciones')
				    redirect(action: "edita", id: vacaciones.id)
				}
			}
		//}
	}
	
	@Secured(['ROLE_COMPRAS'])
    def cancelar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN')) {
			def vacaciones = Vacaciones.get(params.id)
			if (vacaciones){
				//if (vacaciones.status.equals("COMPRADA")){
					vacaciones = procesoService.cancelar(vacaciones)
					vacaciones.save(flush:true)
					redirect(action: "lista")
				//}
				//else{
				//	flash.message = message(code: 'vacaciones.status.message3', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
				//    redirect(action: "lista")
				//}
			}
		//}
	}
    
}
