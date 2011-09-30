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
             
                def cuenta = Cuenta.get(departamento.cuenta.id)
                
            
                if (params.tieneAuxiliares == 'on'){
                params.tieneAuxiliares = true
                }else{params.tieneAuxiliares = false}
            
                if (params.tieneMovimientos == 'on'){
                    params.tieneMovimientos = true
                }else{params.tieneMovimientos = false}
                
                if (params.status == 'on'){
                    params.status = true
                }else{params.status = false}
        
                
                cuenta.tieneAuxiliares = params.tieneAuxiliares
                cuenta.tieneMovimientos = params.tieneMovimientos
                cuenta.status = params.status
                
                departamento.cuenta = cuenta.save()
            
                
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
    
    
     def elimina = {
        def departamento = Departamento.get(params.id)
        if (departamento) {
            def cuenta = Cuenta.get(departamento.cuenta.id)
            def nombre = departamento.nombre
            try {
                
                cuenta.status = false
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'departamento.label', default: 'Departamento'), nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'departamento.label', default: 'Departamento'), nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departamento.label', default: 'Departamento'), params.id])
            redirect(action: "lista")
        }
    }
    
}
