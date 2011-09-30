package mx.edu.um.rh

import general.*
import mx.edu.um.contabilidad.*

class JefeCCosto {
	String ccosto
	String ejercicio
	Empleado jefe
	Empleado subjefe
	Usuario userCaptura
	Date fechaCaptura
	Empleado miJefe
	String status

    static constraints = {
		ccosto nullable: true
		ejercicio nullable:true
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
