package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(GrupoController)
class GrupoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeGrupos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def grupo = new Grupo(
                nombre: "$i"
                , maximo: '1'
                , minimo: '1'
		    ).save()
    		assertNotNull grupo
        }

        def controller = new GrupoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/grupo/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.grupos

        assertEquals 10, model.grupos.size()
        assert 20 <= model.totalDeGrupos
    }

    @Test
    void CrearGrupo() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new GrupoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.grupo
        controller.params.nombre = '1'
        controller.params.maximo = '1'
        controller.params.minimo = '1'
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/grupo/ver')
    }
    
    @Test
    void ModificarGrupo() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def grupo = new Grupo(
            nombre: "1"
            , maximo: 1
            , minimo: 1
	    ).save()
		assertNotNull grupo
    		
        def controller = new GrupoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = grupo.id
        def model = controller.ver()
        assert model.grupo
        assertEquals "1", model.grupo.nombre

        controller.params.id = grupo.id
        model = controller.edita()
        assert model.grupo
        assertEquals "1", model.grupo.nombre

        controller.params.nombre = '2'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/grupo/ver')

        grupo.refresh()
        assertEquals '2', grupo.nombre
    }
    
    @Test
    void EliminarGrupo() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def grupo = new Grupo(
            nombre: '1'
            , maximo: 1
            , minimo: 1
	    ).save()
		assertNotNull grupo
        
        def controller = new GrupoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = grupo.id
        def model = controller.ver()
        assert model.grupo
        assertEquals "1", model.grupo.nombre

        controller.params.id = grupo.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/grupo/lista")

        model = Grupo.get(grupo.id)
        assert !model
    }
}
