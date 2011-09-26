package mx.edu.um.rh

class NivelEstudios {
    String nombre

    static constraints = {
        nombre maxSize: 60
    }
    
    static mapping = {
        table name:'cat_nivel_estudios', schema:'aron'
    }
}
