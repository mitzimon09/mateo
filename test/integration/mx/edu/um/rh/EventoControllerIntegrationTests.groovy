package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

import mx.edu.um.Constantes
import general.Organizacion
import general.Empresa
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(EventoController)
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
                , status: Constantes.STATUS_ABIERTO
                ,
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
        controller.params.status = Constantes.STATUS_ABIERTO
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/evento/ver')
        
        def id = controller.params.id
        def evento = Evento.get(id)
        assertEquals 'test', evento.nombre
        
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
            , status: Constantes.STATUS_ABIERTO
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
            , status: Constantes.STATUS_ABIERTO
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

	@Test
	void IniciarEvento() {
		def evento = new Evento(
            nombre: 'test' 
            , descripcion: 'test'
            , hora_inicio: new Date()
            , hora_final: new Date()
            , status: Constantes.STATUS_ABIERTO
	    ).save()
		assertNotNull evento
		
        def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = evento.id
        def model = controller.ver()
        assert model.evento
        assertEquals Constantes.STATUS_ABIERTO, model.evento.status
        model = controller.iniciarEvento()
        assertEquals evento.status, Constantes.STATUS_INICIADO
	}
	
	@Test
	void CerrarEvento() {
		def evento = new Evento(
            nombre: 'test' 
            , descripcion: 'test'
            , hora_inicio: new Date()
            , hora_final: new Date()
            , status: Constantes.STATUS_INICIADO
	    ).save()
		assertNotNull evento
		
        def controller = new EventoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = evento.id
        assertEquals Constantes.STATUS_INICIADO, evento.status
        
        controller.cerrarEvento()
        assertEquals evento.status, Constantes.STATUS_TERMINADO
	}
	
	@Test
	void PaseDeLista() {
        def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "980"
    	).save()
    	assertNotNull tipoEmpleado
    	
    	def grupo = new Grupo(
            nombre: "1"
            , maximo: 1
            , minimo: 1
	    ).save()
		assertNotNull grupo
        
	    Empleado empleado = new Empleado(
            clave : "9809999"
            , nombre : "TESTA"
            , apPaterno : "TESTA"
            , apMaterno : "TESTA"
            , genero : Constantes.GENERO_MASCULINO
            , fechaNacimiento : new Date()
            , direccion : "TEST"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "TEST123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "TESTP"
            , madre: "TESTM"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
        
	    def evento = new Evento(
            nombre: 'test' 
            , descripcion: 'test'
            , hora_inicio: new Date()
            , hora_final: new Date()
            , status: Constantes.STATUS_INICIADO
            //, clave: '9800001'
	    ).save()
		assertNotNull evento
		
		def controller = new EventoController()
        //controller.springSecurityService = springSecurityService
        controller.params.evento.id = evento.id
        assertEquals evento.id, controller.params.evento
        controller.params.clave = 9809999
        assertEquals Constantes.STATUS_INICIADO, evento.status
        controller.paseLista()
                
        //def model = controller.paseLista()
	}
}
