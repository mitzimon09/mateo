package mx.edu.um.rh

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class VacacionesController {

	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[vacacionesList: Vacaciones.list(params),totalDeVacaciones: Vacaciones.count()]
	}

    def nueva = {
        def vacaciones = new Vacaciones()
        vacaciones.properties = params
        return [vacaciones: vacaciones]
    }

    def save = {
        def vacaciones = new Vacaciones(params)
        if (vacaciones.save(flush: true)) {
            flash.message = message(code: 'default.saveted.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), vacaciones.id])
            redirect(action: "ver", id: vacaciones.id)
        }
        else {
            render(view: "nueva", model: [vacaciones: vacaciones])
        }
    }

    def ver = {
        def vacaciones = Vacaciones.get(params.id)
        if (!vacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
        else {
            [vacaciones: vacaciones]
        }
    }

/*    def edita = {
        def vacaciones = Vacaciones.get(params.id)
        if (!vacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
        else {
            return [vacaciones: vacaciones]
        }
    }

    def actualiza = {
        def vacaciones = Vacaciones.get(params.id)
        if (vacaciones) {
            if (params.version) {
                def version = params.version.toLong()
                if (vacaciones.version > version) {
                    
                    vacaciones.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vacaciones.label', default: 'Vacaciones')] as Object[], "Another user has updated this Vacaciones while you were editing")
                    render(view: "edita", model: [vacaciones: vacaciones])
                    return
                }
            }
            vacaciones.properties = params
            if (!vacaciones.hasErrors() && vacaciones.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), vacaciones.id])
                redirect(action: "ver", id: vacaciones.id)
            }
            else {
                render(view: "edita", model: [vacaciones: vacaciones])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
    }

/*    def elimina = {
        def vacaciones = Vacaciones.get(params.id)
        if (vacaciones) {
            try {
                vacaciones.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vacaciones.label', default: 'Vacaciones'), params.id])
            redirect(action: "lista")
        }
    }*/
}
