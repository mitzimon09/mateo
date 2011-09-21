package mx.edu.um.rh

import general.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SolicitudRHController)
class SolicitudRHControllerIntegrationTests extends BaseIntegrationTest{

	def springSecurityService
	def procesoService
	
	@Test
	void usuarioCreaUnaSolicitudRH(){
		
		def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

		def empresa = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
    
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
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
		
		def controller = new SolicitudRHController()
		
		def model = controller.nueva()
        assert model
        assert model.solicitudRH
		
		controller.params.empresa = empresa
		controller.params.empleado = usuarioEmpleado.empleado
		controller.params.fechaInicial = new Date()
		controller.params.fechaFinal = new Date()
		controller.params.usuarioCrea = usuarioEmpleado.usuario
		controller.params.dateCreated = new Date()
		controller.crea()
		
		assert controller
		assert controller.response.redirectedUrl.startsWith('/solicitudRH/ver')
		
	}
	
	@Test
    void usuarioEmpleadoEnviaSolicitudRH() {
	      authenticateEmp()
	      
	      def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

		def empresa = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
    
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
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
    	).save()
		
		def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "CR", solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.enviar()
        assertEquals "EN", solicitudRH.status
    }
    
    @Test
    void jefeCCostoDebieraPoderAprobarSolicitudRH() {
		authenticateEmp()
	      
	      def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

		def empresa = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
    
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
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, status: "EN"
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.aprobar()
        assertEquals "AP", solicitudRH.status
    }
    
        @Test
    void jefeCCostoDebieraPoderRechazarCheque() {
	      authenticateEmp()
	      
	      def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

		def empresa = new Empresa(
                codigo: "emp2"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
    
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
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, status: "EN"
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.rechazar()
        assertEquals "RE", solicitudRH.status
    }
    
    @Test
    void debieraMostrarListaPorJefeCCosto(){
    	
    }
    
}
