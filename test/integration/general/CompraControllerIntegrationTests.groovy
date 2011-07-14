package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CompraController)
class CompraControllerIntegrationTests extends BaseIntegrationTest {
    def springSecurityService
	
    @Test
    void debieraMostrarListaDeCompras() {
	    authenticateAdmin()

        for(i in 1..20) {
        	new Compra().save()
        }
        
        def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/compra/lista', controller.response.redirectedUrl

	    def model = controller.lista()
	    assertNotNull model
	    assertNotNull model.compras

        assertEquals 10, model.compras.size()
        assert 20 <= model.totalDeCompras
    }
    
    @Test
    void debieraCrearCompra() {
      	authenticateEmp()

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService

        controller.nueva()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/compra/edita')
    }
    
    @Test
    void debieraEliminarCompra() {
        authenticateEmp()

	    def compra = new Compra().save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService

        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.elimina()
        assertEquals "/compra/lista", controller.response.redirectedUrl

        model = Compra.get(compra.id)
        assert !model
    }
    
    @Test
    void debieraAgregarArticuloACompra() {
        authenticateEmp()

		def compra = new Compra().save()
        assertNotNull compra

        def currentUser = springSecurityService.currentUser
        def compraController = new CompraController()
        def articuloController = new ArticuloController()
        compraController.springSecurityService = springSecurityService
        articuloController.springSecurityService = springSecurityService

        compraController.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        def articulo = new Articulo (
        	descripcion: "objeto"
        	, cantidad: 1
        	//, precioUnitario: 1
        	, compra: compra
        ).save()

        compraController.params.id = compra.id
        controller.actualiza()
        assertEquals "/compra/edita/${compra.id}", controller.response.redirectedUrl

        compra.refresh()
    }
 
 //una prueba que cancela deespues de enviar con observaciones 
	
    
    @Test
    void debieraMostrarListaDeComprasDespuesDeCambiarStatus() {
    	authenticateAdmin()
    	        controller.springSecurityService = springSecurityService
    	def compra = new Compra().save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra

        controller.params.id = compra.id
        controller.enviar()
        
        assertEquals "/compra/lista", controller.response.redirectedUrl
    }

	@Test
    void EmpdebieraPoderEnviarCompra() {
	    authenticateEmp()
		
        def compra = new Compra().save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "CREADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.enviar()
        assertEquals "ENVIADA", compra.status
    }

    @Test
    void CCPdebieraPoderAprobarCompra() {
	    authenticateCCP()
        
        def compra = new Compra(
		    status: "ENVIADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.aprobar()
        assertEquals "APROBADA", compra.status
    }

    @Test
    void CCPdebieraPoderRechazarCompra() {
	    authenticateCCP()

        def compra = new Compra(
		    status: "ENVIADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.rechazar()
        assertEquals "RECHAZADA", compra.status
    }

    @Test
    void DirFindebieraPoderAprobarCompra() {
	    authenticateDirfin()

        
        def compra = new Compra(
            status: "ENVIADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.aprobar()
        assertEquals "APROBADA", compra.status
    }

    @Test
    void DirFindebieraPoderRechazarCompra() {
	    authenticateDirfin()

        def compra = new Compra(
		    status: "ENVIADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.rechazar()
        assertEquals "RECHAZADA", compra.status
    }

    @Test
    void ComprasDebieraPoderComprarCompra() {
	    authenticateCompras()

        def compra = new Compra(
		    status: "APROBADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.comprar()
        assertEquals "COMPRADA", compra.status
    }

    @Test
    void ComprasdebieraPoderEntregarCompra() {
	    authenticateCompras()

        def compra = new Compra(
		    status: "APROBADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.entregar()
        assertEquals "ENTREGADA", compra.status
    }
}
