package vo

class AuxiliarVO implements Comparable {
    String nombre
    String id_auxiliar

    int compareTo(other) {
        return id_auxiliar.comparteTo(other.id_auxiliar)
    }
}
