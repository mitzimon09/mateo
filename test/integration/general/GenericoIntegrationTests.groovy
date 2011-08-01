package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import interfaces.ProcesoServiceInterface

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

        compra = procesoServiceInterface.enviar(compra)
        assertEquals "ENVIADA", compra.status
    }

    @Test
    void AprobarCompra() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        status: "ENVIADA"
		        , empresa: currentUser.empresa
	      ).save()
        assertNotNull compra

        assertEquals "ENVIADA", compra.status

        compra = procesoServiceInterface.aprobar(compra)
        assertEquals "APROBADA", compra.status
    }

    @Test
    void RechazarCompra() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def compra = new Compra(
		        status: "ENVIADA"
		        , empresa: currentUser.empresa
	      ).save()
        assertNotNull compra

        assertEquals "ENVIADA", compra.status

        compra = procesoServiceInterface.rechazar(compra)
        assertEquals "RECHAZADA", compra.status
    }
}
