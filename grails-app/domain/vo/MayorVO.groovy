package vo

class MayorVO implements Comparable {
    String nombre
    String id_ctamayor

    int compareTo(other) {
        return id_ctamayor.comparteTo(other.id_ctamayor)
    }
}
