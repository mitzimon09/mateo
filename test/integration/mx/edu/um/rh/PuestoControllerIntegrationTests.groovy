package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(PuestoController)
class PuestoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDePuestos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def categoria = new Categoria(
            nombre: 'test'
        ).save()
    	
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: '1'
            , minimo: '1'
            , rango_academico: '1'
            , categoria: categoria
	    ).save()
        
        for(i in 1..20) {
        	def puesto = new Puesto(
                nombre: 'test$1'
                , maximo: 1
                , minimo: 1
                , seccion: seccion
	        ).save()
		    assertNotNull puesto
        }

        def controller = new PuestoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/puesto/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.puestos

        assertEquals 10, model.puestos.size()
        assert 20 <= model.totalDePuestos
    }

    @Test
    void CrearPuesto() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new PuestoController()
        controller.springSecurityService = springSecurityService
        
        def categoria = new Categoria(
            nombre: 'test'
        ).save()
    	
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: '1'
            , minimo: '1'
            , rango_academico: '1'
            , categoria: categoria
	    ).save()

        def model = controller.nuevo()
        assert model
        assert model.puesto
        controller.params.nombre = "test"
        controller.params.maximo = '1'
        controller.params.minimo = '1'
        controller.params.seccion = seccion
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/puesto/ver')
    }
    
    @Test
    void ModificarPuesto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def categoria = new Categoria(
            nombre: 'test'
        ).save()
    	
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: '1'
            , minimo: '1'
            , rango_academico: '1'
            , categoria: categoria
	    ).save()
        
    	def puesto = new Puesto(
            nombre: 'test'
            , maximo: 1
            , minimo: 1
            , seccion: seccion
	    ).save()
		assertNotNull puesto
    		
        def controller = new PuestoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = puesto.id
        def model = controller.ver()
        assert model.puesto
        assertEquals "test", model.puesto.nombre

        controller.params.id = puesto.id
        model = controller.edita()
        assert model.puesto
        assertEquals "test", model.puesto.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/puesto/ver')

        puesto.refresh()
        assertEquals 'test1', puesto.nombre
    }
    
    @Test
    void EliminarPuesto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def categoria = new Categoria(
            nombre: 'test'
        ).save()
    	
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: '1'
            , minimo: '1'
            , rango_academico: '1'
            , categoria: categoria
	    ).save()
        
    	def puesto = new Puesto(
            nombre: 'test'
            , maximo: 1
            , minimo: 1
            , seccion: seccion
	    ).save()
		assertNotNull puesto
        
        def controller = new PuestoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = puesto.id
        def model = controller.ver()
        assert model.puesto
        assertEquals "test", model.puesto.nombre

        controller.params.id = puesto.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/puesto/lista")

        model = Puesto.get(puesto.id)
        assert !model
    }
}
