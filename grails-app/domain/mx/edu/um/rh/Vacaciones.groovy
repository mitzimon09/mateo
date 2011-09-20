package mx.edu.um.rh
import general.*

class Vacaciones {
	Date dateCreated
	String descripcion
	int dias
	Usuario usuario
	Empleado empleado
	String status = "U"
	SolicitudSalida solicitudSalida
	Empresa empresa
	
	static belongsTo = {[Empleado:empleado, Empresa:empresa]}

    static constraints = {
    	descripcion maxSize: 100
    	dias maxsize: 99, minSize: -99
    	usuario blank: false
    	empleado blank: false
    	status inList: ["P", "U"]
    	solicitudSalida nullable:true
    }
    
    static mapping = {
        table name:'vacaciones', schema:'aron'
    }
    
    static namedQueries = {
        vacacionesParametros{Vacaciones vacaciones ->
        	if (vacaciones){
        		if(vacaciones.solicitudSalida){
        			eq 'solicitudSalida', vacaciones.solicitudSalida
        		}
        	}
        }
    }
}
