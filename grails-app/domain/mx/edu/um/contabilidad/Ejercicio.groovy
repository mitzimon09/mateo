package mx.edu.um.contabilidad

class Ejercicio {
    String idEjercicio
    String nombre
    String status = 'A'
	String masc_Balance
	String masc_Resultado
	String masc_Auxiliar
	String masc_CCosto
	Integer nivel_Contable
	Integer nivel_Tauxiliar

    static constraints = {
        idEjercicio unique: true
        nombre blank: false, maxSize: 64
        status inList:['A','I']
    }
    
/*
    static mapping = {
        table name:'cont_ejercicio', schema:'mateo'
        id_Ejercicio column: 'id_ejercicio'
        version false
        //id composite: ['id_Ejercicio'], generator: "assigned"
    }
    */
    String toString() {
        return "$idEjercicio"
    }
}

