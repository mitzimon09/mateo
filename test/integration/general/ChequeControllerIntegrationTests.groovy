package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ChequeController)
class ChequeControllerIntegrationTests  extends BaseIntegrationTest {

	def springSecurityService
    def procesoServiceInterface
    def procesoService

    @Test
    void EnviarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "CREADA", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.enviar()
        assertEquals "ENVIADA", cheque.status
    }
    
    @Test
    void AprobarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.aprobar()
        assertEquals "APROBADA", cheque.status
    }
    
        @Test
    void RechazarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.rechazar()
        assertEquals "RECHAZADA", cheque.status
    }
    
        @Test
    void CancelarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.cancelar()
        assertEquals "CANCELADA", cheque.status
    }
    
}
