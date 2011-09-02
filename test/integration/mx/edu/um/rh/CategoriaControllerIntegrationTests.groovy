package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CategoriaController)
class CategoriaControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeCategorias() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def categoria = new Categoria(
                nombre: 'test$i' 
		    ).save()
    		assertNotNull categoria
        }

        def controller = new CategoriaController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/categoria/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.categorias

        assertEquals 10, model.categorias.size()
        assert 20 <= model.totalDeCategorias
    }

    @Test
    void CrearCategoria() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new CategoriaController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.categoria
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/categoria/ver')
    }
    
    @Test
    void ModificarCategoria() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def categoria = new Categoria(
            nombre: 'test'
	    ).save()
		assertNotNull categoria
    		
        def controller = new CategoriaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = categoria.id
        def model = controller.ver()
        assert model.categoria
        assertEquals "test", model.categoria.nombre

        controller.params.id = categoria.id
        model = controller.edita()
        assert model.categoria
        assertEquals "test", model.categoria.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/categoria/ver')

        categoria.refresh()
        assertEquals 'test1', categoria.nombre
    }
    
    @Test
    void EliminarCategoria() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def categoria = new Categoria(
            nombre: 'test'
	    ).save()
		assertNotNull categoria
        
        def controller = new CategoriaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = categoria.id
        def model = controller.ver()
        assert model.categoria
        assertEquals "test", model.categoria.nombre

        controller.params.id = categoria.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/categoria/lista")

        model = Categoria.get(categoria.id)
        assert !model
    }
}
