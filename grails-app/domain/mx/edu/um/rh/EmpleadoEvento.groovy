package mx.edu.um.rh

import mx.edu.um.Constantes

class EmpleadoEvento {
    Empleado empleado
    Evento evento
    Date entrada //lsita
    Date salida //lista
    String status
    
    static belongsTo = [Empleado, Evento]
    
    static constraints = {
    	empleado blank: false
    	evento blank: false
    	entrada blank: false
    	salida blank: false
    	status inList: [Constantes.STATUS_ASISTENCIA, Constantes.STATUS_INASISTENCIA, Constantes.STATUS_TARDANZA], nullable: true
    }
}
