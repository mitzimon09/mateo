package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(UniversidadController)
class UniversidadControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeUniversidads() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def universidad = new Universidad(
                nombre: 'test$i'
		    ).save()
    		assertNotNull universidad
        }

        def controller = new UniversidadController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/universidad/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.universidads

        assertEquals 10, model.universidads.size()
        assert 20 <= model.totalDeUniversidads
    }

    @Test
    void CrearUniversidad() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new UniversidadController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.universidad
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/universidad/ver')
    }
    
    @Test
    void ModificarUniversidad() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def universidad = new Universidad(
            nombre: 'test'
	    ).save()
		assertNotNull universidad
    		
        def controller = new UniversidadController()
        controller.springSecurityService = springSecurityService
        controller.params.id = universidad.id
        def model = controller.ver()
        assert model.universidad
        assertEquals "test", model.universidad.nombre

        controller.params.id = universidad.id
        model = controller.edita()
        assert model.universidad
        assertEquals "test", model.universidad.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/universidad/ver')

        universidad.refresh()
        assertEquals 'test1', universidad.nombre
    }
    
    @Test
    void EliminarUniversidad() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def universidad = new Universidad(
            nombre: 'test'
	    ).save()
		assertNotNull universidad
        
        def controller = new UniversidadController()
        controller.springSecurityService = springSecurityService
        controller.params.id = universidad.id
        def model = controller.ver()
        assert model.universidad
        assertEquals "test", model.universidad.nombre

        controller.params.id = universidad.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/universidad/lista")

        model = Universidad.get(universidad.id)
        assert !model
    }
}
