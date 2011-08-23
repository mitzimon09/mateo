package general

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(DocumentoController)
class DocumentoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	def empleadoServiceInt
	
    @Test
    void MostrarListaDeDocumentos() {
		authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def concepto = new Concepto (
            descripcion: 'test'
            , status: 'test'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        System.out.println( "empleado " + empleado)
        
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
                , fecha: new Date()
		    ).save()
    		assertNotNull documento
        }

        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/documento/lista', controller.response.redirectedUrl

        /*		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.documentos

        assertEquals 10, model.documentos.size()
        assert 20 <= model.totalDeDocumentos
        */
    }

    @Test
    void CrearDocumento() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def concepto = new Concepto (
            descripcion: 'test'
            , status: 'test'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        System.out.println( "empleado " + empleado)
        
		def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.crea()
        assert model
        assert model.documento
        controller.params.descripcion = "test"
        controller.params.naturaleza = 'test'
        controller.params.cheque = 'test'
        controller.params.observaciones = 'test'
        controller.params.status = 'CREADO'
        controller.params.importe = new BigDecimal("0.00")
        controller.params.iva = new BigDecimal("0.00")
        controller.params.empleado = empleado
        controller.params.concepto = concepto
        controller.params.usuario = currentUser
        controller.params.fecha = new Date()
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/documento/edita')
    }
    
    @Test
    void ModificarDocumento() {
        authenticateAdmin()
		
		def concepto = new Concepto (
            descripcion: 'test'
            , status: 'test'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        System.out.println( "empleado " + empleado)
        
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
            , fecha: new Date()
	    ).save()
		assertNotNull documento
    		
        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = documento.id
        def model = controller.show()
        assert model.documento
        assertEquals "test", model.documento.descripcion

        controller.params.id = documento.id
        model = controller.edita()
        assert model.documento
        assertEquals "test", model.documento.descripcion

        controller.params.descripcion = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/documento/edita')

        articulo.refresh()
        assertEquals 'test1', documento.descripcion
    }
}
