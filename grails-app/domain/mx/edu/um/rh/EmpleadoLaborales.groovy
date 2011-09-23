package mx.edu.um.rh

class EmpleadoLaborales {
    Empleado empleado
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
    
    //static belongsTo = [empleado:Empleado]
    
    static constraints = {
        cuenta maxSize:16, nullable:true
        curp maxSize:30
        escalafon blank:false
        imms maxSize:15, nullable:true
        rfc maxSize:15,blank:false
        modalidad maxSize:2,blank:false
        turno blank:false
        fechaAlta blank:false
        antiguedadBase blank:false
         adventista nullable:true
        antiguedadFiscal nullable:true
        cuenta nullable:true
        curp nullable:true
        experienciaFueraUM nullable:true
        fechaAntiguedadBase nullable:true
        fechaBaja nullable:true
        ife nullable:true
        imms nullable:true
        rango nullable:true
        tipo nullable:true
    }
    
    static mapping={
        table name:'empleadolaborales',schema:'aron'
        experienciaFueraUM column:'experiencia_fuera_um'
        tipo column:'id_tipoempleado'
        grupo column:'id_grupo'
        adventista type:'yes_no'
        id generator:'foreign',params:[property:'empleado']
    }
}
