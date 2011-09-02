package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(NivelEstudiosController)
class NivelEstudiosControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeNivelEstudioss() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def nivelEstudios = new NivelEstudios(
                nombre: 'test$i'
		    ).save()
    		assertNotNull nivelEstudios
        }

        def controller = new NivelEstudiosController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/nivelEstudios/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.nivelEstudioss

        assertEquals 10, model.nivelEstudioss.size()
        assert 20 <= model.totalDeNivelEstudioss
    }

    @Test
    void CrearNivelEstudios() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new NivelEstudiosController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.nivelEstudios
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/nivelEstudios/ver')
    }
    
    @Test
    void ModificarNivelEstudios() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def nivelEstudios = new NivelEstudios(
            nombre: 'test'
	    ).save()
		assertNotNull nivelEstudios
    		
        def controller = new NivelEstudiosController()
        controller.springSecurityService = springSecurityService
        controller.params.id = nivelEstudios.id
        def model = controller.ver()
        assert model.nivelEstudios
        assertEquals "test", model.nivelEstudios.nombre

        controller.params.id = nivelEstudios.id
        model = controller.edita()
        assert model.nivelEstudios
        assertEquals "test", model.nivelEstudios.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/nivelEstudios/ver')

        nivelEstudios.refresh()
        assertEquals 'test1', nivelEstudios.nombre
    }
    
    @Test
    void EliminarNivelEstudios() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def nivelEstudios = new NivelEstudios(
            nombre: 'test'
	    ).save()
		assertNotNull nivelEstudios
        
        def controller = new NivelEstudiosController()
        controller.springSecurityService = springSecurityService
        controller.params.id = nivelEstudios.id
        def model = controller.ver()
        assert model.nivelEstudios
        assertEquals "test", model.nivelEstudios.nombre

        controller.params.id = nivelEstudios.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/nivelEstudios/lista")

        model = NivelEstudios.get(nivelEstudios.id)
        assert !model
    }
}
