package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ProveedorController)
class ProveedorTests  extends BaseIntegrationTest{

	def springSecurityService
    @Test
    void debieraMostrarListaDeProveedores() {
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
		
        for(i in 1..20) {
            new Proveedor(
				nombre: "proveedor$i"
				, nombreCompleto: "proveedor$i sanchez$i"
				, rfc: "12345678901$i"
				, empresa: empresa1			
			).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/proveedor/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.proveedores
		
        assertEquals 10, model.proveedores.size()
        assert 20 <= model.totalDeProveedores
    }
    
    @Test
    void debieraCrearProveedor() {
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
		
        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        
        //controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model
        assert model.proveedor
        
        controller.params.nombre = "test"
        controller.params.nombreCompleto = "test test"
        controller.params.rfc = "1234567890123"
        controller.params.empresa = empresa1
        controller.crea()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/proveedor/ver')
    }
    
    @Test
    void debieraActualizarProveedor() {
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

        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.params.id = proveedor.id
        def model = controller.ver()
        assert model.proveedor
        assertEquals "proveedor", model.proveedor.nombre

        controller.params.id = proveedor.id
        model = controller.edita()
        assert model.proveedor
        assertEquals "3333333333333", model.proveedor.rfc

        controller.params.id = proveedor.id
        controller.params.version = proveedor.version
        controller.params.rfc = '3210987654321'
        controller.actualiza()
        assertEquals "/proveedor/ver/${proveedor.id}", controller.response.redirectedUrl

        proveedor.refresh()
        assertEquals '3210987654321', proveedor.rfc
    }
 
	@Test
    void debieraEliminarProveedor() {
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
        
        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.params.id = proveedor.id
        def model = controller.ver()
        assert model.proveedor
        assertEquals "proveedor", model.proveedor.nombre

        controller.params.id = proveedor.id
        controller.elimina()
        assertEquals "/proveedor/lista", controller.response.redirectedUrl

        model = Proveedor.get(proveedor.id)
        assert !model
    }
}
