package general

class Articulo {
  	String descripcion
  	Integer cantidad = 0
  	BigDecimal precioUnitario = new BigDecimal("0.00")
    BigDecimal total = new BigDecimal("0.00")
    String status = "AGREGADO"
	  
    static belongsTo = [compra:Compra]
	  
    static constraints = {
    	descripcion(maxSize: 256, blank:false)
    	cantidad(min:1)
    	precioUnitario(sscale:2,precision:8,min:new BigDecimal('0'))
    	total(scale:2,precision:8,min:new BigDecimal('0'))
    	status(inList: ["AGREGADO", "COMPRADO", "ENTREGADO", "CANCELADO"])
    }
    
    String toString (){
      return descripcion
    }

}
