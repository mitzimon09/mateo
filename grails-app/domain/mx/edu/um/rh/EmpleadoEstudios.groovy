package mx.edu.um.rh

import general.Usuario
import mx.edu.um.Constantes

class EmpleadoEstudios {
    Empleado empleado
    String nombre_estudios
    Boolean titulado
    Date fecha_titulacion
    String status
    NivelEstudios nivel_estudios
    Usuario user_captura
    Date fecha_captura

    static constraints = {
        fecha_titulacion nullable: true
        status inList:[Constantes.STATUS_ACTIVO, Constantes.STATUS_INACTIVO]
    }
    
    static mapping = {
        table name:'empleado_estudios', schema:'aron'
        version false
    }
}
