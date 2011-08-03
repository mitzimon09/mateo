package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

class ProcesoService implements ProcesoServiceInterface{

    def enviar(params){
			  params.status = "ENVIADA"
        return params
    }

    def aprobar(params){
			  params.status = "APROBADA"
        return params
    }

    def rechazar(params){
			  params.status = "RECHAZADA"
        return params
    }
    def cancelar(params){
    	params.status = "CANCELADA"
    	return params
    }
    
}
