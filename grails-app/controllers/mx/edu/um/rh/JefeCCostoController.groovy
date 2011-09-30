package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class JefeCCostoController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[jefesCCosto: JefeCCosto.list(params), totalDejefesCCosto: JefeCCosto.count()]
	}

    def nuevo = {
        def jefeCCosto = new JefeCCosto()
        jefeCCosto.properties = params
        return [jefeCCosto: jefeCCosto]
    }

    def crea = {
        def jefeCCosto = new JefeCCosto(params)
        if (jefeCCosto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), jefeCCosto.id])
            redirect(action: "ver", id: jefeCCosto.id)
        }
        else {
            render(view: "nuevo", model: [jefeCCosto: jefeCCosto])
        }
    }

    def ver = {
        def jefeCCosto = JefeCCosto.get(params.id)
        if (!jefeCCosto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
            redirect(action: "lista")
        }
        else {
            [jefeCCosto: jefeCCosto]
        }
    }

    def edita = {
        def jefeCCosto = JefeCCosto.get(params.id)
        if (!jefeCCosto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [jefeCCosto: jefeCCosto]
        }
    }

    def actualiza = {
        def jefeCCosto = JefeCCosto.get(params.id)
        if (jefeCCosto) {
            if (params.version) {
                def version = params.version.toLong()
                if (jefeCCosto.version > version) {
                    
                    jefeCCosto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'jefeCCosto.label', default: 'JefeCCosto')] as Object[], "Another user has updated this JefeCCosto while you were editing")
                    render(view: "edita", model: [jefeCCosto: jefeCCosto])
                    return
                }
            }
            jefeCCosto.properties = params
            if (!jefeCCosto.hasErrors() && jefeCCosto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), jefeCCosto.id])
                redirect(action: "ver", id: jefeCCosto.id)
            }
            else {
                render(view: "edita", model: [jefeCCosto: jefeCCosto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def jefeCCosto = JefeCCosto.get(params.id)
        if (jefeCCosto) {
            try {
                jefeCCosto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jefeCCosto.label', default: 'JefeCCosto'), params.id])
            redirect(action: "lista")
        }
    }
}
