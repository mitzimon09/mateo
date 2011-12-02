package mx.edu.um.rh

import org.junit.*
import general.BaseIntegrationTest
import general.Organizacion
import general.Empresa
import general.Usuario
import mx.edu.um.Constantes

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */

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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        		
    	def empleado = new Empleado (
			clave: "1110000"
			, nombre: "test"
			, apPaterno: "test"
			, apMaterno: "test"
			, genero: Constantes.GENERO_MASCULINO
			, fechaNacimiento: new Date()
			, direccion: "aqui"
			, status: Constantes.STATUS_ACTIVO
			, empresa: empresa
			//
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	//
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
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
                , status: Constantes.STATUS_ACTIVO
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
    void CrearEmpleadoEstudio() {
    	authenticateAdmin()
		
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
        
        def grupo = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        		
    	def empleado = new Empleado (
			clave: "1110000"
			, nombre: "test"
			, apPaterno: "test"
			, apMaterno: "test"
			, genero: Constantes.GENERO_MASCULINO
			, fechaNacimiento: new Date()
			, direccion: "aqui"
			, status: Constantes.STATUS_ACTIVO
			, empresa: empresa
			//
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	//
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
		).save()
		assertNotNull empleado

        //crear nivel de estudios
    	def nivelEstudios = new NivelEstudios(
            nombre: 'test'
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

		def controller = new EmpleadoEstudiosController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoEstudios
        controller.params.empleado = empleado
        controller.params.nombre_estudios = 'test1'
        controller.params.titulado = true
        controller.params.fecha_titulacion = new Date()
        controller.params.status = Constantes.STATUS_ACTIVO
        controller.params.nivel_estudios = nivelEstudios
        controller.params.user_captura = usuario
        controller.params.fecha_captura = new Date()
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoEstudio/ver')
    }
    
    @Test
    void ModificarEmpleadoEstudio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

        def empleadoEstudio = new EmpleadoEstudio(
            nombre: 'test'
	    ).save()
		assertNotNull empleadoEstudio
    		
        def controller = new EmpleadoEstudioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoEstudio.id
        def model = controller.ver()
        assert model.empleadoEstudio
        assertEquals "test", model.empleadoEstudio.nombre

        controller.params.id = empleadoEstudio.id
        model = controller.edita()
        assert model.empleadoEstudio
        assertEquals "test", model.empleadoEstudio.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoEstudio/ver')

        empleadoEstudio.refresh()
        assertEquals 'test1', empleadoEstudio.nombre
    }
    
    @Test
    void EliminarEmpleadoEstudio() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def empleadoEstudio = new EmpleadoEstudio(
            nombre: 'test'
	    ).save()
		assertNotNull empleadoEstudio
        
        def controller = new EmpleadoEstudioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoEstudio.id
        def model = controller.ver()
        assert model.empleadoEstudio
        assertEquals "test", model.empleadoEstudio.nombre

        controller.params.id = empleadoEstudio.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/empleadoEstudio/lista")

        model = EmpleadoEstudio.get(empleadoEstudio.id)
        assert !model
    }
*/
}
