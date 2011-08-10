package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(DocumentoController)
class DocumentoControllerIntegrationTests {

	def springSecurityService
    @Test
    void debieraMostrarListaDeArticulos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def concepto = new Concepto (
            descripcion: 'test'
            , status: 'test'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto
        
        for(i in 1..20) {
        	def documento = new Documento(
                descripcion: 'test'
                , naturaleza: 'test'
                , cheque: 'test'
                , observaciones: 'test'
                , status: 'CREADO'
                , importe: new BigDecimal("0.00")
                , iva: new BigDecimal("0.00")
                , empleado: empleado
                , concepto: concepto
                , usuario: currentUser
		    ).save()
    		assertNotNull documento
        }
        
        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/articulo/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.documentos
		
        assertEquals 10, model.documentos.size()
        assert 20 <= model.totalDeDocumentos
    }

}
