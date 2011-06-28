package general

class Compra {
	String Status = 'CREADA'
	Set articulos
	BigDecimal total = new BigDecimal("0.00")
	
	static hasMany = [articulos: Articulo]
	
    static constraints = {
    	Status (inList :['CREADA', 'ENVIADA', 'RECHAZADA', 'APROBADA', 'COMPRADA', 'ENTREGADA'])
    	total(scale:2, precision:8)
    }
}
