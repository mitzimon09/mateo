package mx.edu.um.rh

import grails.converters.JSON
import mx.edu.um.rh.interfaces.*
import mx.edu.um.rh.*

class EmpleadoController {

	def empleadoService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empleadoInstanceList: Empleado.list(params), empleadoInstanceTotal: Empleado.count()]
	}

    def create = {
        def empleadoInstance = new Empleado()
        empleadoInstance.properties = params
        return [empleadoInstance: empleadoInstance]
    }
	
	
    def save = {
        params.clave = asignarClave(params.tipo)
        def empleadoInstance = new Empleado(params)
    	//empleadoInstance.clave = asignarClave(empleadoInstance.tipo)
        log.debug "clave before saving " + empleadoInstance.clave
        if (empleadoInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleadoInstance.id])
            redirect(action: "show", id: empleadoInstance.id)
        }
        else {
            render(view: "create", model: [empleadoInstance: empleadoInstance])
        }
    }

    def show = {
        def empleadoInstance = Empleado.get(params.id)
        //log.debug empleadoInstance.empleadoPersonales
        if (!empleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
        }
        else {
            [empleadoInstance: empleadoInstance]
        }
    }

    def edit = {
        def empleadoInstance = Empleado.get(params.id)
        if (!empleadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
        }
        else {
            return [empleadoInstance: empleadoInstance]
        }
    }

    def update = {
        def empleadoInstance = Empleado.get(params.id)
        if (empleadoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (empleadoInstance.version > version) {
                    
                    empleadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empleado.label', default: 'Empleado')] as Object[], "Another user has updated this Empleado while you were editing")
                    render(view: "edit", model: [empleadoInstance: empleadoInstance])
                    return
                }
            }
            empleadoInstance.properties = params
            if (!empleadoInstance.hasErrors() && empleadoInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleadoInstance.id])
                redirect(action: "show", id: empleadoInstance.id)
            }
            else {
                render(view: "edit", model: [empleadoInstance: empleadoInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
        }
    }

/*    def delete = {
        def empleadoInstance = Empleado.get(params.id)
        if (empleadoInstance) {
            try {
                empleadoInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "list")
        }
    }*/
    
    def delete = { //cambio de status
    	def empleado = Empleado.get(params.id)
    	if (empleado){
    		empleado.status="I" //inactivo
    		empleado.save(flush:true)
    		redirect(action: "show")
    	}
    }
    
    def asignarClave = { tipoEmpleado ->
    assert tipoEmpleado
    String clave = ""
    def empleados = empleadoService.getEmpleadosByTipo(tipoEmpleado)
    	if (empleados.size() == 0){
    		clave = tipoEmpleado.prefijo + "0000"
    		return clave
    	}
        //empleados = empleados.sort("clave")
        
/*        for (Empleado empleado in empleados){
        	log.debug ":)  " + empleados.clave// check if properly sorted
        }*/
        
        def lastKey = -1
        def finalKey = 0
        def key = 0
        for (i in 0..(empleados.size()-1)){
        	def empleadotmp = empleados.get(i)
        	assert empleadotmp
        	key = empleados[i].clave.substring(3).toInteger()
        	
        	
        	if (lastKey != -1){
        		if ( lastKey+1 != key){
        			finalKey = lastKey+1
        		}
        		else{
        			finalKey = key+1
        		}
        	}
        	lastKey = key
        }
        
        while ((finalKey+"").length() < 4){
        	finalKey = "0"+finalKey
        }
        
        return (tipoEmpleado.prefijo + finalKey)
    }
    
    List<Empleado> getEmpleadosByTipo(TipoEmpleado tipo) throws NullPointerException{
        Empleado empleado=new Empleado()
        empleado.tipo = tipo
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
    
}
