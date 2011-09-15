package mx.edu.um.rh
import general.*
class Empleado {
    String clave
    String nombre
    String apPaterno
    String apMaterno
    String genero
    Date fechaNacimiento
    String direccion
    String status
    //Empresa empresa
    //EmpleadoPersonales empleadoPersonales
    //EmpleadoLaborales empleadoLaborales
    Map perdeds
    static transients = ['perdeds']
    static hasMany=[perdedsList:EmpleadoPerded]

    //static hasOne=[empleadoPersonales:EmpleadoPersonales,empleadoLaborales:EmpleadoLaborales]
    
    
    public String getNombreCompleto(){
        "$nombre $apPaterno $apMaterno"
    }
    
    public String getDisponibilidad(){
        String disponiblidad = ""
        try{            
            disponiblidad=this.empleadoLaborales.turno.toString()
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
        if(this.empleadoLaborales){
            if(this.empleadoLaborales.escalafon!=null && !this.empleadoLaborales.escalafon.equals(0)){
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
        if(this.empleadoLaborales){
            if(this.empleadoLaborales.cuenta!=null && !this.empleadoLaborales.cuenta.trim().equals("")){
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
        if(this.empleadoLaborales){
            if(this.empleadoLaborales.turno!=null &&!this.empleadoLaborales.turno.equals(0)){
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
        if(this.empleadoLaborales){
            if(this.empleadoLaborales.grupo!=null && this.empleadoLaborales.grupo.id!=null){
                isValido=true;
            }
        }
        return isValido
    }
    
    /*
     *Este  Metodo carga un map con las perdeds del empleado cuando se llama empleado.perdeds
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

    static constraints = {
        clave maxSize:7,blank:false,unique:true
        nombre maxSize:50,blank:false
        apPaterno maxSize:30,blank:false,column:'appaterno'
        apMaterno maxSize:30,blank:false,column:'apmaterno'
        fechaNacimiento blank:false,column:'fechanacimiento'
        direccion maxSize:100,blank:false
        genero maxSize:2,blank:false
        status maxSize:2,blank:false
    }
    
    static mapping={
        table name:'empleado',schema:'aron'
        apPaterno column:'appaterno'
        apMaterno column:'apmaterno'
        fechaNacimiento column:'fechanacimiento'        
    }   
    static namedQueries = {
        listaEmpleadosParametros{Empleado empleado, Empleado empleadoDos ->
            //Valida que el usuario no venga null
            if(empleado){
                if(empleadoDos){
                    if(empleado.clave && empleadoDos.clave){
                        between("clave", empleado.clave, empleadoDos.clave)
                    }
                }
                //Valida el status
                if(empleado.status){                    
                    eq 'status',empleado.status                    
                }               
                
                if(empleado.empleadoLaborales){
                    empleadoLaborales{
                        if(empleado.empleadoLaborales.tipo){
                            tipo{
                                if(empleado.empleadoLaborales.tipo.id){
                                    idEq(empleado.empleadoLaborales.tipo.id)   
                                }                            
                            }                            
                        }                        
                    }
                }                
//                if(empleado.empresa){
//                    empresa{
//                        idEq(empleado.empresa.id)
//                    }
//                }
            }
        }        
    }
}
