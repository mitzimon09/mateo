package mx.edu.um.rh

import mx.edu.um.contabilidad.Ejercicio
import mx.edu.um.contabilidad.CentroCosto

class EmpleadoPuesto {
    Empleado empleado
    String ejercicio
	String cCosto
	Puesto puesto
	BigDecimal turno
    String status = 'I'

    static belongsTo = [CentroCosto, Puesto, Ejercicio]
    
    static constraints = {
        status inList: ['I', 'A']
    }
    /*
    static mapping = {
        table name:'empleadopuestos', schema:'aron'
        empleado column: 'empleado_id'
        ejercicio column: 'id_ejercicio'
        cCosto column: 'id_ccosto'
        puesto column: 'puesto_id'
    }
    */
    static namedQueries = {
        listaEmpleadosPuestosParametros{EmpleadoPuesto empleadoPuesto ->
            if(empleadoPuesto){
                //Valida el status
                if(empleadoPuesto.status){                    
                    eq 'status', empleadoPuesto.status                    
                }
                if(empleadoPuesto.empresa){
                    empresa{
                        idEq(empleadoPuesto.empresa.id)
                    }
                }
                              
                if(empleadoPuesto.empleado ){
                    eq 'empleado', empleadoPuesto.empleado
                }
            }//if(empleadoPuesto)
        }
    }//named queries
}
