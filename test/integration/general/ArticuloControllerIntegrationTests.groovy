package general


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ArticuloController)
class ArticuloControllerIntegrationTests extends BaseIntegrationTest{
	def springSecurityService
    @Test
    void debieraMostrarListaDeArticulos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
			, folio: "333"
		).save()
		assertNotNull compra
		
        for(i in 1..20) {
            new Articulo (
            	descripcion: "objeto$i"
            	, cantidad: 1
            	, compra: compra
            ).save()
        }
        
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/articulo/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.articulos
		
        assertEquals 10, model.articulos.size()
        assert 20 <= model.totalDeArticulos
    }
    
    @Test
    void debieraCrearArticulo() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
			, folio: "333"
		).save()
		assertNotNull compra
		
		def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.articulo
        controller.params.descripcion = "test"
        controller.params.cantidad = "1"
        controller.params.compra = compra
        controller.params.precioUnitario = new BigDecimal("2.00")
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/compra/actualiza')
    }
    
    @Test
    void debieraActualizarArticulo() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
			, folio: "333"
		).save()
		assertNotNull compra
		
        def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: "6"
            	, precioUnitario: "10.00"
            	, total: "60.00"
            	, compra: compra
            ).save()
		assertNotNull articulo
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
        controller.params.id = articulo.id
        def model = controller.ver()
        assert model.articulo
        assertEquals "objeto", model.articulo.descripcion

        controller.params.id = articulo.id
        model = controller.edita()
        assert model.articulo
        assertEquals "objeto", model.articulo.descripcion

        controller.params.id = articulo.id
        controller.params.version = articulo.version
        controller.params.descripcion = '10002'
        controller.params.cantidad = "1"
        controller.params.precioUnitario = new BigDecimal("2.00")
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/compra/actualiza')

        articulo.refresh()
        assertEquals '10002', articulo.descripcion
    }
 
	@Test
    void debieraEliminarArticulo() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
		).save()
		assertNotNull compra
		
        def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: "6"
            	, precioUnitario: "10.00"
            	, total: "60.00"
            	, compra: compra
            ).save()
        
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
        controller.params.id = articulo.id
        def model = controller.ver()
        assert model.articulo
        assertEquals "objeto", model.articulo.descripcion

        controller.params.id = articulo.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/articulo/lista")

        model = Articulo.get(articulo.id)
        assert !model
    }
    
    @Test
    void ComprasDebieraPoderComprarArticulo() {
	    authenticateCompras()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
		).save()
		assertNotNull compra
		
        def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: 6
            	, precioUnitario: 10.00
            	, total: "60.00"
            	, compra: compra
            ).save()
        assertEquals "AGREGADO", articulo.status
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
		
        controller.params.id = articulo.id
        def model = controller.edita()
        assert model.articulo
        
        controller.comprar()
        assertEquals "COMPRADO", articulo.status
 		
		assert controller.response.redirectedUrl.startsWith("/compra/completar")
        logout()
    }
    
    @Test
    void ComprasDebieraPoderEntregarArticulo() {
	    authenticateCompras()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
		).save()
		assertNotNull compra
		
        def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: 6.00
            	, precioUnitario: 10.00
            	, total: 60.00
            	, compra: compra
            ).save()
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
		
        controller.params.id = articulo.id
        def model = controller.edita()
        assert model.articulo
        
        controller.comprar()
        assertEquals "COMPRADO", articulo.status
        
        assert controller.response.redirectedUrl.startsWith("/compra/completar")
        logout()
    }
    
    @Test
    void ArticuloDebieraCalcularElTotal(){
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	def compra = new Compra(
    		empresa: currentUser.empresa
       	).save()
       	
       	def articulo = new Articulo (
            	descripcion: "objeto"
            	, cantidad: 6.00
            	, precioUnitario: 10.00
            	, compra: compra
        )
        assertEquals 6.00, articulo.cantidad
        assertEquals 10.00, articulo.precioUnitario
        articulo.save()
        
        def controller = new ArticuloController()
        controller.springSecurityService = springSecurityService
		
		assertEquals 6.00, articulo.cantidad
        assertEquals 10.00, articulo.precioUnitario
        
        controller.params.id = articulo.id
        def model = controller.edita()
        assert model.articulo
        
        assertEquals 6.00, articulo.cantidad
        assertEquals 10.00, articulo.precioUnitario
        
        controller.actualiza()
        assertEquals 60.00, articulo.total
    }
}
