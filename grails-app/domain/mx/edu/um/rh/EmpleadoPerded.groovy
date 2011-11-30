package mx.edu.um.rh

class EmpleadoPerded {
    PerDed perded
    BigDecimal importe
    String tipoImporte
//    String atributos
    Boolean otorgado
    Boolean isEditableByNOM

    static belongsTo=[empleado: Empleado]
    
    static constraints = {
        tipoImporte maxSize:1
    }

    static mapping={
        table name:'empleado_perdeds',schema:'aron'
        isEditableByNOM column: 'editable_by_nom', type:'yes_no'
        otorgado type:'yes_no'
        //id generator:'native'
        perded column:'perded_id'
        empleado column: 'empleado_id'
    }

    String toString() {
    	return "perded: ${perded.clave} | empleado: ${empleado.clave} | importe: ${importe} | tipoImporte: ${tipoImporte} | otorgado: ${otorgado} | isEditableByNOM: ${isEditableByNOM}"
    }
}
