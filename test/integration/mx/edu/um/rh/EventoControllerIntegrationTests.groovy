package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EventoController)
class EventoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEventos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def evento = new Evento(
                nombre: 'test$i' 
                , descripcion: 'test'
                , hora_inicio: new Date()
                , hora_final: new Date()
                , prorroga: '15'
                , status: 'CR'
		    ).save()
    		assertNotNull evento
        }

        def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/evento/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.eventos

        assertEquals 10, model.eventos.size()
        assert 20 <= model.totalDeEventos
    }

    @Test
    void CrearEvento() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.evento
        controller.params.nombre = "test"
        controller.params.descripcion = 'test'
        controller.params.hora_inicio = new Date()
        controller.params.hora_final = new Date()
        controller.params.prorroga = '15'
        controller.params.status = 'CR'
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/evento/ver')
    }
    
    @Test
    void ModificarEvento() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def evento = new Evento(
            nombre: 'test' 
            , descripcion: 'test'
            , hora_inicio: new Date()
            , hora_final: new Date()
            , prorroga: '15'
            , status: 'CR'
	    ).save()
		assertNotNull evento
    		
        def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = evento.id
        def model = controller.ver()
        assert model.evento
        assertEquals "test", model.evento.nombre

        controller.params.id = evento.id
        model = controller.edita()
        assert model.evento
        assertEquals "test", model.evento.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/evento/ver')

        evento.refresh()
        assertEquals 'test1', evento.nombre
    }
    
    @Test
    void EliminarEvento() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def evento = new Evento(
            nombre: 'test' 
            , descripcion: 'test'
            , hora_inicio: new Date()
            , hora_final: new Date()
            , prorroga: '15'
            , status: 'CR'
	    ).save()
		assertNotNull evento
        
        def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = evento.id
        def model = controller.ver()
        assert model.evento
        assertEquals "test", model.evento.nombre

        controller.params.id = evento.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/evento/lista")

        model = Evento.get(evento.id)
        assert !model
    }
}
