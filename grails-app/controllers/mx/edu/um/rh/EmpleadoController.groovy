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
        params.clave = siguienteClave(params.tipo.id)
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
    
    String siguienteClave(def tipo) {
    	TipoEmpleado tipoEmpleado = TipoEmpleado.get(tipo)
        String clave = ""
        def empleados = empleadoService.getEmpleadosByTipo(tipoEmpleado)
        log.debug "empleados > " + empleados
        Map <String, Empleado> claves = new TreeMap<String, Empleado>()
        for(Empleado empleado : empleados){
        	claves.put(empleado.clave,empleado)
        }
        for(i in 1..9999){
        	def keytmp = i
        	while ((keytmp + "").length() < 4) {
		    	keytmp = "0" + keytmp
		    }
		    String claveCompleta = tipoEmpleado.prefijo + keytmp
        	if(!claves.containsKey(claveCompleta)){
        		log.debug "claveCompleta > " + claveCompleta
        		return claveCompleta
        	}
        }
    }
    
    List<Empleado> getEmpleadosByTipo(TipoEmpleado tipo) throws NullPointerException {
        Empleado empleado = new Empleado()
        empleado.tipo = tipo
        empleado.status = Constantes.STATUS_ACTIVO
        def empleados = Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
}
