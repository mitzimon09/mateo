package mx.edu.um.rh

import general.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import mx.edu.um.Constantes


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(SolicitudRHController)
class SolicitudRHControllerIntegrationTests extends BaseIntegrationTest{

	def springSecurityService
	def procesoService
	def solicitudRHService
	
	@Test
	void usuarioCreaUnaSolicitudRH(){
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
		controller.params.folio = "test"
		controller.crea()
		
		assert controller
		assert controller.response.redirectedUrl.startsWith('/solicitudRH/ver')
		
	}
	
	@Test
    void usuarioEmpleadoEnviaSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
    	).save()
		
		def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_CREADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.enviar()
        assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
    }
    
    @Test
    void jefeCCostoDebieraPoderAprobarSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_ENVIADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.aprobar()
        assertEquals Constantes.STATUS_APROBADO, solicitudRH.status
    }
    
    @Test
    void RHOperDebieraPoderRevisarSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_APROBADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_APROBADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.revisar()
        assertEquals Constantes.STATUS_REVISADO, solicitudRH.status
    }
    
    @Test
    void RHOperDebieraPoderAutorizarSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_REVISADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_REVISADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.autorizar()
        assertEquals Constantes.STATUS_AUTORIZADO, solicitudRH.status
    }
    
        @Test
    void jefeCCostoRHOperDirRHDebieranPoderRechazarSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_ENVIADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.rechazar()
        assertEquals Constantes.STATUS_RECHAZADO, solicitudRH.status
    }
    
       @Test
    void dirRHDebieraPoderCancelarSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_ENVIADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.cancelar()
        assertEquals Constantes.STATUS_CANCELADO, solicitudRH.status
    }
    
    
    @Test
    void debieraMostrarListaPorJefeCCosto(){
    	authenticateUser()
    	def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

		def empresa = new Empresa(
                codigo: "emp1"
                , nombre: "emp"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
            
        def categoria = new Categoria(
        	nombre: "test"
        ).save()
            
        def seccion = new Seccion (
        	descripcion: "test"
        	, maximo: new Integer (1)
        	, minimo: new Integer (2)
        	, rango_academico: new Integer(1)
        	, categoria: categoria
        ).save()
            
        def puesto = new Puesto (
        	nombre: "test"
        	, maximo: new Integer(1)
        	, minimo: new Integer(1)
        	, seccion: seccion
        ).save()
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
		
		def empleadoPuesto = new EmpleadoPuesto(
			empleado: empleado
			, ejercicio: "test"
			, cCosto: "cCosto"
			, puesto: puesto
			, turno: new BigDecimal(0.00)
		).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: currentUser
    		, empleado: empleado
    	).save()
    	
    	for(i in 1..10) {
			 new SolicitudRH (
				empresa: currentUser.empresa
				, empleado: usuarioEmpleado.empleado
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: usuarioEmpleado.usuario
				, dateCreated: new Date()
				, folio: "test$i"
				, status: Constantes.STATUS_AUTORIZADO
			).save()
    	}
    	
		def empresa2 = new Empresa(
                codigo: "emp2"
                , nombre: "emp2"
                , nombreCompleto: 'emptest'
                , organizacion: organizacion
            ).save()
            
        Empleado empleado2 = new Empleado(
            clave : "1110002"
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
    
		
		currentUser = springSecurityService.currentUser
		
		def empleadoPuesto2 = new EmpleadoPuesto(
			empleado: empleado2
			, ejercicio: "test2"
			, cCosto: "cCosto"
			, puesto: puesto
			, turno: new BigDecimal(0.00)
		).save()
    	
    	def usuarioEmpleado2 = new UsuarioEmpleado (
    		usuario: currentUser
    		, empleado: empleado2
    	).save()
    	
    	for(i in 1..10) {
    		
			 new SolicitudRH (
				empresa: currentUser.empresa
				, empleado: usuarioEmpleado2.empleado
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: usuarioEmpleado2.usuario
				, dateCreated: new Date()
				, folio: "test$i"
				, status: Constantes.STATUS_AUTORIZADO
			).save()
    	}
    	
    	authenticateCCP()
    	currentUser = springSecurityService.currentUser
    	
    	def empleadoPuesto0 = new EmpleadoPuesto(
			empleado: empleado
			, ejercicio: "test0"
			, cCosto: "cCosto"
			, puesto: puesto
			, turno: new BigDecimal(0.00)
		).save()
    	
    	def usuarioEmpleado0 = new UsuarioEmpleado (
    		usuario: currentUser
    		, empleado: empleado
    	).save()
    	
    	def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/solicitudRH/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.solicitudesRH

        //assertEquals 10, model.solicitudesRH.size()
        assert 20 <= model.totalDeSolicitudesRH
        
        def solicitudesRH = solicitudRHService.getSolicitudesRHByRol()
        for(SolicitudRH solicitud in solicitudesRH){      
            assertEquals solicitud.status, Constantes.STATUS_AUTORIZADO
            //assertEquals solicitud.
        }
        assert 20 <= solicitudesRH.size()
    }
    
    @Test
    void debieraMostrarListaDeSecretariaRH(){
    	authenticateRHOper()
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

    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
		assertNotNull empleado
		
		def currentUser = springSecurityService.currentUser
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: currentUser
    		, empleado: empleado
    	).save()
    	
		assertNotNull usuarioEmpleado
    	
    	for(i in 1..20) {
			 new SolicitudRH (
				empresa: currentUser.empresa
				, empleado: usuarioEmpleado.empleado
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: usuarioEmpleado.usuario
				, dateCreated: new Date()
				, folio: "test$i"
				, status: Constantes.STATUS_APROBADO
			).save()
			assertNotNull SolicitudRH
    	}
    	
    	def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/solicitudRH/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.solicitudesRH

        //assertEquals 10, model.solicitudesRH.size()
        assert 20 <= model.totalDeSolicitudesRH
        
        def solicitudesRH = solicitudRHService.getSolicitudesRHByRol()
//		def solicitudesRH = solicitudRHService.getSolicitudesRHByRHOper(currentUser.empresa)
        for(SolicitudRH solicitud in solicitudesRH){      
            assertEquals solicitud.status, 'AP'
        }
        assert 20 <= solicitudesRH.size()
    }
    
    @Test
    void debieraMostrarListaDeDirectorRH(){
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: currentUser
    		, empleado: empleado
    	).save()
    	
    	for(i in 1..20) {
			 new SolicitudRH (
				empresa: currentUser.empresa
				, empleado: usuarioEmpleado.empleado
				, fechaInicial: new Date()
				, fechaFinal: new Date()
				, usuarioCrea: usuarioEmpleado.usuario
				, dateCreated: new Date()
				, folio: "test$i"
				, status: Constantes.STATUS_AUTORIZADO
			).save()
    	}
    	
    	def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/solicitudRH/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.solicitudesRH

        //assertEquals 10, model.solicitudesRH.size()
        assert 20 <= model.totalDeSolicitudesRH
        
        def solicitudesRH = solicitudRHService.getSolicitudesRHByRol()
        for(SolicitudRH solicitud in solicitudesRH){      
            assertEquals solicitud.status, Constantes.STATUS_AUTORIZADO
        }
        assert 20 <= solicitudesRH.size()
    }
    
    @Test
    void jefeCCostoDebieraPoderSuspenderSolicitudRH() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_ENVIADO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.suspender()
        assertEquals Constantes.STATUS_SUSPENDIDO, solicitudRH.status
    }
    
    @Test
    void usuarioDebieraPoderContinuarProcesoDespuesDeSolicitudRHSuspendida() {
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: new Date()
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_SUSPENDIDO
    	).save()

        def controller = new SolicitudRHController()
        controller.springSecurityService = springSecurityService
		controller.procesoService = procesoService
		assertEquals Constantes.STATUS_SUSPENDIDO, solicitudRH.status
		
		controller.params.id = solicitudRH.id
        def model = controller.edita()
        assert model.solicitudRH
        
        controller.enviar()
        assertEquals Constantes.STATUS_ENVIADO, solicitudRH.status
    }
    
    @Test
    void debieraTenerVariasObservaciones(){
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        for(i in 1..20) {
        	def observaciones = new Observaciones(
                observaciones: "test1"
                , usuario: currentUser
                , dateCreated: new Date()
		    ).save()
        }

        def controller = new ObservacionesController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/observaciones/list', controller.response.redirectedUrl

		def model = controller.list()
		assertNotNull model
		assertNotNull model.observacionesInstanceList

        assertEquals 10, model.observacionesInstanceList.size()
        assert 20 <= model.observacionesInstanceTotal
    }
    
    @Test
    void debieraTraerEmpleadosDeVacacionesEnCiertoRangoDeTiempo(){
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
    
    	def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        
    	def tipoEmpleado = new TipoEmpleado (
        	descripcion: "test"
        	, prefijo: "666"
        ).save()
    
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
    	
    	
    	def solicitudRH = new SolicitudRH (
    		empresa: empresa
    		, empleado: usuarioEmpleado.empleado
			, fechaInicial: fechaInicial
			, fechaFinal: new Date()
			, usuarioCrea: usuarioEmpleado.usuario
			, dateCreated: new Date()
			, folio: "test"
			, status: Constantes.STATUS_CREADO
			, email: "test1"
    	).save()

    	//elegir rango de tiempo, dateEmpieza dateTermina
    	Date fechaEmpiezaRango = new Date() -60;
    	Date fechaTerminaRango = new Date()
    	
    	def solicitudesRH = solicitudRHService.getSolicitudesRHByRangoDeFecha(fechaEmpiezaRango, fechaTerminaRango)
    	for(SolicitudRH solicitud in solicitudesRH){      
            assert ((solicitud.fechaInicial >= fechaEmpiezaRango && solicitud.fechaInicial <= fechaTerminaRango) || (solicitud.fechaFinal >= fechaEmpiezaRango && solicitud.fechaFinal <= fechaTerminaRango) || (solicitud.fechaInicial <= fechaEmpiezaRango && solicitud.fechaInicial >= fechaTerminaRango))
        }
    }
}
