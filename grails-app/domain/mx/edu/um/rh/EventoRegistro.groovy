package mx.edu.um.rh

class EventoRegistro {
    EmpleadoEvento empleadoEvento
    Date fecha = new Date()
    Boolean adentro = false
    
    static belongsTo = [EmpleadoEvento]

    static constraints = {
        empleadoEvento nullable: false
        adentro blank: false
    }
    
    static mapping = {
        table name:'eventoregistro', schema:'aron'
    }
    
    String toString() {
        return fecha
    }
}
