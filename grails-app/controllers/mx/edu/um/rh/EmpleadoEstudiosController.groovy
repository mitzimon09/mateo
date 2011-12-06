package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class EmpleadoEstudiosController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empleadoEstudios: EmpleadoEstudios.list(params), totalDeEmpleadoEstudios: EmpleadoEstudios.count()]
	}

    def nuevo = {
        def empleadoEstudios = new EmpleadoEstudios()
        empleadoEstudios.properties = params
        return [empleadoEstudios: empleadoEstudios]
    }

    def crea = {
        def empleadoEstudios = new EmpleadoEstudios(params)
        if (empleadoEstudios.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), empleadoEstudios.id])
            redirect(action: "ver", id: empleadoEstudios.id)
        }
        else {
            render(view: "nuevo", model: [empleadoEstudios: empleadoEstudios])
        }
    }

    def ver = {
        def empleadoEstudios = EmpleadoEstudios.get(params.id)
        if (!empleadoEstudios) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
            redirect(action: "lista")
        }
        else {
            [empleadoEstudios: empleadoEstudios]
        }
    }

    def edita = {
        def empleadoEstudios = EmpleadoEstudios.get(params.id)
        if (!empleadoEstudios) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empleadoEstudios: empleadoEstudios]
        }
    }

    def actualiza = {
        def empleadoEstudios = EmpleadoEstudios.get(params.id)
        if (empleadoEstudios) {
            if (params.version) {
                def version = params.version.toLong()
                if (empleadoEstudios.version > version) {
                    
                    empleadoEstudios.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios')] as Object[], "Another user has updated this EmpleadoEstudios while you were editing")
                    render(view: "edita", model: [empleadoEstudios: empleadoEstudios])
                    return
                }
            }
            empleadoEstudios.properties = params
            if (!empleadoEstudios.hasErrors() && empleadoEstudios.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), empleadoEstudios.id])
                redirect(action: "ver", id: empleadoEstudios.id)
            }
            else {
                render(view: "edita", model: [empleadoEstudios: empleadoEstudios])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def empleadoEstudios = EmpleadoEstudios.get(params.id)
        if (empleadoEstudios) {
            try {
                empleadoEstudios.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoEstudios.label', default: 'EmpleadoEstudios'), params.id])
            redirect(action: "lista")
        }
    }
}
