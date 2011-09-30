package mx.edu.um.rh

class Puesto {
    String nombre
    Integer maximo
    Integer minimo
    Seccion seccion

    static constraints = {
        nombre maxSize: 50
        maximo blank: false
        minimo blank: false
    }

    static mapping = {
        table name:'puesto', schema:'aron'
    }
    
    String toString() {
        return "$nombre"
    }
}
