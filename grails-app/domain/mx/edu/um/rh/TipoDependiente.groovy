package mx.edu.um.rh

class TipoDependiente {
    String nombre

    static constraints = {
        nombre maxSize: 10
    }
    
    static mapping = {
        table name:'cat_relacion', schema:'aron'
        id column: 'id_relacion'
    }
}
