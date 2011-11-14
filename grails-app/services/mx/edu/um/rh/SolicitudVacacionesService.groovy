package mx.edu.um.rh

import mx.edu.um.*
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudVacacionesService {
		def springSecurityService

    List<SolicitudVacaciones> getSolicitudesAnuales(Empleado empleado) throws NullPointerException{
    	Calendar yearInit = Calendar.getInstance()
		yearInit.set(yearInit.get(Calendar.YEAR), 0, 1, 0, 0)
		def date = yearInit.getTime()
		SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones()
		solicitudVacaciones.empleado = empleado
		solicitudVacaciones.fechaInicial = date
		def solicitudesVacaciones = SolicitudVacaciones.solicitudVacacionesAnuales(solicitudVacaciones)
		if(solicitudesVacaciones== null){
            throw new NullPointerException("solicitudesVacaciones inexistente")
        }
		return solicitudesVacaciones.list()
     }
}
