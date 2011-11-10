package mx.edu.um.rh

import general.Usuario

class DiasFeriados {
    String descripcion
    boolean diadado //se puede hacer boolean
    Date fecharegistro
    Date fecha
    Usuario user

    static constraints = {
        descripcion maxSize: 100
    }
    
    static mapping = {
        table name:'calendario', schema:'moises'
    }
}
