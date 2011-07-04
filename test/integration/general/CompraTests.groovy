package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CompraController)
class CompraTests extends BaseIntegrationTest {


	def springSecurityService
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
        /*def model = controller.nueva()
        assert model
        assert model.compra
        */
        controller.params.folio = "test"
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
}
