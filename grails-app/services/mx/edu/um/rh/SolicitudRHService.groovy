package mx.edu.um.rh

import mx.edu.um.rh.interfaces.*
import general.*
import mx.edu.um.*
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudRHService {
	def springSecurityService

    List<SolicitudRH> getSolicitudRHByEmpleadoCCosto(Empleado empleado) throws NullPointerException{
        SolicitudRH solicitudRH = new SolicitudRH()
        solicitudRH.empleado = empleado
        solicitudRH.status = 'EN'
        def solicitudesRH=SolicitudRH.listaSolicitudesParametros(solicitudRH)
        log.debug "SolicitudesRH ${solicitudesRH.list().size()}"
        return solicitudesRH.list()        
    }
    
    @Secured(['ROLE_RHOPER'])
    List<SolicitudRH> getSolicitudesRHByRHOper(Empresa empresa) throws NullPointerException{
        SolicitudRH solicitudRH = new SolicitudRH()
        solicitudRH.empresa=empresa
        solicitudRH.status = Constantes.STATUS_APROBADO
        def solicitudesRH = SolicitudRH.listaSolicitudesParametros(solicitudRH)
        if (!solicitudesRH){
        	throw new NullPointerException("solicitudesRH.inexistente")
        }
        return solicitudesRH.list()        
    }
    
    @Secured(['ROLE_DIRRH'])
    List<SolicitudRH> getSolicitudesRHByRol() throws NullPointerException{
    	def currentUser = springSecurityService.currentUser
		    SolicitudRH solicitudRH = new SolicitudRH()
		    solicitudRH.empresa=currentUser.empresa
    	if (currentUser.authorities.equals('ROLE_DIRRH')){
		    solicitudRH.status = Constantes.STATUS_AUTORIZADO
		}
		else if (currentUser.authorities.equals('ROLE_RHOPER')){
		    solicitudRH.status = Constantes.STATUS_APROBADO
		}
		else if (currentUser.authorities == 'ROLE_USER'){
			
		}
		else{
			log.debug ">__<"
			log.debug currentUser.authorities
			log.debug currentUser.username
		}
	    def solicitudesRH = SolicitudRH.listaSolicitudesParametros(solicitudRH)
	    if (!solicitudesRH){
	    	throw new NullPointerException("solicitudesRH.inexistente")
	    }
	    return solicitudesRH.list()
    }
    
}
