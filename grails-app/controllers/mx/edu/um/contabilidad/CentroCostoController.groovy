package mx.edu.um.contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class CentroCostoController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[centroCostos: CentroCosto.list(params), totalDeCentroCostos: CentroCosto.count()]
	}

    def nuevo = {
        def centroCosto = new CentroCosto()
        centroCosto.properties = params
        return [centroCosto: centroCosto]
    }

    def crea = {
        def centroCosto = new CentroCosto(params)
        if (centroCosto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), centroCosto.id])
            redirect(action: "ver", id: centroCosto.id)
        }
        else {
            render(view: "nuevo", model: [centroCosto: centroCosto])
        }
    }

    def ver = {
        def centroCosto = CentroCosto.get(params.id)
        if (!centroCosto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
            redirect(action: "lista")
        }
        else {
            [centroCosto: centroCosto]
        }
    }

    def edita = {
        def centroCosto = CentroCosto.get(params.id)
        if (!centroCosto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [centroCosto: centroCosto]
        }
    }

    def actualiza = {
        def centroCosto = CentroCosto.get(params.id)
        if (centroCosto) {
            if (params.version) {
                def version = params.version.toLong()
                if (centroCosto.version > version) {
                    
                    centroCosto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'centroCosto.label', default: 'CentroCosto')] as Object[], "Another user has updated this CentroCosto while you were editing")
                    render(view: "edita", model: [centroCosto: centroCosto])
                    return
                }
            }
            centroCosto.properties = params
            if (!centroCosto.hasErrors() && centroCosto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), centroCosto.id])
                redirect(action: "ver", id: centroCosto.id)
            }
            else {
                render(view: "edita", model: [centroCosto: centroCosto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def centroCosto = CentroCosto.get(params.id)
        if (centroCosto) {
            try {
                centroCosto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'centroCosto.label', default: 'CentroCosto'), params.id])
            redirect(action: "lista")
        }
    }
}
