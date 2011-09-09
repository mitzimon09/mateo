package contabilidad
import general.Organizacion

class Departamento {

    String nombre
    Cuenta cuenta
    Organizacion organizacion

    static belongsTo = [cuenta : Cuenta, organizacion : Organizacion]
    
    

    static constraints = {
        nombre blank:false, maxSize:64
    }

    static mapping = {
        table 'departamentos'
        cuenta cascade:'all'
    }

    String toString() {
        return nombre
    }
}