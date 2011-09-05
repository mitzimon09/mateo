package contabilidad

import general.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import groovy.sql.Sql

@TestFor(TraductorController)
class TraductorControllerIntegrationTests extends BaseIntegrationTest{

    def springSecurityService

//    @Test
//    void debieraLeer() {
//        def controller = new TraductorController()
//        controller.leer()
//        assertEquals "<table><tbody><tr><td>[ID_EJERCICIO:001-2011, ID_LIBRO:30, ID_CCOSTO:1.01, FOLIO:30234, NUMMOVTO:1, FECHA:2011-02-11 00:00:00.0, DESCRIPCION:9900679-ART.LIMPIEZA, IMPORTE:1144.03, NATURALEZA:D, REFERENCIA:[null], REFERENCIA2:9900679, ID_CTAMAYORM:2.4.07, ID_CCOSTOM:1.01.3.02.04, ID_AUXILIARM:0000000, STATUS:A, ID_EJERCICIOM:001-2011, ID_EJERCICIOM2:001-2011, ID_EJERCICIOM3:001-2011, TIPO_CUENTA:R, ID_EJERCICIO2:001-2011, CONCEPTO_ID:20]</td></tr></tbody></table>", controller.response.contentAsString
//        
//    }
    
    @Test
    void debieraCargarCuenta(){
        authenticateAdmin()
        def organizacion = new Organizacion (
            codigo: 'TEST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TEST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        
        def cuenta = new Cuenta(
            codigo: '110103'
            , numero: '1.1.01.04'
            , descripcion: 'CAJA BONOS GASOLINA'
            , organizacion: organizacion
        ).save()
        
        def usuario = springSecurityService.currentUser
        usuario.empresa = empresa

        def controller = new TraductorController()
        controller.springSecurityService = springSecurityService
        controller.cargacuentas(cuenta.numero)
        //controller.index()
        
        assertEquals "1.1.01.04 | 110103", controller.response.contentAsString
    }
}
