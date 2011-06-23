package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ImagenController)
class ImagenTests extends BaseIntegrationTest {


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
        assertEquals '/imagen/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.imagenInstanceList
		
        assertEquals 10, model.imagenInstanceList.size()
        assert 20 <= model.imagenInstanceTotal
    }
    
    @Test
    void debieraCrearImagen() {
    	authenticateAdmin()
		
        def controller = new ImagenController()
        controller.springSecurityService = springSecurityService
        def model = controller.create()
        assert model
        assert model.imagenInstance
        
        controller.params.nombre = "test"
        controller.params.tipoContenido = "imagen"
        controller.params.tamano = 3000
        controller.params.archivo = "Downloads/cosas/tie.gif"
        controller.save()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/imagen/show')
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
        def model = controller.show()
        assert model.imagenInstance
        assertEquals "test", model.imagenInstance.nombre
        controller.params.id = imagen.id
        model = controller.edit()
        assert model.imagenInstance
        assertEquals "imagen", model.imagenInstance.tipoContenido

        controller.params.id = imagen.id
        controller.params.version = imagen.version
        controller.params.nombre = "10002"
        controller.update()
        assertEquals "/imagen/show/${imagen.id}", controller.response.redirectedUrl

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
        def model = controller.show()
        assert model.imagenInstance
        assertEquals "test", model.imagenInstance.nombre

        controller.params.id = imagen.id
        controller.delete()
        assertEquals "/imagen/list", controller.response.redirectedUrl

        model = Imagen.get(imagen.id)
        assert !model
    }
}
