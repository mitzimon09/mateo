package mx.edu.um.contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class EjercicioController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[ejercicios: Ejercicio.list(params), totalDeEjercicios: Ejercicio.count()]
	}

    def nuevo = {
        def ejercicio = new Ejercicio()
        ejercicio.properties = params
        return [ejercicio: ejercicio]
    }

    def crea = {
        def ejercicio = new Ejercicio(params)
        if (ejercicio.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), ejercicio.id])
            redirect(action: "ver", id: ejercicio.id)
        }
        else {
            render(view: "nuevo", model: [ejercicio: ejercicio])
        }
    }

    def ver = {
        def ejercicio = Ejercicio.get(params.id)
        if (!ejercicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
        else {
            [ejercicio: ejercicio]
        }
    }

    def edita = {
        def ejercicio = Ejercicio.get(params.id)
        if (!ejercicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
        else {
            return [ejercicio: ejercicio]
        }
    }

    def actualiza = {
        def ejercicio = Ejercicio.get(params.id)
        if (ejercicio) {
            if (params.version) {
                def version = params.version.toLong()
                if (ejercicio.version > version) {
                    
                    ejercicio.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ejercicio.label', default: 'Ejercicio')] as Object[], "Another user has updated this Ejercicio while you were editing")
                    render(view: "edita", model: [ejercicio: ejercicio])
                    return
                }
            }
            ejercicio.properties = params
            if (!ejercicio.hasErrors() && ejercicio.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), ejercicio.id])
                redirect(action: "ver", id: ejercicio.id)
            }
            else {
                render(view: "edita", model: [ejercicio: ejercicio])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def ejercicio = Ejercicio.get(params.id)
        if (ejercicio) {
            try {
                ejercicio.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
    }
}
