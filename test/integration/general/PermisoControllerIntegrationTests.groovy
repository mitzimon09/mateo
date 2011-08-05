package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import interfaces.ProcesoServiceInterface

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class PermisoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
    def procesoServiceInterface
    def procesoService

    @Test
    void EnviarPermiso() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def permiso = new Permiso(
		        empresa: currentUser.empresa
	      ).save()
        assertNotNull permiso

        def controller = new PermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "CREADA", permiso.status
		
		controller.params.id = permiso.id
        def model = controller.edita()
        assert model.permiso
        
        controller.enviar()
        assertEquals "ENVIADA", permiso.status
    }
    
    @Test
    void AprobarPermiso() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def permiso = new Permiso(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull permiso

        def controller = new PermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", permiso.status
		
		controller.params.id = permiso.id
        def model = controller.edita()
        assert model.permiso
        
        controller.aprobar()
        assertEquals "APROBADA", permiso.status
    }
    
        @Test
    void RechazarPermiso() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def permiso = new Permiso(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull permiso

        def controller = new PermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", permiso.status
		
		controller.params.id = permiso.id
        def model = controller.edita()
        assert model.permiso
        
        controller.rechazar()
        assertEquals "RECHAZADA", permiso.status
    }
    
        @Test
    void CancelarPermiso() {
	      authenticateEmp()
		
        def currentUser = springSecurityService.currentUser
		    def permiso = new Permiso(
		        empresa: currentUser.empresa
		        , status: "ENVIADA"
	      ).save()
        assertNotNull permiso

        def controller = new PermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "ENVIADA", permiso.status
		
		controller.params.id = permiso.id
        def model = controller.edita()
        assert model.permiso
        
        controller.cancelar()
        assertEquals "CANCELADA", permiso.status
    }


}
