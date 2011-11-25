package mx.edu.um.rh

class PerDedAtributo {

    Atributo atributo

    static belongsTo=[perded : PerDed]

    static constraints = {
    }

    static mapping = {
        table name:'perded_atributo',schema:'aron'
    }
}
