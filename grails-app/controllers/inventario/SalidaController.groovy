package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class SalidaController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[salidas: Salida.list(params), totalDeSalidas: Salida.count()]
	}

    def nueva = {
        def salida = new Salida()
        salida.properties = params
        return [salida: salida]
    }

    def crea = {
        def salida = new Salida(params)
        if (salida.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'salida.label', default: 'Salida'), salida.id])
            redirect(action: "ver", id: salida.id)
        }
        else {
            render(view: "nueva", model: [salida: salida])
        }
    }

    def ver = {
        def salida = Salida.get(params.id)
        if (!salida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
        else {
            [salida: salida]
        }
    }

    def edita = {
        def salida = Salida.get(params.id)
        if (!salida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
        else {
            return [salida: salida]
        }
    }

    def actualiza = {
        def salida = Salida.get(params.id)
        if (salida) {
            if (params.version) {
                def version = params.version.toLong()
                if (salida.version > version) {
                    
                    salida.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'salida.label', default: 'Salida')] as Object[], "Another user has updated this Salida while you were editing")
                    render(view: "edita", model: [salida: salida])
                    return
                }
            }
            salida.properties = params
            if (!salida.hasErrors() && salida.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salida.label', default: 'Salida'), salida.id])
                redirect(action: "ver", id: salida.id)
            }
            else {
                render(view: "edita", model: [salida: salida])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def salida = Salida.get(params.id)
        if (salida) {
            try {
                salida.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
    }
}
