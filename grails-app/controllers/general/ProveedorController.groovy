package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
class ProveedorController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[proveedores: Proveedor.list(params), totalDeProveedores: Proveedor.count()]
	}

    def nuevo = {
        def proveedor = new Proveedor()
        proveedor.properties = params
        return [proveedor: proveedor]
    }

    def crea = {
        def proveedor = new Proveedor(params)
        if (proveedor.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
            redirect(action: "ver", id: proveedor.id)
        }
        else {
            render(view: "nuevo", model: [proveedor: proveedor])
        }
    }

    def ver = {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "lista")
        }
        else {
            [proveedor: proveedor]
        }
    }

    def edita = {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "lista")
        }
        else {
            return [proveedor: proveedor]
        }
    }

    def actualiza = {
        def proveedor = Proveedor.get(params.id)
        if (proveedor) {
            if (params.version) {
                def version = params.version.toLong()
                if (proveedor.version > version) {
                    
                    proveedor.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proveedor.label', default: 'Proveedor')] as Object[], "Another user has updated this Proveedor while you were editing")
                    render(view: "edita", model: [proveedor: proveedor])
                    return
                }
            }
            proveedor.properties = params
            if (!proveedor.hasErrors() && proveedor.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
                redirect(action: "ver", id: proveedor.id)
            }
            else {
                render(view: "edita", model: [proveedor: proveedor])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def proveedor = Proveedor.get(params.id)
        if (proveedor) {
            try {
                proveedor.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "lista")
        }
    }
}
