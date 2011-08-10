package general

class Concepto {
    String descripcion
    String status
    String nombre
    String tags

    static constraints = {
        descripcion blank:false, maxSize: 128
        status blank:false
        nombre blank: false
        tags blank: false
    }
    
    static mapping = {
        table name: 'nom_concepto', schema: 'mateo'
    }
}
