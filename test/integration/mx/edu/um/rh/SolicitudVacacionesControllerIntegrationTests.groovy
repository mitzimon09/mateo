package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken
import mx.edu.um.Constantes
import mx.edu.um.rh.*

class SolicitudVacacionesControllerIntegrationTests extends BaseIntegrationTest{
	def springSecurityService
	def procesoService
	def solicitudVacacionesService

    @Test
    void debieraMostrarListaDeSolicitudesDeVacaciones() {
	    authenticateUser()
    	def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, folio: "test$i"
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
    	def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
        controller.params.folio = "test"
        
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/solicitudVacaciones/ver')
    }
    
    @Test
    void debieraActualizarSolicitudVacaciones() {
		
		def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, folio: "test"
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
	      
	     def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, status: "CR"
				, dateCreated: new Date()
				, folio: "test"
			).save()
		
		def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "CR", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.enviar()
        assertEquals Constantes.STATUS_ENVIADO, solicitudVacaciones.status
    }
    @Test
    void jefeCCostoDebieraPoderAprobarSolicitudVacaciones() {
		authenticateCCP()
	      
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, status: Constantes.STATUS_ENVIADO
				, dateCreated: new Date()
				, folio: "test"
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.aprobar()
        assertEquals Constantes.STATUS_APROBADO, solicitudVacaciones.status
    }
    
    @Test
    void RHOperDebieraPoderRevisarSolicitudVacaciones() {
		authenticateRHOper()
	      
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, folio: "test"
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals "AP", solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.revisar()
        assertEquals Constantes.STATUS_REVISADO, solicitudVacaciones.status
    }
    
    @Test
    void RHOperDebieraPoderAutorizarSolicitudVacaciones() {
		authenticateRHOper()
	      
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, status: Constantes.STATUS_REVISADO
				, dateCreated: new Date()
				, folio: "test"
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_REVISADO, solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.autorizar()
        assertEquals Constantes.STATUS_AUTORIZADO, solicitudVacaciones.status
    }
    
        @Test
    void jefeCCostoRHOperDirRHDebieranPoderRechazarSolicitudVacaciones() {
	      authenticateCCP()
	      
	     def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, status: Constantes.STATUS_ENVIADO
				, dateCreated: new Date()
				, folio: "test"
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.rechazar()
        assertEquals Constantes.STATUS_RECHAZADO , solicitudVacaciones.status
    }
    
       @Test
    void dirRHDebieraPoderCancelarSolicitudVacaciones() {
	      authenticateDirRH()
	      
	     def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, status: Constantes.STATUS_ENVIADO
				, dateCreated: new Date()
				, folio: "test"
			).save()

        def controller = new SolicitudVacacionesController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudVacaciones.status
		
		controller.params.id = solicitudVacaciones.id
        def model = controller.edita()
        assert model.solicitudVacaciones
        
        controller.cancelar()
        assertEquals Constantes.STATUS_CANCELADO, solicitudVacaciones.status
    }
    
    @Test
    void debieraAcumularObservaciones() {
		
		def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
				, folio: "test"
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
    void debieraPodePedirPrimaVacacionalSoloConMasDe7DiasDeVacaciones(){
    	authenticateDirRH()
	     def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
    	
    	def fechaInicial = new Date()
    	fechaInicial = fechaInicial - 10
    	def currentUser = springSecurityService.currentUser
    	   	
    	def controller = new SolicitudVacacionesController()
        
        def model = controller.nueva()
        
        controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = new Date()-1
        controller.params.fechaFinal = new Date()
        controller.params.destino = "test"
        controller.params.kilometros = 8
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        controller.crea()
        
        
		//find a way to assert the result of crea()
        assertEquals 0, controller.params.userPrimaVacacional, 0 

    }
    
    @Test
    void primaVacacionalSolicitadaSoloUnaVezAlAnio(){
    	authenticateDirRH()
	    def currentUser = springSecurityService.currentUser
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
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
    	
    	
    	def solicitudVacaciones = new SolicitudVacaciones (
				empleado: empleado
				, empresa: empleado.empresa
				, fechaCaptura: new Date()
				, fechaInicial: new Date()-30
				, fechaFinal: new Date()
				, diasVacaciones: new Integer(1)
				, destino: "test"
				, kilometros: new Integer(1)
				, usuarioCrea: currentUser
				, status: Constantes.STATUS_ENVIADO
				, dateCreated: new Date()
				, folio: "test"
				, userPrimaVacacional: 1000
			).save()
    	   	
    	def controller = new SolicitudVacacionesController()
		def model = controller.nueva()
		controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = new Date()-30
        controller.params.fechaFinal = new Date()
        controller.params.diasVacaciones = 30
        controller.params.destino = "test"
        controller.params.kilometros = 8
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        model.solicitudVacaciones.id = controller.crea()
		//find a way to assert the result of crea()
        assertEquals 0, controller.params.userPrimaVacacional, 0 //AssertionError: expected:<0.0> but was:<1000.0>
		
    }
    @Test
    void agregar2DiasDeVacacionesSiVisitaPadresYSonMasDe900Kilometros(){
    	authenticateDirRH()
	      def currentUser = springSecurityService.currentUser
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	assertNotNull usuario
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	assertNotNull usuarioEmpleado
    	
    	Calendar jueves = Calendar.getInstance()
		jueves.set(jueves.get(Calendar.YEAR), 11, 13, 0, 0)
		def datej = jueves.getTime()
    	
		def solicitudVacaciones = new SolicitudVacaciones (
			empleado: empleado
			, empresa: empleado.empresa
			, fechaCaptura: new Date()
			, fechaInicial: datej-30
			, fechaFinal: datej
			, diasVacaciones: new Integer(1)
			, destino: "test"
			, kilometros: new Integer(1)
			, usuarioCrea: currentUser
			, status: Constantes.STATUS_ENVIADO
			, dateCreated: new Date()
			, folio: "test"
			, userPrimaVacacional: new BigDecimal(1000.00)
		).save()
		assertNotNull solicitudVacaciones

    	def controller = new SolicitudVacacionesController()
	   	
		def model = controller.nueva()
		controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = datej-30
        controller.params.fechaFinal = datej
        controller.params.destino = "test"
        controller.params.kilometros = 1000
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.visitaPadres = true
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/solicitudVacaciones/ver')
        
        
        assertEquals 28,controller.params.diasVacaciones
    	
    }
    
        @Test
    void agregarDiasExtrasSiVacacionesTerminanEnViernesOSabado(){
    	authenticateDirRH()
	      def currentUser = springSecurityService.currentUser
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	assertNotNull usuario
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	assertNotNull usuarioEmpleado
    	
    	def controller = new SolicitudVacacionesController()
		
    	   	
    	Calendar viernes = Calendar.getInstance()
		viernes.set(viernes.get(Calendar.YEAR)-100, 10, 10, 0, 0)
		def datev = viernes.getTime()
		
		Calendar sabado = Calendar.getInstance()
		sabado.set(sabado.get(Calendar.YEAR), 10, 12, 0, 0)
		def dates = sabado.getTime()
    	   	
		def model = controller.nueva()
		controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = datev - 30
        controller.params.fechaFinal = datev
        controller.params.destino = "test"
        controller.params.kilometros = 1000
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        controller.crea()
        assertEquals 32, controller.params.diasVacaciones
    	
    }
    
    @Test
    void noContarDiasFeriadosComoVacaciones(){
    	authenticateDirRH()
	      def currentUser = springSecurityService.currentUser
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	assertNotNull usuario
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	assertNotNull usuarioEmpleado
    	
    	def controller = new SolicitudVacacionesController()

		authenticateRHOper()
		currentUser = springSecurityService.currentUser

		Calendar jueves = Calendar.getInstance()
		jueves.set(jueves.get(Calendar.YEAR)-100, 11, 17, 0, 0)
		def datej = jueves.getTime()
		
    	def diasFeriados = new DiasFeriados(
            descripcion: 'test'
            , fecha: datej -7
            , user: currentUser
	    ).save()
		assertNotNull diasFeriados
    	   	
		def model = controller.nueva()
		controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = datej -30
        controller.params.fechaFinal = datej
        controller.params.destino = "test"
        controller.params.kilometros = 10
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.visitaPadres = true
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        controller.crea()
        assertEquals 29, controller.params.diasVacaciones
    	
    }
    
    @Test
    void validarSiHayDiasDeVacacionesDisponibles(){
    	authenticateDirRH()
	      def currentUser = springSecurityService.currentUser
	    def organizacion = new Organizacion(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
            
    	Empleado empleado = new Empleado(
            clave : "1110000"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : Constantes.GENERO_FEMENINO
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : Constantes.STATUS_ACTIVO
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : Constantes.MODALIDAD_APOYO
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO
            , tipo: tipoEmpleado
            , grupo: grupo
        ).save()
        assertNotNull empleado
		
		def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	assertNotNull usuario
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	assertNotNull usuarioEmpleado
    	
		
		def vacaciones = new Vacaciones (
				empleado: empleado
				, usuario: currentUser
				, descripcion: "test"
				, empresa: empresa
				, dias: 2
			).save()
			
			new Vacaciones (
				empleado: empleado
				, usuario: currentUser
				, descripcion: "test"
				, empresa: empresa
				, dias: 18
			).save()
			
			new Vacaciones (
				empleado: empleado
				, usuario: currentUser
				, descripcion: "test"
				, empresa: empresa
				, dias: -5
			).save()
		
		authenticateRHOper()
		
		currentUser = springSecurityService.currentUser

		Calendar jueves = Calendar.getInstance()
		jueves.set(jueves.get(Calendar.YEAR)-100, 10, 10, 0, 0)
		def datej = jueves.getTime()		
    	
		
    	def controller = new SolicitudVacacionesController()
    	   	
		def model = controller.nueva()
		controller.params.empleado = empleado
        controller.params.empresa = empleado.empresa
        controller.params.fechaCaptura =  new Date()
        controller.params.fechaInicial = datej -30
        controller.params.fechaFinal = datej
        controller.params.destino = "test"
        controller.params.kilometros = 1000
        controller.params.usuarioCrea = currentUser
        controller.params.dateCreated = new Date()
        controller.params.visitaPadres = true
        controller.params.folio = "test"
        controller.params.userPrimaVacacional = new BigDecimal(1000.00)
        controller.crea()
        assertEquals 30, controller.params.diasVacaciones
        
        int dias = solicitudVacacionesService.totalDeDiasDeVacaciones(empleado)
        assertEquals 15, dias
    	
    }
}
