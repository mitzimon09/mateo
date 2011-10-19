package mx.edu.um.rh

import general.*
class EmpleadoPuestoService {

    EmpleadoPuesto getEmpleadoPuestoByEmpleado(Empleado empleado) throws NullPointerException{
        EmpleadoPuesto empleadoPuesto = new EmpleadoPuesto()
        empleadoPuesto.empleado=empleado
        //empleado.status=Constantes.STATUS_ACTIVO
        def empleadoPuestos=EmpleadoPuesto.listaEmpleadosPuestosParametros(empleadoPuesto)
        if(!empleadoPuestos){
            throw new NullPointerException("empleadoPuestos.inexistentes")
        }
        return empleadoPuestos.list().get(0)
    }
    
    List<EmpleadoPuesto> getEmpleadosByEmpresaAndCCosto(Empresa empresa, String cCosto) throws NullPointerException{
        EmpleadoPuesto empleadoPuesto = new EmpleadoPuesto()
        empleadoPuesto.cCosto = cCosto
        def empleadoPuestos=EmpleadoPuesto.listaEmpleadosPuestosParametros(empleadoPuesto)
        if(!empleadoPuestos){
            throw new NullPointerException("empleadoPuestos.inexistentes")
        }
        return empleadoPuestos.list()
    }
    
}
