package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
//import grails.test.mixin.support.*
import org.junit.*
import org.codehaus.groovy.grails.plugins.GrailsPluginManager
import org.codehaus.groovy.grails.plugins.PluginManagerHolder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EntradaController)
class EntradaTests extends BaseIntegrationTest {


	def springSecurityService
    @Test
    void debieraMostrarListaDeEntradas() {
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
		assertNotNull almacen
		
		
        //def flag = true
        for(i in 1..20) {
            new Entrada (
            	folio: "folio$i"
            	, factura: "factura$i"
            	, comentarios: "no"
            	, tipoCambio: "0.00"
            	, proveedor: proveedor
            	, almacen: almacen
            ).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/entrada/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.entradaInstanceList
		
        assertEquals 10, model.entradaInstanceList.size()//////////////////////////////
        assert 20 <= model.entradaInstanceTotal
    }
    
    @Test
    void debieraCrearEntrada() {
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
                , nombreCompleto: "emptest"
                , organizacion: organizacion
            ).save()
            
            assertNotNull empresa1
		
		def proveedor = new Proveedor(
			nombre: 'proveedor'
			, nombreCompleto: 'proveedorsanchez'
			, rfc: '3333333333333'
			, empresa: empresa1			
		).save()
		
		assertNotNull proveedor
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: 'almacen1'
			, empresa: empresa1			
		).save()
		
		assertNotNull almacen
		
		
		assertNotNull almacen
		
        def entrada = new Entrada (
				factura: '10000'
				, almacen: almacen
				, proveedor: proveedor
            ).save()
            
            assertNotNull entrada

        def controller = new EntradaController()
        //controller.springSecurityService = springSecurityService
        def model = controller.create()
        assert model.entradaInstance

        controller.params.folio = '001'
        controller.params.factura = "10000"
        controller.params.tipoCambio= '12'
        controller.params.estatus='ABIERTA'
        controller.create()
        assert controller
        assert controller.response.redirectedUrl.startsWith('/entrada/show')
    }
    
    /*@Test
    void debieraActualizarEntrada() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: "emptest"
                , organizacion: organizacion
            ).save()
		
		def proveedor = new Proveedor(
			nombre: 'provedor'
			, nombreCompleto: 'proveedor sanchez'
			, rfc: 'mmmmmmmmmmmmmmmmmm'
			, curp: 'wwwwwwwwwwwwwwwwww'
			, direccion:'none'
			, empresa: empresa1			
		).save()
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: 'almacen1'
			, empresa: empresa1			
		).save()
		
        def entrada = new Entrada (
        		folio: '001'
				, factura: '10000'
				, comentarios: 'none'
				, tipoCambio: '12'
				, estatus: 'ABIERTA'
				, proveedor: proveedor
				, almacen: almacen
				, facturaAlmacen: new FacturaAlmacen()
            ).create()
        //def usuario = springSecurityService.currentUser
        //usuario.empresa = empresa

		assertNotNull entrada
        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = entrada.id
        def model = controller.show()
        assert model.entrada
        assertEquals 'TEST-1', model.empresa.nombre

        controller.params.id = empresa.id
        model = controller.edita()
        assert model.empresa
        assertEquals '10000', model.empresa.factura

        controller.params.id = entrada.id
        controller.params.version = entrada.version
        controller.params.folio = '10002'
        controller.actualiza()
        assertEquals "/entrada/show/${empresa.id}", controller.response.redirectedUrl

        empresa.refresh()
        assertEquals '10002', entrada.factura
    }
 
	protected void setUp() {
   super.setUp()
   PluginManagerHolder.pluginManager = [hasGrailsPlugin: { String name -> true }] as GrailsPluginManager
}

protected void tearDown() {
   super.tearDown()
   PluginManagerHolder.pluginManager = null
}   */
}
