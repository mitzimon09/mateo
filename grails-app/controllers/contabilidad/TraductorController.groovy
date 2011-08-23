package contabilidad
 
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.sql.*
import vo.*
 
@Secured(['ROLE_EMP'])
class TraductorController {
 
    def index = { }
 
    def springSecurityService
 
    def test = {
        log.debug "Entro a traer el metodo test"
        String  id_ejercicio = '001-2011'
//        def eje = traerEjercicio(id_ejercicio)
 
        String id_libro = '21'
        String id_ccosto = '1.01'
        String folio = '00019'
//        def poliza = traerPoliza(id_ejercicio, id_libro, id_ccosto, folio)
 
        traerMovimientos(id_ejercicio, id_libro, id_ccosto, folio)
 
        //valida las cuentas
        //invoca metodo traductor
        //hacer integration Test
        //guardar en mateo (postgres)
    }
 
    def traerEjercicio = { id_ejercicio ->
        log.debug "Entro a traer el ejercicio"
 
        def sql = Sql.newInstance("jdbc:oracle:thin:@172.16.254.14:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
        String a = sql.rows("select * from mateo.cont_ejercicio where id_ejercicio = ?",[id_ejercicio])
 
        def ejercicio = new Ejercicio()
        ejercicio.balance = a.split(",")[3].split("=")[1]
        ejercicio.resultado = a.split(",")[4].split("=")[1]
        ejercicio.auxiliar = a.split(",")[5].split("=")[1]
 
        sql.close()
        return ejercicio
    }
 
    def traerPoliza = { id_ejercicio, id_libro, id_ccosto, folio ->
        log.debug "Entro a traer la poliza "
 
        def sql = Sql.newInstance("jdbc:oracle:thin:@172.16.254.14:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
        String b = sql.rows("select * from mateo.cont_poliza where id_ejercicio = ? and id_libro = ? and id_ccosto = ? and folio = ?",[id_ejercicio, id_libro, id_ccosto, folio])
 
        def poliza = new Poliza()
        poliza.id_ejercicio = b.split(",")[0].split("=")[1]
        poliza.id_libro = b.split(",")[1].split("=")[1]
        poliza.id_ccosto = b.split(",")[2].split("=")[1]
        poliza.folio = b.split(",")[3].split("=")[1]
        poliza.estatus = b.split(",")[6].split("=")[1]
 
        sql.close()
        return poliza
    }
 
    def traerMovimientos = { id_ejercicio, id_libro, id_ccosto, folio ->
        log.debug "Entro a traer los movimientos"
 
        def sql = Sql.newInstance("jdbc:oracle:thin:@172.16.254.14:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
 
        def movs = []
        sql.eachRow("select * from mateo.cont_movimiento where id_ejercicio = ? and id_libro = ? and id_ccosto = ? and folio = ?",[id_ejercicio, id_libro, id_ccosto, folio]){
            movs << it.toRowResult()
        }
 
        for(i in 0 .. movs.size()-1){
//            log.debug movs[i].toString().split(",")[11].split("=")[1].toString()
            def a = movs[i].toString().split(",")[11].split("=")[1].toString()
            //log.debug a +" "+ cargaCuentas(a)
//            log.debug a
            cargaCuentas(a)
        }
       
        sql.close()
    }
 
    def cargaCuentas = { x ->
       def usuario = springSecurityService.currentUser
       def retorno
       def ctas = Cuenta.findAllByOrganizacion(usuario.empresa.organizacion)
       for ( cuenta in ctas ) {
           if(x.equals(cuenta.numero))
               retorno = cuenta
       }
       render retorno.toString()
   }
}