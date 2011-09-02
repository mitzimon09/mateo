package mx.edu.um.rh

class Evento {
    String nombre
    String descripcion
    Date hora_inicio
    Date hora_final
    Integer prorroga
    String status

    static constraints = {
        nombre maxSize: 50
        descripcion maxSize: 250
        prorroga maxSize: 10
        status maxSize: 250
    }
    
    static mapping = {
        table name:'evento', schema:'aron'
    }
}
