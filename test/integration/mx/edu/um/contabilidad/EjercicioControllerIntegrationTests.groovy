package mx.edu.um.contabilidad

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EjercicioController)
class EjercicioControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEjercicios() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
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
        }

        def controller = new EjercicioController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/ejercicio/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.ejercicios

        assertEquals 10, model.ejercicios.size()
        assert 20 <= model.totalDeEjercicios
    }
    
    
    @Test
    void CrearEjercicio() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new EjercicioController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.ejercicio
        controller.params.id_Ejercicio = '0001-2012'
        controller.params.nombre = 'test'
        controller.params.status = 'A'
    	controller.params.masc_Balance = 'test'
        controller.params.masc_Resultado = 'test'
        controller.params.masc_Auxiliar = 'test'
        controller.params.masc_CCosto = 'test'
        controller.params.nivel_Contable = 1
        controller.params.nivel_Tauxiliar = 1
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/ejercicio/ver')
    }
    
    @Test
    void ModificarEjercicio() {
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
	 	
        def controller = new EjercicioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = ejercicio.id
        def model = controller.ver()
        assert model.ejercicio
        assertEquals "test", model.ejercicio.nombre

        controller.params.id = ejercicio.id
        model = controller.edita()
        assert model.ejercicio
        assertEquals "test", model.ejercicio.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/ejercicio/ver')

        ejercicio.refresh()
        assertEquals 'test1', ejercicio.nombre
    }
    
    @Test
    void EliminarEjercicio() {
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
        
        def controller = new EjercicioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = ejercicio.id
        def model = controller.ver()
        assert model.ejercicio
        assertEquals "test", model.ejercicio.nombre

        controller.params.id = ejercicio.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/ejercicio/lista")

        model = Ejercicio.get(ejercicio.id)
        assert !model
    }
}

