package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.plugins.springsecurity.Secured

class RelacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def springSecurityService

    def index() {
        redirect(action: "lista", params: params)
    }

    def lista() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [relaciones: Relacion.list(params), totalDeRelaciones: Relacion.count()]
    }

    def nuevo() {
        [relacion: new Relacion(params)]
    }

    def crea() {
        def relacion = new Relacion(params)
        if (!relacion.save(flush: true)) {
            render(view: "nuevo", model: [relacion: relacion])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'relacion.label', default: 'Relacion'), relacion.id])
        redirect(action: "ver", id: relacion.id)
    }

    def ver() {
        def relacion = Relacion.get(params.id)
        if (!relacion) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "lista")
            return
        }

        [relacion: relacion]
    }

    def edita() {
        def relacion = Relacion.get(params.id)
        if (!relacion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "lista")
            return
        }

        [relacion: relacion]
    }

    def actualiza() {
        def relacion = Relacion.get(params.id)
        if (!relacion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "lista")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (relacion.version > version) {
                relacion.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'relacion.label', default: 'Relacion')] as Object[],
                          "Another user has updated this Relacion while you were editaaing")
                render(view: "edita", model: [relacion: relacion])
                return
            }
        }

        relacion.properties = params

        if (!relacion.save(flush: true)) {
            render(view: "edita", model: [relacion: relacion])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'relacion.label', default: 'Relacion'), relacion.id])
        redirect(action: "ver", id: relacion.id)
    }

    def elimina() {
        def relacion = Relacion.get(params.id)
        if (!relacion) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "lista")
            return
        }

        try {
            relacion.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "lista")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'relacion.label', default: 'Relacion'), params.id])
            redirect(action: "ver", id: params.id)
        }
    }
}
