package general

class Concepto {
    String descripcion
    String status
    String nombre
    String tags

    static constraints = {
        descripcion blank:false, maxSize: 128
        status blank:false, maxSize: 2
        nombre blank: false
        tags blank: false
    }
    
    static mapping = {
        table name: 'cont_concepto', schema: 'mateo'
    }
}
