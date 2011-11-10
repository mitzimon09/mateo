package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_RHOPER'])
class TipoDependienteController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tipoDependientes: TipoDependiente.list(params), totalDeTipoDependientes: TipoDependiente.count()]
	}

    def nuevo = {
        def tipoDependiente = new TipoDependiente()
        tipoDependiente.properties = params
        return [tipoDependiente: tipoDependiente]
    }

    def crea = {
        def tipoDependiente = new TipoDependiente(params)
        if (tipoDependiente.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), tipoDependiente.id])
            redirect(action: "ver", id: tipoDependiente.id)
        }
        else {
            render(view: "nuevo", model: [tipoDependiente: tipoDependiente])
        }
    }

    def ver = {
        def tipoDependiente = TipoDependiente.get(params.id)
        if (!tipoDependiente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
            redirect(action: "lista")
        }
        else {
            [tipoDependiente: tipoDependiente]
        }
    }

    def edita = {
        def tipoDependiente = TipoDependiente.get(params.id)
        if (!tipoDependiente) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
            redirect(action: "lista")
        }
        else {
            return [tipoDependiente: tipoDependiente]
        }
    }

    def actualiza = {
        def tipoDependiente = TipoDependiente.get(params.id)
        if (tipoDependiente) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoDependiente.version > version) {
                    
                    tipoDependiente.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoDependiente.label', default: 'TipoDependiente')] as Object[], "Another user has updated this TipoDependiente while you were editing")
                    render(view: "edita", model: [tipoDependiente: tipoDependiente])
                    return
                }
            }
            tipoDependiente.properties = params
            if (!tipoDependiente.hasErrors() && tipoDependiente.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), tipoDependiente.id])
                redirect(action: "ver", id: tipoDependiente.id)
            }
            else {
                render(view: "edita", model: [tipoDependiente: tipoDependiente])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def tipoDependiente = TipoDependiente.get(params.id)
        if (tipoDependiente) {
            try {
                tipoDependiente.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDependiente.label', default: 'TipoDependiente'), params.id])
            redirect(action: "lista")
        }
    }
}
