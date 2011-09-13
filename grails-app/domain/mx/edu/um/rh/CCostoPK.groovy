package mx.edu.um.rh

import mx.edu.um.contabilidad.Ejercicio

class CCostoPK {
    Ejercicio ejercicio
    String idCCosto

    String toString() {
        return "$idCCosto$ejercicio"
    }
}
