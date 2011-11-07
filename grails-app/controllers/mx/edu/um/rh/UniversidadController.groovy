package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_RHOPER'])
class UniversidadController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[universidades: Universidad.list(params), totalDeUniversidades: Universidad.count()]
	}

    def nuevo = {
        def universidad = new Universidad()
        universidad.properties = params
        return [universidad: universidad]
    }

    def crea = {
        def universidad = new Universidad(params)
        if (universidad.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'universidad.label', default: 'Universidad'), universidad.id])
            redirect(action: "ver", id: universidad.id)
        }
        else {
            render(view: "nuevo", model: [universidad: universidad])
        }
    }

    def ver = {
        def universidad = Universidad.get(params.id)
        if (!universidad) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
            redirect(action: "lista")
        }
        else {
            [universidad: universidad]
        }
    }

    def edita = {
        def universidad = Universidad.get(params.id)
        if (!universidad) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
            redirect(action: "lista")
        }
        else {
            return [universidad: universidad]
        }
    }

    def actualiza = {
        def universidad = Universidad.get(params.id)
        if (universidad) {
            if (params.version) {
                def version = params.version.toLong()
                if (universidad.version > version) {
                    
                    universidad.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'universidad.label', default: 'Universidad')] as Object[], "Another user has updated this Universidad while you were editing")
                    render(view: "edita", model: [universidad: universidad])
                    return
                }
            }
            universidad.properties = params
            if (!universidad.hasErrors() && universidad.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'universidad.label', default: 'Universidad'), universidad.id])
                redirect(action: "ver", id: universidad.id)
            }
            else {
                render(view: "edita", model: [universidad: universidad])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def universidad = Universidad.get(params.id)
        if (universidad) {
            try {
                universidad.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'universidad.label', default: 'Universidad'), params.id])
            redirect(action: "lista")
        }
    }
}
