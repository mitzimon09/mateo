package contabilidad

import groovy.sql.*

class TraductorService {
//    def test(String mov, args){
//        def cuentas = new LinkedList()
//
//        log.debug("ntro ")
//        println "etnroroa "
//        println mov[0][2].toString()
//
//        if(!cuentas.get(mov[0][2]))
//            cuentas.add(mov[0][2])
//
//        cuentas.toArray()
//    }

    def traerEjercicio(){
        def sql = Sql.newInstance("jdbc:oracle:thin:@172.16.254.14:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
        def test = sql.eachRow("select * from mateo.cont_ejercicio where id_ejercicio = '001-2011'")
        log.debug ""+test
    }
}