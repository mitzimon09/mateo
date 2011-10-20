package mx.edu.um.rh

import grails.converters.JSON
import mx.edu.um.rh.interfaces.*
import grails.plugins.springsecurity.Secured
import mx.edu.um.rh.*

@Secured(['ROLE_ADMIN'])
class EmpleadoController {

	def empleadoService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empleados: Empleado.list(params), totalDeEmpleados: Empleado.count()]
	}

    def nuevo = {
        return [empleado:new Empleado()]
    }
	
	
    def crea = {
    	log.debug "tipo > " + params.tipo.id
        params.clave = asignarClave(params.tipo.id)
        log.debug "clave > " + params.clave
        def empleado = new Empleado(params)
        log.debug "clave before saving " + empleado.clave
        if (empleado.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.id])
            redirect(action: "ver", id: empleado.id)
        }
        else {
            render(view: "nuevo", model: [empleado: empleado])
        }
    }

    def ver = {
        def empleado = Empleado.get(params.id)
        if (!empleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "lista")
        }
        else {
            [empleado: empleado]
        }
    }

    def edita = {
        def empleado = Empleado.get(params.id)
        if (!empleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empleado: empleado]
        }
    }

    def actualiza = {
        def empleado = Empleado.get(params.id)
        if (empleado) {
            if (params.version) {
                def version = params.version.toLong()
                if (empleado.version > version) {
                    
                    empleado.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empleado.label', default: 'Empleado')] as Object[], "Another user has updated this Empleado while you were editing")
                    render(view: "edita", model: [empleado: empleado])
                    return
                }
            }
            empleado.properties = params
            if (!empleado.hasErrors() && empleado.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.id])
                redirect(action: "ver", id: empleado.id)
            }
            else {
                render(view: "edita", model: [empleado: empleado])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
            redirect(action: "lista")
        }
    }

    def delete = { //cambio de status
    	def empleado = Empleado.get(params.id)
    	if (empleado){
    		empleado.status="I" //inactivo
    		empleado.save(flush:true)
    		redirect(action: "ver", id: empleado.id)
    	}
    }
    
    String asignarClave(def tipo) {
    	TipoEmpleado tipoEmpleado = TipoEmpleado.get(tipo)
        String clave = ""
        def empleados = empleadoService.getEmpleadosByTipo(tipoEmpleado)
        log.debug "empleados > " + empleados
	    if (empleados.size() == 0) {
		    clave = tipoEmpleado.prefijo + "0000"
		    return clave
	    }
	    log.debug "otro coso" + empleados[0].clave
	    def lastKey = -1
	    def finalKey = 0
	    def key = 0
	    for (i in 0..(empleados.size() - 1)) {
	    	def empleadotmp = empleados.get(i)
	    	assert empleadotmp
	    	key = empleados[i].clave.substring(3).toInteger()
	    	if (lastKey != -1) {
	    		if (lastKey + 1 != key) {
	    			finalKey = lastKey + 1
	    		}
	    		else if (lastKey != key){
	    			finalKey = lastKey
	    			//finalKey = key + 1
	    		}
	    	}
	    	else{
				lastKey = key+1
	    	}
	    }
	    while ((finalKey + "").length() < 4) {
	    //while (finalKey.length() < 4) {
	    	finalKey = "0" + finalKey
	    }
	    return (tipoEmpleado.prefijo + finalKey)
    }
    
    List<Empleado> getEmpleadosByTipo(TipoEmpleado tipo) throws NullPointerException {
        Empleado empleado = new Empleado()
        empleado.tipo = tipo
        empleado.status = Constantes.STATUS_ACTIVO
        def empleados = Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
}
