package general

import mx.edu.um.rh.Empleado
import mx.edu.um.rh.JefeCCosto

class UsuarioEmpleado {
	Usuario usuario
	Empleado empleado
	
	static belongsTo = {[Usuario:usuario, Empleado:empleado]}

    static constraints = {
    	usuario blank: false
    	empleado blank: false
    }
    
    static namedQueries = {
    	getEmpleado{UsuarioEmpleado usuarioEmpleado ->
            if(usuarioEmpleado){
                if(usuarioEmpleado.usuario){
                    eq 'usuario', usuarioEmpleado.usuario
                }
            }
        }
    }
}
