package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ChequeController)
class ChequeTests  extends BaseIntegrationTest {

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
        assertEquals "CR", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.enviar()
        assertEquals "EN", cheque.status
    }
    
    @Test
    void AprobarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "EN"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "EN", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.aprobar()
        assertEquals "AP", cheque.status
    }
    
        @Test
    void RechazarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "EN"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "EN", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.rechazar()
        assertEquals "RE", cheque.status
    }
    
        @Test
    void CancelarCheque() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def cheque = new Cheque(
		        empresa: currentUser.empresa
		        , status: "EN"
	      ).save()
        assertNotNull cheque

        def controller = new ChequeController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "EN", cheque.status
		
		controller.params.id = cheque.id
        def model = controller.edita()
        assert model.cheque
        
        controller.cancelar()
        assertEquals "CA", cheque.status
    }
    
}
