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
    def procesoServiceInterface
    def procesoService
	  /*
	  Status:
	  A) CREADA
	  B) ENVIADA
	  C) APROBADA
	  D) RECHAZADA
	  E) COMPRADA
	  F) ENTREGADA
	  G) CANCELADA	
	  */
	  
    @Test
    void MostrarListaDeCompras() {
	      authenticateAdmin()

        def currentUser = springSecurityService.currentUser
        for(i in 1..20) {
        	  new Compra(
        	      empresa: currentUser.empresa
        	  ).save()
        }
        
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
        
        controller.index()
        assertEquals '/compra/lista', controller.response.redirectedUrl

	      def model = controller.lista()
	      assertNotNull model
	      assertNotNull model.compras

        assertEquals 10, model.compras.size()
        assert 20 <= model.totalDeCompras
        logout()
    }
    
    @Test
    void CrearCompra() {
      	authenticateEmp()

        def currentUser = springSecurityService.currentUser
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService

        controller.nueva()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/compra/edita')
        logout()
    }
    
    @Test
    void EliminarCompra() {
        authenticateEmp()

        def currentUser = springSecurityService.currentUser
    	  def compra = new Compra(
    	      empresa: currentUser.empresa
    	  ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService

        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.elimina()
        assertEquals "/compra/lista", controller.response.redirectedUrl

        model = Compra.get(compra.id)
        assert !model
        logout()
    }
    
    @Test
    void AgregarArticuloACompra() {
        authenticateEmp()

        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService

        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        def articulo = new Articulo (
          	descripcion: "objeto"
          	, cantidad: 1
          	, precioUnitario: 1
          	, compra: compra
        ).save()

        controller.params.id = compra.id
        controller.actualiza()
        assertEquals "/compra/edita/${compra.id}", controller.response.redirectedUrl

        compra.refresh()
        logout()
    }
    
    @Test
    void EmpDebieraPoderEnviarCompra() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CREADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra

        controller.enviar()
        assertEquals "ENVIADA", compra.status
        
        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void CCPDebieraPoderAprobarCompra() {
	      authenticateCCP()
        
        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.aprobar()
        assertEquals "APROBADA", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void CCPdebieraPoderRechazarCompra() {
	      authenticateCCP()

        def currentUser = springSecurityService.currentUser
        def compra = new Compra(
            empresa: currentUser.empresa
		        , status: "ENVIADA"
    		    , observaciones: "test"
	      ).save()
	      assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.rechazar()
        assertEquals "RECHAZADA", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void DirFindebieraPoderAprobarCompra() {
  	    authenticateDirfin()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
            , status: "ENVIADA"
		    ).save()
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra

        controller.aprobar()
        assertEquals "APROBADA", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void DirFindebieraPoderRechazarCompra() {
  	    authenticateDirfin()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
    		    , observaciones: "test dsfsd lorem"
		    ).save()
		    assertNotNull compra.observaciones
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "ENVIADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.rechazar()
        assertEquals "RECHAZADA", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void ComprasDebieraPoderComprarCompra() {
	      authenticateCompras()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
    		    , status: "APROBADA"
		    ).save()
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.comprar()
        assertEquals "COMPRADA", compra.status
 
        assert controller.response.redirectedUrl.startsWith("/compra/lista")
        logout()
    }
    
    @Test
    void EmpDebieraPoderCancelarCompra() {
  	    authenticateEmp()
		
		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
        ).save()
    		assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CREADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.cancelar()
        assertEquals "CANCELADA", compra.status
        logout()
    }
    
    @Test
    void DirfindebieraPoderCancelarCompra() {
  	    authenticateDirfin()
		
		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
        ).save()
    		assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CREADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.cancelar()
        assertEquals "CANCELADA", compra.status
        logout()
    }
    
    /*@Test
    void UserNoDebieraPoderEnviarCompra() {
	      authenticateUser()
		
		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
        ).save()
    		assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CREADA", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.enviar()
        assertEquals "CREADA", compra.status
        logout()
    }
    */
    
    @Test
    void CompraDebieraCalcularElTotal() {
    	authenticateAdmin()
    	
    	def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
        	, folio: "333"
       	).save()
       	
       	def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: "6"
            	, precioUnitario: "10.00"
            	, compra: compra
            ).save()
        
        assertEquals "600", articulo.total
        
        def articulo2 = new Articulo (
            	descripcion: "objeto2"
            	, cantidad: "5"
            	, precioUnitario: "1.00"
            	, compra: compra
            ).save()
            
        assertEquals "5.00", articulo2.total
        
        def articulo3 = new Articulo (
            	descripcion: "objeto"
            	, cantidad: "10"
            	, precioUnitario: "10.00"
            	, compra: compra
            ).save()
        assertEquals "100.00", articulo3.total
        
        assertEquals "705.00", compra.total
    }
}

