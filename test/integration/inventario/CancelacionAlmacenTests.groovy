package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CancelacionAlmacenController)
class CancelacionAlmacenTests extends BaseIntegrationTest {
def springSecurityService
    @Test
    void debieraMostrarListaDeEntradas() {
		authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		assertNotNull almacen
		
        for(i in 1..20) {
            new CancelacionAlmacen(
            	folio: "10$i"
            	, creador: "persona"
            	, almacen: almacen
            ).save()
        }
        
		def currentUser = springSecurityService.currentUser
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/cancelacionAlmacen/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.cancelacionAlmacenInstanceList
		
        assertEquals 10, model.cancelacionAlmacenInstanceList.size()
        assert 20 <= model.cancelacionAlmacenInstanceTotal
    }
    
    @Test
    void debieraCrearEntrada() {
    	authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        
        //controller.springSecurityService = springSecurityService
        def model = controller.create()
        assert model
        assert model.cancelacionAlmacenInstance
        
        controller.params.folio = "100"
        controller.params.creador = "persona"
        controller.params.almacen = almacen
        controller.save()
        
        assert controller
 
        assert controller.response.redirectedUrl.startsWith('/cancelacionAlmacen/show')
    }
    
    @Test
    void debieraActualizarEntrada() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		
		def cancelacionAlmacen = new CancelacionAlmacen(
            	folio: "100"
            	, creador: "persona"
            	, almacen: almacen
            ).save()
		
		assertNotNull cancelacionAlmacen
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cancelacionAlmacen.id
        def model = controller.show()
        assert model.cancelacionAlmacenInstance
        assertEquals "100", model.cancelacionAlmacenInstance.folio

        controller.params.id = cancelacionAlmacen.id
        model = controller.edit()
        assert model.cancelacionAlmacenInstance
        assertEquals "persona", model.cancelacionAlmacenInstance.creador

        controller.params.id = cancelacionAlmacen.id
        controller.params.version = cancelacionAlmacen.version
        controller.params.folio = '10002'
        controller.update()
        assertEquals "/cancelacionAlmacen/show/${cancelacionAlmacen.id}", controller.response.redirectedUrl

        cancelacionAlmacen.refresh()
        assertEquals '10002', cancelacionAlmacen.folio
    }
 
	@Test
    void debieraEliminarEntrada() {
        authenticateAdmin()
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        
		def empresa1 = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
		
		def almacen = new Almacen(
			codigo: '222'
			, nombre: "almacen1"
			, empresa: empresa1			
		).save()
		
		def cancelacionAlmacen = new CancelacionAlmacen(
            	folio: "100"
            	, creador: "persona"
            	, almacen: almacen
            ).save()
        
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cancelacionAlmacen.id
        def model = controller.show()
        assert model.cancelacionAlmacenInstance
        assertEquals "100", model.cancelacionAlmacenInstance.folio

        controller.params.id = cancelacionAlmacen.id
        controller.delete()
        assertEquals "/cancelacionAlmacen/list", controller.response.redirectedUrl

        model = CancelacionAlmacen.get(cancelacionAlmacen.id)
        assert !model
    }
}
