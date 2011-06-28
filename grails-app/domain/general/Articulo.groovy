package general

class Articulo {
	String descripcion
	Integer cantidad = 0
	BigDecimal precioUnitario = new BigDecimal("0.00")
	BigDecimal total = new BigDecimal("0.00")
	
    static constraints = {
    	descripcion(maxSize: 256, blank:false)
    	cantidad(min:1)
    	precioUnitario(scale:2, precision:8)
    	total(scale:2, precision:8)
    }
}
