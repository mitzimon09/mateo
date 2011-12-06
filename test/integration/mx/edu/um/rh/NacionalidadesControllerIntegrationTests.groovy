package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(NacionalidadesController)
class NacionalidadesControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeNacionalidadess() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def nacionalidades = new Nacionalidades(
                nombre: 'test$i' 
		    ).save()
    		assertNotNull nacionalidades
        }

        def controller = new NacionalidadesController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/nacionalidades/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.nacionalidadess

        assertEquals 10, model.nacionalidadess.size()
        assert 20 <= model.totalDeNacionalidadess
    }

    @Test
    void CrearNacionalidades() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new NacionalidadesController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.nacionalidades
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/nacionalidades/ver')
    }
    
    @Test
    void ModificarNacionalidades() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def nacionalidades = new Nacionalidades(
            nombre: 'test'
	    ).save()
		assertNotNull nacionalidades
    		
        def controller = new NacionalidadesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = nacionalidades.id
        def model = controller.ver()
        assert model.nacionalidades
        assertEquals "test", model.nacionalidades.nombre

        controller.params.id = nacionalidades.id
        model = controller.edita()
        assert model.nacionalidades
        assertEquals "test", model.nacionalidades.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/nacionalidades/ver')

        nacionalidades.refresh()
        assertEquals 'test1', nacionalidades.nombre
    }
    
    @Test
    void EliminarNacionalidades() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def nacionalidades = new Nacionalidades(
            nombre: 'test'
	    ).save()
		assertNotNull nacionalidades
        
        def controller = new NacionalidadesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = nacionalidades.id
        def model = controller.ver()
        assert model.nacionalidades
        assertEquals "test", model.nacionalidades.nombre

        controller.params.id = nacionalidades.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/nacionalidades/lista")

        model = Nacionalidades.get(nacionalidades.id)
        assert !model
    }
}
