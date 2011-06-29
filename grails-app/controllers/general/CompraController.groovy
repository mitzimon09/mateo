package general

import grails.converters.JSON

class CompraController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[compraList: Compra.list(params), compraTotal: Compra.count()]
	}

    def nueva = {
        def compra = new Compra()
        compra.properties = params
        compra.folio = Compra.count()+1
        return [compra: compra]
    }

    def crea = {
        params.folio = Compra.count()+1
        def compra = new Compra(params)
        
        if (compra.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
            redirect(action: "edita", id: compra.id)
        }
        else {
            render(view: "nueva", model: [compra: compra])
        }
    }

    def ver = {
        def compra = Compra.get(params.id)
        if (!compra) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
        else {
            [compra: compra]
        }
    }

    def edita = {
        def compra = Compra.get(params.id)
        if (!compra) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
        else {
            return [compra: compra]
        }
    }

    def actualiza = {
        def compra = Compra.get(params.id)
        if (compra) {
            if (params.version) {
                def version = params.version.toLong()
                if (compra.version > version) {
                    
                    compra.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'compra.label', default: 'Compra')] as Object[], "Another user has updated this Compra while you were editing")
                    render(view: "edita", model: [compra: compra])
                    return
                }
            }
            compra.properties = params
            if (!compra.hasErrors() && compra.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
                redirect(action: "ver", id: compra.id)
            }
            else {
                render(view: "edita", model: [compra: compra])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def compra = Compra.get(params.id)
        if (compra) {
            try {
                compra.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect(action: "lista")
        }
    }
}
