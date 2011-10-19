package mx.edu.um.rh
import general.*

class Observaciones {
	String observaciones
	Usuario usuario
	Date dateCreated

    static constraints = {
    	observaciones blank:false, maxSize:500
    	dateCreated blank: false
    }
}
