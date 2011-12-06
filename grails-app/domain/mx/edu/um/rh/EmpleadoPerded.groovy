package mx.edu.um.rh

class EmpleadoPerded {
    Empleado empleado
    PerDed perded
    BigDecimal importe
    String tipoImporte ='$'
//    String atributos
    Boolean otorgado = false
    Boolean isEditableByNOM = false

    static belongsTo=[empleado: Empleado]
    
    static constraints = {
        empleado nullable:false
        perded nullable:false
        importe nullable:false
        tipoImporte maxSize:1, inList:['$','%'], nullable:false
//        atributos nullable:true
        otorgado nullable:true
        isEditableByNOM nullable:true
        
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
