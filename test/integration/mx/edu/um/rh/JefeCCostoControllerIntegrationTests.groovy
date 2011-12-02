package mx.edu.um.rh

import general.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(JefeCCostoController)
class JefeCCostoControllerIntegrationTests extends BaseIntegrationTest {

    @Test
    void debieraMostrarListaDeJefeCCosto(){
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
    	
    	def usuario = new Usuario (
    		username: "test"
    		, password: "test"
    		, nombre: "test"
    		, apellido: "test"
    		, correo: "test@test.test"
    		, empresa: empresa
    	).save()
    	assertNotNull usuario
    	
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
    
    	for(i in 1..20) {
        	def jefeCCosto = new JefeCCosto (
        		jefe: empleado
        		, userCaptura: usuario
        		, fechaCaptura: new Date()
        	).save()
        	assertNotNull jefeCCosto
        }
        
        def controller = new JefeCCostoController()
        controller.index()
        assertEquals '/jefeCCosto/lista', controller.response.redirectedUrl
        
        def model = controller.lista()
		assertNotNull model
		assertNotNull model.jefesCCosto
		
        assertEquals 10, model.jefesCCosto.size()
        assert 20 <= model.totalDejefesCCosto
       
    }
    
    @Test
    void debieraCrearJefeCCosto(){
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
    
    
    	def controller = new JefeCCostoController()
    	def model = controller.nuevo()
        assert model
        assert model.jefeCCosto
        
        
        controller.params.jefe = empleado
        controller.params.userCaptura = usuario
        controller.params.fechaCaptura = new Date()
        controller.crea()
        assert controller 
        assert controller.response.redirectedUrl.startsWith('/jefeCCosto/ver')
    }
    
    @Test
    void debieraActualizarJefeCCosto(){
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
    
    	def jefeCCosto = new JefeCCosto (
        		jefe: empleado
        		, userCaptura: usuario
        		, fechaCaptura: new Date()
        	).save()
    	
    	def controller = new JefeCCostoController()
    	controller.params.id = jefeCCosto.id
        def model = controller.ver()
        assert model
        assert model.jefeCCosto
        
        model = controller.edita()
        assert model.jefeCCosto
        controller.actualiza()
        assertEquals "/jefeCCosto/ver/${jefeCCosto.id}", controller.response.redirectedUrl

        jefeCCosto.refresh()
    }
    
    @Test
    void debieraEliminarJefeCCosto() {
    	
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
    
    	def jefeCCosto = new JefeCCosto (
        		jefe: empleado
        		, userCaptura: usuario
        		, fechaCaptura: new Date()
        	).save()
        
        def controller = new JefeCCostoController()
    	controller.params.id = jefeCCosto.id
        def model = controller.ver()
        assert model
        assert model.jefeCCosto

        controller.params.id = jefeCCosto.id
        controller.elimina()
        assertEquals "/jefeCCosto/lista", controller.response.redirectedUrl

        model = JefeCCosto.get(jefeCCosto.id)
        assert !model
    }
}
