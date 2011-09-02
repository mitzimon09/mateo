package mx.edu.um.rh

class Nacionalidades {
    String nombre

    static constraints = {
        nombre maxSize: 255
    }
    
    static mapping={
        table name:'nacionalidades',schema:'aron'
    }
}
