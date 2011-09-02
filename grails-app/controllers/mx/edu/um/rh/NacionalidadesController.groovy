package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class NacionalidadesController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[nacionalidadess: Nacionalidades.list(params), totalDeNacionalidadess: Nacionalidades.count()]
	}

    def nuevo = {
        def nacionalidades = new Nacionalidades()
        nacionalidades.properties = params
        return [nacionalidades: nacionalidades]
    }

    def crea = {
        def nacionalidades = new Nacionalidades(params)
        if (nacionalidades.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), nacionalidades.id])
            redirect(action: "ver", id: nacionalidades.id)
        }
        else {
            render(view: "nuevo", model: [nacionalidades: nacionalidades])
        }
    }

    def ver = {
        def nacionalidades = Nacionalidades.get(params.id)
        if (!nacionalidades) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
            redirect(action: "lista")
        }
        else {
            [nacionalidades: nacionalidades]
        }
    }

    def edita = {
        def nacionalidades = Nacionalidades.get(params.id)
        if (!nacionalidades) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
            redirect(action: "lista")
        }
        else {
            return [nacionalidades: nacionalidades]
        }
    }

    def actualiza = {
        def nacionalidades = Nacionalidades.get(params.id)
        if (nacionalidades) {
            if (params.version) {
                def version = params.version.toLong()
                if (nacionalidades.version > version) {
                    
                    nacionalidades.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'nacionalidades.label', default: 'Nacionalidades')] as Object[], "Another user has updated this Nacionalidades while you were editing")
                    render(view: "edita", model: [nacionalidades: nacionalidades])
                    return
                }
            }
            nacionalidades.properties = params
            if (!nacionalidades.hasErrors() && nacionalidades.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), nacionalidades.id])
                redirect(action: "ver", id: nacionalidades.id)
            }
            else {
                render(view: "edita", model: [nacionalidades: nacionalidades])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def nacionalidades = Nacionalidades.get(params.id)
        if (nacionalidades) {
            try {
                nacionalidades.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidades.label', default: 'Nacionalidades'), params.id])
            redirect(action: "lista")
        }
    }
}
