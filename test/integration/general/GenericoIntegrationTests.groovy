package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import mx.edu.um.rh.Vacaciones
import mx.edu.um.rh.VacacionesController

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CompraController)
class GenericoIntegrationTests extends BaseIntegrationTest {
    def springSecurityService
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
        //compra = procesoServiceInterface.enviar(compra)
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
    
     @Test
    void Cancelar2Compra() {
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
        //compra = procesoServiceInterface.enviar(compra)
        assertEquals "CANCELADA", compra.status
    }
    
     @Test
    void Aprobar2Compra() {
	      authenticateEmp()
		
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
        //compra = procesoServiceInterface.enviar(compra)
        assertEquals "APROBADA", compra.status
    }
    
     @Test
    void Rechazar2Compra() {
	      authenticateEmp()
		
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
        
        controller.rechazar()
        //compra = procesoServiceInterface.enviar(compra)
        assertEquals "RECHAZADA", compra.status
    }
    
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
}
