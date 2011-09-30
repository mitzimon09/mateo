package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class UsuarioEmpleadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[usuarioEmpleados: UsuarioEmpleado.list(params), totalDeUsuarioEmpleados: UsuarioEmpleado.count()]
	}

    def nuevo = {
        def usuarioEmpleado = new UsuarioEmpleado()
        usuarioEmpleado.properties = params
        return [usuarioEmpleado: usuarioEmpleado]
    }

    def crea = {
        def usuarioEmpleado = new UsuarioEmpleado(params)
        if (usuarioEmpleado.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), usuarioEmpleado.id])
            redirect(action: "ver", id: usuarioEmpleado.id)
        }
        else {
            render(view: "nuevo", model: [usuarioEmpleado: usuarioEmpleado])
        }
    }

    def ver = {
        def usuarioEmpleado = UsuarioEmpleado.get(params.id)
        if (!usuarioEmpleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
            redirect(action: "lista")
        }
        else {
            [usuarioEmpleado: usuarioEmpleado]
        }
    }

    def edita = {
        def usuarioEmpleado = UsuarioEmpleado.get(params.id)
        if (!usuarioEmpleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
            redirect(action: "lista")
        }
        else {
            return [usuarioEmpleado: usuarioEmpleado]
        }
    }

    def actualiza = {
        def usuarioEmpleado = UsuarioEmpleado.get(params.id)
        if (usuarioEmpleado) {
            if (params.version) {
                def version = params.version.toLong()
                if (usuarioEmpleado.version > version) {
                    
                    usuarioEmpleado.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado')] as Object[], "Another user has updated this UsuarioEmpleado while you were editing")
                    render(view: "edita", model: [usuarioEmpleado: usuarioEmpleado])
                    return
                }
            }
            usuarioEmpleado.properties = params
            if (!usuarioEmpleado.hasErrors() && usuarioEmpleado.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), usuarioEmpleado.id])
                redirect(action: "ver", id: usuarioEmpleado.id)
            }
            else {
                render(view: "edita", model: [usuarioEmpleado: usuarioEmpleado])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def usuarioEmpleado = UsuarioEmpleado.get(params.id)
        if (usuarioEmpleado) {
            try {
                usuarioEmpleado.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado'), params.id])
            redirect(action: "lista")
        }
    }
}
