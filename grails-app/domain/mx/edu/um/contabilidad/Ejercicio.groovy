package mx.edu.um.contabilidad

class Ejercicio {
    String id_Ejercicio
    String nombre
    String status = 'A'
	String masc_Balance
	String masc_Resultado
	String masc_Auxiliar
	String masc_CCosto
	Integer nivel_Contable
	Integer nivel_Tauxiliar

    static constraints = {
        nombre blank:false, maxSize:64
        status inList:['A','I']
    }

    static mapping = {
        table name:'cont_ejercicio', schema:'mateo'
        id column:'id_Ejercicio'
    }
    
    String toString() {
        return nombre
    }
}
