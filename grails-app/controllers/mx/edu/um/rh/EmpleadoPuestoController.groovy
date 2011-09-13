package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class EmpleadoPuestoController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empleadoPuestos: EmpleadoPuesto.list(params), totalDeEmpleadoPuestos: EmpleadoPuesto.count()]
	}

    def nuevo = {
        def empleadoPuesto = new EmpleadoPuesto()
        empleadoPuesto.properties = params
        return [empleadoPuesto: empleadoPuesto]
    }

    def crea = {
        def empleadoPuesto = new EmpleadoPuesto(params)
        if (empleadoPuesto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), empleadoPuesto.id])
            redirect(action: "ver", id: empleadoPuesto.id)
        }
        else {
            render(view: "nuevo", model: [empleadoPuesto: empleadoPuesto])
        }
    }

    def ver = {
        def empleadoPuesto = EmpleadoPuesto.get(params.id)
        if (!empleadoPuesto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
            redirect(action: "lista")
        }
        else {
            [empleadoPuesto: empleadoPuesto]
        }
    }

    def edita = {
        def empleadoPuesto = EmpleadoPuesto.get(params.id)
        if (!empleadoPuesto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empleadoPuesto: empleadoPuesto]
        }
    }

    def actualiza = {
        def empleadoPuesto = EmpleadoPuesto.get(params.id)
        if (empleadoPuesto) {
            if (params.version) {
                def version = params.version.toLong()
                if (empleadoPuesto.version > version) {
                    
                    empleadoPuesto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto')] as Object[], "Another user has updated this EmpleadoPuesto while you were editing")
                    render(view: "edita", model: [empleadoPuesto: empleadoPuesto])
                    return
                }
            }
            empleadoPuesto.properties = params
            if (!empleadoPuesto.hasErrors() && empleadoPuesto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), empleadoPuesto.id])
                redirect(action: "ver", id: empleadoPuesto.id)
            }
            else {
                render(view: "edita", model: [empleadoPuesto: empleadoPuesto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def empleadoPuesto = EmpleadoPuesto.get(params.id)
        if (empleadoPuesto) {
            try {
                empleadoPuesto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto'), params.id])
            redirect(action: "lista")
        }
    }
}
