package general

class Permiso {
	String status = 'CREADA'
	Empresa empresa
	String observaciones
	
	static belongsTo = [Empresa]
	
    static constraints = {
    	status (inList :['CREADA', 'ENVIADA', 'RECHAZADA', 'APROBADA', 'COMPRADA', 'ENTREGADA', 'CANCELADA'])
    	observaciones nullable:true, maxSize:128
    }
    
    String toString (){
        return "compras $status"
    }
}
