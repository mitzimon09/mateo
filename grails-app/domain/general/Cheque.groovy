package general

class Cheque {
	String status = 'CR'
	Empresa empresa
	String observaciones
	
	static belongsTo = [Empresa]
	
    static constraints = {
    	status inList: ['CR', 'EN', 'RE', 'AP', 'CO', 'EN', 'CA']
    	observaciones nullable: true, maxSize: 128
    }
    
    String toString () {
        return "compras $status"
    }
}
