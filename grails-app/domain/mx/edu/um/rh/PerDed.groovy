package mx.edu.um.rh

class PerDed {
    String clave
    String nombre
    String naturaleza
    String frecuenciaPago
    String status
    String formula
    Map atributosMap
    //Queda Pendiente lo que Antes era CentroCosto,CtaMayor,Concepto

    static transients = ['atributosMap']

    static hasMany=[atributos:PerDedAtributo]

    static constraints = {
        clave maxSize:6,unique:true,blank:false
        nombre maxSize:50,unique:true,blank:false
        naturaleza maxSize:1,blank:false
        status maxSize:1,blank:false
        formula maxSize:255,blank:false
    }
    static mapping={
        table name:'perdeds',schema:'aron'
        frecuenciaPago column:'periodo'
    }

    /**
     * Otiene un Map de los atributos de la Percpecion <Simbolo, Nombre>
    **/
    Map<String, Atributo> getAtributosMap(){
        //log.debug "getAtributos"
        List atributosList = atributos.toList()
        Map atributosMap = new HashMap()
        for(atr in atributosList){
            //log.debug "atributosMap: --> ${atr.atributo.nombre},${atr.atributo}"
            atributosMap.put(atr.atributo.nombre , atr.atributo)
        }
        return atributosMap
    }

    Boolean tieneAtributoPercepcionBasica(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("BASICA")) ? true : false
    }

    Boolean tieneAtributoPrevisionSocial(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("PREVISION_SOCIAL")) ? true : false
    }

    Boolean tieneAtributoDiezmo(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("DIEZMO")) ? true : false
    }

    Boolean tieneAtributoSobresueldo(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("SOBRESUELDO")) ? true : false
    }

    Boolean tieneAtributoISPT(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("ISPT")) ? true : false
    }

    Boolean tieneAtributoPNomina(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("NOMINA")) ? true : false
    }

    Boolean tieneAtributoBaseNomina(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("BASE_NOMINA")) ? true : false
    }

    Boolean tieneAtributoPorcentajeNomina(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("PORCENTEAJE_NOMINA")) ? true : false
    }

    Boolean tieneAtributoValor(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("VALOR")) ? true : false
    }

    Boolean tieneAtributoPalabraReservada(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("PALABRA_RESERVADA")) ? true : false
    }

    Boolean tieneAtributoPercepcionRH(){
        Map <String,Atributo> atributosMap = getAtributosMap()
        return atributosMap.containsValue(Atributo.findByNombre("PERCEPCION_RH")) ? true : false
    }

    String toString(){
        return "clave: ${clave} | nombre: ${nombre} | formula: ${formula} | naturaleza: ${naturaleza}"
    }

}
