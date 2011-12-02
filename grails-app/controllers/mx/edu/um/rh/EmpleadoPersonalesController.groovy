package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class EmpleadoPersonalesController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empleadoPersonales: EmpleadoPersonales.list(params), totalDeEmpleadoPersonales: EmpleadoPersonales.count()]
	}

    def nuevo = {
        def empleadoPersonales = new EmpleadoPersonales()
        empleadoPersonales.properties = params
        return [empleadoPersonales: empleadoPersonales]
    }

    def crea = {
        def empleadoPersonales = new EmpleadoPersonales(params)
        if (empleadoPersonales.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), empleadoPersonales.id])
            redirect(action: "ver", id: empleadoPersonales.id)
        }
        else {
            render(view: "nuevo", model: [empleadoPersonales: empleadoPersonales])
        }
    }

    def ver = {
        def empleadoPersonales = EmpleadoPersonales.get(params.id)
        if (!empleadoPersonales) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
            redirect(action: "lista")
        }
        else {
            [empleadoPersonales: empleadoPersonales]
        }
    }

    def edita = {
        def empleadoPersonales = EmpleadoPersonales.get(params.id)
        if (!empleadoPersonales) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empleadoPersonales: empleadoPersonales]
        }
    }

    def actualiza = {
        def empleadoPersonales = EmpleadoPersonales.get(params.id)
        if (empleadoPersonales) {
            if (params.version) {
                def version = params.version.toLong()
                if (empleadoPersonales.version > version) {
                    
                    empleadoPersonales.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales')] as Object[], "Another user has updated this EmpleadoPersonales while you were editing")
                    render(view: "edita", model: [empleadoPersonales: empleadoPersonales])
                    return
                }
            }
            empleadoPersonales.properties = params
            if (!empleadoPersonales.hasErrors() && empleadoPersonales.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), empleadoPersonales.id])
                redirect(action: "ver", id: empleadoPersonales.id)
            }
            else {
                render(view: "edita", model: [empleadoPersonales: empleadoPersonales])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def empleadoPersonales = EmpleadoPersonales.get(params.id)
        if (empleadoPersonales) {
            try {
                empleadoPersonales.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPersonales.label', default: 'EmpleadoPersonales'), params.id])
            redirect(action: "lista")
        }
    }
}
