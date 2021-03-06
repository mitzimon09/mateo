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
            , status: 'AP'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
        for(i in 1..20) {
        	def documento = new Documento(
                descripcion: 'test'
                , naturaleza: 'C'
                , cheque: 'N'
                , observaciones: 'test'
                , status: 'CR'
                , importe: new BigDecimal("0.00")
                , iva: new BigDecimal("0.00")
                , empleado: empleado
                , concepto: concepto
                , user: currentUser
                , fecha: new Date()
		    ).save()
    		assertNotNull documento
        }

        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/documento/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.documentos

        assertEquals 10, model.documentos.size()
        assert 20 <= model.totalDeDocumentos
    }

    @Test
    void CrearDocumento() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def concepto = new Concepto (
            descripcion: 'test'
            , status: 'AP'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
		def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.documento
        controller.params.descripcion = "test"
        controller.params.naturaleza = 'C'
        controller.params.cheque = 'N'
        controller.params.observaciones = 'test'
        controller.params.status = 'CR'
        controller.params.importe = new BigDecimal("0.00")
        controller.params.iva = new BigDecimal("0.00")
        controller.params.empleado = empleado
        controller.params.concepto = concepto
        controller.params.user = currentUser
        controller.params.fecha = new Date()
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/documento/ver')
    }
    
    @Test
    void ModificarDocumento() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def concepto = new Concepto (
            descripcion: 'test'
            , status: 'AP'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
        def documento = new Documento(
            descripcion: 'test'
            , naturaleza: 'C'
            , cheque: 'N'
            , observaciones: 'test'
            , status: 'CR'
            , importe: new BigDecimal("0.00")
            , iva: new BigDecimal("0.00")
            , empleado: empleado
            , concepto: concepto
            , user: currentUser
            , fecha: new Date()
	    ).save()
		assertNotNull documento
    		
        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = documento.id
        def model = controller.ver()
        assert model.documento
        assertEquals "test", model.documento.descripcion

        controller.params.id = documento.id
        model = controller.edita()
        assert model.documento
        assertEquals "test", model.documento.descripcion

        controller.params.descripcion = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/documento/ver')

        documento.refresh()
        assertEquals 'test1', documento.descripcion
    }
    
    @Test
    void EliminarDocumento() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def concepto = new Concepto (
            descripcion: 'test'
            , status: 'AP'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
    	def documento = new Documento(
            descripcion: 'test'
            , naturaleza: 'C'
            , cheque: 'N'
            , observaciones: 'test'
            , status: 'CR'
            , importe: new BigDecimal("0.00")
            , iva: new BigDecimal("0.00")
            , empleado: empleado
            , concepto: concepto
            , user: currentUser
            , fecha: new Date()
	    ).save()
		assertNotNull documento
        
        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = documento.id
        def model = controller.ver()
        assert model.documento
        assertEquals "test", model.documento.descripcion

        controller.params.id = documento.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/documento/lista")

        model = Documento.get(documento.id)
        assert !model
    }
    
    @Test
    void EnviarDocumento() {
        authenticateAdmin()
		
        def currentUser = springSecurityService.currentUser
        def procesoService
    	
    	def concepto = new Concepto (
            descripcion: 'test'
            , status: 'AP'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
    	def documento = new Documento(
            descripcion: 'test'
            , naturaleza: 'C'
            , cheque: 'N'
            , observaciones: 'test'
            , status: 'CR'
            , importe: new BigDecimal("0.00")
            , iva: new BigDecimal("0.00")
            , empleado: empleado
            , concepto: concepto
            , user: currentUser
            , fecha: new Date()
	    ).save()
		assertNotNull documento

        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "CR", documento.status
        
        controller.params.id = documento.id
        def model = controller.ver()
        assert model.documento
        
        controller.enviar()
        assertEquals "E", documento.status
    }

    @Test
    void RevisarDocumento() {
        authenticateAdmin()
		
        def currentUser = springSecurityService.currentUser
        def procesoService
    	
    	def concepto = new Concepto (
            descripcion: 'test'
            , status: 'A'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
    	def documento = new Documento(
            descripcion: 'test'
            , naturaleza: 'C'
            , cheque: 'N'
            , observaciones: 'test'
            , status: 'E'
            , importe: new BigDecimal("0.00")
            , iva: new BigDecimal("0.00")
            , empleado: empleado
            , concepto: concepto
            , user: currentUser
            , fecha: new Date()
	    ).save()
		assertNotNull documento

        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
        controller.procesoService = procesoService
		
        assertEquals "E", documento.status
        
        controller.params.id = documento.id
        def model = controller.ver()
        assert model.documento
        
        controller.revisar()
        assertEquals "RE", documento.status
    }

    @Test
    void AutorizarDocumento() {
        authenticateAdmin()
		
        def currentUser = springSecurityService.currentUser
        def procesoService
    	
    	def concepto = new Concepto (
            descripcion: 'test'
            , status: 'A'
            , nombre: 'test'
            , tags: 'test'
        ).save()
        assertNotNull concepto

        def clave="9800052"
        def empleado = empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        
    	def documento = new Documento(
            descripcion: 'test'
            , naturaleza: 'C'
            , cheque: 'N'
            , observaciones: 'test'
            , status: 'RE'
            , importe: new BigDecimal("0.00")
            , iva: new BigDecimal("0.00")
            , empleado: empleado
            , concepto: concepto
            , user: currentUser
            , fecha: new Date()
	    ).save()
		assertNotNull documento

        def controller = new DocumentoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
        assertEquals "RE", documento.status
		
		controller.params.id = documento.id
        def model = controller.ver()
        assert model.documento
        
        controller.autorizar()
        assertEquals "AU", documento.status
    }
}
