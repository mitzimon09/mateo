package general

import grails.converters.JSON

class ArticuloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[articuloInstanceList: Articulo.list(params), articuloInstanceTotal: Articulo.count()]
	}

    def create = {
        def articuloInstance = new Articulo()
        articuloInstance.properties = params
        return [articuloInstance: articuloInstance]
    }

    def save = {
        def articuloInstance = new Articulo(params)
        if (articuloInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'articulo.label', default: 'Articulo'), articuloInstance.id])
            redirect(action: "show", id: articuloInstance.id)
        }
        else {
            render(view: "create", model: [articuloInstance: articuloInstance])
        }
    }

    def show = {
        def articuloInstance = Articulo.get(params.id)
        if (!articuloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "list")
        }
        else {
            [articuloInstance: articuloInstance]
        }
    }

    def edit = {
        def articuloInstance = Articulo.get(params.id)
        if (!articuloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "list")
        }
        else {
            return [articuloInstance: articuloInstance]
        }
    }

    def update = {
        def articuloInstance = Articulo.get(params.id)
        if (articuloInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (articuloInstance.version > version) {
                    
                    articuloInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'articulo.label', default: 'Articulo')] as Object[], "Another user has updated this Articulo while you were editing")
                    render(view: "edit", model: [articuloInstance: articuloInstance])
                    return
                }
            }
            articuloInstance.properties = params
            if (!articuloInstance.hasErrors() && articuloInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articulo.label', default: 'Articulo'), articuloInstance.id])
                redirect(action: "show", id: articuloInstance.id)
            }
            else {
                render(view: "edit", model: [articuloInstance: articuloInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def articuloInstance = Articulo.get(params.id)
        if (articuloInstance) {
            try {
                articuloInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "list")
        }
    }
}
