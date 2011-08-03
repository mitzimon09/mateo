package general

class Cheque {
	String status = 'CREADA'
	Empresa empresa
	
	static belongsTo = [Empresa]
	
    static constraints = {
    	status (inList :['CREADA', 'ENVIADA', 'RECHAZADA', 'APROBADA', 'COMPRADA', 'ENTREGADA', 'CANCELADA'])
    }
    
    String toString (){
        return "compras $status"
    }
}
