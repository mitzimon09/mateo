package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(DiasFeriadosController)
class DiasFeriadosControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeDiasFeriadoss() {
		authenticateRHOper()
		
		def currentUser = springSecurityService.currentUser
        println("user >" + currentUser)
        for(i in 1..20) {
        	def diasFeriados = new DiasFeriados(
                descripcion: 'test$i'
                , fecha: new Date()
                , user: currentUser
		    ).save()
    		assertNotNull diasFeriados
        }

        def controller = new DiasFeriadosController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/diasFeriados/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.diasFeriados

        assertEquals 10, model.diasFeriados.size()
        assert 20 <= model.totalDeDiasFeriados
    }

    @Test
    void CrearDiasFeriados() {
    	authenticateRHOper()

		def controller = new DiasFeriadosController()
        controller.springSecurityService = springSecurityService
		def currentUser = springSecurityService.currentUser
        
        def model = controller.nuevo()
        assert model
        assert model.diasFeriados
        controller.params.descripcion = "test"
        controller.params.fecha = new Date()
        controller.params.user = currentUser
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/diasFeriados/ver')
    }
    
    @Test
    void ModificarDiasFeriados() {
        authenticateRHOper()
		
		def currentUser = springSecurityService.currentUser

    	def diasFeriados = new DiasFeriados(
            descripcion: 'test'
            , fecha: new Date()
            , user: currentUser
	    ).save()
		assertNotNull diasFeriados
    		
        def controller = new DiasFeriadosController()
        controller.springSecurityService = springSecurityService
        controller.params.id = diasFeriados.id
        def model = controller.ver()
        assert model.diasFeriados
        assertEquals "test", model.diasFeriados.descripcion

        controller.params.id = diasFeriados.id
        model = controller.edita()
        assert model.diasFeriados
        assertEquals "test", model.diasFeriados.descripcion

        controller.params.descripcion = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/diasFeriados/ver')

        diasFeriados.refresh()
        assertEquals 'test1', diasFeriados.descripcion
    }
    
    @Test
    void EliminarDiasFeriados() {
        authenticateRHOper()
		
		def currentUser = springSecurityService.currentUser
    	
    	def diasFeriados = new DiasFeriados(
            descripcion: 'test'
            , fecha: new Date()
            , user: currentUser
	    ).save()
		assertNotNull diasFeriados
        
        def controller = new DiasFeriadosController()
        controller.springSecurityService = springSecurityService
        controller.params.id = diasFeriados.id
        def model = controller.ver()
        assert model.diasFeriados
        assertEquals "test", model.diasFeriados.descripcion

        controller.params.id = diasFeriados.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/diasFeriados/lista")

        model = DiasFeriados.get(diasFeriados.id)
        assert !model
    }
}
