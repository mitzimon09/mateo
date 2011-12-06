package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.BaseIntegrationTest
import general.Organizacion
import general.Empresa
import general.Usuario
import mx.edu.um.Constantes

class EmpleadoDependientesControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEmpleadoDependientes() {
		authenticateAdmin()
		
		//crear empleado
    	def organizacion = new Organizacion (
            codigo: 'test'
            , nombre: 'test'
            , nombreCompleto: 'test'
        ).save()
        assertNotNull organizacion

		def empresa = new Empresa(
            codigo: "test"
            , nombre: "test"
            , nombreCompleto: "test"
            , organizacion: organizacion
        ).save()
        assertNotNull empresa
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
        
        def grupo = new Grupo(
            nombre : "T"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupo
        		
    	def empleado = new Empleado (
			clave: "1119999"
			, nombre: "test"
			, apPaterno: "test"
			, apMaterno: "test"
			, genero: Constantes.GENERO_MASCULINO
			, fechaNacimiento: new Date()
			, direccion: "aqui"
			, status: Constantes.STATUS_ACTIVO
			, empresa: empresa
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
		).save()
		assertNotNull empleado

        def relacion = new Relacion(
            nombre: 'test'
        ).save()
	    assertNotNull relacion
	    
	    def colegio = new Colegio(
            nombre: 'test'
            , status: '00'
	    ).save()
		assertNotNull colegio
		
		def currentUser = springSecurityService.currentUser
        
        for(i in 1..20) {
        	def empleadoDependientes = new EmpleadoDependientes(
        	    empleado: empleado
        	    , nombre: "test$i"
                , bday: new Date()
                , relacion: relacion
                , colegio: colegio
		    ).save()
    		assertNotNull empleadoDependientes
            println "for >>>>>>>" + i + " : " + empleadoDependientes
        }
        
        def controller = new EmpleadoDependientesController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/empleadoDependientes/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.empleadoDependientes

        assertEquals 10, model.empleadoDependientes.size()
        assert 20 <= model.totalDeEmpleadosDependientes
        
    }

    @Test
    void CrearEmpleadoDependiente() {
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
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
		).save()
		assertNotNull empleado

        def relacion = new Relacion(
            nombre: 'test'
        ).save()
	    assertNotNull relacion
	    
	    def colegio = new Colegio(
            nombre: 'test$i'
            , status: '00'
	    ).save()
		assertNotNull colegio
        
		def currentUser = springSecurityService.currentUser

		def controller = new EmpleadoDependientesController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoDependientes
        controller.params.nombre = "test"
        controller.params.empleado = empleado
        controller.params.bday = new Date()
        controller.params.relacion = relacion
        controller.params.colegio = colegio
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoDependientes/ver')
    }
    
    @Test
    void ModificarEmpleadoDependiente() {
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
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
		).save()
		assertNotNull empleado

        def relacion = new Relacion(
            nombre: 'test'
        ).save()
	    assertNotNull relacion
	    
	    def colegio = new Colegio(
            nombre: 'test'
            , status: '00'
	    ).save()
		assertNotNull colegio
		
		def currentUser = springSecurityService.currentUser

        def empleadoDependientes = new EmpleadoDependientes(
            nombre: "test"
    	    , empleado: empleado
            , bday: new Date()
            , relacion: relacion
            , colegio: colegio
	    ).save()
		assertNotNull empleadoDependientes
    		
        def controller = new EmpleadoDependientesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoDependientes.id
        def model = controller.ver()
        assert model.empleadoDependientes
        assertEquals "test", model.empleadoDependientes.nombre

        controller.params.id = empleadoDependientes.id
        model = controller.edita()
        assert model.empleadoDependientes
        assertEquals "test", model.empleadoDependientes.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoDependientes/ver')

        empleadoDependientes.refresh()
        assertEquals 'test1', empleadoDependientes.nombre
    }
    
    @Test
    void EliminarEmpleadoDependiente() {
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
			, curp: 1234567890097876
        	, escalafon: 3
        	, turno: 1
        	, rfc: 12345678901234
        	, modalidad: Constantes.MODALIDAD_APOYO
        	, fechaAlta: new Date()
        	, antiguedadFiscal: new BigDecimal(0.00)
        	, antiguedadBase: new BigDecimal(0.00)
        	, estadoCivil: Constantes.ESTADO_CIVIL_SOLTERO
        	, madre: "test"
        	, padre: "test"
        	, grupo: grupo
        	, tipo: tipoEmpleado
		).save()
		assertNotNull empleado

        def relacion = new Relacion(
            nombre: 'test'
        ).save()
	    assertNotNull relacion
	    
	    def colegio = new Colegio(
            nombre: 'test'
            , status: '00'
	    ).save()
		assertNotNull colegio
		
		def currentUser = springSecurityService.currentUser
    	
    	def empleadoDependientes = new EmpleadoDependientes(
    	    nombre: "test"
    	    , empleado: empleado
            , bday: new Date()
            , relacion: relacion
            , colegio: colegio
	    ).save()
		assertNotNull empleadoDependientes
        
        def controller = new EmpleadoDependientesController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoDependientes.id
        def model = controller.ver()
        assert model.empleadoDependientes
        assertEquals "test", model.empleadoDependientes.nombre

        println("id >>>>>>>>" + empleadoDependientes.id)
        controller.params.id = empleadoDependientes.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/empleadoDependientes/lista")

        model = EmpleadoDependientes.get(empleadoDependientes.id)
        assert !model
    }
}

