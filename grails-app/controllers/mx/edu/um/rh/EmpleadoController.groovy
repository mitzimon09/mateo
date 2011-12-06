package mx.edu.um.rh

import grails.converters.JSON
import mx.edu.um.rh.interfaces.*
import grails.plugins.springsecurity.Secured
import mx.edu.um.rh.*

@Secured(['ROLE_RHOPER'])
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
            flash.message = message(code: 'default.created.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.clave])
            redirect(action: "ver", id: empleado.id)
        }
        else {
            render(view: "nuevo", model: [empleado: empleado])
        }
    }

    def ver = {
        log.debug "session.empleado > " + session.empleado
        //if(session.empleado){
        //    params.id = session.empleado
        //}
        def empleado = Empleado.get(params.id)
        if (!empleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.clave])
            redirect(action: "lista")
        }
        else {
            [empleado: empleado]
        }
    }

    def edita = {
        def empleado = Empleado.get(params.id)
        if (!empleado) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.clave])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.clave])
                redirect(action: "ver", id: empleado.id)
            }
            else {
                render(view: "edita", model: [empleado: empleado])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.clave])
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
    
    def eventos () {
        def empleado = Empleado.get(params.id)
        def empleadoEventos = empleadoService.eventosPorEmpleado(empleado)
        [empleadoEventos: empleadoEventos]
    }
    
    def buscar(){
        render(view: "buscarEmpleado")
    }
    
    def buscarEmpleado() {
        log.debug "Params > " + params
        def filtro = "%${params.term}%"
        def empleados = Empleado.listaConFiltro(filtro).list(params)
        def lista = []
        for(empleado in empleados) {
            def value = empleado.clave + " " + empleado.nombreCompleto
            lista << [id: empleado.id, value: value]
        }
        //log.debug "Lista >: " + lista
        render lista as JSON
    }
    
    def cargarEmpleado() {
        session.empleado = params.empleado.id
        redirect(action: "ver", id: session.empleado)
    }
    
    def muestrapercepciones () {
        def empleado = Empleado.get(params.id)
        List percepciones = new ArrayList()
        //def percepciones = empleado.perdedsList
        //log.debug "percepcipones de empleado: ${empleado.clave} | ${percepciones.size()}"
        //def empleadoPerded = EmpleadoPerded.findAllByPerded(percepciones)
        def grupo = empleado.grupo
        def porcentajes = Porcentaje.findAllByGrupo(Grupo.findByNombre(grupo.nombre))
        for(p in porcentajes){
            percepciones.add(p.perded)
            println("Mis Percepcuones" +p.perded.clave)
        }
        
        // def tipoEmpleado = empleado.tipo
        //println("Percepciones: " +percepciones)
        println("Grupo: " +grupo)
        println("Porcentaje: " +porcentajes)
        //println("Tipo Emp: " +tipoEmpleados)
        render(view: "percepciones", model:[empleado:empleado, percepciones:percepciones])
    }
    
    def percepciones (){
        def empleado = Empleado.get(params.id)
        [empleado:empleado]
    }
    
    def formagrega (){
        
        def empleado = Empleado.get(params.id)
        def empleadoPerded=new EmpleadoPerded(params)
        empleadoPerded.empleado=empleado
        //        
        //        def perded = PerDed.get(params.id)
        [empleadoPerded:empleadoPerded]
    }
    
    def grabapercepcion(){
        log.debug("PARAMETROS EMPLEADOPERDED:           "+params)
        def empleado = Empleado.get(params.empleado.id)
        log.debug("EMPLEADO:           "+empleado)
        def perded = PerDed.get(params.perded.id)
        log.debug("PERDED:           "+perded)
        def importe = new BigDecimal(params.importe)
        log.debug("IMPORTE:           "+importe)
        def tipoImporte = params.tipoImporte
        log.debug("TIPOIMPORTE:           "+tipoImporte)
        def atributos = params.atributos
        log.debug("ATRIBUTOS:           "+atributos)
        def otorgado = params.otorgado
        if(otorgado == "on"){
            otorgado = true
        }else {
            otorgado = false
        }
        log.debug("OTORGADO:           "+otorgado)
        def isEditableByNOM = params.isEditableByNOM
        if(isEditableByNOM == "on"){
            isEditableByNOM = true
        }else {
            isEditableByNOM = false
        }
        log.debug("EDITABLENOM:           "+isEditableByNOM)
        empleadoService.addPercepcionToEmpleado(empleado, perded, importe, tipoImporte, atributos, otorgado, isEditableByNOM)
        redirect(action:"ver",id:empleado.id)
    }
}
