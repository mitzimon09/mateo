package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class ColegioController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[colegios: Colegio.list(params), totalDeColegios: Colegio.count()]
	}

    def nuevo = {
        def colegio = new Colegio()
        colegio.properties = params
        return [colegio: colegio]
    }

    def crea = {
        def colegio = new Colegio(params)
        if (colegio.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'colegio.label', default: 'Colegio'), colegio.id])
            redirect(action: "ver", id: colegio.id)
        }
        else {
            render(view: "nuevo", model: [colegio: colegio])
        }
    }

    def ver = {
        def colegio = Colegio.get(params.id)
        if (!colegio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
            redirect(action: "lista")
        }
        else {
            [colegio: colegio]
        }
    }

    def edita = {
        def colegio = Colegio.get(params.id)
        if (!colegio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
            redirect(action: "lista")
        }
        else {
            return [colegio: colegio]
        }
    }

    def actualiza = {
        def colegio = Colegio.get(params.id)
        if (colegio) {
            if (params.version) {
                def version = params.version.toLong()
                if (colegio.version > version) {
                    
                    colegio.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'colegio.label', default: 'Colegio')] as Object[], "Another user has updated this Colegio while you were editing")
                    render(view: "edita", model: [colegio: colegio])
                    return
                }
            }
            colegio.properties = params
            if (!colegio.hasErrors() && colegio.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'colegio.label', default: 'Colegio'), colegio.id])
                redirect(action: "ver", id: colegio.id)
            }
            else {
                render(view: "edita", model: [colegio: colegio])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def colegio = Colegio.get(params.id)
        if (colegio) {
            try {
                colegio.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'colegio.label', default: 'Colegio'), params.id])
            redirect(action: "lista")
        }
    }
}
