package mx.edu.um.rh
import general.*

class SolicitudVacaciones extends SolicitudRH{
	
	Date fechaRecibeJefe
	Date fechaRecibeRh
	Date fechaAutorizacionRh
	Integer diasVacaciones
	
	Boolean primaVacacional = false
	BigDecimal userPrimaVacacional = new BigDecimal(0.00)
	Date fechaPrimaVacacional
	Boolean visitaPadres = false
	Boolean nacional = true
	String destino
	Integer kilometros
	
	
	String folioPago
	String status = "CR"
	String furlough
	
	static belongsTo = {[Empresa:empresa, Empleado:empleado]}
	
    static constraints = {
		fechaRecibeJefe nullable: true
		fechaRecibeRh nullable: true
		fechaAutorizacionRh nullable: true
		diasVacaciones blank:false

		userPrimaVacacional nullable: true
		fechaPrimaVacacional nullable: true
		destino blank:false
		kilometros blank:false

		
		observaciones nullable: true
		folioPago nullable: true

		furlough nullable: true
		status inList: ['CR', 'EN', 'RE', 'AP', 'CO', 'EN', 'CA', 'AU', 'SU', 'RV']
			
    }
    
    String toString () {
        return "vacacion $status"
    }
    
    static mapping = {
        table name:'solicitud_vacaciones', schema:'noe'
    }
    
    
   static namedQueries = {
    	solicitudVacacionesParametros{SolicitudVacaciones solicitudVacaciones, SolicitudVacaciones solicitudVacacionesDos ->
            if(solicitudVacaciones){
                if(solicitudVacacionesDos){
                    if(solicitudVacaciones.folio && solicitudVacacionesDos.folio){
                        between("folio", solicitudVacaciones.folio, solicitudVacacionesDos.folio)
                    }
                }
            }
        }
        
        solicitudVacacionesAnuales{ SolicitudVacaciones solicitudVacaciones->
                if (solicitudVacaciones.fechaInicial){
                	if(solicitudVacaciones.empleado){
				        solicitudVacaciones{
				        	and{
				            ge 'fechaFinal', solicitudVacaciones.fechaInicial
				            eq 'empleado', solicitudVacacionesempleado
				        	}
				        }
		            }
                }
        }
    }
}
