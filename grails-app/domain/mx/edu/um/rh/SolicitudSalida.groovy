package mx.edu.um.rh
import general.*

class SolicitudSalida {
	/*Date dateCreated
	String status = 'CR'
	Empresa empresa
	String observaciones
	Vacaciones vacaciones
	Empleado empleado
	Usuario usuario
	String folio*/
	
	Empleado empleado
	Integer diasVacaciones
	Integer primaVacacional
	Date fechaInicial
	String destino
	Integer kilometros
	String contacto
	Usuario user
	Date fechaCaptura
	Usuario recibeUser
	Date fechaRecibeRh
	Usuario rhUser
	Date fechaAutorizacionRh
	String status
	Integer visitaPadres
	Integer nacional
	Integer userPrimaVacacional
	Date fechaPrimaVacacional
	String folio
	String observacionesEmpleado
	String folioPago
	Date fechaFinal
	String contactoTelefono
	String contactoEmail
	Date fechaRecibeJefe
	Usuario jefeUserId
	String observacionesRh
	String furlough
	
	static belongsTo = {[Empresa:empresa, Empleado:empleado]}
	
    static constraints = {
    	/*status inList :['CR', 'EN', 'RE', 'AP', 'CO', 'EN', 'CA']
    	observaciones nullable: true, maxSize: 128
    	empresa blank:false
    	vacaciones nullable:true
    	empleado blank: false
    	folio blank:false, maxSize: 128
    	*/
    	
    	empleado blank:false
		diasVacaciones blank:false
		primaVacacional blank:false
		fechaInicial blank:false
		destino blank:false
		kilometros blank:false
		contacto nullable: true
		user blank:false
		fechaCaptura blank:false
		recibeUser nullable: true
		fechaRecibeRh nullable: true
		rhUser nullable: true
		fechaAutorizacionRh nullable: true
		status nullable: true
		visitaPadres blank:false
		nacional blank:false
		userPrimaVacacional nullable: true
		fechaPrimaVacacional nullable: true
		folio nullable: true
		observacionesEmpleado nullable: true
		folioPago nullable: true
		fechaFinal blank:false
		contactoTelefono nullable: true
		contactoEmail nullable: true
		fechaRecibeJefe nullable: true
		jefeUserId nullable: true
		observacionesRh nullable: true
		furlough nullable: true
			
    }
    
    String toString () {
        return "vacacion $status"
    }
    
    static mapping = {
        table name:'solicitud_salida', schema:'aron'
    }
    
    
   static namedQueries = {
    	SolicitudSalidaParametros{SolicitudSalida solicitudSalida, SolicitudSalida solicitudSalidaDos ->
            if(solicitudSalida){
                if(solicitudSalidaDos){
                    if(solicitudSalida.folio && solicitudSalidaDos.folio){
                        between("folio", solicitudSalida.folio, solicitudSalidaDos.folio)
                    }
                }
            }
        }
    }
}
