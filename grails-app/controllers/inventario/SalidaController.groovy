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
		[salidaInstanceList: Salida.list(params), salidaInstanceTotal: Salida.count()]
	}

    def nueva = {
        def salidaInstance = new Salida()
        salidaInstance.properties = params
        return [salidaInstance: salidaInstance]
    }

    def crea = {
        def salidaInstance = new Salida(params)
        if (salidaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'salida.label', default: 'Salida'), salidaInstance.id])
            redirect(action: "ver", id: salidaInstance.id)
        }
        else {
            render(view: "nueva", model: [salidaInstance: salidaInstance])
        }
    }

    def ver = {
        def salidaInstance = Salida.get(params.id)
        if (!salidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
        else {
            [salidaInstance: salidaInstance]
        }
    }

    def edita = {
        def salidaInstance = Salida.get(params.id)
        if (!salidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
        else {
            return [salidaInstance: salidaInstance]
        }
    }

    def actualiza = {
        def salidaInstance = Salida.get(params.id)
        if (salidaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (salidaInstance.version > version) {
                    
                    salidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'salida.label', default: 'Salida')] as Object[], "Another user has updated this Salida while you were editing")
                    render(view: "edita", model: [salidaInstance: salidaInstance])
                    return
                }
            }
            salidaInstance.properties = params
            if (!salidaInstance.hasErrors() && salidaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salida.label', default: 'Salida'), salidaInstance.id])
                redirect(action: "ver", id: salidaInstance.id)
            }
            else {
                render(view: "edita", model: [salidaInstance: salidaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def salidaInstance = Salida.get(params.id)
        if (salidaInstance) {
            try {
                salidaInstance.delete(flush: true)
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
