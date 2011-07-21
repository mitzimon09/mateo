package general

class Compra {
	  /*
	  Status:
	  A) CREADA
	  B) ENVIADA
	  C) APROBADA
	  D) RECHAZADA
	  E) COMPRADA
	  F) ENTREGADA
	  G) CANCELADA	
	  */
	  String status = 'CREADA'
	  Set articulos
	  BigDecimal total = new BigDecimal("0.00")
	  Date fechaCreado = new Date()
	  Date fechaAutorizado = new Date()
	  Date fechaComprado = new Date()
	  Date fechaEntregado = new Date()
	  String observaciones
	  Empresa empresa

    static belongsTo = [Empresa]
	
	  static hasMany = [articulos: Articulo]
	
    static constraints = {
    	status (inList :['CREADA', 'ENVIADA', 'RECHAZADA', 'APROBADA', 'COMPRADA', 'ENTREGADA', 'CANCELADA'])
    	total (scale:2,precision:8,min:new BigDecimal('0'))
    	observaciones nullable:true, maxSize:128
    }
    
    String toString (){
      return "compras $status"
    }
}
