package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class ObservacionesController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [observacionesInstanceList: Observaciones.list(params), observacionesInstanceTotal: Observaciones.count()]
    }

    def create() {
        [observacionesInstance: new Observaciones(params)]
    }

    def save() {
        def observacionesInstance = new Observaciones(params)
        observacionesInstance.usuario = springSecurityService.currentUser
        if (!observacionesInstance.save(flush: true)) {
            render(view: "create", model: [observacionesInstance: observacionesInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), observacionesInstance.id])
        redirect(action: "show", id: observacionesInstance.id)
    }

    def show() {
        def observacionesInstance = Observaciones.get(params.id)
        if (!observacionesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        [observacionesInstance: observacionesInstance]
    }

    def edit() {
        def observacionesInstance = Observaciones.get(params.id)
        if (!observacionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        [observacionesInstance: observacionesInstance]
    }

    def update() {
        def observacionesInstance = Observaciones.get(params.id)
        if (!observacionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (observacionesInstance.version > version) {
                observacionesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'observaciones.label', default: 'Observaciones')] as Object[],
                          "Another user has updated this Observaciones while you were editing")
                render(view: "edit", model: [observacionesInstance: observacionesInstance])
                return
            }
        }

        observacionesInstance.properties = params

        if (!observacionesInstance.save(flush: true)) {
            render(view: "edit", model: [observacionesInstance: observacionesInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), observacionesInstance.id])
        redirect(action: "show", id: observacionesInstance.id)
    }

    def delete() {
        def observacionesInstance = Observaciones.get(params.id)
        if (!observacionesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        try {
            observacionesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
