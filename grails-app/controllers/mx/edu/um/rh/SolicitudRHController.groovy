package mx.edu.um.rh

import general.*
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudRHController {

	def springSecurityService
	def procesoService
	def solicitudRHService
	def usuarioEmpleadoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	@Secured(['ROLE_CCP'])
    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		//def solitudesRH = solicitudRHService.getSolicitudesRHByRol()
		//[solicitudesRH: SolicitudesRH, totalDeSolicitudesRH: SolicitudesRH.count()]
		[solicitudesRH: SolicitudRH.list(params), totalDeSolicitudesRH: SolicitudRH.count()]
	}

    def nueva = {
        def solicitudRH = new SolicitudRH()
        solicitudRH.properties = params
        return [solicitudRH: solicitudRH]
    }

    def crea = {
        def solicitudRH = new SolicitudRH(params)
        if (solicitudRH.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), solicitudRH.id])
            redirect(action: "ver", id: solicitudRH.id)
        }
        else {
            render(view: "nueva", model: [solicitudRH: solicitudRH])
        }
    }

    def ver = {
        def solicitudRH = SolicitudRH.get(params.id)
        if (!solicitudRH) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
        else {
            [solicitudRH: solicitudRH]
        }
    }

    def edita = {
        def solicitudRH = SolicitudRH.get(params.id)
        if (!solicitudRH) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
        else {
            return [solicitudRH: solicitudRH]
        }
    }

    def actualiza = {
        def solicitudRH = SolicitudRH.get(params.id)
        if (solicitudRH) {
            if (params.version) {
                def version = params.version.toLong()
                if (solicitudRH.version > version) {
                    
                    solicitudRH.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'solicitudRH.label', default: 'SolicitudRH')] as Object[], "Another user has updated this SolicitudRH while you were editing")
                    render(view: "edita", model: [solicitudRH: solicitudRH])
                    return
                }
            }
            solicitudRH.properties = params
            if (!solicitudRH.hasErrors() && solicitudRH.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), solicitudRH.id])
                redirect(action: "ver", id: solicitudRH.id)
            }
            else {
                render(view: "edita", model: [solicitudRH: solicitudRH])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def solicitudRH = SolicitudRH.get(params.id)
        if (solicitudRH) {
            try {
                solicitudRH.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
    }
    
    
    @Secured(['ROLE_USER'])
    def enviar = {
		def solicitudRH = SolicitudRH.get(params.id)
		if (solicitudRH){
			if(solicitudRH.status.equals("CR")){
				solicitudRH = procesoService.enviar(solicitudRH)
				solicitudRH.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'solicitudRH.status.message5', args: [message(code: 'solicitudRH.label', default: 'solicitudRH'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    @Secured(['ROLE_CCP'])
    def aprobar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
				if(solicitudRH.status.equals("EN") || solicitudRH.status.equals("RE")){
					solicitudRH = procesoService.aprobar(solicitudRH)
					solicitudRH.save(flush:true)
					redirect(action: "lista")
				}
				else if (solicitudRH.status.equals("CR")){
					flash.message = message(code: 'solicitudRH.status.message1', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
			        redirect(action: "lista")
				}
				else{
					flash.message = message(code: 'solicitudRH.status.message2', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
			        redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_RHOPER'])
    def revisar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
				if(solicitudRH.status.equals("AP")){
					solicitudRH = procesoService.revisar(solicitudRH)
					solicitudRH.save(flush:true)
					redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_CCP', 'ROLE_DIRRH'])
    def autorizar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
				if(solicitudRH.status.equals("RE")){
					solicitudRH = procesoService.autorizar(solicitudRH)
					solicitudRH.save(flush:true)
					redirect(action: "lista")
				}
			}
		//}
    }
    
    @Secured(['ROLE_CCP','ROLE_DIRFIN', 'ROLE_RHOPER'])
    def rechazar = {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
				if (solicitudRH.observaciones != ""){
					if(solicitudRH.status.equals("EN")){
						solicitudRH = procesoService.rechazar(solicitudRH)
						solicitudRH.observaciones = params.observaciones
						solicitudRH.save(flush:true)
						redirect(action: "lista")
					}
					else if (solicitudRH.status.equals("CREADA")){
						flash.message = message(code: 'solicitudRH.status.message1', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
			            redirect(action: "lista")
					}
					else{
						flash.message = message(code: 'solicitudRH.status.message2', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
			            redirect(action: "lista")
					}
				}else{
				    flash.message = message(code: 'solicitudRH.observaciones')
				    redirect(action: "edita", id: solicitudRH.id)
				}
			}
	}
	
	@Secured(['ROLE_DIRRH'])
    def cancelar = {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
					solicitudRH = procesoService.cancelar(solicitudRH)
					solicitudRH.save(flush:true)
					redirect(action: "lista")
			}
	}
    
}
