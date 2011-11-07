package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_RHOPER'])
class PuestoController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[puestos: Puesto.list(params), totalDePuestos: Puesto.count()]
	}

    def nuevo = {
        def puesto = new Puesto()
        puesto.properties = params
        return [puesto: puesto]
    }

    def crea = {
        def puesto = new Puesto(params)
        if (puesto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'puesto.label', default: 'Puesto'), puesto.id])
            redirect(action: "ver", id: puesto.id)
        }
        else {
            render(view: "nuevo", model: [puesto: puesto])
        }
    }

    def ver = {
        def puesto = Puesto.get(params.id)
        if (!puesto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
            redirect(action: "lista")
        }
        else {
            [puesto: puesto]
        }
    }

    def edita = {
        def puesto = Puesto.get(params.id)
        if (!puesto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [puesto: puesto]
        }
    }

    def actualiza = {
        def puesto = Puesto.get(params.id)
        if (puesto) {
            if (params.version) {
                def version = params.version.toLong()
                if (puesto.version > version) {
                    
                    puesto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'puesto.label', default: 'Puesto')] as Object[], "Another user has updated this Puesto while you were editing")
                    render(view: "edita", model: [puesto: puesto])
                    return
                }
            }
            puesto.properties = params
            if (!puesto.hasErrors() && puesto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'puesto.label', default: 'Puesto'), puesto.id])
                redirect(action: "ver", id: puesto.id)
            }
            else {
                render(view: "edita", model: [puesto: puesto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def puesto = Puesto.get(params.id)
        if (puesto) {
            try {
                puesto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'puesto.label', default: 'Puesto'), params.id])
            redirect(action: "lista")
        }
    }
}
