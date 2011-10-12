package mx.edu.um.rh

import general.*

class Porcentaje {
    
    BigDecimal valor = new BigDecimal("0.00")
    BigDecimal valorDos = new BigDecimal("0.00")
    PerDed perded
    Grupo grupo
    //Empresa empresa //Posteriormente el Ejercicio_ID y CCosto_id se sutituiran por la empresa

    static constraints = {
        valor blank:false, maxSize:19, scale:6
        valorDos blank:true, maxSize:19, scale:6
        perded blank:false
        grupo blank:false
        //empresa blank:false
    }
    
    static mapping={
        table name:'PERDEDS_PORCENTAJES',schema:'aron'
        perded column:'perded_id'
        valorDos column:'valor_dos'
        grupo column:'grupo_id'
    }

    String toString() {
        "$valor $valorDos perded: ${perded.id} grupo: ${grupo.nombre}"
    }
}
