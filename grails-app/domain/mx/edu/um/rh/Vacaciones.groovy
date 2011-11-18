package mx.edu.um.rh
import general.*

class Vacaciones {
	Date dateCreated
	String descripcion
	int dias
	Usuario usuario
	Empleado empleado
	String status = "U"
	SolicitudVacaciones solicitudVacaciones
	Empresa empresa
	
	
	
	static belongsTo = {[Empleado:empleado, Empresa:empresa]}

    static constraints = {
    	descripcion maxSize: 100
    	dias maxsize: 99, minSize: -99
    	usuario blank: false
    	empleado blank: false
    	status inList: ["P", "U"]
    	solicitudVacaciones nullable:true
    }
    
    static mapping = {
        table name:'vacaciones', schema:'aron'
    }
    
    static namedQueries = {
        vacacionesParametros{Vacaciones vacaciones ->
        	if (vacaciones){
        		if(vacaciones.solicitudVacaciones){
        			eq 'solicitudVacaciones', vacaciones.solicitudVacaciones
        		}
        	}
        }
        diasTotales{Empleado empleado ->
        	if (empleado){
        		eq 'empleado', empleado
        	}
        }
        
    }
}
