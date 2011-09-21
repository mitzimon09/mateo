package general

import mx.edu.um.rh.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UsuarioEmpleadoController)
class UsuarioEmpleadoControllerIntegrationTests extends BaseIntegrationTest{

	def springSecurityService
	def usuarioEmpleadoService
	
	@Test
    void obtenerEmpleadoConElUsernameDeCurrentUser(){
    
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
    	
    	def empleado2 = new Empleado (
			clave: "1110002"
			, nombre: "test2"
			, apPaterno: "test2"
			, apMaterno: "test2"
			, genero: "fm"
			, fechaNacimiento: new Date()
			, direccion: "aqui2"
			, status: "23"
			, empresa: empresa
			//
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901232
        	, modalidad: "tt"
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	//
        	, estadoCivil: "te"
        	, madre: "test2"
        	, padre: "test2"
		).save()
		
		def usuario2 = new Usuario (
    		username: "test2"
    		, password: "test2"
    		, nombre: "test2"
    		, apellido: "test2"
    		, correo: "test2@test.test"
    		, empresa: empresa
    	).save()
    	
    	def usuarioEmpleado = new UsuarioEmpleado (
    		usuario: usuario
    		, empleado: empleado
    	).save()
    	
    	def usuarioEmpleado2 = new UsuarioEmpleado (
    		usuario: usuario2
    		, empleado: empleado2
    	).save()
    	
    	def username = "test2"
    	def empleadoTest = usuarioEmpleadoService.getEmpleadoByUsername("test2")
    	
    	assertEquals "1110002", empleadoTest.clave
    	
    }
}
