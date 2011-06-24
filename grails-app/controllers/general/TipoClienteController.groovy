package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class TipoClienteController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tipoClientes: TipoCliente.list(params), totalDeTipoClientes: TipoCliente.count()]
	}

    def nuevo = {
        def tipoCliente = new TipoCliente()
        tipoCliente.properties = params
        return [tipoCliente: tipoCliente]
    }

    def crea = {
        def tipoCliente = new TipoCliente(params)
        if (tipoCliente.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), tipoCliente.id])
            redirect(action: "ver", id: tipoCliente.id)
        }
        else {
            render(view: "nuevo", model: [tipoCliente: tipoCliente])
        }
    }

    def ver = {
        def tipoCliente = TipoCliente.get(params.id)
        if (!tipoCliente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "lista")
        }
        else {
            [tipoCliente: tipoCliente]
        }
    }

    def edita = {
        def tipoCliente = TipoCliente.get(params.id)
        if (!tipoCliente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "lista")
        }
        else {
            return [tipoCliente: tipoCliente]
        }
    }

    def actualiza = {
        def tipoCliente = TipoCliente.get(params.id)
        if (tipoCliente) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoCliente.version > version) {
                    
                    tipoCliente.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoCliente.label', default: 'TipoCliente')] as Object[], "Another user has updated this TipoCliente while you were editing")
                    render(view: "edita", model: [tipoCliente: tipoCliente])
                    return
                }
            }
            tipoCliente.properties = params
            if (!tipoCliente.hasErrors() && tipoCliente.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), tipoCliente.id])
                redirect(action: "ver", id: tipoCliente.id)
            }
            else {
                render(view: "edita", model: [tipoCliente: tipoCliente])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def tipoCliente = TipoCliente.get(params.id)
        if (tipoCliente) {
            try {
                tipoCliente.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "lista")
        }
    }
}
