package mx.edu.um.contabilidad

import mx.edu.um.rh.CCostoPK

class CentroCosto {
    String nombre
    String detalle
    String iniciales
    String rfc
	CCostoPK key
	Ejercicio ejercicio

	//Set cuentas
	//Set polizas
	CentroCosto cCosto
	//static hasMany = [cuentas: Cuenta, polizas: Poliza]
	
    static constraints = {
        nombre maxSize: 255
        detalle maxSize: 255
        nombre maxSize: 255       
    }
    
    static mapping = {
        table name:'cont_ccosto', schema:'mateo'
        id column: 'key'
        //id composite:['CentroCosto.get(id)', 'ejercicio']
    }
}
