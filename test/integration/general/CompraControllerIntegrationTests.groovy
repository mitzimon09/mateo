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
	  def folioService
	
    @Test
    void debieraMostrarListaDeCompras() {
		    authenticateAdmin()

        for(i in 1..20) {
        	new Compra (
        		folio: "test$i"
        	).save()
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
      	authenticateAdmin()

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.folioService = folioService
        
        def model = controller.nueva()
        assert model
        assert model.compra
		
        controller.params.folio = "test"
        assertNotNull controller.params
        controller.crea()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/compra/edita')
    }
    
    @Test
    void debieraActualizarCompra() {
        authenticateAdmin()

		def compra = new Compra (
            folio: 'test'
        ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        controller.params.id = compra.id
        model = controller.edita()
        assert model.compra

        controller.params.id = compra.id
        controller.params.version = compra.version
        controller.params.folio = "test1"
        controller.actualiza()
        assertEquals "/compra/edita/${compra.id}", controller.response.redirectedUrl

        compra.refresh()
        assertEquals "test1", compra.folio
    }
 
	@Test
    void debieraEliminarCompra() {
        authenticateAdmin()

		    def compra = new Compra(
			    folio: "test"
		    ).save()
        
        def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio

        controller.params.id = compra.id
        controller.elimina()
        assertEquals "/compra/lista", controller.response.redirectedUrl

        model = Compra.get(compra.id)
        assert !model
    }
    
    @Test
    void debieraCambiarStatusAEnviada() {
    	def compra = new Compra(
			    folio: "test"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        
        assertEquals  "CREADA", compra.status

        controller.params.id = compra.id
        controller.enviar()
        assertEquals  "ENVIADA", compra.status
    }
    
    @Test
    void debieraCambiarStatusAAprobada() {
    	def compra = new Compra(
			    folio: "test"
			    , status: "ENVIADA"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        
        controller.params.id = compra.id
        controller.aprobar()
        assertEquals  "APROBADA", compra.status
    }
    
    @Test
    void debieraCambiarStatusARechazada() {
    	def compra = new Compra(
			    folio: "test"
			    , status: "ENVIADA"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        
        controller.params.id = compra.id
        controller.rechazar()
        assertEquals  "RECHAZADA", compra.status
    }
    
    @Test
    void debieraCambiarStatusAComprada() {
    	def compra = new Compra(
			    folio: "test"
			    , status: "APROBADA"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        
        controller.params.id = compra.id
        controller.comprar()
        assertEquals  "COMPRADA", compra.status
    }
    
    @Test
    void debieraCambiarStatusAEntregada() {
    	def compra = new Compra(
			    folio: "test"
			    , status: "COMPRADA"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio
        
        controller.params.id = compra.id
        controller.entregar()
        assertEquals  "ENTREGADA", compra.status
    }
    
    @Test
    void debieraMostrarListaDeComprasDespuesDeCambiarStatus() {
    	authenticateAdmin()
    	
    	def compra = new Compra(
			    folio: "test"
		    ).save()
		
		def controller = new CompraController()
        controller.params.id = compra.id
        def model = controller.ver()
        assert model.compra
        assertEquals "test", model.compra.folio

        controller.params.id = compra.id
        controller.enviar()
        
        assertEquals "/compra/lista", controller.response.redirectedUrl
    }

	@Test
    void OrgdebieraPoderEnviarCompra() {
		    authenticateOrg()
		
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
            
        def usuario = new Usuario(
        	username: "test"
        	, password: "pass"
        	, nombre: "test"
        	, apellido: "test"
        	, correo: "test@test.com"
        	, empresa: empresa1
        )
        
        def compra = new Compra(
			    folio: "test"
			    //, status: "CREADA"
		).save()
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
			    folio: "test"
			    , status: "ENVIADA"
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
			    folio: "test"
			    , status: "ENVIADA"
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
			    folio: "test"
			    , status: "ENVIADA"
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
			    folio: "test"
			    , status: "ENVIADA"
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
			    folio: "test"
			    , status: "APROBADA"
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
    void ComprasdebieraPoderEntregarZCompra() {
		    authenticateDirfin()

        
        def compra = new Compra(
			    folio: "test"
			    , status: "ENVIADA"
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
