package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ClienteController)
class ClienteTests extends BaseIntegrationTest {


	def springSecurityService
    @Test
    void debieraMostrarListaDeClientes() {
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
		
        for(i in 1..20) {
        	new Cliente (
        		nombre: "test$i"
        		, nombreCompleto: "test cliente"
        		, rfc: "1234567890123"
        		, empresa: empresa1
        		, tipoCliente: tipoCliente
        	).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new ClienteController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/cliente/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.clienteInstanceList
		
        assertEquals 10, model.clienteInstanceList.size()
        assert 20 <= model.clienteInstanceTotal
    }
    
    @Test
    void debieraCrearCliente() {
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
		
        def controller = new ClienteController()
        controller.springSecurityService = springSecurityService
        def model = controller.create()
        assert model
        assert model.clienteInstance
        
        controller.params.nombre = "test"
        controller.params.nombreCompleto = "test cliente"
        controller.params.rfc = "1234567890123"
        controller.params.empresa = empresa1
        controller.params.tipoCliente = tipoCliente
        controller.save()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/cliente/show')
    }
    
    @Test
    void debieraActualizarCliente() {
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
		
		def cliente = new Cliente(
			nombre: "test"
			, nombreCompleto: "test Cliente"
			, rfc: "1234567890123"
			, empresa: empresa1
			, tipoCliente: tipoCliente
		).save()
		
		
        def controller = new ClienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cliente.id
        def model = controller.show()
        assert model.clienteInstance
        assertEquals "test", model.clienteInstance.nombre
        controller.params.id = cliente.id
        model = controller.edit()
        assert model.clienteInstance
        assertEquals "1234567890123", model.clienteInstance.rfc

        controller.params.id = cliente.id
        controller.params.version = cliente.version
        controller.params.nombre = "10002"
        controller.update()
        assertEquals "/cliente/show/${cliente.id}", controller.response.redirectedUrl

        cliente.refresh()
        assertEquals "10002", cliente.nombre
    }
 
	@Test
    void debieraEliminarCliente() {
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
		
		def cliente = new Cliente(
			nombre: "test"
			, nombreCompleto: "test Cliente"
			, rfc: "1234567890123"
			, empresa: empresa1
			, tipoCliente: tipoCliente
		).save()
        
        def controller = new ClienteController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cliente.id
        def model = controller.show()
        assert model.clienteInstance
        assertEquals "test", model.clienteInstance.nombre

        controller.params.id = cliente.id
        controller.delete()
        assertEquals "/cliente/list", controller.response.redirectedUrl

        model = Cliente.get(cliente.id)
        assert !model
    }
}
