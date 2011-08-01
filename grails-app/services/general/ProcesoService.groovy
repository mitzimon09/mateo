package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

class ProcesoService implements ProcesoServiceInterface{

    def enviar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "ENVIADA"
        log.debug "compra + " + compra
        return compra
    }

    def aprobar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "APROBADA"
        log.debug "compra + " + compra
        return compra
    }

    def rechazar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "RECHAZADA"
        log.debug "compra + " + compra
        return compra
    }
    def cancelar(Compra compra){
    	compra.status = "CANCELADA"
    	return compra
    }
    
}
