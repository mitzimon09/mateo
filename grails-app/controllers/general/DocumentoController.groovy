package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_EMP'])
class DocumentoController {
	def springSecurityService
	def procesoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[documentos: Documento.list(params), totalDeDocumentos: Documento.count()]
	}

    def nuevo = {
        def documento = new Documento()
        documento.properties = params
        return [documento: documento]
    }

    def crea = {
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
                redirect(action: "lista")
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
    
    @Secured(['ROLE_EMP'])
    def enviar = {
		def documento = Documento.get(params.id)
		if (documento){
			if(documento.status.equals("C")){
		        log.debug "documento = " + documento
				documento = procesoService.enviar(documento)
				documento.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'documento.status.message5', args: [message(code: 'documento.label', default: 'documento'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    @Secured(['ROLE_EMP'])
    def revisar = {
		def documento = Documento.get(params.id)
		if (documento){
			if(documento.status.equals("ENVIADO")){
				documento = procesoService.revisar(documento)
				documento.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'documento.status.message5', args: [message(code: 'documento.label', default: 'documento'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    @Secured(['ROLE_EMP'])
    def autorizar = {
		def documento = Documento.get(params.id)
		if (documento){
			if(documento.status.equals("REVISADO")){
				documento = procesoService.autorizar(documento)
				documento.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'documento.status.message5', args: [message(code: 'documento.label', default: 'documento'), params.id])
		        redirect(action: "lista")
			}
		}
    }
}
