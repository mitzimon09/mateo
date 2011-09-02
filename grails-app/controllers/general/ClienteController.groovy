package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class ClienteController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[clientes: Cliente.list(params), totalDeClientes: Cliente.count()]
	}

    def nuevo = {
        def cliente = new Cliente()
        cliente.properties = params
        return [cliente: cliente]
    }

    def crea = {
        def cliente = new Cliente(params)
        if (cliente.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
            redirect(action: "ver", id: cliente.id)
        }
        else {
            render(view: "nuevo", model: [cliente: cliente])
        }
    }

    def ver = {
        def cliente = Cliente.get(params.id)
        if (!cliente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect(action: "lista")
        }
        else {
            [cliente: cliente]
        }
    }

    def edita = {
        def cliente = Cliente.get(params.id)
        if (!cliente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect(action: "lista")
        }
        else {
            return [cliente: cliente]
        }
    }

    def actualiza = {
        def cliente = Cliente.get(params.id)
        if (cliente) {
            if (params.version) {
                def version = params.version.toLong()
                if (cliente.version > version) {
                    
                    cliente.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cliente.label', default: 'Cliente')] as Object[], "Another user has updated this Cliente while you were editing")
                    render(view: "edita", model: [cliente: cliente])
                    return
                }
            }
            cliente.properties = params
            if (!cliente.hasErrors() && cliente.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
                redirect(action: "ver", id: cliente.id)
            }
            else {
                render(view: "edita", model: [cliente: cliente])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def cliente = Cliente.get(params.id)
        if (cliente) {
            try {
                cliente.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect(action: "lista")
        }
    }
}
