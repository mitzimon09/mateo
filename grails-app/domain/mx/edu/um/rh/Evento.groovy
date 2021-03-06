package mx.edu.um.rh

import mx.edu.um.Constantes

class Evento {
    String nombre
    String descripcion
    Date hora_inicio
    Date hora_final
    Integer prorroga = new Integer('0')
    String status = Constantes.STATUS_ABIERTO
    Integer tiempoTotal = new Integer('0')
    
    static constraints = {
        nombre maxSize: 50
        descripcion maxSize: 250
        prorroga maxSize: 10, min: 0
        status maxSize: 250, inList:[Constantes.STATUS_ABIERTO, Constantes.STATUS_INICIADO, Constantes.STATUS_CERRADO]
    }
    
    static mapping = {
        table name:'evento', schema:'aron'
    }
    
    String toString() {
        return "$nombre"
    }
}
