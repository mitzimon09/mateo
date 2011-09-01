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
    	log.debug "clave antes: " + params.clave
    	if (params.clave == null){
			params.clave = asignarClave()
		}
		log.debug "clave despues: " + params.clave
        def empleadoInstance = new Empleado()
        empleadoInstance.properties = params
        return [empleadoInstance: empleadoInstance]
    }
	
	
    def save = {
        def empleadoInstance = new Empleado(params)
        if (empleadoInstance.clave == null){
        	empleadoInstance.clave = asignarClave(empleadoInstance.tipo)
        }
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
    log.debug "entró a método asignarClave"
    log.debug "tipo " + tipoEmpleado
    assert tipoEmpleado
    String clave = ""
    def empleados = empleadoService.getEmpleadosByTipo(tipoEmpleado)
    log.debug "empleados size " + empleados.size()
    	if (empleados.size() == 0){
    	log.debug "empleados cuestión"
    		clave = tipoEmpleado.prefijo + "0000"
    		log.debug "clave " + clave
    		return clave
    	}
    	log.debug "empleados 139 "
    	log.debug "empleados lista " + empleados
        //empleados = empleados.sort("clave")
        //def results = Book.list(sort:"title")
        
        empleados.each(){
        	log.debug ":)  " + $it.clave// check if properly sorted
        }
        
        def lastKey = 0
        def finalKey = 0
        def key = 0
        for (i in 1..empleados.size()){
        	
        	assertEquals "blah", i
        	assert empleados[i]
        	def empleadotmp = empleados.get(i)
        	assert empleadotmp
        	System.out.println(empleados[i].clave)
        	key = empleados[i].clave.substring(3).toInteger()
        	log.debug "key = " + key
        	
        	
        	if (lastKey != 0){
        		if ( lastKey+1 != key){
        			finalKey = lastKey+1
        		}
        		else{
        			finalKey = key+1
        		}
        	}
        	lastKey = Key
        }
        
        log.debug "last key = " + lastKey
        log.debug "final key = " + finalKey
        
        return (empleadoInstance.tipo.prefijo + finalKey)
    }
    
    List<Empleado> getEmpleadosByTipo(TipoEmpleado tipo) throws NullPointerException{
        Empleado empleado=new Empleado()
        empleado.tipo = tipo
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
    
}
