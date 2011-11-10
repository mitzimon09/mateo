package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudVacacionesController extends SolicitudRHController{
	def springSecurityService
	def procesoService
	def solicitudVacacionesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "lista", params: params)
    }

    def lista() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [solicitudesVacaciones: SolicitudVacaciones.list(params), totalDeSolicitudesVacaciones: SolicitudVacaciones.count()]
    }

    def nueva() {
    	def solicitudVacaciones = new SolicitudVacaciones()
        solicitudVacaciones.properties = params
        return [solicitudVacaciones: solicitudVacaciones]
    }

    def crea() {
        def solicitudVacaciones = new SolicitudVacaciones(params)
        solicitudVacaciones.fechaCaptura = new Date()
        solicitudVacaciones.usuarioCrea = springSecurityService.currentUser
        solicitudVacaciones.diasVacaciones = solicitudVacaciones.fechaFinal - solicitudVacaciones.fechaInicial
        
        log.debug "dias de vacaciones" + solicitudVacaciones.diasVacaciones
        
        log.debug "                      empleado " + solicitudVacaciones.empleado
        
        if ((solicitudVacaciones.diasVacaciones < 7) || derechoAPrimaVacacionalUnaVezAnual(solicitudVacaciones.empleado)){
        	solicitudVacaciones.userPrimaVacacional = 0.00
        	def date = new Date()
        	log.debug "cuestion " + date
        }
        
        //if kilometros >= 900, se agregan 2 días a las vacaciones del empleado
        if (((solicitudVacaciones.kilometros >= 900) && (solicitudVacaciones.visitaPadres)) && !visitaPadresUnaVezAnual){
        	solicitudVacacaciones.diasVacaciones = solicitudVacacaciones.diasVacaciones + 2
        }
        
        if (!solicitudVacaciones.save(flush: true)) {
            render(view: "nueva", model: [solicitudVacaciones: solicitudVacaciones])
            return
        }
		log.debug "Se supone que debe de ir " + solicitudVacaciones
		flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), solicitudVacaciones.id])
        redirect(action: "ver", id: solicitudVacaciones.id)
    }

    def ver() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        def permisos = permisos()
        if (!solicitudVacaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "list")
            return
        }

        [solicitudVacaciones: solicitudVacaciones, permisos: permisos]
    }

    def edita() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        def permisos = permisos()
        if (!solicitudVacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }

        [solicitudVacaciones: solicitudVacaciones, permisos: permisos]
    }

    def actualiza() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        if (!solicitudVacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudVacaciones.version > version) {
                solicitudVacaciones.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')] as Object[],
                          "Another user has updated this SolicitudVacaciones while you were editing")
                render(view: "edita", model: [solicitudVacaciones: solicitudVacaciones])
                return
            }
        }

        solicitudVacaciones.properties = params

        if (!solicitudVacaciones.save(flush: true)) {
            render(view: "edita", model: [solicitudVacaciones: solicitudVacaciones])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), solicitudVacaciones.id])
        redirect(action: "ver", id: solicitudVacaciones.id)
    }

    def elimina() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        if (!solicitudVacaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }

        try {
            solicitudVacaciones.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "ver", id: params.id)
        }
    }
    
    def derechoAPrimaVacacionalUnaVezAnual(Empleado empleado){
    	def solicitudesVacaciones = solicitudVacacionesService.getSolicitudesAnuales(empleado)
    	
    	for (SolicitudVacaciones tmp in solicitudesVacaciones){
    		if (tmp.primaVacacional > 0){
    			return false
    		}
    	}
    	return true
    }
    
    def visitaPadresUnaVezAnual(Empleado empleado){
    	def solicitudesVacaciones = solicitudVacacionesService.getSolicitudesAnuales(empleado)
    	log.debug "o____o"
    	for (SolicitudVacaciones tmp in solicitudesVacaciones){
    		if (tmp.visitaPadres){
    			return false
    		}
    	}
    	return true
    }
}
