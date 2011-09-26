package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(TipoClienteController)
class TipoClienteControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
    @Test
    void debieraMostrarListaDeTipoClientes() {
		authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
        for(i in 1..20) {
        	new TipoCliente(
			nombre: "test$i"
			, empresa: empresa1
		) .save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new TipoClienteController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/tipoCliente/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.tipoClientes
		
        assertEquals 10, model.tipoClientes.size()
        assert 20 <= model.totalDeTipoCliente
    }
    
    @Test
    void debieraCrearTipoCliente() {
    	authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()

		
        def controller = new TipoClienteController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model
        assert model.tipoCliente
        
        controller.params.nombre = "test"
        controller.params.empresa = empresa1
        controller.crea()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/tipoCliente/ver')
    }
    
    @Test
    void debieraActualizarTipoCliente() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()

        def tipoCliente = new TipoCliente(
			nombre: "test"
			, empresa: empresa1
		) .save()
		
        def controller = new TipoClienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoCliente.id
        def model = controller.ver()
        assert model.tipoCliente
        assertEquals "test", model.tipoCliente.nombre
        controller.params.id = tipoCliente.id
        model = controller.edita()
        assert model.tipoCliente
        assertEquals empresa1, model.tipoCliente.empresa

        controller.params.id = tipoCliente.id
        controller.params.version = tipoCliente.version
        controller.params.nombre = "10002"
        controller.actualiza()
        assertEquals "/tipoCliente/ver/${tipoCliente.id}", controller.response.redirectedUrl

        tipoCliente.refresh()
        assertEquals "10002", tipoCliente.nombre
    }
 
	@Test
    void debieraEliminarTipoCliente() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()

        def tipoCliente = new TipoCliente(
			nombre: "test"
			, empresa: empresa1
		) .save()
		
        
        def controller = new TipoClienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoCliente.id
        def model = controller.ver()
        assert model.tipoCliente
        assertEquals "test", model.tipoCliente.nombre

        controller.params.id = tipoCliente.id
        controller.elimina()
        assertEquals "/tipoCliente/lista", controller.response.redirectedUrl

        model = TipoCliente.get(tipoCliente.id)
        assert !model
    }
}
