package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(TipoDependienteController)
class TipoDependienteControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeTipoDependientes() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def tipoDependiente = new TipoDependiente(
                nombre: 'test$i'
		    ).save()
    		assertNotNull tipoDependiente
        }

        def controller = new TipoDependienteController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/tipoDependiente/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.tipoDependientes

        assertEquals 10, model.tipoDependientes.size()
        assert 20 <= model.totalDeTipoDependientes
    }

    @Test
    void CrearTipoDependiente() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new TipoDependienteController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.tipoDependiente
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/tipoDependiente/ver')
    }
    
    @Test
    void ModificarTipoDependiente() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def tipoDependiente = new TipoDependiente(
            nombre: 'test'
	    ).save()
		assertNotNull tipoDependiente
    		
        def controller = new TipoDependienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoDependiente.id
        def model = controller.ver()
        assert model.tipoDependiente
        assertEquals "test", model.tipoDependiente.nombre

        controller.params.id = tipoDependiente.id
        model = controller.edita()
        assert model.tipoDependiente
        assertEquals "test", model.tipoDependiente.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/tipoDependiente/ver')

        tipoDependiente.refresh()
        assertEquals 'test1', tipoDependiente.nombre
    }
    
    @Test
    void EliminarTipoDependiente() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def tipoDependiente = new TipoDependiente(
            nombre: 'test'
	    ).save()
		assertNotNull tipoDependiente
        
        def controller = new TipoDependienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoDependiente.id
        def model = controller.ver()
        assert model.tipoDependiente
        assertEquals "test", model.tipoDependiente.nombre

        controller.params.id = tipoDependiente.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/tipoDependiente/lista")

        model = TipoDependiente.get(tipoDependiente.id)
        assert !model
    }
}
