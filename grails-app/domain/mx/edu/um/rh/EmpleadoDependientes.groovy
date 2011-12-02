package mx.edu.um.rh

class EmpleadoDependientes {
    Empleado empleado
    String nombre
    Date bday
    Relacion relacion
    Colegio colegio
    String estudios
    Integer grado
    String matricula

    static constraints = {
        nombre nullable: false, maxSize: 255
        estudios nullable: true, maxSize: 100
        grado nullable: true
        matricula nullable: true, size: 7..7
    }
    
    static mapping = {
        table name:'empleado_dependientes', schema:'aron'
        version false
    }
    
    String toString() {
         return "$empleado | $nombre"
    }
}
