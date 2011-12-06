package mx.edu.um.contabilidad

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(CentroCostoController)
class CentroCostoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeCentroCostos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
		
		def ejercicio = new Ejercicio(
    	    id_Ejercicio: '0001-2012'
            , nombre: 'test$i' 
            , status: 'A'
        	, masc_Balance: 'test'
            , masc_Resultado: 'test'
            , masc_Auxiliar: 'test'
            , masc_CCosto: 'test'
            , nivel_Contable: 1
            , nivel_Tauxiliar: 1
	    ).save()
	 	assertNotNull ejercicio
	 	
	 	def key = new CCostoPK(
	 	    ejercicio: ejercicio
	 	    , idCCosto: 1
 	    ).save()
 	    assertNotNull key

        for(i in 1..20) {
        	def centroCosto = new CentroCosto(
                nombre: 'test$i' 
                , detalle: 'test'
            	, iniciales: 'test'
                , rfc: 'test'
                , key: key
                , ejercicio: ejercicio
		    ).save()
    	 	assertNotNull centroCosto
        }

        def controller = new CentroCostoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/centroCosto/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.centroCostos

        assertEquals 10, model.centroCostos.size()
        assert 20 <= model.totalDeCentroCostos
    }

    @Test
    void CrearCentroCosto() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new CentroCostoController()
        controller.springSecurityService = springSecurityService
        
        def ejercicio = new Ejercicio(
    	    id_Ejercicio: '0001-2012'
            , nombre: 'test$i' 
            , status: 'A'
        	, masc_Balance: 'test'
            , masc_Resultado: 'test'
            , masc_Auxiliar: 'test'
            , masc_CCosto: 'test'
            , nivel_Contable: 1
            , nivel_Tauxiliar: 1
	    ).save()
	 	assertNotNull ejercicio
	 	
	 	def key = new CCostoPK(
	 	    ejercicio: ejercicio
	 	    , idCCosto: 1
 	    ).save()
 	    assertNotNull key

        def model = controller.nuevo()
        assert model
        assert model.centroCosto
        controller.params.nombre = "test"
        controller.params.detalle = 'test'
        controller.params.iniciales = 'test'
        controller.params.rfc = 'test'
        controller.params.key = key
        controller.params.ejercicio = ejercicio
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/centroCosto/ver')
    }
    
    @Test
    void ModificarCentroCosto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def ejercicio = new Ejercicio(
    	    id_Ejercicio: '0001-2012'
            , nombre: 'test'
            , status: 'A'
        	, masc_Balance: 'test'
            , masc_Resultado: 'test'
            , masc_Auxiliar: 'test'
            , masc_CCosto: 'test'
            , nivel_Contable: 1
            , nivel_Tauxiliar: 1
	    ).save()
	 	assertNotNull ejercicio
	 	
	 	def key = new CCostoPK(
	 	    ejercicio: ejercicio
	 	    , idCCosto: 1
 	    ).save()
 	    assertNotNull key

    	def centroCosto = new CentroCosto(
            nombre: 'test' 
            , detalle: 'test'
        	, iniciales: 'test'
            , rfc: 'test'
            , key: key
            , ejercicio: ejercicio
	    ).save()
	 	assertNotNull centroCosto
    		
        def controller = new CentroCostoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = centroCosto.id
        def model = controller.ver()
        assert model.centroCosto
        assertEquals "test", model.centroCosto.nombre

        controller.params.id = centroCosto.id
        model = controller.edita()
        assert model.centroCosto
        assertEquals "test", model.centroCosto.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/centroCosto/ver')

        centroCosto.refresh()
        assertEquals 'test1', centroCosto.nombre
    }
    
    @Test
    void EliminarCentroCosto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
		def ejercicio = new Ejercicio(
    	    id_Ejercicio: '0001-2012'
            , nombre: 'test'
            , status: 'A'
        	, masc_Balance: 'test'
            , masc_Resultado: 'test'
            , masc_Auxiliar: 'test'
            , masc_CCosto: 'test'
            , nivel_Contable: 1
            , nivel_Tauxiliar: 1
	    ).save()
	 	assertNotNull ejercicio
	 	
	 	def key = new CCostoPK(
	 	    ejercicio: ejercicio
	 	    , idCCosto: 1
 	    ).save()
 	    assertNotNull key

    	def centroCosto = new CentroCosto(
            nombre: 'test' 
            , detalle: 'test'
        	, iniciales: 'test'
            , rfc: 'test'
            , key: key
            , ejercicio: ejercicio
	    ).save()
	 	assertNotNull centroCosto
        
        def controller = new CentroCostoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = centroCosto.id
        def model = controller.ver()
        assert model.centroCosto
        assertEquals "test", model.centroCosto.nombre

        controller.params.id = centroCosto.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/centroCosto/lista")

        model = CentroCosto.get(centroCosto.id)
        assert !model
    }
}
