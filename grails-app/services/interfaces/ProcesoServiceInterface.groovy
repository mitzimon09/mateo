package interfaces

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import interfaces.ProcesoServiceInterface
import general.Compra

interface ProcesoServiceInterface {

    def springSecurityService

    Compra enviar(Compra compra);
    
}
