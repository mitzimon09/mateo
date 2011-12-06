package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException

class EmpleadoPerdedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [empleadoPerdedList: EmpleadoPerded.list(params), empleadoPerdedTotal: EmpleadoPerded.count()]
    }

    def create() {
        [empleadoPerded: new EmpleadoPerded(params)]
    }

    def save() {
        def empleadoPerded = new EmpleadoPerded(params)
        if (!empleadoPerded.save(flush: true)) {
            render(view: "create", model: [empleadoPerded: empleadoPerded])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), empleadoPerded.id])
        redirect(action: "show", id: empleadoPerded.id)
    }

    def show() {
        def empleadoPerded = EmpleadoPerded.get(params.id)
        if (!empleadoPerded) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "list")
            return
        }

        [empleadoPerded: empleadoPerded]
    }

    def edit() {
        def empleadoPerded = EmpleadoPerded.get(params.id)
        if (!empleadoPerded) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "list")
            return
        }

        [empleadoPerded: empleadoPerded]
    }

    def update() {
        def empleadoPerded = EmpleadoPerded.get(params.id)
        if (!empleadoPerded) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (empleadoPerded.version > version) {
                empleadoPerded.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded')] as Object[],
                          "Another user has updated this EmpleadoPerded while you were editing")
                render(view: "edit", model: [empleadoPerded: empleadoPerded])
                return
            }
        }

        empleadoPerded.properties = params

        if (!empleadoPerded.save(flush: true)) {
            render(view: "edit", model: [empleadoPerded: empleadoPerded])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), empleadoPerded.id])
        redirect(action: "show", id: empleadoPerded.id)
    }

    def delete() {
        def empleadoPerded = EmpleadoPerded.get(params.id)
        if (!empleadoPerded) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "list")
            return
        }

        try {
            empleadoPerded.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleadoPerded.label', default: 'EmpleadoPerded'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
