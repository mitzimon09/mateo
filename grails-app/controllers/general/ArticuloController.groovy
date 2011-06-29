package general

import grails.converters.JSON

class ArticuloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[articulos: Articulo.list(params), totalDeArticulos: Articulo.count()]
	}

    def nuevo = {
        def articulo = new Articulo()
        articulo.properties = params
        return [articulo: articulo]
    }

    def crea = {
        def articulo = new Articulo(params)
        println "cantidad " + articulo.cantidad
        articulo.total = articulo.cantidad * articulo.precioUnitario
        if (articulo.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
            redirect(controller:"compra", action: "edita", id: articulo.compra.id)
        }
        else {
            render(view: "nuevo", model: [articulo: articulo])
        }
    }

    def ver = {
        def articulo = Articulo.get(params.id)
        if (!articulo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
        else {
            [articulo: articulo]
        }
    }

    def edita = {
        def articulo = Articulo.get(params.id)
        if (!articulo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
        else {
            return [articulo: articulo]
        }
    }

    def actualiza = {
        def articulo = Articulo.get(params.id)
        articulo.total = articulo.cantidad * articulo.precioUnitario
        if (articulo) {
            if (params.version) {
                def version = params.version.toLong()
                if (articulo.version > version) {
                    
                    articulo.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'articulo.label', default: 'Articulo')] as Object[], "Another user has updated this Articulo while you were editing")
                    render(view: "edit", model: [articulo: articulo])
                    return
                }
            }
            articulo.properties = params
            if (!articulo.hasErrors() && articulo.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect(controller:"compra", action: "edita", id: articulo.compra.id)
            }
            else {
                render(view: "edita", model: [articulo: articulo])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def articulo = Articulo.get(params.id)
        if (articulo) {
            try {
                articulo.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
            redirect(action: "lista")
        }
    }
}
