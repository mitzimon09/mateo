package mx.edu.um.rh

import mx.edu.um.Constantes

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
        status maxSize: 250, inList:[Constantes.STATUS_CREADO, Constantes.STATUS_INICIADO, Constantes.STATUS_TERMINADO]
    }
    
    static mapping = {
        table name:'evento', schema:'aron'
    }
}
