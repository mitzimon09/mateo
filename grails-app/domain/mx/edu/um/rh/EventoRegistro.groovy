package mx.edu.um.rh

class EventoRegistro {
    Empleado empleado
    Date fecha

    static constraints = {
        empleado nullable: false
        fecha nullable: false
    }
    
    static mapping = {
        table name:'eventoregistro', schema:'aron'
    }
}
