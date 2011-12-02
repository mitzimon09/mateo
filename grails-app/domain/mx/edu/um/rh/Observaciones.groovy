package mx.edu.um.rh
import general.*

class Observaciones {
	String observaciones
	Usuario usuario
	Date dateCreated
	SolicitudRH solicitudRH

	static belongsTo = [solicitudRH: SolicitudRH]

    static constraints = {
    	observaciones blank:false, maxSize:500
    	dateCreated blank: false
    }
    
    String toString () {
    	return observaciones
    }
}
