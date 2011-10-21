package mx.edu.um.rh

class SolicitudPermiso extends SolicitudRH{
	String motivo
	BigDecimal hospedaje = new BigDecimal(0.00)
	BigDecimal viaticos = new BigDecimal(0.00)
	BigDecimal otros = new BigDecimal(0.00)
	BigDecimal totalDeGastos = new BigDecimal(0.00)
	
	boolean actualizacion
	
    static constraints = {
    	motivo blank:false
    }
}
