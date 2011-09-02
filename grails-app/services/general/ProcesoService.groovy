package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import mx.edu.um.Constantes

class ProcesoService {
    def springSecurityService

    def enviar(params){
              log.debug "status = " + params.status
			  params.status = Constantes.STATUS_ENVIADO
        return params
    }

    def aprobar(params){
			  params.status = Constantes.STATUS_APROBADO
        return params
    }

    def rechazar(params){
			  params.status = Constantes.STATUS_RECHAZADO
        return params
    }
    def cancelar(params){
    	params.status = Constantes.STATUS_CANCELADO
    	return params
    }
    
    def revisar(params){
    	params.status = Constantes.STATUS_REVISADO
    	return params
    }

    def autorizar(params){
    	params.status = Constantes.STATUS_AUTORIZADO
    	return params
    }
    
    
}
