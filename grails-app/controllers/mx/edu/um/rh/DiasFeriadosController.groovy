package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class DiasFeriadosController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[diasFeriadoss: DiasFeriados.list(params), totalDeDiasFeriadoss: DiasFeriados.count()]
	}

    def nuevo = {
        def diasFeriados = new DiasFeriados()
        diasFeriados.properties = params
        return [diasFeriados: diasFeriados]
    }

    def crea = {
        def diasFeriados = new DiasFeriados(params)
        if (diasFeriados.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), diasFeriados.id])
            redirect(action: "ver", id: diasFeriados.id)
        }
        else {
            render(view: "nuevo", model: [diasFeriados: diasFeriados])
        }
    }

    def ver = {
        def diasFeriados = DiasFeriados.get(params.id)
        if (!diasFeriados) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
            redirect(action: "lista")
        }
        else {
            [diasFeriados: diasFeriados]
        }
    }

    def edita = {
        def diasFeriados = DiasFeriados.get(params.id)
        if (!diasFeriados) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
            redirect(action: "lista")
        }
        else {
            return [diasFeriados: diasFeriados]
        }
    }

    def actualiza = {
        def diasFeriados = DiasFeriados.get(params.id)
        if (diasFeriados) {
            if (params.version) {
                def version = params.version.toLong()
                if (diasFeriados.version > version) {
                    
                    diasFeriados.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'diasFeriados.label', default: 'DiasFeriados')] as Object[], "Another user has updated this DiasFeriados while you were editing")
                    render(view: "edita", model: [diasFeriados: diasFeriados])
                    return
                }
            }
            diasFeriados.properties = params
            if (!diasFeriados.hasErrors() && diasFeriados.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), diasFeriados.id])
                redirect(action: "ver", id: diasFeriados.id)
            }
            else {
                render(view: "edita", model: [diasFeriados: diasFeriados])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def diasFeriados = DiasFeriados.get(params.id)
        if (diasFeriados) {
            try {
                diasFeriados.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diasFeriados.label', default: 'DiasFeriados'), params.id])
            redirect(action: "lista")
        }
    }
}
