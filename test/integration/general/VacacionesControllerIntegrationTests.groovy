package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(VacacionesController)
class VacacionesControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
    def procesoServiceInterface
    def procesoService

    @Test
    void EnviarVacaciones() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def vacaciones = new Vacaciones(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull vacaciones

        def controller = new VacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "CREADA", vacaciones.status
		
		controller.params.id = vacaciones.id
        def model = controller.edita()
        assert model.vacaciones
        
        controller.enviar()
        assertEquals "ENVIADA", vacaciones.status
    }
    
    @Test
    void AprobarVacaciones() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def vacaciones = new Vacaciones(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull vacaciones

        def controller = new VacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", vacaciones.status
		
		controller.params.id = vacaciones.id
        def model = controller.edita()
        assert model.vacaciones
        
        controller.aprobar()
        assertEquals "APROBADA", vacaciones.status
    }
    
        @Test
    void RechazarVacaciones() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def vacaciones = new Vacaciones(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull vacaciones

        def controller = new VacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", vacaciones.status
		
		controller.params.id = vacaciones.id
        def model = controller.edita()
        assert model.vacaciones
        
        controller.rechazar()
        assertEquals "RECHAZADA", vacaciones.status
    }
    
        @Test
    void CancelarVacaciones() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def vacaciones = new Vacaciones(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull vacaciones

        def controller = new VacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", vacaciones.status
		
		controller.params.id = vacaciones.id
        def model = controller.edita()
        assert model.vacaciones
        
        controller.cancelar()
        assertEquals "CANCELADA", vacaciones.status
    }
}
