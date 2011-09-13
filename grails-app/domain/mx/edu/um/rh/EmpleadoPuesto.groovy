package mx.edu.um.rh

import mx.edu.um.contabilidad.Ejercicio
import mx.edu.um.contabilidad.CentroCosto

class EmpleadoPuesto {
    Integer id
    Empleado empleado
    Ejercicio ejercicio
	CentroCosto centroCosto
	Puesto puesto
	BigDecimal turno
    String status = 'I'

    static belongsTo = [CentroCosto, Puesto, Ejercicio]
    
    static constraints = {
        status inList: ['I', 'A']
    }
    
    static mapping = {
        table name:'empleadopuestos', schema:'aron'
    }
}
