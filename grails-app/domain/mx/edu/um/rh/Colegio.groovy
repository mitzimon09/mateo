package mx.edu.um.rh

class Colegio {
    String nombre
    String status

    static constraints = {
        nombre maxSize: 60
        status maxSize: 2
    }

    static mapping = {
        table name:'colegios', schema:'aron'
    }
}
