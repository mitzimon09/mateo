package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import general.interfaces.ProcesoServiceInterface

class ProcesoService implements ProcesoServiceInterface{

    def springSecurityService

    def enviar(Compra compra){
			compra.status = "ENVIADA"
			return compra
    }
    def cancelar(Compra compra){
    	compra.status = "CANCELADA"
    	return compra
    }
    
}
