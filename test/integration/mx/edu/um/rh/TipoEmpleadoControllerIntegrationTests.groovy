package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
//@TestFor(TipoEmpleadoController)
class TipoEmpleadoControllerIntegrationTests {
	/*@Test
    void debieraMostrarListaDeTipoEmpleados() {
		
		for(i in 1..20) {
			def tipoEmpleado = new TipoEmpleado (
		    	descripcion: "test"
    		, prefijo: "111"
		    ).save()
        }
        
        def controller = new TipoEmpleadoController()
        controller.index()
        assertEquals '/tipoEmpleado/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.tipoEmpleadoInstanceList
		
        assertEquals 10, model.tipoEmpleadoInstanceList.size()
        assert 20 <= model.totalDeTipoEmpleado
    }
    
    @Test
    void debieraCrearTipoEmpleado() {

		def controller = new TipoEmpleadoController()
        
        def model = controller.nuevo()
        assert model
        assert model.tipoEmpleado
        controller.params.descripcion = "test"
        controller.params.prefijo = "111"
        controller.save()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/tipoEmpleado/show')
    }
    
    @Test
    void debieraActualizarTipoEmpleado() {
		
		def tipoEmpleado = new TipoEmpleado (
		    	descripcion: "test"
    			, prefijo: "111"
		    ).save()
        
        def controller = new TipoEmpleadoController()

        controller.params.id = tipoEmpleado.id
        def model = controller.ver()
        assert model.tipoEmpleado
        assertEquals "test", model.tipoEmpleado.descripcion

        controller.params.id = tipoEmpleado.id
        model = controller.edita()
        assert model.tipoEmpleado
        assertEquals "111", model.tipoEmpleado.prefijo

        controller.params.id = tipoEmpleado.id
        controller.params.version = tipoEmpleado.version
        controller.params.descripcion = "test2"
        controller.params.prefijo = "112"
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/tipoEmpleado/show')

        tipoEmpleado.refresh()
        assertEquals "test2", tipoEmpleado.descripcion
        assertEquals "112", tipoEmpleado.prefijo
    }
*/
}
