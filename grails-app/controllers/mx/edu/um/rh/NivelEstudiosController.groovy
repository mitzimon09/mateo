package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_RHOPER'])
class NivelEstudiosController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[nivelEstudios: NivelEstudios.list(params), totalDeNivelEstudios: NivelEstudios.count()]
	}

    def nuevo = {
        def nivelEstudios = new NivelEstudios()
        nivelEstudios.properties = params
        return [nivelEstudios: nivelEstudios]
    }

    def crea = {
        def nivelEstudios = new NivelEstudios(params)
        if (nivelEstudios.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), nivelEstudios.id])
            redirect(action: "ver", id: nivelEstudios.id)
        }
        else {
            render(view: "nuevo", model: [nivelEstudios: nivelEstudios])
        }
    }

    def ver = {
        def nivelEstudios = NivelEstudios.get(params.id)
        if (!nivelEstudios) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
            redirect(action: "lista")
        }
        else {
            [nivelEstudios: nivelEstudios]
        }
    }

    def edita = {
        def nivelEstudios = NivelEstudios.get(params.id)
        if (!nivelEstudios) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
            redirect(action: "lista")
        }
        else {
            return [nivelEstudios: nivelEstudios]
        }
    }

    def actualiza = {
        def nivelEstudios = NivelEstudios.get(params.id)
        if (nivelEstudios) {
            if (params.version) {
                def version = params.version.toLong()
                if (nivelEstudios.version > version) {
                    
                    nivelEstudios.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'nivelEstudios.label', default: 'NivelEstudios')] as Object[], "Another user has updated this NivelEstudios while you were editing")
                    render(view: "edita", model: [nivelEstudios: nivelEstudios])
                    return
                }
            }
            nivelEstudios.properties = params
            if (!nivelEstudios.hasErrors() && nivelEstudios.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), nivelEstudios.id])
                redirect(action: "ver", id: nivelEstudios.id)
            }
            else {
                render(view: "edita", model: [nivelEstudios: nivelEstudios])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def nivelEstudios = NivelEstudios.get(params.id)
        if (nivelEstudios) {
            try {
                nivelEstudios.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelEstudios.label', default: 'NivelEstudios'), params.id])
            redirect(action: "lista")
        }
    }
}
