package general

class Compra {
  String folio
	String status = 'CREADA'
	Set articulos
	BigDecimal total = new BigDecimal("0.00")
	Date fechaCreado = new Date()
	Date fechaAutorizado = new Date()
	Date fechaComprado = new Date()
	Date fechaEntregado = new Date()
	
	static hasMany = [articulos: Articulo]
	
    static constraints = {
      folio unique:true, nullable:false
    	status (inList :['CREADA', 'ENVIADA', 'RECHAZADA', 'APROBADA', 'COMPRADA', 'ENTREGADA'])
    	total (scale:2,precision:8,min:new BigDecimal('0'))
    }
    
    String toString (){
      return folio
    }
}
