package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class ImagenController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[imagenes: Imagen.list(params), totalDeImagenes: Imagen.count()]
	}

    def nueva = {
        def imagen = new Imagen()
        imagen.properties = params
        return [imagen: imagen]
    }

    def crea = {
        def imagen = new Imagen(params)
        if (imagen.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'imagen.label', default: 'Imagen'), imagen.id])
            redirect(action: "ver", id: imagen.id)
        }
        else {
            render(view: "nueva", model: [imagen: imagen])
        }
    }

    def ver = {
        def imagen = Imagen.get(params.id)
        if (!imagen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "lista")
        }
        else {
            [imagen: imagen]
        }
    }

    def edita = {
        def imagen = Imagen.get(params.id)
        if (!imagen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "lista")
        }
        else {
            return [imagen: imagen]
        }
    }

    def actualiza = {
        def imagen = Imagen.get(params.id)
        if (imagen) {
            if (params.version) {
                def version = params.version.toLong()
                if (imagen.version > version) {
                    
                    imagen.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'imagen.label', default: 'Imagen')] as Object[], "Another user has updated this Imagen while you were editing")
                    render(view: "edita", model: [imagen: imagen])
                    return
                }
            }
            imagen.properties = params
            if (!imagen.hasErrors() && imagen.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'imagen.label', default: 'Imagen'), imagen.id])
                redirect(action: "ver", id: imagen.id)
            }
            else {
                render(view: "edita", model: [imagen: imagen])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def imagen = Imagen.get(params.id)
        if (imagen) {
            try {
                imagen.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "lista")
        }
    }
}
