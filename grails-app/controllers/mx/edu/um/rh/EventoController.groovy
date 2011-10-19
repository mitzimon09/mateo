package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import mx.edu.um.Constantes

@Secured(['ROLE_DIRRH'])
class EventoController {
    def springSecurityService
    def empleadoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[eventos: Evento.list(params), totalDeEventos: Evento.count()]
	}

    def nuevo = {
        def evento = new Evento()
        evento.properties = params
        return [evento: evento]
    }

    def crea = {
        def evento = new Evento(params)
        if (evento.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
            redirect(action: "ver", id: evento.id)
        }
        else {
            render(view: "nuevo", model: [evento: evento])
        }
    }

    @Secured(['ROLE_EMP'])
    def ver = {
        def evento = Evento.get(params.id)
        if (!evento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
        else {
            if(evento.status == Constantes.STATUS_INICIADO) {
                render(view: "paseLista", model: [evento: evento])
            }else {
                [evento: evento]
            }
        }
    }

    def edita = {
        def evento = Evento.get(params.id)
        if (!evento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
        else {
            return [evento: evento]
        }
    }

    def actualiza = {
        def evento = Evento.get(params.id)
        if (evento) {
            if (params.version) {
                def version = params.version.toLong()
                if (evento.version > version) {
                    
                    evento.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evento.label', default: 'Evento')] as Object[], "Another user has updated this Evento while you were editing")
                    render(view: "edita", model: [evento: evento])
                    return
                }
            }
            evento.properties = params
            if (!evento.hasErrors() && evento.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect(action: "ver", id: evento.id)
            }
            else {
                render(view: "edita", model: [evento: evento])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def evento = Evento.get(params.id)
        if (evento) {
            //if(evento.status != Constantes.STATUS_CREADO)
            try {
                evento.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
    }
    
    def iniciarEvento = {
        def evento = Evento.get(params.id)
        if(evento.status == Constantes.STATUS_CREADO){
            evento.status = Constantes.STATUS_INICIADO
            render(view: "paseLista", model: [evento: evento])
        }else {
            flash.message = message(code: 'El evento {0} ya ha terminado', args: [evento.nombre])
            redirect(action: "lista")
        }
        //redirect(action: "paseLista", id: params.id])
    }
    
    def cerrarEvento = {
        def evento = Evento.get(params.id)
        if(evento.status == Constantes.STATUS_INICIADO){
            evento.status = Constantes.STATUS_TERMINADO
        }
        redirect(action: "lista")
    }
    
    def paseLista = {
        def evento = Evento.get(params.id)
        log.debug "evento.clave = " + params.clave
        def empleado = empleadoService.getEmpleado(params.clave)
        log.debug "empleado>"+empleado
    }
}
