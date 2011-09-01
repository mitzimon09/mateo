package mx.edu.um.rh

class Universidad {
    String nombre

    static constraints = {
        nombre maxSize: 255
    }
    
    static mapping = {
        table name:'categoria', schema:'aron'
    }
}
