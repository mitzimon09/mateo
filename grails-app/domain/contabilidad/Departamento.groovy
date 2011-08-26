package contabilidad

class Departamento {

    String nombre
    Cuenta cuenta

    static belongsTo = [Cuenta]

    static constraints = {
        nombre blank:false, maxSize:64
    }

    static mapping = {
        table 'departamentos'
    }

    String toString() {
        return nombre
    }
}