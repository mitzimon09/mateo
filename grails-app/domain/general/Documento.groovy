package general

class Documento {
    Date fecha
    String descripcion
    String naturaleza
    String cheque //Cheque cheque
    String observaciones
    String status = 'CREADO'
    BigDecimal importe = new BigDecimal("0.00")
    BigDecimal iva = new BigDecimal("0.00")
    Date dateCreated
    Date lastUpdated
    Empleado empleado
    Concepto concepto
    Usuario usuario

    static constraints = {
        descripcion blank: false, maxSize: 128
        naturaleza balnk: false, maxSize: 10
    	observaciones nullable: true, maxSize: 128
    	status inList: ["CREADO"]
    }
    
    static mapping = {
        table name: 'nom_facturass', schema: 'mateo'
    }
}
