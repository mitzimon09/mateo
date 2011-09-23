package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SobresueldoController)
class SobresueldoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
    def procesoServiceInterface
    def procesoService

    @Test
    void EnviarSobresueldo() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def sobresueldo = new Sobresueldo(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull sobresueldo

        def controller = new SobresueldoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "CREADA", sobresueldo.status
		
		controller.params.id = sobresueldo.id
        def model = controller.edita()
        assert model.sobresueldo
        
        controller.enviar()
        assertEquals "ENVIADA", sobresueldo.status
    }
    
    @Test
    void AprobarSobresueldo() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def sobresueldo = new Sobresueldo(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull sobresueldo

        def controller = new SobresueldoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", sobresueldo.status
		
		controller.params.id = sobresueldo.id
        def model = controller.edita()
        assert model.sobresueldo
        
        controller.aprobar()
        assertEquals "APROBADA", sobresueldo.status
    }
    
        @Test
    void RechazarSobresueldo() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def sobresueldo = new Sobresueldo(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull sobresueldo

        def controller = new SobresueldoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", sobresueldo.status
		
		controller.params.id = sobresueldo.id
        def model = controller.edita()
        assert model.sobresueldo
        
        controller.rechazar()
        assertEquals "RECHAZADA", sobresueldo.status
    }
    
    @Test
    void CancelarSobresueldo() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def sobresueldo = new Sobresueldo(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull sobresueldo

        def controller = new SobresueldoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", sobresueldo.status
		
		controller.params.id = sobresueldo.id
        def model = controller.edita()
        assert model.sobresueldo
        
        controller.cancelar()
        assertEquals "CANCELADA", sobresueldo.status
    }
    
        @Test
    void RevisarSobresueldo() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def sobresueldo = new Sobresueldo(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull sobresueldo

        def controller = new SobresueldoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", sobresueldo.status
		
		controller.params.id = sobresueldo.id
        def model = controller.edita()
        assert model.sobresueldo
        
        controller.revisar()
        assertEquals "REVISADA", sobresueldo.status
    }
    
}
