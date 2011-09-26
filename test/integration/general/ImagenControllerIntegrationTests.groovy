package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ImagenController)
class ImagenControllerIntegrationTests extends BaseIntegrationTest {


	def springSecurityService
    @Test
    void debieraMostrarListaDeImagenes() {
		authenticateAdmin()
		
		
        for(i in 1..20) {
        	new Imagen(
        		nombre: "test"
        		, tipoContenido: "imagen"
        		, tamano: 50000
        		, archivo: "/Downloads/cosas/tie.gif"
        	).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new ImagenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/imagen/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.imagenes
		
        assertEquals 10, model.imagenes.size()
        assert 20 <= model.totalDeImagenes
    }
    
    @Test
    void debieraCrearImagen() {
    	authenticateAdmin()
		
        def controller = new ImagenController()
        controller.springSecurityService = springSecurityService
        def model = controller.nueva()
        assert model
        assert model.imagen
        
        controller.params.nombre = "test"
        controller.params.tipoContenido = "imagen"
        controller.params.tamano = 3000
        controller.params.archivo = "Downloads/cosas/tie.gif"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/imagen/ver')
    }
    
    @Test
    void debieraActualizarImagen() {
        authenticateAdmin()
		
		def imagen = new Imagen(
			nombre: "test"
			, tipoContenido: "imagen"
        	, tamano: 50000
        	, archivo: "/Downloads/cosas/tie.gif"
		).save()
		
        def controller = new ImagenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = imagen.id
        def model = controller.ver()
        assert model.imagen
        assertEquals "test", model.imagen.nombre
        controller.params.id = imagen.id
        model = controller.edita()
        assert model.imagen
        assertEquals "imagen", model.imagen.tipoContenido

        controller.params.id = imagen.id
        controller.params.version = imagen.version
        controller.params.nombre = "10002"
        controller.actualiza()
        assertEquals "/imagen/ver/${imagen.id}", controller.response.redirectedUrl

        imagen.refresh()
        assertEquals "10002", imagen.nombre
    }
 
	@Test
    void debieraEliminarImagen() {
        authenticateAdmin()
		
		def imagen = new Imagen(
			nombre: "test"
			, tipoContenido: "imagen"
       		, tamano: 50000
       		, archivo: "/Downloads/cosas/tie.gif"
		).save()
        
        def controller = new ImagenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = imagen.id
        def model = controller.ver()
        assert model.imagen
        assertEquals "test", model.imagen.nombre

        controller.params.id = imagen.id
        controller.elimina()
        assertEquals "/imagen/lista", controller.response.redirectedUrl

        model = Imagen.get(imagen.id)
        assert !model
    }
}
