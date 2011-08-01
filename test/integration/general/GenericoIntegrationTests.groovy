package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CompraController)
class GenericoIntegrationTests extends BaseIntegrationTest {
    def springSecurityService
    def procesoServiceInterface
    def procesoService

    @Test
    void EnviarCompra() {
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
}
