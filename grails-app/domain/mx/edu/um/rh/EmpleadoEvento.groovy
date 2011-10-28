package mx.edu.um.rh

import mx.edu.um.Constantes

class EmpleadoEvento {
    Empleado empleado
    Evento evento
    String status
    Boolean adentro = false
    
    static belongsTo = [Empleado, Evento]
    
    static constraints = {
    	empleado blank: false
    	evento blank: false
    	status inList: [Constantes.STATUS_ASISTENCIA, Constantes.STATUS_INASISTENCIA, Constantes.STATUS_TARDANZA], nullable: true
    }
    
    static mapping={
        table name:'empleado_evento',schema:'aron'
    }
    
    String toString() {
        return "$evento-$empleado.clave"
    }
}
