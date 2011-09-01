package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import au.com.bytecode.opencsv.CSVReader

@Secured(['ROLE_ORG'])
class DepartamentoController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
     }

    @Secured(['ROLE_EMP'])
    def lista = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
        [departamentos: Departamento.findAllByOrganizacion(usuario.empresa.organizacion, params), totalDeDepartamentos: Departamento.countByOrganizacion(usuario.empresa.organizacion)]
    }

    def nuevo = {
        def departamento = new Departamento()
        departamento.properties = params
        return [departamento: departamento]
    }
    
    def crea = {
        
        log.debug "departamento#############: $params"
        
        def departamento = new Departamento(params)
        def usuario = springSecurityService.currentUser
        departamento.organizacion = usuario.empresa.organizacion
        
        if (params.tieneAuxiliares == null){
            params.tieneAuxiliares = false
        }
        if (params.tieneMovimientos == null){
            params.tieneMovimientos = false
        }
        if (params.status == null){
            params.status = false
        }
        
        def cuenta = new Cuenta (
                    codigo : params.codigo
                    ,numero : params.numero
                    ,descripcion : params.descripcion
                    ,padre : ""
                    ,tieneAuxiliares : params.tieneAuxiliares
                    ,tieneMovimientos : params.tieneMovimientos
                    ,status : params.status
                    ,organizacion : usuario.empresa.organizacion
        ).save(flush:true)
        
        departamento.cuenta = cuenta
        
        if (departamento.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'departamento.label', default: 'Departamento'), departamento.nombre])
            redirect(action: "ver", id: departamento.id)
        }
        else {
            render(view: "nuevo", model: [departamento: departamento])
        }
    }
    
    def ver = {
        def departamento = Departamento.get(params.id)
        if (!departamento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departamento.label', default: 'Departamento'), params.id])
            redirect(action: "lista")
        }
        else {
            [departamento: departamento]
        }
    }
    
    def edita = {
        def departamento = Departamento.get(params.id)
        log.debug "departamento: $departamento.id"
        if (!departamento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departamento.label', default: 'Departamento'), params.id])
            redirect(action: "lista")
        }
        else {
            return [departamento: departamento]
        }
    }
    
    def actualiza = {

        def departamento = Departamento.get(params.id)
        if (departamento) {
            if (params.version) {
                def version = params.version.toLong()
                if (departamento.version > version) {
                    
                    departamento.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'departamento.label', default: 'Departamento')] as Object[], "Another user has updated this Departamento while you were editing")
                    render(view: "edita", model: [departamento: departamento])
                    return
                }
            }
            
            
            log.debug "departamento#############: $params.tieneAuxiliares"
            log.debug "departamento: $departamento.cuenta.id"
            
            departamento.cuenta = Cuenta.actualiza(departamento.cuenta.id)
           
            departamento.properties = params
            if (!departamento.hasErrors() && departamento.save(flush: true)) {

                flash.message = message(code: 'default.updated.message', args: [message(code: 'departamento.label', default: 'Departamento'), departamento.nombre])
                redirect(action: "ver", id: departamento.id)
            }
            else {
                render(view: "edita", model: [departamento: departamento])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departamento.label', default: 'Departamento'), params.id])
            redirect(action: "lista")
        }
    }
    
}
