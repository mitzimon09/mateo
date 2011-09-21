package mx.edu.um.contabilidad

class CentroCosto {
    String nombre
    String detalle
    String iniciales
    String rfc
	CCostoPK key
	Ejercicio ejercicio

    static constraints = {
        nombre maxSize: 255
        detalle maxSize: 255
        rfc nullable: true
        ejercicio nullable: false
        key nullable: false
    }
    
/*    static mapping = {
        table name:'cont_ccosto', schema:'mateo'
        id column: 'key'
        //id composite:['CentroCosto.get(id)', 'ejercicio']
    }
*/
    String toString() {
        return "$key"
    }
}
