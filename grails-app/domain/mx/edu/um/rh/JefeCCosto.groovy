package mx.edu.um.rh

import general.*
import mx.edu.um.contabilidad.*

class JefeCCosto {
	Ejercicio ejercicio
	CentroCosto ccosto
	Empleado jefe
	Empleado subjefe
	Usuario userCaptura
	Date fechaCaptura
	Empleado miJefe
	String status

    static constraints = {
    	ejercicio nullable: true
		ccosto nullable: true
		jefe blank: false
		subjefe nullable: true
		userCaptura blank: false
		fechaCaptura blank: false
		miJefe nullable: true
		status nullable: true
    }
    
    static mapping = {
        table name:'CAT_JEFES', schema:'aron'
    }
}
