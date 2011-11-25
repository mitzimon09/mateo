package mx.edu.um.rh

class Atributo {
    String nombre
    String descripcion
    String simbolo
    
    static constraints = {
        nombre maxSize:50,blank:false
        descripcion maxSize:100,blank:false
        simbolo maxSize:2,blank:false
    }

    static mapping={
        sort "simbolo"
        table name:'atributo',schema:'aron'
    }

    String toString(){
        return "${nombre} | ${descripcion} | ${simbolo}"
    }
}
