package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(DiasFeriadosController)
class DiasFeriadosControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeDiasFeriadoss() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def diasFeriados = new DiasFeriados(
                descripcion: 'test$i'
                , diadado: 's'
                , fecharegistro: new Date()
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
		assertNotNull model.diasFeriadoss

        assertEquals 10, model.diasFeriadoss.size()
        assert 20 <= model.totalDeDiasFeriadoss
    }

    @Test
    void CrearDiasFeriados() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new DiasFeriadosController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.diasFeriados
        controller.params.descripcion = "test"
        controller.params.diadado = 's'
        controller.params.fecharegistro = new Date()
        controller.params.fecha = new Date()
        controller.params.user = currentUser
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/diasFeriados/ver')
    }
    
    @Test
    void ModificarDiasFeriados() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def diasFeriados = new DiasFeriados(
            descripcion: 'test'
            , diadado: 's'
            , fecharegistro: new Date()
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
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def diasFeriados = new DiasFeriados(
            descripcion: 'test'
            , diadado: 's'
            , fecharegistro: new Date()
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
