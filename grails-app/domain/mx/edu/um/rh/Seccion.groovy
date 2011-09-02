package mx.edu.um.rh

class Seccion {
    String descripcion
    Integer maximo
    Integer minimo
    Integer rango_academico
    Categoria categoria

    static constraints = {
        descripcion maxSize: 200
        maximo blank: false
        minimo blank: false
        rango_academico blank: false
    }
    
    static mapping = {
        table name:'seccion', schema:'aron'
    }
}
