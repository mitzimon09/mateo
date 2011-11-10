package mx.edu.um.rh

class Categoria {
    String nombre

    static constraints = {
        nombre maxSize: 255
    }
    
    static mapping = {
        table name:'categoria', schema:'aron'
    }
    
    String toString() {
        return "$nombre"
    }
}
