package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class SeccionController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[seccions: Seccion.list(params), totalDeSeccions: Seccion.count()]
	}

    def nuevo = {
        def seccion = new Seccion()
        seccion.properties = params
        return [seccion: seccion]
    }

    def crea = {
        def seccion = new Seccion(params)
        if (seccion.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'seccion.label', default: 'Seccion'), seccion.id])
            redirect(action: "ver", id: seccion.id)
        }
        else {
            render(view: "nuevo", model: [seccion: seccion])
        }
    }

    def ver = {
        def seccion = Seccion.get(params.id)
        if (!seccion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
            redirect(action: "lista")
        }
        else {
            [seccion: seccion]
        }
    }

    def edita = {
        def seccion = Seccion.get(params.id)
        if (!seccion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
            redirect(action: "lista")
        }
        else {
            return [seccion: seccion]
        }
    }

    def actualiza = {
        def seccion = Seccion.get(params.id)
        if (seccion) {
            if (params.version) {
                def version = params.version.toLong()
                if (seccion.version > version) {
                    
                    seccion.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'seccion.label', default: 'Seccion')] as Object[], "Another user has updated this Seccion while you were editing")
                    render(view: "edita", model: [seccion: seccion])
                    return
                }
            }
            seccion.properties = params
            if (!seccion.hasErrors() && seccion.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'seccion.label', default: 'Seccion'), seccion.id])
                redirect(action: "ver", id: seccion.id)
            }
            else {
                render(view: "edita", model: [seccion: seccion])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def seccion = Seccion.get(params.id)
        if (seccion) {
            try {
                seccion.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seccion.label', default: 'Seccion'), params.id])
            redirect(action: "lista")
        }
    }
}
