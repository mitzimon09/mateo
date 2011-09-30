package mx.edu.um.rh

import general.*

class SolicitudRH {
	Empleado empleado
	Empresa empresa
	Date fechaInicial
	Date fechaFinal
	Usuario usuarioCrea
	Date dateCreated
	Usuario usuarioRecibe
	Date fechaRecibe
	Usuario usuarioAutoriza
	Date fechaAutoriza
	String observaciones
	String telContacto
	String email
	SolicitudSalida solicitudSalida
	Vacaciones vacaciones
	String status = "CR"
	JefeCCosto jefeCCosto
	
	static belongsTo = {[Empleado:empleado, Empresa:empresa]}

    static constraints = {
    	empleado blank:false
		fechaInicial blank: false
		fechaFinal blank: false
		usuarioCrea blank:false
		dateCreated blank: false
		usuarioRecibe nullable: true
		fechaRecibe nullable: true
		usuarioAutoriza nullable: true
		fechaAutoriza nullable: true
		observaciones nullable: true
		telContacto nullable: true
		email nullable: true
		solicitudSalida nullable: true
		vacaciones nullable: true
		status inList: ['CR', 'EN', 'RE', 'AP', 'CO', 'EN', 'CA', 'AU']
		jefeCCosto nullable: true
    }
    
    static namedQueries = {
        listaSolicitudesParametros{SolicitudRH solicitudRH ->
            if(solicitudRH){
                //Valida el status
                if(solicitudRH.status){                    
                    eq 'status', solicitudRH.status                    
                }
                if(solicitudRH.empresa){
                    empresa{
                        idEq(solicitudRH.empresa.id)
                    }
                }
                              
                if(solicitudRH.empleado ){
                    eq 'jefeCCosto', solicitudRH.empleado
                }
            }//if(solicitudRH)
        }
    }//named queries
    
}
