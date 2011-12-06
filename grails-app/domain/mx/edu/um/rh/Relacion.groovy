package mx.edu.um.rh

class Relacion {
    String nombre

    static constraints = {
        nombre maxSize: 10, nullable: false
    }
    
    static mapping = {
        table name:'cat_relacion', schema:'aron'
        id column: 'id_relacion'
    }
    
    String toString() {
        return nombre
    }
}
