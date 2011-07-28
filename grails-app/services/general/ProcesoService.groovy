package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import interfaces.ProcesoServiceInterface
import general.Compra

class ProcesoService implements ProcesoServiceInterface{

    def springSecurityService

    Compra enviar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "ENVIADA"
        log.debug "compra + " + compra
        return compra
    }
}
