package mx.edu.um.rh

import mx.edu.um.rh.interfaces.*
import general.*
import mx.edu.um.*
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudRHService {
	def springSecurityService
	def usuarioEmpleadoService
	def empleadoService

    List<SolicitudRH> getSolicitudRHByEmpleado(Empleado empleado) throws NullPointerException{
        SolicitudRH solicitudRH = new SolicitudRH()
        solicitudRH.empleado = empleado
        solicitudRH.status = Constantes.STATUS_AUTORIZADO
        def solicitudesRH=SolicitudRH.listaSolicitudesParametros(solicitudRH)
        return solicitudesRH.list()        
    }
    
    @Secured(['ROLE_RHOPER'])
    List<SolicitudRH> getSolicitudesRHByRHOper(Empresa empresa) throws NullPointerException{
        SolicitudRH solicitudRH = new SolicitudRH()
        solicitudRH.empresa = empresa
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
    	if (SpringSecurityUtils.ifAnyGranted('ROLE_DIRRH')){
		    solicitudRH.status = Constantes.STATUS_AUTORIZADO
		}
		else if (SpringSecurityUtils.ifAnyGranted('ROLE_RHOPER')){
		    solicitudRH.status = Constantes.STATUS_APROBADO
		}
		else if (SpringSecurityUtils.ifAnyGranted('ROLE_CCP')){
			def empleadoUsuario = usuarioEmpleadoService.getUsuarioEmpleadoByUsername(currentUser.username+"")
			def empleado = empleadoUsuario.empleado
			def solicitudesRH = new ArrayList<SolicitudRH>()
			def empleados = empleadoService.getEmpleadosByEmpleadoCCosto(empleado)
			for (Empleado empleadotmp in empleados){
				def solicitudesTmp = getSolicitudRHByEmpleado(empleadotmp)
				for (SolicitudRH solicitud1 in solicitudesTmp){
					solicitudesRH.add(solicitud1)
				}
			}
			return solicitudesRH
		}
		else if (SpringSecurityUtils.ifAnyGranted('ROLE_USER')){
		}
		else{
		}
	    def solicitudesRH = SolicitudRH.listaSolicitudesParametros(solicitudRH)
	    if (!solicitudesRH){
	    	throw new NullPointerException("solicitudesRH.inexistente")
	    }
	    return solicitudesRH.list()
    }
    
}
