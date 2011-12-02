package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(TipoEmpleadoController)
class TipoEmpleadoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeTipoEmpleados() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def tipoEmpleado = new TipoEmpleado(
                descripcion: 'test$i' 
                , prefijo: '000'
		    ).save()
    		assertNotNull tipoEmpleado
        }

        def controller = new TipoEmpleadoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/tipoEmpleado/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.tipoEmpleados

        assertEquals 10, model.tipoEmpleados.size()
        assert 20 <= model.totalDeTipoEmpleados
    }

    @Test
    void CrearTipoEmpleado() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new TipoEmpleadoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.tipoEmpleado
        controller.params.descripcion = "test"
        controller.params.prefijo = "000"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/tipoEmpleado/ver')
    }
    
    @Test
    void ModificarTipoEmpleado() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def tipoEmpleado = new TipoEmpleado(
            descripcion: 'test'
            , prefijo: '000'
	    ).save()
		assertNotNull tipoEmpleado
    		
        def controller = new TipoEmpleadoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoEmpleado.id
        def model = controller.ver()
        assert model.tipoEmpleado
        assertEquals "test", model.tipoEmpleado.descripcion

        controller.params.id = tipoEmpleado.id
        model = controller.edita()
        assert model.tipoEmpleado
        assertEquals "test", model.tipoEmpleado.descripcion

        controller.params.descripcion = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/tipoEmpleado/ver')

        tipoEmpleado.refresh()
        assertEquals 'test1', tipoEmpleado.descripcion
    }
    
    @Test
    void EliminarTipoEmpleado() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def tipoEmpleado = new TipoEmpleado(
            descripcion: 'test'
            , prefijo: '000'
	    ).save()
		assertNotNull tipoEmpleado
        
        def controller = new TipoEmpleadoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoEmpleado.id
        def model = controller.ver()
        assert model.tipoEmpleado
        assertEquals "test", model.tipoEmpleado.descripcion

        controller.params.id = tipoEmpleado.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/tipoEmpleado/lista")

        model = TipoEmpleado.get(tipoEmpleado.id)
        assert !model
    }
}
