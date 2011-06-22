package inventario

import general.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(FacturaAlmacenController)
class FacturaAlmacenTests extends BaseIntegrationTest {


	def springSecurityService
    @Test
    void debieraMostrarListaDeFacturaAlmacen() {
		authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		assertNotNull almacen
		
		def tipoCliente = new TipoCliente(
			nombre: "test"
			, empresa: empresa1
		) .save()
		assertNotNull tipoCliente
		
		def cliente = new Cliente(
			nombre: "Test"
			, nombreCompleto: "Test Cliente"
			, rfc: "1234567890123"
			, empresa: empresa1
			, tipoCliente: tipoCliente
		).save()
		
		
        for(i in 1..20) {
            new FacturaAlmacen (
            	folio: "folio$i"
            	, fecha: new Date()
            	, cliente: cliente
            	, almacen: almacen
            ).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/facturaAlmacen/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.facturaAlmacenInstanceList
		
        assertEquals 10, model.facturaAlmacenInstanceList.size()
        assert 20 <= model.facturaAlmacenInstanceTotal
    }
    
    @Test
    void debieraCrearFacturaAlmacen() {
    	authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		assertNotNull almacen
		
		def tipoCliente = new TipoCliente(
			nombre: "test"
			, empresa: empresa1
		) .save()
		assertNotNull tipoCliente
		
		def cliente = new Cliente(
			nombre: "Test"
			, nombreCompleto: "Test Cliente"
			, rfc: "1234567890123"
			, empresa: empresa1
			, tipoCliente: tipoCliente
		).save()
		
        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        
        //controller.springSecurityService = springSecurityService
        def model = controller.create()
        assert model
        assert model.facturaAlmacenInstance
        
        controller.params.folio = "folio"
        controller.params.cliente = cliente
        controller.params.almacen = almacen
        controller.params.fecha = new Date()
        controller.save()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/facturaAlmacen/show')
    }
    
    @Test
    void debieraActualizarFacturaAlmacen() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		assertNotNull almacen
		
		def tipoCliente = new TipoCliente(
			nombre: "test"
			, empresa: empresa1
		) .save()
		assertNotNull tipoCliente
		
		def cliente = new Cliente(
			nombre: "Test"
			, nombreCompleto: "Test Cliente"
			, rfc: "1234567890123"
			, empresa: empresa1
			, tipoCliente: tipoCliente
		).save()

		def facturaAlmacen = new FacturaAlmacen (
            	folio: "100"
            	, cliente: cliente
            	, almacen: almacen
            	, fecha: new Date()
            ).save()
		
		assertNotNull facturaAlmacen
        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = facturaAlmacen.id
        def model = controller.show()
        assert model.facturaAlmacenInstance
        assertEquals "100", model.facturaAlmacenInstance.folio

        controller.params.id = facturaAlmacen.id
        model = controller.edit()
        assert model.facturaAlmacenInstance
        assertEquals cliente, model.facturaAlmacenInstance.cliente

        controller.params.id = facturaAlmacen.id
        controller.params.version = facturaAlmacen.version
        controller.params.folio = '10002'
        controller.params.fecha = new Date()
        controller.update()
        assertEquals "/facturaAlmacen/show/${facturaAlmacen.id}", controller.response.redirectedUrl

        facturaAlmacen.refresh()
        assertEquals '10002', facturaAlmacen.folio
    }
 
	@Test
    void debieraEliminarEntrada() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
		def proveedor = new Proveedor(
			nombre: 'proveedor'
			, nombreCompleto: 'proveedorsanchez'
			, rfc: '3333333333333'
			, empresa: empresa1			
		).save()
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		
        def entrada = new Entrada (
            	folio: "001"
            	, factura: "10000"
            	, comentarios: "no"
            	, tipoCambio: "0.00"
            	, proveedor: proveedor
            	, almacen: almacen
            ).save()
        
        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = entrada.id
        def model = controller.ver()
        assert model.entrada
        assertEquals "001", model.entrada.folio

        controller.params.id = entrada.id
        controller.elimina()
        assertEquals "/facturaAlmacen/lista", controller.response.redirectedUrl

        model = Entrada.get(entrada.id)
        assert !model
    }
}
