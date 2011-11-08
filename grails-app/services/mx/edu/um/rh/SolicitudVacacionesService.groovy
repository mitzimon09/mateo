package mx.edu.um.rh

import mx.edu.um.*
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class SolicitudVacacionesService {
		def springSecurityService

    List<SolicitudVacaciones> getSolicitudesRHByRangoDeFecha() throws NullPointerException{
    	Calendar yearInit = Calendar.getInstance()
		yearInit.set(yearInit.get(Calendar.YEAR), 0, 1, 0, 0)
		def date = yearInit.getTime()
		
		def solicitudesVacaciones = SolicitudVacaciones.solicitudVacacionesAnuales(date)
		return solicitudesVacaciones.list()
     }
}
