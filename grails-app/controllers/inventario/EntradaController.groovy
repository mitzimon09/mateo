package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class EntradaController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[entradaInstanceList: Entrada.list(params), entradaInstanceTotal: Entrada.count()]
	}

    def nueva = {
        def entradaInstance = new Entrada()
        entradaInstance.properties = params
        return [entradaInstance: entradaInstance]
    }

    def crea = {
        def entradaInstance = new Entrada(params)
        if (entradaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'entrada.label', default: 'Entrada'), entradaInstance.id])
            redirect(action: "ver", id: entradaInstance.id)
        }
        else {
            render(view: "nueva", model: [entradaInstance: entradaInstance])
        }
    }

    def sver = {
        def entradaInstance = Entrada.get(params.id)
        if (!entradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
        else {
            [entradaInstance: entradaInstance]
        }
    }

    def edita = {
        def entradaInstance = Entrada.get(params.id)
        if (!entradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
        else {
            return [entradaInstance: entradaInstance]
        }
    }

    def actualiza = {
        def entradaInstance = Entrada.get(params.id)
        if (entradaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (entradaInstance.version > version) {
                    
                    entradaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'entrada.label', default: 'Entrada')] as Object[], "Another user has updated this Entrada while you were editing")
                    render(view: "edita", model: [entradaInstance: entradaInstance])
                    return
                }
            }
            entradaInstance.properties = params
            if (!entradaInstance.hasErrors() && entradaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'entrada.label', default: 'Entrada'), entradaInstance.id])
                redirect(action: "ver", id: entradaInstance.id)
            }
            else {
                render(view: "edita", model: [entradaInstance: entradaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
    }

    def delete = {
        def entradaInstance = Entrada.get(params.id)
        if (entradaInstance) {
            try {
                entradaInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
    }
}
