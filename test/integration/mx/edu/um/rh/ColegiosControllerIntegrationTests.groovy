package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ColegioController)
class ColegiosControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeColegios() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def colegio = new Colegio(
                nombre: 'test$i'
                , status: '00'
		    ).save()
    		assertNotNull colegio
        }

        def controller = new ColegioController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/colegio/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.colegios

        assertEquals 10, model.colegios.size()
        assert 20 <= model.totalDeColegios
    }

    @Test
    void CrearColegio() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new ColegioController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.colegio
        controller.params.nombre = "test"
        controller.params.status = '00'
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/colegio/ver')
    }
    
    @Test
    void ModificarColegio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def colegio = new Colegio(
            nombre: 'test'
            , status: '00'
	    ).save()
		assertNotNull colegio
    		
        def controller = new ColegioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = colegio.id
        def model = controller.ver()
        assert model.colegio
        assertEquals "test", model.colegio.nombre

        controller.params.id = colegio.id
        model = controller.edita()
        assert model.colegio
        assertEquals "test", model.colegio.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/colegio/ver')

        colegio.refresh()
        assertEquals 'test1', colegio.nombre
    }
    
    @Test
    void EliminarColegio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def colegio = new Colegio(
            nombre: 'test'
            , status: '00'
	    ).save()
		assertNotNull colegio
        
        def controller = new ColegioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = colegio.id
        def model = controller.ver()
        assert model.colegio
        assertEquals "test", model.colegio.nombre

        controller.params.id = colegio.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/colegio/lista")

        model = Colegio.get(colegio.id)
        assert !model
    }
}
