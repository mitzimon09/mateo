package vo

class CuentaVO implements Comparable {
    AuxiliarVO auxiliar
    MayorVO ctamayor
    String id_ejercicio
    String id_ccosto

    int compareTo(other) {
        return auxiliar.comparteTo(other.auxiliar)
    }
}
