package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(EmpleadoPersonalesController)
class EmpleadoPersonalesControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEmpleadoPersonaless() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
		def empleadoService = new EmpleadoController()
		empleadoService.empleadoServiceInt = empleadoServiceInt

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        
        for(i in 1..20) {
        	def empleadoPersonales = new EmpleadoPersonales(
		    	estadoCivil: "te"
		    	, madre: "test"
		    	, padre: "test"
		    	, empleado: empleado
		    ).save()
    		assertNotNull empleadoPersonales
        }

        def controller = new EmpleadoPersonalesController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/empleadoPersonales/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.empleadoPersonales

        assertEquals 10, model.empleadoPersonales.size()
        assert 20 <= model.totalDeEmpleadoPersonales
    }

    @Test
    void CrearEmpleadoPersonales() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new EmpleadoPersonalesController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            estadoCivil: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.empleadoPersonales
        controller.params.estadoCivil = "te"
        controller.params.madre = "test"
        controller.params.padre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoPersonales/ver')
    }
    
    @Test
    void ModificarEmpleadoPersonales() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def empleadoPersonales = new EmpleadoPersonales(
	    	estadoCivil: "te"
	    	, madre: "test"
	    	, padre: "test"
	    ).save()
		assertNotNull empleadoPersonales
    		
        def controller = new EmpleadoPersonalesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoPersonales.id
        def model = controller.ver()
        assert model.empleadoPersonales
        assertEquals "te", model.empleadoPersonales.estadoCivil

        controller.params.id = empleadoPersonales.id
        model = controller.edita()
        assert model.empleadoPersonales
        assertEquals "te", model.empleadoPersonales.estadoCivil

        controller.params.estadoCivil = 't1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoPersonales/ver')

        empleadoPersonales.refresh()
        assertEquals 't1', empleadoPersonales.estadoCivil
    }
    
    @Test
    void EliminarEmpleadoPersonales() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def empleadoPersonales = new EmpleadoPersonales(
        	estadoCivil: "te"
        	, madre: "test"
        	, padre: "test"
	    ).save()
		assertNotNull empleadoPersonales
        
        def controller = new EmpleadoPersonalesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoPersonales.id
        def model = controller.ver()
        assert model.empleadoPersonales
        assertEquals "test", model.empleadoPersonales.estadoCivil

        controller.params.id = empleadoPersonales.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/empleadoPersonales/lista")

        model = EmpleadoPersonales.get(empleadoPersonales.id)
        assert !model
    }
}
