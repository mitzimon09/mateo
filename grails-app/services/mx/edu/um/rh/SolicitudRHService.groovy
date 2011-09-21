package mx.edu.um.rh

class SolicitudRHService {

    List<SolicitudRH> getSolicitudRHByJefeCCosto(JefeCCosto jefeCCosto) throws NullPointerException{
        SolicitudRH solicitudRH = new SolicitudRH()
        solicitud.clave=claveUno
        empleadoDos.clave=claveDos
        //EmpleadoLaborales laborales=new EmpleadoLaborales()
        //laborales.tipo=tipo
        //empleado.empleadoLaborales=laborales
        empleado.empresa=empresa
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,empleadoDos)
        log.debug "Empleados ${empleados.list().size()}"
        return empleados.list()        
    }
}
