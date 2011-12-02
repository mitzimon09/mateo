package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.BaseIntegrationTest

class RelacionControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeRelacions() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def relacion = new Relacion(
                nombre: 'test$i'
	        ).save()
		    assertNotNull relacion
        }

        def controller = new RelacionController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/relacion/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.relaciones

        assertEquals 10, model.relaciones.size()
        assert 20 <= model.totalDeRelaciones
    }

    @Test
    void CrearRelacion() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new RelacionController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.relacion
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/relacion/ver')
    }
    
    @Test
    void ModificarRelacion() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
        
    	def relacion = new Relacion(
            nombre: 'test'
	    ).save()
		assertNotNull relacion
    		
        def controller = new RelacionController()
        controller.springSecurityService = springSecurityService
        controller.params.id = relacion.id
        def model = controller.ver()
        assert model.relacion
        assertEquals "test", model.relacion.nombre

        controller.params.id = relacion.id
        model = controller.edita()
        assert model.relacion
        assertEquals "test", model.relacion.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/relacion/ver')

        relacion.refresh()
        assertEquals 'test1', relacion.nombre
    }
    
    @Test
    void EliminarRelacion() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def relacion = new Relacion(
            nombre: 'test'
	    ).save()
		assertNotNull relacion
        
        def controller = new RelacionController()
        controller.springSecurityService = springSecurityService
        controller.params.id = relacion.id
        def model = controller.ver()
        assert model.relacion
        assertEquals "test", model.relacion.nombre

        controller.params.id = relacion.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/relacion/lista")

        model = Relacion.get(relacion.id)
        assert !model
    }
}
