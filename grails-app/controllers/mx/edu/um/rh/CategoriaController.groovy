package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_RHOPER'])
class CategoriaController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[categorias: Categoria.list(params), totalDeCategorias: Categoria.count()]
	}

    def nuevo = {
        def categoria = new Categoria()
        categoria.properties = params
        return [categoria: categoria]
    }

    def crea = {
        def categoria = new Categoria(params)
        if (categoria.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.id])
            redirect(action: "ver", id: categoria.id)
        }
        else {
            render(view: "nuevo", model: [categoria: categoria])
        }
    }

    def ver = {
        def categoria = Categoria.get(params.id)
        if (!categoria) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
            redirect(action: "lista")
        }
        else {
            [categoria: categoria]
        }
    }

    def edita = {
        def categoria = Categoria.get(params.id)
        if (!categoria) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
            redirect(action: "lista")
        }
        else {
            return [categoria: categoria]
        }
    }

    def actualiza = {
        def categoria = Categoria.get(params.id)
        if (categoria) {
            if (params.version) {
                def version = params.version.toLong()
                if (categoria.version > version) {
                    
                    categoria.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'categoria.label', default: 'Categoria')] as Object[], "Another user has updated this Categoria while you were editing")
                    render(view: "edita", model: [categoria: categoria])
                    return
                }
            }
            categoria.properties = params
            if (!categoria.hasErrors() && categoria.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.id])
                redirect(action: "ver", id: categoria.id)
            }
            else {
                render(view: "edita", model: [categoria: categoria])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def categoria = Categoria.get(params.id)
        if (categoria) {
            try {
                categoria.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
            redirect(action: "lista")
        }
    }
}
