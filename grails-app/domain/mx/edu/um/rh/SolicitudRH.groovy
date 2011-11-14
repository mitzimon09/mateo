package mx.edu.um.rh

import general.*
import mx.edu.um.Constantes

class SolicitudRH {
	Date fechaCaptura = new Date()
	Empleado empleado
	Empresa empresa
	Date fechaInicial
	Date fechaFinal
	Usuario usuarioCrea //user
	Date dateCreated
	Usuario usuarioRecibe //jefeCCosto
	Date fechaRecibe
	Usuario usuarioAutoriza //dirRH
	Date fechaAutoriza
	Observaciones observaciones
	String telContacto
	String email
	SolicitudSalida solicitudSalida
	Vacaciones vacaciones
	String status = "CR"
	JefeCCosto jefeCCosto
	
	String folio
	
	
	
	static belongsTo = {[Empleado:empleado, Empresa:empresa]}

    static constraints = {
    	empleado blank:false
		fechaInicial blank: false
		fechaFinal blank: false
		usuarioCrea blank:false
		dateCreated blank: false
		usuarioRecibe nullable: true
		fechaRecibe nullable: true
		usuarioAutoriza nullable: true
		fechaAutoriza nullable: true
		observaciones nullable: true
		telContacto nullable: true
		email nullable: true
		solicitudSalida nullable: true
		vacaciones nullable: true
		status inList: [Constantes.STATUS_CREADO, Constantes.STATUS_ENVIADO, Constantes.STATUS_REVISADO, Constantes.STATUS_APROBADO, Constantes.STATUS_CANCELADO, Constantes.STATUS_AUTORIZADO, Constantes.STATUS_SUSPENDIDO, Constantes.STATUS_REVISADO, Constantes.STATUS_RECHAZADO]
		jefeCCosto nullable: true
		folio nullable: true
    }
    
    static namedQueries = {
        listaSolicitudesParametros{SolicitudRH solicitudRH ->
            if(solicitudRH){
                //Valida el status
                if(solicitudRH.status){                    
                    eq 'status', solicitudRH.status                    
                }
                if(solicitudRH.empleado){
                	eq 'empleado', solicitudRH.empleado
                }
                if(solicitudRH.empresa){
                    empresa{
                        idEq(solicitudRH.empresa.id)
                    }
                }
            }//if(solicitudRH)
        }
        solicitudesPorRangoDeFecha{ fechaEmpiezaRango, fechaTerminaRango ->
        	or {
        		and {
					ge 'fechaInicial', fechaEmpiezaRango
			    	le 'fechaInicial', fechaTerminaRango
        		}
        		and {
					ge 'fechaFinal', fechaEmpiezaRango
					le 'fechaFinal', fechaTerminaRango
        		}
        		and {
        			le 'fechaInicial', fechaEmpiezaRango
        			ge 'fechaFinal', fechaTerminaRango
        		}
        	}
        }//solicitudesPorRangoDeFecha
    }//named queries
    
    String toString () {
        return "Solicitud RH $folio"
    }
}
