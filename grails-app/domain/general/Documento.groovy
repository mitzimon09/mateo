package general

import mx.edu.um.rh.Empleado

class Documento {
    Date fecha
    String descripcion
    String naturaleza
    String cheque //Cheque cheque
    String observaciones
    String status = 'C'
    BigDecimal importe = new BigDecimal("0.00")
    BigDecimal iva = new BigDecimal("0.00")
    //Date dateCreated
    //Date lastUpdated
    Empleado empleado
    Concepto concepto
    Usuario user

    static constraints = {
        descripcion blank: false, maxSize: 128
        naturaleza balnk: false, maxSize: 1
    	observaciones nullable: true, maxSize: 128
    	status inList: ["C","E","EL","RE","A"]
    }
    
    static mapping = {
        table name: 'nom_facturass', schema: 'mateo'
        //id column:  'select max(id) from nom_facturass' + 1
    }
}
