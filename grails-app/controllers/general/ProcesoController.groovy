package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

//@Secured(['ROLE_EMP'])
class ProcesoController {

    def springSecurityService

    def index = { }
    
    @Secured(['ROLE_EMP'])
    def enviar = {
    	//log.debug "user" + springSecurityService.currentUser
    	//log.debug "user" + springSecurityService.currentUser.authorities
    	//(SpringSecurityUtils.ifAnyGranted('ROLE_EMP')) {
			def compra = Compra.get(params.id)
			if (compra){
				if(compra.status.equals("CREADA")){
					compra.status = "ENVIADA"
					compra.save(flush:true)
					redirect(action: "lista")
				}
				else {
					flash.message = message(code: 'compra.status.message5', args: [message(code: 'compra.label', default: 'Compra'), params.id])
			        redirect(action: "lista")
				}
			}
		//}
    }
}
