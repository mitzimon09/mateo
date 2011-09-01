package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SeccionController)
class SeccionControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeSeccions() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def categoria = new Categoria(
            nombre: 'test'
        ).save()
        
        for(i in 1..20) {
        	def seccion = new Seccion(
                descripcion: 'test$i'
                , maximo: '1'
                , minimo: '1'
                , rango_academico: '1'
                , categoria: categoria
		    ).save()
    		assertNotNull seccion
        }

        def controller = new SeccionController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/seccion/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.seccions

        assertEquals 10, model.seccions.size()
        assert 20 <= model.totalDeSeccions
    }

    @Test
    void CrearSeccion() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new SeccionController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()

        def model = controller.nuevo()
        assert model
        assert model.seccion
        controller.params.descripcion = "test"
        controller.params.maximo = '1'
        controller.params.minimo = '1'
        controller.params.rango_academico = '1'
        controller.params.categoria = categoria
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/seccion/ver')
    }
    
    @Test
    void ModificarSeccion() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def categoria = new Categoria(
            nombre: 'test'
        ).save()
        
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: 1
            , minimo: 1
            , rango_academico: 1
            , categoria: categoria
	    ).save()
		assertNotNull seccion
    		
        def controller = new SeccionController()
        controller.springSecurityService = springSecurityService
        controller.params.id = seccion.id
        def model = controller.ver()
        assert model.seccion
        assertEquals "test", model.seccion.descripcion

        controller.params.id = seccion.id
        model = controller.edita()
        assert model.seccion
        assertEquals "test", model.seccion.descripcion

        controller.params.descripcion = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/seccion/ver')

        seccion.refresh()
        assertEquals 'test1', seccion.descripcion
    }
    
    @Test
    void EliminarSeccion() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def categoria = new Categoria(
            nombre: 'test'
        ).save()
        
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: 1
            , minimo: 1
            , rango_academico: 1
            , categoria: categoria
	    ).save()
		assertNotNull seccion
        
        def controller = new SeccionController()
        controller.springSecurityService = springSecurityService
        controller.params.id = seccion.id
        def model = controller.ver()
        assert model.seccion
        assertEquals "test", model.seccion.descripcion

        controller.params.id = seccion.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/seccion/lista")

        model = Seccion.get(seccion.id)
        assert !model
    }
}
