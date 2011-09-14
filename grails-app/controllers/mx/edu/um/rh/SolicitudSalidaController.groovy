package mx.edu.um.rh

import grails.converters.JSON

class SolicitudSalidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[solicitudSalidas: SolicitudSalida.list(params), totalDeSolicitudSalidas: SolicitudSalida.count()]
	}

    def nueva = {
        def solicitudSalida = new SolicitudSalida()
        solicitudSalida.properties = params
        return [solicitudSalida: solicitudSalida]
    }

    def crea = {
        def solicitudSalida = new SolicitudSalida(params)
        if (solicitudSalida.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), solicitudSalida.id])
            redirect(action: "ver", id: solicitudSalida.id)
        }
        else {
            render(view: "nueva", model: [solicitudSalida: solicitudSalida])
        }
    }

    def ver = {
        def solicitudSalida = SolicitudSalida.get(params.id)
        if (!solicitudSalida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
            redirect(action: "lista")
        }
        else {
            [solicitudSalida: solicitudSalida]
        }
    }

    def edita = {
        def solicitudSalida = SolicitudSalida.get(params.id)
        if (!solicitudSalida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
            redirect(action: "lista")
        }
        else {
            return [solicitudSalida: solicitudSalida]
        }
    }

    def actualiza = {
        def solicitudSalida = SolicitudSalida.get(params.id)
        if (solicitudSalida) {
            if (params.version) {
                def version = params.version.toLong()
                if (solicitudSalida.version > version) {
                    
                    solicitudSalida.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'solicitudSalida.label', default: 'SolicitudSalida')] as Object[], "Another user has updated this SolicitudSalida while you were editing")
                    render(view: "edita", model: [solicitudSalida: solicitudSalida])
                    return
                }
            }
            solicitudSalida.properties = params
            if (!solicitudSalida.hasErrors() && solicitudSalida.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), solicitudSalida.id])
                redirect(action: "ver", id: solicitudSalida.id)
            }
            else {
                render(view: "edita", model: [solicitudSalida: solicitudSalida])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
            redirect(action: "lista")
        }
    }

/*    def elimina = {
        def solicitudSalida = SolicitudSalida.get(params.id)
        if (solicitudSalida) {
            try {
                solicitudSalida.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudSalida.label', default: 'SolicitudSalida'), params.id])
            redirect(action: "lista")
        }
    }*/
}
