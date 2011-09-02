package mx.edu.um.rh

import mx.edu.um.rh.interfaces.*
import general.*
import mx.edu.um.*

class EmpleadoService implements EmpleadoServiceInt {
    
    
    Empleado getEmpleado(String clave) throws NullPointerException{
        //log.debug "getEmpleadoByClave"
        def empleado=Empleado.findByClave(clave)
        //log.debug "Empleado: $empleado"
        if(!empleado){
            throw new NullPointerException("empleado.inexistente")
        }
        return empleado
    }
    
    List<Empleado> getEmpleadosByRangoEmpresaAndTipo(Empresa empresa,TipoEmpleado tipo,String claveUno,String claveDos) throws NullPointerException{
        Empleado empleado=new Empleado()
        Empleado empleadoDos=new Empleado()
        empleado.clave=claveUno
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
    
    List<Empleado> getEmpleadosByEmpresaAndTipo(Empresa empresa,TipoEmpleado tipo) throws NullPointerException{
        log.debug "getEmpleadosByEmpresaAndTipo $empresa.id $tipo.id"
        Empleado empleado=new Empleado()
        empleado.empresa=empresa
        //EmpleadoLaborales emplLaborales=new EmpleadoLaborales()
        //emplLaborales.tipo=tipo
        //empleado.empleadoLaborales=emplLaborales
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        log.debug "Empleados ${empleados.list().size()}"
        return empleados.list()
    }
    
    List<Empleado> getEmpleadosByEmpresa(Empresa empresa) throws NullPointerException{
        Empleado empleado=new Empleado()
        empleado.empresa=empresa
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
    
    List<Empleado> getEmpleadosByTipo(TipoEmpleado tipo) throws NullPointerException{
        Empleado empleado=new Empleado()
        empleado.tipo = tipo
        //EmpleadoLaborales emplLaborales=new EmpleadoLaborales()
        //emplLaborales.tipo=tipo
        //empleado.empleadoLaborales=emplLaborales
        //empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
    
    List<Empleado> getEmpleadosByInicioClave(Empresa empresa) throws NullPointerException{
        Empleado empleado=new Empleado()
        empleado.empresa=empresa
        empleado.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleado,null)
        return empleados.list()
    }
    
    /*
     *
     */
    Map<String,String> validaEmpleados(List empleados){
        Map empleadosConErrores=new HashMap()
        //valida si la lista es distinta de null
        if(empleados){
            for(Empleado empleado:empleados){
                //verifica si el empleado tiene un escalafon valido
                if(!empleado.isEscalafonValido()){
                    log.debug "Escalafon Invalido"
                    //esta seccion de codigo verifica si la llave ya a sido guardada si no la guarda si ya existe esa llave trae el valor y concatena la clave
                    if(empleadosConErrores.containsKey("empleado.escalafon.invalido")){
                        String tmp=empleadosConErrores.get("empleado.escalafon.invalido")
                        empleadosConErrores.put("empleado.escalafon.invalido",tmp+"@${empleado.clave}}")                        
                    }else{
                        empleadosConErrores.put("empleado.escalafon.invalido",empleado.clave)
                    }
                }
                //verifica si el empleado tiene una cuenta de banco valida
                if(!empleado.isCuentaBancoValida()){
                    log.debug "Cuenta Invalida"
                    if(empleadosConErrores.containsKey("empleado.cuentaBanco.invalido")){
                        String tmp=empleadosConErrores.get("empleado.cuentaBanco.invalido")
                        empleadosConErrores.put("empleado.cuentaBanco.invalido",tmp+"@${empleado.clave}}")                        
                    }else{
                        empleadosConErrores.put("empleado.cuentaBanco.invalido",empleado.clave)
                    }                    
                }
                //verifica si el empleado tiene un turno valido
                if(!empleado.isDisponibilidadValida()){
                    log.debug "Turno Invalido"
                    if(empleadosConErrores.containsKey("empleado.turno.invalido")){
                        String tmp=empleadosConErrores.get("empleado.turno.invalido")
                        empleadosConErrores.put("empleado.turno.invalido",tmp+"@${empleado.clave}}")                        
                    }else{
                        empleadosConErrores.put("empleado.turno.invalido",empleado.clave)
                    }                    
                }
                //verifica si el empleado tiene un grupo valido
                if(!empleado.isGrupoValido()){
                    log.debug "Grupo Invalido"
                    if(empleadosConErrores.containsKey("empleado.grupo.invalido")){
                        String tmp=empleadosConErrores.get("empleado.grupo.invalido")
                        empleadosConErrores.put("empleado.grupo.invalido",tmp+"@${empleado.clave}}")                        
                    }else{
                        empleadosConErrores.put("empleado.grupo.invalido",empleado.clave)
                    }                    
                }
            }
        }
        log.debug "errores ${empleadosConErrores}"
        return empleadosConErrores
    }    
}
