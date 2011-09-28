package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest
import general.Organizacion
import general.Empresa
import general.Usuario

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class EmpleadoEstudiosControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEmpleadoEstudios() {
		authenticateAdmin()
		
		//crear empleado
    	def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion

		def empresa = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        		
    	def empleado = new Empleado (
			clave: "1110000"
			, nombre: "test"
			, apPaterno: "test"
			, apMaterno: "test"
			, genero: "fm"
			, fechaNacimiento: new Date()
			, direccion: "aqui"
			, status: "23"
			, empresa: empresa
			//
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: "tt"
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	//
        	, estadoCivil: "te"
        	, madre: "test"
        	, padre: "test"
		).save()
		assertNotNull empleado

        //crear nivel de estudios
    	def nivelEstudios = new NivelEstudios(
            nombre: 'test$i'
	    ).save()
		assertNotNull nivelEstudios

		//crear usuario
		def usuario = new Usuario(
		    username: 'test'
            , password: 'test'
            , nombre: 'test'
            , apellido: 'test'
            , correo: 'test'
            , empresa: empresa
        ).save()
        assertNotNull usuario
		
		def currentUser = springSecurityService.currentUser
        
    	List<EmpleadoEstudios> ees = new ArrayList<EmpleadoEstudios>()
        
        for(i in 1..20) {
        	def empleadoEstudios = new EmpleadoEstudios(
        	    empleado: empleado
                , nombre_estudios: 'test'
                , titulado: true
                , fecha_titulacion: new Date()
                , status: 'A'
                , nivel_estudios: nivelEstudios
                , user_captura: usuario
                , fecha_captura: new Date()
		    ).save()
    		assertNotNull empleadoEstudios
    		
    		ees.add(empleadoEstudios)
        }
        
        empleado.estudios = ees
        assertNotNull empleado.estudios

        assertEquals 20, ees.size()

        ArrayList<EmpleadoEstudios> estudiosEmpleado = empleado.estudios
        assertNotNull estudiosEmpleado
        assertEquals empleado, estudiosEmpleado.get(0).empleado

        
    }
/*
    @Test
    	authenticateAdmin()
    void CrearEmpleadoEstidio() {
		
		def currentUser = springSecurityService.currentUser

		def controller = new EmpleadoEstidioController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoEstidio
        controller.params.nombre = "test"
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoEstidio/ver')
    }
    
    @Test
    void ModificarEmpleadoEstidio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def empleadoEstidio = new EmpleadoEstidio(
            nombre: 'test'
	    ).save()
		assertNotNull empleadoEstidio
    		
        def controller = new EmpleadoEstidioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoEstidio.id
        def model = controller.ver()
        assert model.empleadoEstidio
        assertEquals "test", model.empleadoEstidio.nombre

        controller.params.id = empleadoEstidio.id
        model = controller.edita()
        assert model.empleadoEstidio
        assertEquals "test", model.empleadoEstidio.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoEstidio/ver')

        empleadoEstidio.refresh()
        assertEquals 'test1', empleadoEstidio.nombre
    }
    
    @Test
    void EliminarEmpleadoEstidio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def empleadoEstidio = new EmpleadoEstidio(
            nombre: 'test'
	    ).save()
		assertNotNull empleadoEstidio
        
        def controller = new EmpleadoEstidioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoEstidio.id
        def model = controller.ver()
        assert model.empleadoEstidio
        assertEquals "test", model.empleadoEstidio.nombre

        controller.params.id = empleadoEstidio.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/empleadoEstidio/lista")

        model = EmpleadoEstidio.get(empleadoEstidio.id)
        assert !model
    }
*/
}
