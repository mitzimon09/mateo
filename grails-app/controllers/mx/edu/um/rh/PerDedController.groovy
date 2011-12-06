package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException

class PerDedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "lista", params: params)
    }

    def lista() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [perDedList: PerDed.list(params), perDedTotal: PerDed.count()]
    }

    def nuevo() {
        [perDed: new PerDed(params)]
    }

    def guardar() {
        def perDed = new PerDed(params)
        if (!perDed.save(flush: true)) {
            render(view: "create", model: [perDed: perDed])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'perDed.label', default: 'PerDed'), perDed.id])
        redirect(action: "show", id: perDed.id)
    }

    def ver() {
        def perDed = PerDed.get(params.id)
        if (!perDed) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "list")
            return
        }

        [perDed: perDed]
    }

    def edita() {
        def perDed = PerDed.get(params.id)
        if (!perDed) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "list")
            return
        }

        [perDed: perDed]
    }

    def actualiza() {
        def perDed = PerDed.get(params.id)
        if (!perDed) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (perDed.version > version) {
                perDed.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'perDed.label', default: 'PerDed')] as Object[],
                          "Another user has updated this PerDed while you were editing")
                render(view: "edit", model: [perDed: perDed])
                return
            }
        }

        perDed.properties = params

        if (!perDed.save(flush: true)) {
            render(view: "edit", model: [perDed: perDed])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'perDed.label', default: 'PerDed'), perDed.id])
        redirect(action: "show", id: perDed.id)
    }

    def elimina() {
        def perDed = PerDed.get(params.id)
        if (!perDed) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "list")
            return
        }

        try {
            perDed.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'perDed.label', default: 'PerDed'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
