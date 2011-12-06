package mx.edu.um.rh

class SolicitudPermiso extends SolicitudRH{
	boolean personal = false
	String motivo
	BigDecimal hospedaje = new BigDecimal(0.00)
	BigDecimal viaticos = new BigDecimal(0.00)
	BigDecimal otros = new BigDecimal(0.00)
	BigDecimal totalDeGastos = new BigDecimal(0.00)
	
	boolean actualizacion = false
	
	static mapping = {
		table name:'solicitud_permiso',schema:'aron'
	}
	
    static constraints = {
    	motivo blank:false
    }
}
