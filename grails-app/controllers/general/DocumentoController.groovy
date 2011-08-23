package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class DocumentoController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[documentos: Documento.list(params), totalDeDocumentos: Documento.count()]
	}

    def crea = {
        def documento = new Documento()
        documento.properties = params
        return [documento: documento]
    }

    def nuevo = {
        def documento = new Documento(params)
        if (documento.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documento.id])
            redirect(action: "ver", id: documento.id)
        }
        else {
            render(view: "crea", model: [documento: documento])
        }
    }

    def ver = {
        def documento = Documento.get(params.id)
        if (!documento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "lista")
        }
        else {
            [documento: documento]
        }
    }

    def edita = {
        def documento = Documento.get(params.id)
        if (!documento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "lista")
        }
        else {
            return [documento: documento]
        }
    }

    def actualiza = {
        def documento = Documento.get(params.id)
        if (documento) {
            if (params.version) {
                def version = params.version.toLong()
                if (documento.version > version) {
                    
                    documento.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'documento.label', default: 'Documento')] as Object[], "Another user has updated this Documento while you were editing")
                    render(view: "edita", model: [documento: documento])
                    return
                }
            }
            documento.properties = params
            if (!documento.hasErrors() && documento.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documento.id])
                redirect(action: "ver", id: documento.id)
            }
            else {
                render(view: "edita", model: [documento: documento])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def documento = Documento.get(params.id)
        if (documento) {
            try {
                documento.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
            redirect(action: "lista")
        }
    }
}
