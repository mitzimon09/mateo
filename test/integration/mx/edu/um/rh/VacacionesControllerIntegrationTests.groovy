package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.*
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(VacacionesController)
class VacacionesControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	def vacacionesService
	
	@Test
    void debieraDarDeAltaVacaciones(){
	    authenticateAdmin()
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	
    	def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
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

        assertNotNull empresa
        
        def currentUser = springSecurityService.currentUser

		def controller = new VacacionesController()
		controller.params.dateCreated = new Date()
		controller.params.descripcion = "test"
		controller.params.dias = 3
		controller.params.usuario = usuario
		controller.params.empleado = empleado
		controller.params.status = "U"
		controller.params.empresa = empresa
        controller.save()

        assert controller
        assertNotNull controller.response.redirectedUrl
        assert controller.response.redirectedUrl.startsWith('/vacaciones/ver')
    }

	@Test
    void noDebieraModificarVacaciones(){

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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
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
		
		def vacaciones = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
		).save()

        def controller = new VacacionesController()
        controller.params.id = vacaciones.id
        def model = controller.ver()
        assert model
        assert model.vacaciones

        assertEquals "test", model.vacaciones.descripcion

        controller.params.id = vacaciones.id
        model = controller.ver()
        assert model.vacaciones

        controller.params.id = vacaciones.id
        controller.params.version = vacaciones.version
        controller.params.descripcion = "cambiado"
        controller.ver()

		assertEquals "test", model.vacaciones.descripcion
    }
    
    @Test
    void noDebieraEliminarVacaciones() {
        authenticateAdmin()
		
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
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
		
		def vacaciones = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
		).save()
        
        def controller = new VacacionesController()
        controller.params.id = vacaciones.id
        def model = controller.ver()
        assert model.vacaciones
        assertEquals "test", model.vacaciones.descripcion

        controller.params.id = vacaciones.id

        model = Vacaciones.get(vacaciones.id)
        assert model
    }
    

    @Test
    void debieraMostrarVacaciones() {
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
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
		
		def vacaciones = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
		).save()

		def controller = new VacacionesController()
        controller.params.id = vacaciones.id
        controller.params.descripcion = "test"
        controller.params.dias = 3
        controller.params.usuario = usuario
        controller.params.empleado = empleado
        controller.params.empresa = empresa
        def model = controller.ver()
        assert model.vacaciones

        controller.save()
        assertNotNull controller.response.redirectedUrl
        assertNotNull controller.response
        assert controller.response.redirectedUrl.startsWith('/vacaciones/ver')
    }

	@Test
    void MostrarListaDeVacacionesConSolicitudesCorrespondientes() {
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
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
    	
    	
    	def solicitudSalida = new SolicitudSalida(
    		empleado: empleado
			, diasVacaciones: 12
			, primaVacacional: 12
			, fechaInicial: new Date()
			, destino: "test"
			, kilometros: 3
			, user: usuario
			, fechaCaptura: new Date()
			, visitaPadres: 4
			, nacional: 4
			, fechaFinal: new Date()
    	).save()
    	assertNotNull solicitudSalida
    	
    	def vacaciones = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
			, solicitudSalida: solicitudSalida
		).save() 

        for(i in 1..20) {
			vacaciones = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test$i"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
			, solicitudSalida: solicitudSalida
		).save() 
		}
        def controller = new VacacionesController()
        
        controller.index()
        assertEquals '/vacaciones/lista', controller.response.redirectedUrl
		
		def model = controller.lista()
      	assertNotNull model
      	assertNotNull model.vacacionesList

        assertEquals 10, model.vacacionesList.size()
        assert 20 <= model.totalDeVacaciones
    }
    
    @Test
    void traerVacacionesCorrespondienteAUnFolio(){
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
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
    	
		
		def solicitudSalida1 = new SolicitudSalida(
    		empleado: empleado
			, diasVacaciones: 12
			, primaVacacional: 12
			, fechaInicial: new Date()
			, destino: "test1"
			, kilometros: 3
			, user: usuario
			, fechaCaptura: new Date()
			, visitaPadres: 4
			, nacional: 4
			, fechaFinal: new Date()
			, folio: "test1"
    	).save()
    	
    	def  vacaciones1 = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test1"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
			, solicitudSalida: solicitudSalida1
		).save()
		
		
		def solicitudSalida = new SolicitudSalida(
    		empleado: empleado
			, diasVacaciones: 12
			, primaVacacional: 12
			, fechaInicial: new Date()
			, destino: "test2"
			, kilometros: 3
			, user: usuario
			, fechaCaptura: new Date()
			, visitaPadres: 4
			, nacional: 4
			, fechaFinal: new Date()
			, folio: "test2"
    	).save()
    	
    	def  vacaciones2 = new Vacaciones (
			dateCreated: new Date()
			, descripcion: "test2"
			, dias: 3
			, usuario: usuario
			, empleado: empleado
			, empresa: empresa
			, solicitudSalida: solicitudSalida
		).save()
		
    	def folio = "test1"
    	def vacaciones = vacacionesService.getVacacionesBySolicitudSalida(folio)
    	assertEquals vacaciones.descripcion, "test1"
    	assertEquals vacaciones.solicitudSalida.folio, "test1"
    	
    	folio = "test2"
    	vacaciones = vacacionesService.getVacacionesBySolicitudSalida(folio)
    	assertEquals vacaciones.descripcion, "test2"
    	assertEquals vacaciones.solicitudSalida.folio, "test2"
    }
}
