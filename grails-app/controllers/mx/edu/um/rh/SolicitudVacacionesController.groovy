package mx.edu.um.rh

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import mx.edu.um.rh.*
import grails.converters.JSON
import general.*
import mx.edu.um.Constantes

class SolicitudVacacionesController extends SolicitudRHController{
	def springSecurityService
	def procesoService
	def solicitudVacacionesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "lista", params: params)
    }

    def lista() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [solicitudesVacaciones: SolicitudVacaciones.list(params), totalDeSolicitudesVacaciones: SolicitudVacaciones.count()]
    }

    def nueva() {
        return [solicitudVacaciones: new SolicitudVacaciones(params)]
    }

    def crea() {
	    	
        params.fechaCaptura = new Date()
        params.usuarioCrea = springSecurityService.currentUser
        params.diasVacaciones = (params.fechaFinal - params.fechaInicial)+1
        boolean primaAnual = derechoAPrimaVacacionalUnaVezAnual(Integer.parseInt(params.empleado.id))
        
        if (!((params.diasVacaciones > 7) && primaAnual)){//si los dias no (son mayores a 7 y tiene derecho)
        	params.userPrimaVacacional = 0.00
        	def date = new Date()
        }
        if (params.userPrimaVacacional > 0.00){
	        params.primaVacacionla = true;
        }
        boolean visitaAnual = visitaPadresUnaVezAnual(Integer.parseInt(params.empleado.id))
        boolean vacaciones900 = false
        
        int weekend = terminanVacacionesEnViernesOSabado(params.fechaFinal)
		params.fechaFinal += weekend
        params.diasVacaciones = (params.fechaFinal - params.fechaInicial)+1
        if (weekend == 1){
        	flash.error = "Se te ha contado un día más por terminar tus vacaciones en sábado"
        }
        else if(weekend == 2){
        	flash.error = "Se te han contado 2 días más por terminar tus vacaciones en viernes"
        }

        //if kilometros >= 900, se restan 2 días a las vacaciones del empleado
        if (((params.kilometros >= 900) && (params.visitaPadres)) && visitaAnual){
        	params.diasVacaciones -= 2
        	flash.message = "Se te han dado 2 días extras por tu visita a padres a más de 900 km anual"
        	vacaciones900 = true
        }
        
        
        
        int diasferiados = solicitudVacacionesService.getDiasFeriadosEnElRango(params.fechaInicial, params.fechaFinal).size()
        params.diasVacaciones -= diasferiados;
        if (diasferiados >1){
        	flash.warning = "No se han contado " + diasferiados + " días por ser días feriados"
        }
        else if (diasferiados == 1 ){
        	flash.warning = "No se ha contado " + diasferiados + " día por ser día feriado"
        }
        //Observaciones
        log.debug "params " + params
        def observaciones = null
        if (!params.observacion.equals("")){
        	log.debug "Systema"
		    observaciones = new Observaciones(observaciones: params.observacion, usuario: params.usuarioCrea, datecreated: new Date())
        }
	    params.observacion = null
        def solicitudVacaciones = new SolicitudVacaciones(params)
        solicitudVacaciones.observaciones = new LinkedHashSet()
        if(observaciones != null){
		    observaciones.solicitudRH = solicitudVacaciones
		    observaciones.save()
		    solicitudVacaciones.observaciones.add(observaciones)
        }
        def empleado = Empleado.get(params.empleado.id)
        int dias = solicitudVacacionesService.totalDeDiasDeVacaciones(empleado)
        if ((dias - solicitudVacaciones.diasVacaciones) < 0){terminanVacacionesEnViernesOSabado(params.fechaFinal)
        	solicitudPermiso.errors.rejectValue("fechaFinal", "Solo tienes derecho a " + dias + " días de vacaciones y haz pedido " + solicitudVacaciones.diasVacaciones +". Tu solicitud no puede ser procesada")
        }
        if (!solicitudVacaciones.save(flush: true)) {
            render(view: "nueva", model: [solicitudVacaciones: solicitudVacaciones])
            return
        }
		log.debug "Se supone que debe de ir " + solicitudVacaciones
		flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), solicitudVacaciones.id])
        redirect(action: "ver", id: solicitudVacaciones.id)
    }

    def ver() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        def permisos = permisos()
        log.debug "permisos " + permisos
        if (!solicitudVacaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "list")
            return
        }

        [solicitudVacaciones: solicitudVacaciones, permisos: permisos]
    }
    
    @Secured(['ROLE_RHOPER', 'ROLE_DIRRH'])
        def autorizar () {
        	def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        	log.debug params
			if (solicitudVacaciones){
				solicitudVacaciones.fechaAutoriza = new Date()
				if(solicitudVacaciones.status.equals(Constantes.STATUS_REVISADO)){
					solicitudVacaciones = procesoService.autorizar(solicitudVacaciones)
					solicitudVacaciones.usuarioAutoriza = springSecurityService.currentUser
					log.debug "empleado de solicitud " + params.empleado
					Vacaciones vacaciones = new Vacaciones(dateCreated: new Date(), descripcion: "Vacaciones", dias: solicitudVacaciones.diasVacaciones, usuario: springSecurityService.currentUser, empleado: solicitudVacaciones.empleado, solcitudVacaciones: solicitudVacaciones, empresa: solicitudVacaciones.empleado.empresa, status: "P").save()
					solicitudVacaciones.save(flush:true)
					redirect(action: "lista")
				}
			}
        	
        }

    def edita() {
    	log.debug "params " + params
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        
        def permisos = permisos()
        if (!solicitudVacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }

        [solicitudVacaciones: solicitudVacaciones, permisos: permisos]
    }

    def actualiza() {
    	//Observaciones
    	def solicitud = SolicitudVacaciones.get(params.id)
        log.debug "params " + params
        params.usuarioCrea = solicitud.usuarioCrea
        log.debug "ciencia" + params.usuarioCrea
        def observaciones = null
        if (!params.observacion.equals("")){
        	log.debug "Systema"
		    observaciones = new Observaciones(observaciones: params.observacion, usuario: params.usuarioCrea, datecreated: new Date())
        }
	    params.observacion = null
        def solicitudVacaciones = new SolicitudVacaciones(params)
        if(observaciones != null){
		    observaciones.solicitudRH = solicitudVacaciones
		    observaciones.save()
		    solicitudVacaciones.observaciones.add(observaciones)
        }
        if (params.version) {
            def version = params.version.toLong()
            if (solicitudVacaciones.version > version) {
                solicitudVacaciones.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')] as Object[],
                          "Another user has updated this SolicitudVacaciones while you were editing")
                render(view: "edita", model: [solicitudVacaciones: solicitudVacaciones])
                return
            }
        }
        
        if (!solicitudVacaciones) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }


        if (!solicitudVacaciones.save(flush: true)) {
            render(view: "edita", model: [solicitudVacaciones: solicitudVacaciones])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), solicitudVacaciones.id])
        redirect(action: "ver", id: solicitudVacaciones.id)
    }

    def elimina() {
        def solicitudVacaciones = SolicitudVacaciones.get(params.id)
        if (!solicitudVacaciones) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
            return
        }

        try {
            solicitudVacaciones.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "lista")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones'), params.id])
            redirect(action: "ver", id: params.id)
        }
    }
    
    def derechoAPrimaVacacionalUnaVezAnual(int empleadoid){
    	def empleado = Empleado.get(empleadoid)
    	def solicitudesVacaciones = solicitudVacacionesService.getSolicitudesAnuales(empleado)
    	if (solicitudesVacaciones == null){
    		return true
    	}
    	for (SolicitudVacaciones tmp in solicitudesVacaciones){
    		if (tmp.userPrimaVacacional > 0){
    			return false
    		}
    	}
    	return true
    }
    
    def visitaPadresUnaVezAnual(int empleadoid){
    	def empleado = Empleado.get(empleadoid)
    	def solicitudesVacaciones = solicitudVacacionesService.getSolicitudesAnuales(empleado)
    	for (SolicitudVacaciones tmp in solicitudesVacaciones){
    		if (tmp.visitaPadres){
    			return false
    		}
    	}
    	return true
    }
    
    def terminanVacacionesEnViernesOSabado(Date terminanVacaciones){
    	int diasExtras = 0
    	if ((terminanVacaciones.toString()).startsWith("Fri")){
    		diasExtras = 2
    	}
    	else if ((terminanVacaciones.toString()).startsWith("Sat")){
    		diasExtras = 1
    	}
    	return diasExtras
    }
}
