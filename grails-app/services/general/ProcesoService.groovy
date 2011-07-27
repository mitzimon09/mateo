package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import interfaces.ProcesoServiceInterface

class ProcesoService implements ProcesoServiceInterface{

    def springSecurityService

    def enviar(Compra compra){
			compra.status = "ENVIADA"
    }
}
