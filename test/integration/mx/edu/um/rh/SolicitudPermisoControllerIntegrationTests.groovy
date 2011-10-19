package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

class SolicitudPermisoControllerIntegrationTests extends BaseIntegrationTest{

    def springSecurityService
	def procesoService

    @Test
    void debieraMostrarListaDeSolicitudesDePermisos() {
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
			new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, dateCreated: new Date()
				, motivo: "test"
			).save()
        }
        
        def controller = new SolicitudPermisoController()
        controller.index()
        assertEquals '/solicitudPermiso/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
		assertNotNull model
		assertNotNull model.solicitudesPermiso
		
        assertEquals 10, model.solicitudesPermiso.size()
        assert 20 <= model.totalSolicitudesPermiso
    }
    
    @Test
    void debieraCrearSolicitudPermiso() {
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
		

		def controller = new SolicitudPermisoController()
        
        def model = controller.nueva()
        assert model
        assert model.solicitudPermiso
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
        controller.params.motivo = "test"
        
        
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/solicitudPermiso/ver')
    }
    
    @Test
    void debieraActualizarSolicitudPermiso() {
		
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
		
		def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: currentUser
				, dateCreated: new Date()
				, motivo: "test"
			).save()
        
        def controller = new SolicitudPermisoController()

        controller.params.id = solicitudPermiso.id
        def model = controller.ver()
        assert model.solicitudPermiso
        assertEquals "test", model.solicitudPermiso.motivo

        controller.params.id = solicitudPermiso.id
        controller.params.version = solicitudPermiso.version
        controller.params.destino = "otro"
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/solicitudPermiso/ver')

        solicitudPermiso.refresh()
        assertEquals "test", solicitudPermiso.motivo
    }
    
    @Test
    void usuarioEmpleadoEnviaSolicitudPermiso() {
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
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, startus: "CR"
				, dateCreated: new Date()
				, motivo: "test"
			).save()
		
		def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "CR", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.enviar()
        assertEquals "EN", solicitudPermiso.status
    }
    @Test
    void jefeCCostoDebieraPoderAprobarSolicitudPermiso() {
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
    	
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
				, motivo: "test"
			).save()

        def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.aprobar()
        assertEquals "AP", solicitudPermiso.status
    }
    
    @Test
    void RHOperDebieraPoderRevisarSolicitudPermiso() {
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
    	
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, status: "AP"
				, dateCreated: new Date()
				, motivo: "test"
			).save()

        def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "AP", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.revisar()
        assertEquals "RE", solicitudPermiso.status
    }
    
    @Test
    void RHOperDebieraPoderAutorizarSolicitudPermiso() {
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
    	
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, status: "RE"
				, dateCreated: new Date()
				, motivo: "test"
			).save()

        def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "RE", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.autorizar()
        assertEquals "AU", solicitudPermiso.status
    }
    
        @Test
    void jefeCCostoRHOperDirRHDebieranPoderRechazarSolicitudPermiso() {
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
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
				, motivo: "test"
			).save()

        def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.rechazar()
        assertEquals "RE", solicitudPermiso.status
    }
    
       @Test
    void dirRHDebieraPoderCancelarSolicitudPermiso() {
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
    	
    	def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				
				, usuarioCrea: currentUser
				, status: "EN"
				, dateCreated: new Date()
				, motivo: "test"
			).save()

        def controller = new SolicitudPermisoController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "EN", solicitudPermiso.status
		
		controller.params.id = solicitudPermiso.id
        def model = controller.edita()
        assert model.solicitudPermiso
        
        controller.cancelar()
        assertEquals "CA", solicitudPermiso.status
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
		
		def solicitudPermiso = new SolicitudPermiso (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: currentUser
				, dateCreated: new Date()
				, motivo: "test"
			).save()
        
        def controller = new SolicitudPermisoController()

        controller.params.id = solicitudPermiso.id
        def model = controller.ver()
        assert model.solicitudPermiso
        assertEquals "test", model.solicitudPermiso.motivo

        controller.params.id = solicitudPermiso.id
        controller.params.version = solicitudPermiso.version
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/solicitudPermiso/ver')

        solicitudPermiso.refresh()
        assertEquals "test", solicitudPermiso.motivo
    }
}
