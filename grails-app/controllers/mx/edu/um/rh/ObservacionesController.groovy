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
        [observacionesList: Observaciones.list(params), observacionesTotal: Observaciones.count()]
    }

    def create() {
        [observaciones: new Observaciones(params)]
    }

    def save() {
        def observaciones = new Observaciones(params)
        observaciones.usuario = springSecurityService.currentUser
        if (!observaciones.save(flush: true)) {
            render(view: "create", model: [observaciones: observaciones])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), observaciones.id])
        redirect(action: "show", id: observaciones.id)
    }

    def show() {
        def observaciones = Observaciones.get(params.id)
        if (!observaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        [observaciones: observaciones]
    }

    def edit() {
        def observaciones = Observaciones.get(params.id)
        if (!observaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        [observaciones: observaciones]
    }

    def update() {
        def observaciones = Observaciones.get(params.id)
        if (!observaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (observaciones.version > version) {
                observaciones.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'observaciones.label', default: 'Observaciones')] as Object[],
                          "Another user has updated this Observaciones while you were editing")
                render(view: "edit", model: [observaciones: observaciones])
                return
            }
        }

        observaciones.properties = params

        if (!observaciones.save(flush: true)) {
            render(view: "edit", model: [observaciones: observaciones])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), observaciones.id])
        redirect(action: "show", id: observaciones.id)
    }

    def delete() {
        def observaciones = Observaciones.get(params.id)
        if (!observaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
            return
        }

        try {
            observaciones.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'observaciones.label', default: 'Observaciones'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
