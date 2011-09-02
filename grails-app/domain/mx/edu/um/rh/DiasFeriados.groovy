package mx.edu.um.rh

import general.Usuario

class DiasFeriados {
    String descripcion
    char diadado
    Date fecharegistro
    Date fecha
    Usuario user

    static constraints = {
        descripcion maxSize: 100
        diadado maxSize: 1
    }
    
    static mapping = {
        table name:'calendario', schema:'moises'
    }
    
}
