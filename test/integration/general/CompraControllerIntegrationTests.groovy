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
		
        assertEquals "CR", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra

        controller.enviar()
        assertEquals "EN", compra.status
        
        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void CCPDebieraPoderAprobarCompra() {
	      authenticateCCP()
        
        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        empresa: currentUser.empresa
		        , status: "EN"
	      ).save()
        assertNotNull compra

        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "EN", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.aprobar()
        assertEquals "AP", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void CCPdebieraPoderRechazarCompra() {
	      authenticateCCP()

        def currentUser = springSecurityService.currentUser
        def compra = new Compra(
            empresa: currentUser.empresa
		        , status: "EN"
    		    , observaciones: "test"
	      ).save()
	      assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "EN", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.rechazar()
        assertEquals "RE", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void DirFindebieraPoderAprobarCompra() {
  	    authenticateDirfin()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
            , status: "EN"
		    ).save()
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "EN", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra

        controller.aprobar()
        assertEquals "AP", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void DirFindebieraPoderRechazarCompra() {
  	    authenticateDirfin()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
		        , status: "EN"
    		    , observaciones: "test dsfsd lorem"
		    ).save()
		    assertNotNull compra.observaciones
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "EN", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.rechazar()
        assertEquals "RE", compra.status

        assertEquals "/compra/lista", controller.response.redirectedUrl
        logout()
    }

    @Test
    void ComprasDebieraPoderComprarCompra() {
	      authenticateCompras()

		    def currentUser = springSecurityService.currentUser
        def compra = new Compra(
		        empresa: currentUser.empresa
    		    , status: "AP"
		    ).save()
		    assertNotNull compra
		
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.completar()
        assertEquals "CO", compra.status
 
        //assert controller.response.redirectedUrl.startsWith("/compra/lista")
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
		
        assertEquals "CR", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.cancelar()
        assertEquals "CA", compra.status
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
		
        assertEquals "CR", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.cancelar()
        assertEquals "CA", compra.status
        logout()
    }
    
    @Test
    void CompraDebieraCalcularElTotal() {
    	authenticateAdmin()
    	
    	def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
       	).save()
       	
        def controller = new CompraController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CR", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
 
       	def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: 6
            	, precioUnitario: 10.00
            	, compra: compra
            	, total: 60.00
            ).save()
 
        def articulo2 = new Articulo (
            	descripcion: "objeto2"
            	, cantidad: 5
            	, precioUnitario: 1.00
            	, compra: compra
            	, total: 5.00
            ).save()
            
	    def articulo3 = new Articulo (
            	descripcion: "objeto"
            	, cantidad: 10
            	, precioUnitario: 10.00
            	, total: 100.00
            	, compra: compra
            ).save()
 		controller.actualiza()
        assertEquals 165.00, compra.total
	    assert controller.response.redirectedUrl.startsWith("/compra/edita")
	    
    }
    
    @Test
    void CompraDebieraValidarArticulosEntregadosOCancelados() {
    	authenticateAdmin()
    	
    	def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
        	, folio: "333"
        	, status: "AP"
       	).save()
       	
       	def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: "6"
            	, precioUnitario: "10.00"
            	, compra: compra
            	, status: "CA"
            ).save()
        
        def articulo2 = new Articulo (
            	descripcion: "objeto2"
            	, cantidad: "5"
            	, precioUnitario: "1.00"
            	, compra: compra
            	, status: "EN"
            ).save()
            
         compra.articulos=[articulo, articulo2]
         def controller = new CompraController()
        controller.springSecurityService = springSecurityService
		
        assertEquals "AP", compra.status
        
        controller.params.id = compra.id
        def model = controller.edita()
        assert model.compra
        
        controller.completar()
        assertEquals "CO", compra.status
        logout()
       
    }
    
}

