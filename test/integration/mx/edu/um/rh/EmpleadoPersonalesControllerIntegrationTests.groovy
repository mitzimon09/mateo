package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EmpleadoPersonalesController)
class EmpleadoPersonalesControllerIntegrationTests {
	@Test
    void debieraMostrarListaDeEmpleadoPersonaless() {
		
		for(i in 1..20) {
			def empleadoPersonales = new EmpleadoPersonales(
		    	estadoCivil: "te"
		    	, madre: "test"
		    	, padre: "test"
        	)
        }
        
        def controller = new EmpleadoPersonalesController()
        controller.index()
        assertEquals '/empleadoPersonales/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.empleadoPersonaless
		
        assertEquals 10, model.empleadoPersonales.size()
        assert 20 <= model.totalDeEmpleadoPersonales
    }
    
    @Test
    void debieraCrearEmpleadoPersonales() {
		
		def controller = new EmpleadoPersonalesController()
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoPersonales
        controller.params.estadoCivil = "3e"
        controller.params.madre = "test"
        controller.params.padre = "test"
        
        controller.save()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoPersonales/show')
    }
    
    @Test
    void debieraActualizarEmpleadoPersonales() {
		
        def empleadoPersonales = new EmpleadoPersonales(
        	estadoCivil: "te"
        	, madre: "test"
        	, padre: "test"
        )
        
        def controller = new EmpleadoPersonalesController()

        controller.params.id = empleadoPersonales.id
        def model = controller.ver()
        assert model.empleadoPersonales
        assertEquals 3, model.empleadoPersonales.escalafon

        controller.params.id = empleadoPersonales.id
        model = controller.edita()
        assert model.empleadoPersonales
        assertEquals 1, model.empleadoPersonales.turno

        controller.params.id = empleadoPersonales.id
        controller.params.version = empleadoPersonales.version
        controller.params.escalafom = 2
        controller.params.turno = 2
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoPersonales/show')

        empleadoPersonales.refresh()
        assertEquals 2, empleadoPersonales.escalafon
        assertEquals 2, empleadoPersonales.turno
    }
}
