package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class EmpleadoDependientesController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista")
    }

    def lista() {
        log.debug "lista"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [empleadoDependientes: EmpleadoDependientes.list(params), totalDeEmpleadosDependientes: EmpleadoDependientes.count()]
    }

    def nuevo() {
        [empleadoDependientes: new EmpleadoDependientes(params)]
    }

    def crea() {
        def empleadoDependientes = new EmpleadoDependientes(params)
        if (!empleadoDependientes.save(flush: true)) {
            render(view: "nuevo", model: [empleadoDependientes: empleadoDependientes])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), empleadoDependientes.id])
        redirect(action: "ver", id: empleadoDependientes.id)
    }

    def ver() {
        def empleadoDependientes = EmpleadoDependientes.get(params.id)
        if (!empleadoDependientes) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "lista")
            return
        }

        [empleadoDependientes: empleadoDependientes]
    }

    def edita() {
        def empleadoDependientes = EmpleadoDependientes.get(params.id)
        if (!empleadoDependientes) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "lista")
            return
        }

        [empleadoDependientes: empleadoDependientes]
    }

    def actualiza() {
        def empleadoDependientes = EmpleadoDependientes.get(params.id)
        if (!empleadoDependientes) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "lista")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (empleadoDependientes.version > version) {
                empleadoDependientes.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes')] as Object[],
                          "Another user has updated this EmpleadoDependientes while you were editing")
                render(view: "edita", model: [empleadoDependientes: empleadoDependientes])
                return
            }
        }

        empleadoDependientes.properties = params

        if (!empleadoDependientes.save(flush: true)) {
            render(view: "edita", model: [empleadoDependientes: empleadoDependientes])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), empleadoDependientes.id])
        redirect(action: "ver", id: empleadoDependientes.id)
    }

    def elimina() {
        def empleadoDependientes = EmpleadoDependientes.get(params.id)
        if (!empleadoDependientes) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "lista")
            return
        }

        try {
            empleadoDependientes.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "lista")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes'), params.id])
            redirect(action: "ver", id: params.id)
        }
    }
}
