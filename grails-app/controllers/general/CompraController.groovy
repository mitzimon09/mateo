package general

import grails.converters.JSON

class CompraController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[compraInstanceList: Compra.list(params), compraInstanceTotal: Compra.count()]
	}

    def create = {
        def compraInstance = new Compra()
        compraInstance.properties = params
        return [compraInstance: compraInstance]
    }

    def save = {
        def compraInstance = new Compra(params)
        if (compraInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
            redirect(action: "show", id: compraInstance.id)
        }
        else {
            render(view: "create", model: [compraInstance: compraInstance])
        }
    }

    def show = {
        def compraInstance = Compra.get(params.id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "list")
        }
        else {
            [compraInstance: compraInstance]
        }
    }

    def edit = {
        def compraInstance = Compra.get(params.id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "list")
        }
        else {
            return [compraInstance: compraInstance]
        }
    }

    def update = {
        def compraInstance = Compra.get(params.id)
        if (compraInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (compraInstance.version > version) {
                    
                    compraInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'compra.label', default: 'Compra')] as Object[], "Another user has updated this Compra while you were editing")
                    render(view: "edit", model: [compraInstance: compraInstance])
                    return
                }
            }
            compraInstance.properties = params
            if (!compraInstance.hasErrors() && compraInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
                redirect(action: "show", id: compraInstance.id)
            }
            else {
                render(view: "edit", model: [compraInstance: compraInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def compraInstance = Compra.get(params.id)
        if (compraInstance) {
            try {
                compraInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "list")
        }
    }
}
