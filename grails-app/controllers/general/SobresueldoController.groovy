package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

@Secured(['ROLE_EMP'])
class SobresueldoController {

	def springSecurityService
	def procesoService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[sobresueldos: Sobresueldo.list(params), totalDeSobresueldos: Sobresueldo.count()]
	}

    def nuevo = {
        def sobresueldo = new Sobresueldo()
        sobresueldo.properties = params
        return [sobresueldo: sobresueldo]
    }

    def crea = {
        def sobresueldo = new Sobresueldo(params)
        if (sobresueldo.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), sobresueldo.id])
            redirect(action: "ver", id: sobresueldo.id)
        }
        else {
            render(view: "nuevo", model: [sobresueldo: sobresueldo])
        }
    }

    def ver = {
        def sobresueldo = Sobresueldo.get(params.id)
        if (!sobresueldo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
            redirect(action: "lista")
        }
        else {
            [sobresueldo: sobresueldo]
        }
    }

    def edita = {
        def sobresueldo = Sobresueldo.get(params.id)
        if (!sobresueldo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
            redirect(action: "lista")
        }
        else {
            return [sobresueldo: sobresueldo]
        }
    }

    def actualiza = {
        def sobresueldo = Sobresueldo.get(params.id)
        if (sobresueldo) {
            if (params.version) {
                def version = params.version.toLong()
                if (sobresueldo.version > version) {
                    
                    sobresueldo.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sobresueldo.label', default: 'Sobresueldo')] as Object[], "Another user has updated this Sobresueldo while you were editing")
                    render(view: "edita", model: [sobresueldo: sobresueldo])
                    return
                }
            }
            sobresueldo.properties = params
            if (!sobresueldo.hasErrors() && sobresueldo.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), sobresueldo.id])
                redirect(action: "ver", id: sobresueldo.id)
            }
            else {
                render(view: "edita", model: [sobresueldo: sobresueldo])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def sobresueldo = Sobresueldo.get(params.id)
        if (sobresueldo) {
            try {
                sobresueldo.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
            redirect(action: "lista")
        }
    }
    
    def enviar = {
		def sobresueldo = Sobresueldo.get(params.id)
		if (sobresueldo){
			if(sobresueldo.status.equals("CREADA")){
				sobresueldo = procesoService.enviar(sobresueldo)
				sobresueldo.save(flush:true)
				redirect(action: "lista")
			}
			else {
				flash.message = message(code: 'sobresueldo.status.message5', args: [message(code: 'sobresueldo.label', default: 'sobresueldo'), params.id])
		        redirect(action: "lista")
			}
		}
    }
    
    def aprobar = {
			def sobresueldo = Sobresueldo.get(params.id)
			if (sobresueldo){
				if(sobresueldo.status.equals("ENVIADA") || sobresueldo.status.equals("RECHAZADA")){
					sobresueldo = procesoService.aprobar(sobresueldo)
					sobresueldo.save(flush:true)
					redirect(action: "lista")
				}
				else if (sobresueldo.status.equals("CREADA")){
					flash.message = message(code: 'sobresueldo.status.message1', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
			        redirect(action: "lista")
				}
				else{
					flash.message = message(code: 'sobresueldo.status.message2', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
			        redirect(action: "lista")
				}
			}
    }
    
    def rechazar = {
			def sobresueldo = Sobresueldo.get(params.id)
			if (sobresueldo){
				if (sobresueldo.observaciones != ""){
					if(sobresueldo.status.equals("ENVIADA")){
						sobresueldo = procesoService.rechazar(sobresueldo)
						sobresueldo.observaciones = params.observaciones
						sobresueldo.save(flush:true)
						redirect(action: "lista")
					}
					else if (sobresueldo.status.equals("CREADA")){
						flash.message = message(code: 'sobresueldo.status.message1', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
			            redirect(action: "lista")
					}
					else{
						flash.message = message(code: 'sobresueldo.status.message2', args: [message(code: 'sobresueldo.label', default: 'Sobresueldo'), params.id])
			            redirect(action: "lista")
					}
				}else{
				    flash.message = message(code: 'sobresueldo.observaciones')
				    redirect(action: "edita", id: sobresueldo.id)
				}
			}
	}
	
    def cancelar = {
			def sobresueldo = Sobresueldo.get(params.id)
			if (sobresueldo){
					sobresueldo = procesoService.cancelar(sobresueldo)
					sobresueldo.save(flush:true)
					redirect(action: "lista")
			}
	}
	
	def revisar = {
		def sobresueldo = Sobresueldo.get(params.id)
			if (sobresueldo){
					sobresueldo = procesoService.revisar(sobresueldo)
					sobresueldo.save(flush:true)
					redirect(action: "lista")
			}
	}
    
}
