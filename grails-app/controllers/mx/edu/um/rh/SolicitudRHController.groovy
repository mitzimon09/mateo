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

	@Secured(['ROLE_CCP', 'ROLE_DIRRH'])
    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		log.debug "o " + springSecurityService.currentUser.authorities
		def solicitudesRH1 = solicitudRHService.getSolicitudesRHByRol()
		if(solicitudesRH1.size() > 0)
		[solicitudesRH: solicitudesRH1, totalDeSolicitudesRH: solicitudesRH1.size()]
		else{
		[solicitudesRH: SolicitudRH.list(params), totalDeSolicitudesRH: SolicitudRH.count()]
		}
	}

    def nueva = {
        def solicitudRH = new SolicitudRH()
        solicitudRH.properties = params
        solicitudRH.fechaCaptura = new Date()
        return [solicitudRH: solicitudRH]
    }

    def crea = {
        def solicitudRH = new SolicitudRH(params)
        solicitudRH.usuarioCrea = springSecurityService.currentUser
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
        def permisos = permisos()
        log.debug "fechas " (solicitudRH.fechaFinal - solicitudRH.fechaInicial)
        if (!solicitudRH) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
        else {
            [solicitudRH: solicitudRH, permisos: permisos]
        }
    }

    def edita = {
        def solicitudRH = SolicitudRH.get(params.id)
        def permisos = permisos()
        if (!solicitudRH) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudRH.label', default: 'SolicitudRH'), params.id])
            redirect(action: "lista")
        }
        else {
            return [solicitudRH: solicitudRH, permisos: permisos]
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
        if(solicitudRH.status.equals("CR")){
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
		    else{
		    	cancelar()
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
			if(solicitudRH.status.equals("CR") || solicitudRH.status.equals("SU")){
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
				solicitudRH.usuarioRecibe = springSecurityService.currentUser
				solicitudRH.fechaRecibe = new Date()
				if(solicitudRH.status.equals("EN") || solicitudRH.status.equals("RE")){
					solicitudRH = procesoService.aprobar(solicitudRH)
					solicitudRH.usuarioRecibe = springSecurityService.currentUser
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
    
    @Secured(['ROLE_RHOPER', 'ROLE_DIRRH'])
    def autorizar = {
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_DIRFIN') || SpringSecurityUtils.ifAnyGranted('ROLE_CCP')) {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
				solicitudRH.usuarioAutoriza = springSecurityService.currentUser
				solicitudRH.fechaAutoriza = new Date()
				if(solicitudRH.status.equals("RV")){
					solicitudRH = procesoService.autorizar(solicitudRH)
					solicitudRH.usuarioAutoriza = springSecurityService.currentUser
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
	
	@Secured(['ROLE_DIRRH', 'ROLE_CCP'])
    def suspender = {
			def solicitudRH = SolicitudRH.get(params.id)
			if (solicitudRH){
					solicitudRH = procesoService.suspender(solicitudRH)
					solicitudRH.save(flush:true)
					redirect(action: "lista")
			}
	}
	
	@Secured(['ROLE_DIRRH', 'ROLE_RHOPER'])
	def seleccionarPorRango() {
		//log.debug "o " + springSecurityService.currentUser.authorities
		def fechaEmpiezaRango = new Date()
		def fechaTerminaRango = new Date()
		def empleados = solicitudRHService.getEmpleadosDeSolicitudesRHByRangoDeFecha(fechaEmpiezaRango, fechaTerminaRango)
		[empleados: empleados, totalDeEmpleados: empleados.size()]
	}
	
    @Secured(['ROLE_DIRRH', 'ROLE_RHOPER'])
	def rangoList = {
		log.info "debug"
		log.debug params
		def fechaEmpiezaRango = params.fechaEmpiezaRango
		def fechaTerminaRango = params.fechaTerminaRango
		log.debug "fecha inicial" + params.fechaEmpiezaRango
		log.debug "fecha final" + params.fechaTerminaRango
		
		def solicitudesRH1 = solicitudRHService.getSolicitudesRHByRangoDeFecha(fechaEmpiezaRango, fechaTerminaRango)
		[solicitudesRH: solicitudesRH1, totalDeSolicitudesRH: solicitudesRH1.size()]
	}
	
	def rango(){
		
	}
	
	def buscarPorRango(){
	
		render(view: "rango")
	}
	
	def permisos = {
        //total de Permisos, 1 = Enviar/continuar proceso despues de suspendida, 2 = Suspender/Rechazar/aprobar, 3= rhoper:revisar/autorizar/rechazar
        //3= dirh: rechazar/autorizar/cancelar, 4 = Todos
        def totalPermisos = 0
        if(SpringSecurityUtils.ifAnyGranted('ROLE_USER')) {
            totalPermisos = 1
        }else if(SpringSecurityUtils.ifAnyGranted('ROLE_CCP')){
            totalPermisos = 2
        }else if(SpringSecurityUtils.ifAnyGranted('ROLE_RHOPER')){
            totalPermisos = 3
        }
        else if (SpringSecurityUtils.ifAnyGranted('ROLE_DIRRH')){
        	totalPermisos = 4
        }
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')){
        	  totalPermisos = 5
        }
        log.debug "totalPermisosCompra = " + totalPermisos
        return totalPermisos
    }
    
}
