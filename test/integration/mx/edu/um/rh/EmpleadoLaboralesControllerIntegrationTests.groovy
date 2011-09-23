package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EmpleadoLaboralesController)
class EmpleadoLaboralesControllerIntegrationTests {
	@Test
    void debieraMostrarListaDeEmpleadoLaboraless() {
		
		for(i in 1..20) {
			def empleadoLaborales = new EmpleadoLaborales (
		    	escalafon: 3
		    	, turno: 1
		    	, rfc: "1234567890123$i"
		    	, curp: "1234"
		    	, modalidad: "tt"
		    	, fechaAlta: new Date()
		    	, antiguedadBase: new BigDecimal(0.00)
        		, antiguedadFiscal: new BigDecimal(0.00)
		    	//, empleado: empleado
		    ).save()
        }
        
        def controller = new EmpleadoLaboralesController()
        controller.index()
        assertEquals '/empleadoLaborales/list', controller.response.redirectedUrl
		
		def model = controller.list()
		assertNotNull model
		assertNotNull model.empleadoLaboraless
		
        assertEquals 10, model.empleadoLaborales.size()
        assert 20 <= model.totalDeEmpleadoLaborales
    }
    
    @Test
    void debieraCrearEmpleadoLaborales() {

		def controller = new EmpleadoLaboralesController()
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoLaborales
        controller.params.escalafon = 3
        controller.params.turno = 1
        controller.params.rfc = "12345678901234"
        controller.params.curp = "1232"
        controller.params.modalidad = "tt"
        controller.params.fechaAlta = new Date()
        controller.params.antiguedadBase = new BigDecimal(0.00)
        controller.params.antiguedadFiscal = new BigDecimal(0.00)
        
        controller.save()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoLaborales/show')
    }
    
    @Test
    void debieraActualizarEmpleadoLaborales() {
		
		def empleadoLaborales = new EmpleadoLaborales (
		    	escalafon: 3
		    	, turno: 1
		    	, rfc: "1234567890123$i"
		    	, curp: "1234"
		    	, modalidad: "tt"
		    	, fechaAlta: new Date()
		    	, antiguedadBase: new BigDecimal(0.00)
        		, antiguedadFiscal: new BigDecimal(0.00)
		    	//, empleado: empleado
		    ).save()
        
        def controller = new EmpleadoLaboralesController()

        controller.params.id = empleadoLaborales.id
        def model = controller.ver()
        assert model.empleadoLaborales
        assertEquals 3, model.empleadoLaborales.escalafon

        controller.params.id = empleadoLaborales.id
        model = controller.edita()
        assert model.empleadoLaborales
        assertEquals 1, model.empleadoLaborales.turno

        controller.params.id = empleadoLaborales.id
        controller.params.version = empleadoLaborales.version
        controller.params.escalafom = 2
        controller.params.turno = 2
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoLaborales/show')

        empleadoLaborales.refresh()
        assertEquals 2, empleadoLaborales.escalafon
        assertEquals 2, empleadoLaborales.turno
    }
}
