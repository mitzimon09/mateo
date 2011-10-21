package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

class SolicitudVacacionesControllerIntegrationTests extends BaseIntegrationTest{
	def springSecurityService
	def procesoService

    @Test
    void debieraMostrarListaDeSolicitudesDeVacaciones() {
	    authenticateUser()
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
		
		def currentUser = springSecurityService.currentUser
		assertNotNull currentUser
		
		for(i in 1..20) {
			new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, dateCreated: new Date()
			).save()
        }
        
        def controller = new SolicitudVacacionesController()
        controller.index()
        assertEquals '/solicitudVacaciones/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.solicitudesVacaciones
		
        assertEquals 10, model.solicitudesVacaciones.size()
        assert 20 <= model.totalDeSolicitudesVacaciones
    }
    
    @Test
    void debieraCrearSolicitudVacaciones() {
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
		
		def currentUser = springSecurityService.currentUser
		

		def controller = new SolicitudVacacionesController()
        
        def model = controller.nueva()
        assert model
        assert model.solicitudVacaciones
        controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = new Date()
        controller.params.fechaFinal = new Date()
        controller.params.diasVacaciones = 2
        controller.params.destino = "test"
        controller.params.kilometros = 8
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        
        
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/solicitudVacaciones/ver')
    }
    
    @Test
    void debieraActualizarSolicitudVacaciones() {
		
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
		
		def currentUser = springSecurityService.currentUser
		
		def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, dateCreated: new Date()
			).save()
        
        def controller = new SolicitudVacacionesController()

        controller.params.id = solicitudVacaciones.id
        def model = controller.ver()
        assert model.solicitudVacaciones
        assertEquals "test", model.solicitudVacaciones.destino

        controller.params.id = solicitudVacaciones.id
        model = controller.edita()
        assert model.solicitudVacaciones
        assertEquals new Integer(1), model.solicitudVacaciones.kilometros

        controller.params.id = solicitudVacaciones.id
        controller.params.version = solicitudVacaciones.version
        controller.params.destino = "otro"
        controller.params.kilometros = new Integer(5)
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/solicitudVacaciones/ver')

        solicitudVacaciones.refresh()
        assertEquals "otro", solicitudVacaciones.destino
        assertEquals new Integer(5), solicitudVacaciones.kilometros
    }
    
    @Test
    void usuarioEmpleadoEnviaSolicitudVacaciones() {
	      authenticateUser()
	      
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
    	def currentUser = springSecurityService.currentUser
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, startus: "CR"
				, dateCreated: new Date()
			).save()
		
		def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "CR", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.enviar()
        assertEquals "EN", solicitudVacaciones.status
    }
    @Test
    void jefeCCostoDebieraPoderAprobarSolicitudVacaciones() {
		authenticateCCP()
	      
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
    	def currentUser = springSecurityService.currentUser
    	
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.aprobar()
        assertEquals "AP", solicitudVacaciones.status
    }
    
    @Test
    void RHOperDebieraPoderRevisarSolicitudVacaciones() {
		authenticateRHOper()
	      
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
    	def currentUser = springSecurityService.currentUser
    	
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: "AP"
				, dateCreated: new Date()
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "AP", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.revisar()
        assertEquals "RE", solicitudVacaciones.status
    }
    
    @Test
    void RHOperDebieraPoderAutorizarSolicitudVacaciones() {
		authenticateRHOper()
	      
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
    	def currentUser = springSecurityService.currentUser
    	
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: "RE"
				, dateCreated: new Date()
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "RE", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.autorizar()
        assertEquals "AU", solicitudVacaciones.status
    }
    
        @Test
    void jefeCCostoRHOperDirRHDebieranPoderRechazarSolicitudVacaciones() {
	      authenticateCCP()
	      
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
    	def currentUser = springSecurityService.currentUser
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.rechazar()
        assertEquals "RE", solicitudVacaciones.status
    }
    
       @Test
    void dirRHDebieraPoderCancelarSolicitudVacaciones() {
	      authenticateDirRH()
	      
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
    	def currentUser = springSecurityService.currentUser
    	
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.cancelar()
        assertEquals "CA", solicitudVacaciones.status
    }
    
    @Test
    void debieraAcumularObservaciones() {
		
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
		
		def currentUser = springSecurityService.currentUser
		
		def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, dateCreated: new Date()
			).save()
        
        def controller = new SolicitudVacacionesController()

        controller.params.id = solicitudVacaciones.id
        def model = controller.ver()
        assert model.solicitudVacaciones
        assertEquals "test", model.solicitudVacaciones.destino

        controller.params.id = solicitudVacaciones.id
        model = controller.edita()
        assert model.solicitudVacaciones
        assertEquals new Integer(1), model.solicitudVacaciones.kilometros

        controller.params.id = solicitudVacaciones.id
        controller.params.version = solicitudVacaciones.version
        controller.params.destino = "otro"
        controller.params.kilometros = new Integer(5)
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/solicitudVacaciones/ver')

        solicitudVacaciones.refresh()
        assertEquals "otro", solicitudVacaciones.destino
        assertEquals new Integer(5), solicitudVacaciones.kilometros
    }
}
