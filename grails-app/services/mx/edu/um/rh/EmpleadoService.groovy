package mx.edu.um.rh

import mx.edu.um.rh.interfaces.*
import general.*
import mx.edu.um.*

class EmpleadoService implements EmpleadoServiceInt {
    
    def empleadoPuestoService
    //static session = "session"

    Empleado getEmpleado(String clave) throws NullPointerException{
        log.debug "getEmpleadoByClave"
        def empleado=Empleado.findByClave(clave)
        if(!empleado){
            throw new NullPointerException("empleado.inexistente")
        }
        return empleado
    }

    List<Empleado> getEmpleadosByRango(String claveUno,String claveDos) throws NullPointerException{
        log.debug "getEmpleadosByRango"
        Empleado empleadoUno=new Empleado()
        Empleado empleadoDos=new Empleado()
        empleadoUno.clave=claveUno
        empleadoDos.clave=claveDos
        empleadoUno.status=Constantes.STATUS_ACTIVO
        empleadoDos.status=Constantes.STATUS_ACTIVO
        def empleados=Empleado.listaEmpleadosParametros(empleadoUno,empleadoDos)
        log.debug "EmpleadosByRango ${empleados.list().size()}"
        return empleados.list()
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
    
    List<Empleado> getEmpleadosByEmpleadoCCosto(Empleado empleado) throws NullPointerException{
        def empleadoPuesto = empleadoPuestoService.getEmpleadoPuestoByEmpleado(empleado)
        def empleadoPuestos = empleadoPuestoService.getEmpleadosByEmpresaAndCCosto(empleado.empresa, empleadoPuesto.cCosto)
        def empleados = new ArrayList<EmpleadoPuesto>()
        for (EmpleadoPuesto empleadoPuesto1 in empleadoPuestos){
        	empleados.add(empleadoPuesto1.empleado)
        }
        return empleados
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
    
    def getEmpleadoEvento(Empleado empleado, Evento evento) {
        log.debug "obtiene EmpleadoEvento"
        def eventoEmpleados = EmpleadoEvento.list()
        def empleadoEvento
        for(eventoEmpleado in eventoEmpleados) {
            if(eventoEmpleado.empleado == empleado && eventoEmpleado.evento == evento) {
                log.debug "la relación empleado-evento existe"
                empleadoEvento = eventoEmpleado
            }
        }
        if(empleadoEvento == null) {
            log.debug "creando relación empleado-evento"
            empleadoEvento = new EmpleadoEvento (
                empleado: empleado
                , evento: evento
            ).save()
        }
        return empleadoEvento
    }

    boolean addPercepcionToEmpleado(Empleado empleado, PerDed perded, BigDecimal importe, String tipoImporte, String atributos, boolean otorgado, boolean isEditableByNOM){
        EmpleadoPerded empleadoPerded = new EmpleadoPerded(
            perded : perded,
            empleado : empleado,
            importe : importe,
            tipoImporte : tipoImporte,
            atributos : atributos,
            otorgado : otorgado,
            isEditableByNOM : isEditableByNOM
        )

        empleado.addToPerdedsList(empleadoPerded)
        
        if(empleado.save()){
            log.debug "true"
            return true
        }
        else{
            log.debug "false"
            return false
        }
    }

    /**
     * Modifica una percepcion (EmpleadoPerded)
     * La percepcion (EmpleadoPerded) del Parametro debe ya traer asignados los cambios con los que se va a guardar
    **/
    boolean updatePercepcionFromEmpleado(EmpleadoPerded empleadoPerded){
        if(empleadoPerded.save()){
            return true
        }
        else{
            return false
        }
    }
    
    def eventosPorEmpleado(Empleado empleado) {
        def eventos = Evento.list()
        Map<String, String> asistidos = new TreeMap<String, String>()
        for(evento in eventos) {
            def empleadoEvento = EmpleadoEvento.findByEmpleadoAndEvento(empleado, evento)
            if(evento.status == Constantes.STATUS_CERRADO && empleadoEvento != null) {
                asistidos.put(evento.descripcion, empleadoEvento.status)
            }else{
                asistidos.put(evento.descripcion, "")
            }
        }
        return asistidos
    }

    /**
     * Elimina una percepcion (EmpleadoPerded)
    **/
    Boolean deletePercepcionFromEmpleado(Empleado empleado, EmpleadoPerded empleadoPerded){

        Boolean removio = empleado.removeFromPerdedsList(empleadoPerded)

        return removio
    }
}
