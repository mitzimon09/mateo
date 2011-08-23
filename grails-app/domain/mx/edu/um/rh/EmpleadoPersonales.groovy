package mx.edu.um.rh

class EmpleadoPersonales {
    Empleado empleado
    String padre
    String madre
    String estadoCivil
    String conyuge
    Date fechaMatrimonio
    Short finadoPadre
    Short finadoMadre
    String iglesia
    String responsabilidad
    
    //static belongsTo=[Empleado]

    static constraints = {
        estadoCivil maxSize:2,blank:false
        madre maxSize:50,blank:false        
        padre maxSize:50,blank:false        
        conyuge maxSize:50, nullable:true
        //
        fechaMatrimonio nullable:true
        finadoPadre nullable:true
        finadoMadre nullable:true
        iglesia nullable:true
        responsabilidad nullable:true   
    }
    
    static mapping={
        table name:'empleadopersonales',schema:'aron'
        id generator:'foreign',params:[property:'empleado']
        estadoCivil column:'estadocivil'
        fechaMatrimonio column:'fechaMatrimonio'
        empleado joinTable:false
    }
}
