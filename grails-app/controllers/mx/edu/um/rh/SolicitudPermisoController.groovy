package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudPermisoController extends SolicitudRHController{

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "lista", params: params)
    }

    def lista() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [solicitudesPermiso: SolicitudPermiso.list(params), totalSolicitudesPermiso: SolicitudPermiso.count()]
    }

    def nueva() {
    	def solicitudPermiso = new SolicitudPermiso()
        solicitudPermiso.properties = params
        return [solicitudPermiso: solicitudPermiso]
    }

    def crea() {
    	//Observaciones
        def observaciones = null
        if (!params.observacion.equals("")){
        	log.debug "Systema"
		    observaciones = new Observaciones(observaciones: params.observacion, usuario: params.usuarioCrea, datecreated: new Date())
        }
	    params.observacion = null
        def solicitudPermiso = new SolicitudPermiso(params)
        solicitudPermiso.observaciones = new LinkedHashSet()
        if(observaciones != null){
		    observaciones.solicitudRH = solicitudPermiso
		    observaciones.save()
		    solicitudPermiso.observaciones.add(observaciones)
        }
        solicitudPermiso.
        solicitudPermiso.usuarioCrea = springSecurityService.currentUser
        solicitudPermiso.totalDeGastos = solicitudPermiso.otros + solicitudPermiso.viaticos + solicitudPermiso.hospedaje
        
        if ((solicitudPermiso.totalDeGastos > 0 || solicitudPermiso.totalDeGastos < 0) && solicitudPermiso.personal){
        	log.debug "in gastos cos personal"
        	if (solicitudPermiso.otros > 0){
	        	solicitudPermiso.errors.rejectValue("otros", "No puedes tener gastos en un permiso personal")
        	}
        	if (solicitudPermiso.viaticos > 0){
	        	solicitudPermiso.errors.rejectValue("viaticos", "No puedes tener gastos en un permiso personal")
        	}
        	if (solicitudPermiso.hospedaje > 0){
	        	solicitudPermiso.errors.rejectValue("hospedaje", "No puedes tener gastos en un permiso personal")
        	}
        	solicitudPermiso.otros = 0.00
        	solicitudPermiso.viaticos = 0.00
        	solicitudPermiso.hospedaje = 0.00
        	render(view: "nueva", model: [solicitudPermiso: solicitudPermiso])
            return
        }
        solicitudPermiso.totalDeGastos = solicitudPermiso.otros + solicitudPermiso.viaticos + solicitudPermiso.hospedaje
        if (!solicitudPermiso.save(flush: true)) {
            render(view: "nueva", model: [solicitudPermiso: solicitudPermiso])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), solicitudPermiso.id])
        redirect(action: "ver", id: solicitudPermiso.id)
    }

    def ver() {
    	
        def solicitudPermiso = SolicitudPermiso.get(params.id)
        def permisos = permisos()
        log.debug "o " +solicitudPermiso.observaciones
        if (!solicitudPermiso) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "lista")
            return
        }

        [solicitudPermiso: solicitudPermiso, permisos: permisos]
    }

    def edita() {
        def solicitudPermiso = SolicitudPermiso.get(params.id)
        def permisos = permisos()
        if (!solicitudPermiso) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "lista")
            return
        }

        [solicitudPermiso: solicitudPermiso, permisos: permisos]
    }

    def actualiza() {
        def solicitudPermiso = SolicitudPermiso.get(params.id)
        if (!solicitudPermiso) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "lista")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (solicitudPermiso.version > version) {
                solicitudPermiso.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso')] as Object[],
                          "Another user has updated this SolicitudPermiso while you were editing")
                render(view: "edita", model: [solicitudPermiso: solicitudPermiso])
                return
            }
        }

        solicitudPermiso.properties = params

        if (!solicitudPermiso.save(flush: true)) {
            render(view: "edita", model: [solicitudPermiso: solicitudPermiso])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), solicitudPermiso.id])
        redirect(action: "ver", id: solicitudPermiso.id)
    }

    def elimina() {
        def solicitudPermiso = SolicitudPermiso.get(params.id)
        if (!solicitudPermiso) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "lista")
            return
        }

        try {
            solicitudPermiso.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "lista")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso'), params.id])
            redirect(action: "ver", id: params.id)
        }
    }
}
