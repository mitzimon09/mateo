package mx.edu.um.rh

import general.*
import mx.edu.um.Constantes

//@Audited
class Empleado {

    //static auditable = true

    String clave
    String nombre
    String apPaterno
    String apMaterno
    String nombreCompleto
    String genero
    Date fechaNacimiento
    String direccion
    String status
    Map perdeds
    ArrayList<EmpleadoEstudios> estudios
    
    //laborales
    Empresa empresa
    TipoEmpleado tipo
    String curp
    String rfc
    String cuenta
    String imms
    Integer escalafon
    Integer turno
    Date fechaAlta
    Date fechaBaja
    BigDecimal experienciaFueraUM
    String modalidad
    String ife
    String rango
    Boolean adventista
    Date fechaAntiguedadBase
    BigDecimal antiguedadBase
    BigDecimal antiguedadFiscal
    Grupo grupo
    
    //personales
    String padre
    String madre
    String estadoCivil
    String conyuge
    Date fechaMatrimonio
    Short finadoPadre
    Short finadoMadre
    String iglesia
    String responsabilidad
    String telefonoCasa
    String telefonocelular
    String email
    
    static transients = ['perdeds', 'estudios', 'nombreCompleto']
    
    static hasMany=[perdedsList:EmpleadoPerded, estudiosList: EmpleadoEstudios]//, empleado:EmpleadoPersonales, empleado:empleado]

    static constraints = {
        clave maxSize: 7, blank: false, unique: true
        nombre maxSize: 50, blank: false
        apPaterno maxSize: 30, blank: false
        apMaterno maxSize: 30, blank: false
        fechaNacimiento blank:false
        direccion maxSize: 100, blank: false
        genero maxSize: 2, blank: false, inList:[Constantes.GENERO_MASCULINO,Constantes.GENERO_FEMENINO]
        status maxSize: 2, blank: false, inList:[Constantes.STATUS_ACTIVO,Constantes.STATUS_INACTIVO] //No seria mejor crear otro enum para los estatus de Empleado?
        //Laborales
        cuenta maxSize: 16, nullable: true
        curp maxSize: 30, blank: false //No se debe validar el tamanio de la CURP aqui? Que yo sepa hay manera de hacer validaciones en el Constraints
        escalafon min: 0, blank: false 
        imms maxSize: 15, nullable: true
        rfc maxSize: 15, blank: false //Validar el tamanio del RFC tambien, no?
        modalidad maxSize: 2, blank: false, inList:[Constantes.MODALIDAD_APOYO,Constantes.MODALIDAD_DOCENTE]
        turno blank: false
        fechaAlta blank: false
        antiguedadBase blank: false
        antiguedadFiscal blank: false
        //
        adventista nullable: true
        experienciaFueraUM nullable: true
        fechaAntiguedadBase nullable: true
        fechaBaja nullable: true
        ife nullable: true
        rango nullable: true
        tipo blank: false
        grupo blank: false
        //Personales
        estadoCivil maxSize: 2, blank: false ,inList:[Constantes.ESTADO_CIVIL_SOLTERO, Constantes.ESTADO_CIVIL_CASADO, Constantes.ESTADO_CIVIL_DIVORSIADO, Constantes.ESTADO_CIVIL_VIUDO]
        madre maxSize: 50, blank: false
        padre maxSize: 50, blank: false
        conyuge maxSize: 50, nullable: true
        //
        fechaMatrimonio nullable: true
        finadoPadre nullable: true
        finadoMadre nullable: true
        iglesia nullable: true
        telefonoCasa nullable: true
	    telefonocelular nullable: true
	    email nullable: true
        responsabilidad nullable: true
    }

    static mapping={
        table name:'empleado_grails',schema:'aron'
        apPaterno column:'appaterno'
        apMaterno column:'apmaterno'
        fechaNacimiento column:'fechanacimiento'

        experienciaFueraUM column:'experiencia_fuera_um'
        tipo column:'id_tipoempleado'
        grupo column:'id_grupo'
        adventista type:'yes_no'

        estadoCivil column:'estadocivil'
        fechaMatrimonio column:'fechaMatrimonio'

        perdedsList cascade:'all-delete-orphan'
    }
    
    public String getNombreCompleto(){
        return "$nombre $apPaterno $apMaterno"
    }

    public String getDisponibilidad(){
        String disponiblidad = ""
        try{
            disponiblidad=this.turno.toString()
        }catch (NullPointerException npe){
            log.error("Empleado "+this.clave+","+npe);
            npe.printStackTrace();
        }
        return disponiblidad
    }
    /*
     *Checa si el escalafon es diferente de cero y null si es asi manda true
     **/
    public boolean isEscalafonValido(){
        boolean isValido=false
        if(this){
            if(this.escalafon!=null && !this.escalafon.equals(0)){
                isValido=true;
            }
        }
        return isValido
    }
    /*
     *Checa si el empleado tiene una cuenta diferente de vacio o null
     */
    public boolean isCuentaBancoValida(){
        boolean isValido=false
        if(this){
            if(this.cuenta!=null && !this.cuenta.trim().equals("")){
                isValido=true;
            }
        }
        return isValido
    }
    /*
     *Checa el tiempo que trabaja el empleado, dicho tiempo debe ser mayor a cero y diferente de null
     */
    public boolean isDisponibilidadValida(){
        boolean isValido=false
        if(this){
            if(this.turno!=null &&!this.turno.equals(0)){
                isValido=true
            }
        }
        return isValido
    }

    /*
     *Checa si el empleado tiene un grupo valido, dicho tiempo debe ser mayor a cero y diferente de null
     */
    public boolean isGrupoValido(){
        boolean isValido=false
        if(this){
            if(this.grupo!=null && this.grupo.id!=null){
                isValido=true;
            }
        }
        return isValido
    }

    /*
     *Este  Metodo carga un map con las perdeds del empleado cuando se llama empleado.perdeds
     *Map <Perded.id,EmpleadoPerded>
     */
    Map getPerdeds(){
        log.debug "getPerdeds"
        List perdedsEmpleado=perdedsList.toList()
        Map perdedsMap=new HashMap()
        for(EmpleadoPerded ep:perdedsList.toList()){
            //log.debug "guarda: key $ep.perded.id value $ep"
            perdedsMap.put(ep.perded.id.toString(),ep)
        }
        return perdedsMap
    }

    /*
     *Este  Metodo carga un map con los estudios del empleado cuando se llama empleado.estudios
    Map getEstudios(){
        log.debug "getEstudios"
        List estudiosEmpleado = estudios.toList()
        Map estudiosMap = new HashMap()
        for(EmpleadoEstudios ee : estudios.toList()){
            //log.debug "guarda: key $ep.perded.id value $ep"
            estudiosMap.put(ee.empleado.id.toString(),ee)
        }        
        return estudiosMap
    }
     */
    
    static namedQueries = {
        listaEmpleadosParametros { Empleado empleado, Empleado empleadoDos ->
            //Valida que el usuario no venga null
            if(empleado){
                if(empleadoDos){
                    if(empleado.clave && empleadoDos.clave){
                        //println "claveUno: ${empleado.clave} | claveDos: ${empleadoDos.clave}}"
                        between("clave", empleado.clave.toString(), empleadoDos.clave.toString())
                    }
                }
                //Valida el status
                if(empleado.status){
                    eq 'status',empleado.status
                }

                //if(empleado){
                        if(empleado.tipo){
                            tipo{
                                if(empleado.tipo.id){
                                    idEq(empleado.tipo.id)
                                }
                            }
                        }
                //}
                if(empleado.empresa){
                    empresa{
                        idEq(empleado.empresa.id)
                    }
                }
            }
        }
        listaConFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike('clave',filtro)
                ilike('nombre',filtro)
                ilike('apPaterno',filtro)
                ilike('apMaterno',filtro)
            }
        }
    }

//    def onSave = {
//        println "Nuevo Empleado dado de Alta"
//        // may optionally refer to newState map
//    }
//
//    def onDelete = {
//        println "Empleado dado de Baja"
//        // may optionally refer to oldState map
//    }
//
//    def onChange = { oldMap,newMap ->
//        println "Empleado Modificado ..."
//        oldMap.each({ key, oldVal ->
//            if(oldVal != newMap[key]) {
//                println " * ${key} cambiado a: ${oldVal} to " + newMap[key]
//            }
//        })
//    }
    
    String toString() {
    	return "$nombre $apPaterno $apMaterno"
    }
}
