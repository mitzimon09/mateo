package mx.edu.um.rh

class EmpleadoEvento {
    Empleado empleado
    Evento evento
    Date entrada
    Date salida
    
    static belongsTo = [Empleado, Evento]
    
    static constraints = {
    }
}
