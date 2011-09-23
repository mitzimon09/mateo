package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class GrupoController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[grupos: Grupo.list(params), totalDeGrupos: Grupo.count()]
	}

    def nuevo = {
        def grupo = new Grupo()
        grupo.properties = params
        return [grupo: grupo]
    }

    def crea = {
        def grupo = new Grupo(params)
        if (grupo.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
            redirect(action: "ver", id: grupo.id)
        }
        else {
            render(view: "nuevo", model: [grupo: grupo])
        }
    }

    def ver = {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "lista")
        }
        else {
            [grupo: grupo]
        }
    }

    def edita = {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "lista")
        }
        else {
            return [grupo: grupo]
        }
    }

    def actualiza = {
        def grupo = Grupo.get(params.id)
        if (grupo) {
            if (params.version) {
                def version = params.version.toLong()
                if (grupo.version > version) {
                    
                    grupo.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'grupo.label', default: 'Grupo')] as Object[], "Another user has updated this Grupo while you were editing")
                    render(view: "edita", model: [grupo: grupo])
                    return
                }
            }
            grupo.properties = params
            if (!grupo.hasErrors() && grupo.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
                redirect(action: "ver", id: grupo.id)
            }
            else {
                render(view: "edita", model: [grupo: grupo])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def grupo = Grupo.get(params.id)
        if (grupo) {
            try {
                grupo.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "lista")
        }
    }
}
