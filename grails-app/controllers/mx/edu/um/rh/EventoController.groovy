package mx.edu.um.rh

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import mx.edu.um.Constantes

@Secured(['ROLE_DIRRH'])
class EventoController {
    def springSecurityService
    def empleadoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[eventos: Evento.list(params), totalDeEventos: Evento.count()]
	}

    def nuevo = {
        def evento = new Evento()
        evento.properties = params
        return [evento: evento]
    }

    def crea = {
        def evento = new Evento(params)
        long diferenciaMils = evento.hora_final.getTime() - evento.hora_inicio.getTime();
        //obtenemos los segundos
        long segundos = diferenciaMils / 1000;
        def tiempoTotal = segundos
        log.debug "tiempoTotal > " + tiempoTotal
        //obtenemos las horas
        long horas = segundos / 3600;
        //restamos las horas para continuar con minutos
        segundos -= horas*3600;
        //igual que el paso anterior
        long minutos = segundos /60;
        segundos -= minutos*60;
        
        log.debug "horas    > " + horas
        log.debug "minutos  > " + minutos
        log.debug "segundos > " + segundos
        
        evento.tiempoTotal = tiempoTotal
        log.debug "tiempo total > " + evento.tiempoTotal
        if (evento.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
            redirect(action: "ver", id: evento.id)
        }
        else {
            render(view: "nuevo", model: [evento: evento])
        }
    }

    @Secured(['ROLE_EMP'])
    def ver = {
        def evento = Evento.get(params.id)
        if (!evento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
        else {
            if(evento.status == Constantes.STATUS_INICIADO) {
                render(view: "paseLista", model: [evento: evento])
            }else {
                [evento: evento]
            }
        }
    }

    def edita = {
        def evento = Evento.get(params.id)
        if (!evento) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
        else {
            return [evento: evento]
        }
    }

    def actualiza = {
        def evento = Evento.get(params.id)
        if (evento) {
            if (params.version) {
                def version = params.version.toLong()
                if (evento.version > version) {
                    
                    evento.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evento.label', default: 'Evento')] as Object[], "Another user has updated this Evento while you were editing")
                    render(view: "edita", model: [evento: evento])
                    return
                }
            }
            evento.properties = params
            long diferenciaMils = evento.hora_final.getTime() - evento.hora_inicio.getTime();
            //obtenemos los segundos
            long segundos = diferenciaMils / 1000;
            def tiempoTotal = segundos
            log.debug "tiempoTotal > " + tiempoTotal
            //obtenemos las horas
            long horas = segundos / 3600;
            //restamos las horas para continuar con minutos
            segundos -= horas*3600;
            //igual que el paso anterior
            long minutos = segundos /60;
            segundos -= minutos*60;
            
            log.debug "horas    > " + horas
            log.debug "minutos  > " + minutos
            log.debug "segundos > " + segundos
            
            evento.tiempoTotal = tiempoTotal
            if (!evento.hasErrors() && evento.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect(action: "ver", id: evento.id)
            }
            else {
                render(view: "edita", model: [evento: evento])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def evento = Evento.get(params.id)
        if (evento) {
            //if(evento.status != Constantes.STATUS_CREADO)
            try {
                evento.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
            redirect(action: "lista")
        }
    }
    
    def iniciarEvento = {
        def evento = Evento.get(params.id)
        if(evento.status == Constantes.STATUS_CREADO){
            evento.status = Constantes.STATUS_INICIADO
            render(view: "paseLista", model: [evento: evento])
        }else {
            flash.message = message(code: 'El evento {0} ya ha terminado', args: [evento.nombre])
            redirect(action: "lista")
        }
    }
    
    def cerrarEvento = {
        def evento = Evento.get(params.id)
        if(evento.status == Constantes.STATUS_INICIADO) {
            evento.status = Constantes.STATUS_TERMINADO
        }
        def empleadoEventos = EmpleadoEvento.findByEvento(evento).list()
        log.debug "empleadoEventos >>>>>>>>>>>>>>>>>>>" + empleadoEventos
        def temp = 1
        for(empleadoEvento in empleadoEventos) {
            def eventoRegistros = EventoRegistro.findAllByEmpleadoEvento(empleadoEvento)
            if(eventoRegistros.size()%2 != 0 ) {
                def salida = new EventoRegistro (
                    empleadoEvento: empleadoEvento
                    , adentro: false
                ).save(flush: true)
                assert salida
            }
            empleadoEvento.refresh()
            log.debug "empleado >>>>> " + empleadoEvento.empleado
            log.debug "empleadoEvento >>>>> " + empleadoEvento
            eventoRegistros = EventoRegistro.findAllByEmpleadoEvento(empleadoEvento)
            log.debug "eventoRegistros >>>>>>>>>>>>>>>>>>>" + eventoRegistros
            def tiempoPresente = 0
            def tiempoTmp = 0
            for(eventoRegistro in eventoRegistros) {
                if(eventoRegistro.adentro) {
                    tiempoTmp = eventoRegistro.fecha.getTime()
                } else {
                    tiempoTmp -= eventoRegistro.fecha.getTime()
                    tiempoPresente += (tiempoTmp*-1)
                }
            }
            log.debug "tiempoPresente > " + tiempoPresente/1000
            empleadoEvento.tiempoPresente = tiempoPresente/1000
            if(evento.tiempoTotal == empleadoEvento.tiempoPresente) {
                empleadoEvento.status = Constantes.STATUS_ASISTENCIA
            } else if(empleadoEvento.tiempoPresente > (evento.tiempoTotal - evento.prorroga)) {
                empleadoEvento.status = Constantes.STATUS_TARDANZA
            } else {
                empleadoEvento.status = Constantes.STATUS_INASISTENCIA
            }
        }
        redirect(action: "lista")
    }
    
    def paseLista = {
    	log.info "paseLista"
        def evento = Evento.get(params.evento.id)
        assert evento
        log.debug "evento > " + evento
        log.debug "clave > " + params.clave
        if(params.clave.size() == 7) {
		    def empleado = empleadoService.getEmpleado(params.clave)
		    assert empleado
		    log.debug "empleado > " + empleado
	        def empleadoEvento = empleadoService.getEmpleadoEvento(empleado, evento)
	        assert empleadoEvento
	        log.debug "empeladoEvento > " + empleadoEvento
            //def entradas = EventoRegistro.findAllEmpleadoEvento(empleadoEvento)
            //log.debug "entradas > " + entradas
            def entro = false
	        if(empleadoEvento.adentro) {
	            empleadoEvento.adentro = false
	            entro = true
	        } else {
	            empleadoEvento.adentro = true
	            entro = false
	        }
	        def eventoRegistro = new EventoRegistro (
	            empleadoEvento: empleadoEvento
                , adentro: entro
            ).save()
            assert eventoRegistro
            log.debug "eventoRegistro > " + eventoRegistro

            flash.message = message(code: 'Se registro al Empleado {0} en el evento {1}', args: [params.clave, evento.nombre])
	        render(view: "paseLista", model: [evento: evento])
        } else {
            flash.message = message(code: 'El Empleado con clave {0} no ha sido encontrado', args: [params.clave])
            render(view: "paseLista", model: [evento: evento])
        }
    }
    
    def reporte() {
        def evento = Evento.get(params.id)
        if(evento.status == Constantes.STATUS_TERMINADO) {
            def empleadoEventos = EmpleadoEvento.findByEvento(evento).list()
            log.debug "empleadoEventos >>>>>>>>>>>>>>>>>>>" + empleadoEventos
            render(view: "reporte", model: [evento:evento, empleadoEventos: empleadoEventos])
        } else {
            redirect(action: "ver", id: evento.id)
        }
    }
}
