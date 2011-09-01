package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class TipoEmpleadoController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tipoEmpleados: TipoEmpleado.list(params), totalDeTipoEmpleados: TipoEmpleado.count()]
	}

    def nuevo = {
        def tipoEmpleado = new TipoEmpleado()
        tipoEmpleado.properties = params
        return [tipoEmpleado: tipoEmpleado]
    }

    def crea = {
        def tipoEmpleado = new TipoEmpleado(params)
        if (tipoEmpleado.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), tipoEmpleado.id])
            redirect(action: "ver", id: tipoEmpleado.id)
        }
        else {
            render(view: "nuevo", model: [tipoEmpleado: tipoEmpleado])
        }
    }

    def ver = {
        def tipoEmpleado = TipoEmpleado.get(params.id)
        if (!tipoEmpleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
            redirect(action: "lista")
        }
        else {
            [tipoEmpleado: tipoEmpleado]
        }
    }

    def edita = {
        def tipoEmpleado = TipoEmpleado.get(params.id)
        if (!tipoEmpleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
            redirect(action: "lista")
        }
        else {
            return [tipoEmpleado: tipoEmpleado]
        }
    }

    def actualiza = {
        def tipoEmpleado = TipoEmpleado.get(params.id)
        if (tipoEmpleado) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoEmpleado.version > version) {
                    
                    tipoEmpleado.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado')] as Object[], "Another user has updated this TipoEmpleado while you were editing")
                    render(view: "edita", model: [tipoEmpleado: tipoEmpleado])
                    return
                }
            }
            tipoEmpleado.properties = params
            if (!tipoEmpleado.hasErrors() && tipoEmpleado.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), tipoEmpleado.id])
                redirect(action: "ver", id: tipoEmpleado.id)
            }
            else {
                render(view: "edita", model: [tipoEmpleado: tipoEmpleado])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def tipoEmpleado = TipoEmpleado.get(params.id)
        if (tipoEmpleado) {
            try {
                tipoEmpleado.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEmpleado.label', default: 'TipoEmpleado'), params.id])
            redirect(action: "lista")
        }
    }
}
