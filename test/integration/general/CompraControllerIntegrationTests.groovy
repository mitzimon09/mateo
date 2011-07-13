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
    
    //folio no necesita folio
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
    void debieraActualizarCompra() {
        authenticateEmp()

		def compra = new Compra().save()
        assertNotNull compra

        def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        controller.params.id = compra.id
        model = controller.edita()
        assert model.compra

        controller.params.id = compra.id
        controller.params.version = compra.version
        controller.actualiza()
        controller.permisos(currentUser)
        assertEquals "/compra/edita/${compra.id}", controller.response.redirectedUrl

        compra.refresh()
    }
 
 //dos pruebas borrar cuando este creada 
 //una prueba que cancela deespues de enviar con observac`iones 
	@Test
    void debieraEliminarCompra() {
        authenticateEmp()

		    def compra = new Compra().save()
        
        def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra

        controller.params.id = compra.id
        controller.elimina()
        assertEquals "/compra/lista", controller.response.redirectedUrl

        model = Compra.get(compra.id)
        assert !model
    }
    
    @Test
    void debieraCambiarStatusAEnviada() {
        authenticateEmp()
    	def compra = new Compra().save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        
        assertEquals  "CREADA", compra.status

        controller.params.id = compra.id
        controller.enviar()
        assertEquals  "ENVIADA", compra.status
    }
    
    //toda orden de compra llega a control < al limite > llega al dirfin  olas especiales de ccp manda al dirfin
    
    @Test
    void debieraCambiarStatusAAprobada() {
        //authenticateCCP()
        //authenticateDirFin()
        
    	def compra = new Compra(
		    status: "ENVIADA"
	    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        
        controller.params.id = compra.id
        controller.aprobar()
        assertEquals  "APROBADA", compra.status
    }
    
    //rechazado con obsevaciones
    @Test
    void debieraCambiarStatusARechazada() {
        //authenticateCCP()
        //authenticateDirFin()
        
    	def compra = new Compra(
		    status: "ENVIADA"
	    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        
        controller.params.id = compra.id
        controller.rechazar()
        assertEquals  "RECHAZADA", compra.status
    }
    
    @Test
    void debieraCambiarStatusAComprada() {
        authenticateCompras()

    	def compra = new Compra(
		    status: "APROBADA"
	    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        
        controller.params.id = compra.id
        controller.comprar()
        assertEquals  "COMPRADA", compra.status
    }
    
    //departamento tenga algo que ver con la entrega 
    @Test
    void debieraCambiarStatusAEntregada() {
        authenticateCompras()

    	def compra = new Compra(
		    status: "COMPRADA"
	    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        
        controller.params.id = compra.id
        controller.entregar()
        assertEquals  "ENTREGADA", compra.status
    }
    
    @Test
    void debieraMostrarListaDeComprasDespuesDeCambiarStatus() {
    	authenticateAdmin()
    	
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
		    status: "ENVIADA"
		).save()
		assertNotNull compra
		
		def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        controller.params.id = compra.id
        def model = controller.ver()
        assertNotNull controller.params
        controller.rechazar()
        assertEquals "RECHAZADA", compra.status
    }
}
