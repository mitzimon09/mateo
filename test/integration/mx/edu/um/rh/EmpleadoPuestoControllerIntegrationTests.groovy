package mx.edu.um.rh

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import general.BaseIntegrationTest
import general.Organizacion
import general.Empresa
import mx.edu.um.contabilidad.Ejercicio
import mx.edu.um.contabilidad.CentroCosto

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(EmpleadoPuestoController)
class EmpleadoPuestoControllerIntegrationTests extends BaseIntegrationTest {

	def springSecurityService
	
    @Test
    void MostrarListaDeEmpleadoPuestos() {
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

        def ejercicio = new Ejercicio(
            idEjercicio: '0001-2012'
            , nombre: 'test'
            , status: 'A'
	        , mascBalance: 'test'
	        , mascResultado: 'test'
	        , mascAuxiliar: 'test'
	        , mascCcosto: 'test'
	        , nivelContable: 0
	        , nivelTauxiliar: 0
        ).save()
        assertNotNull ejercicio
        
        def key = new CCostoPK(
            ejercicio: ejercicio
            , idCCosto: '0001'
        ).save()
        assertNotNull key
        
        def cCosto = new CentroCosto(
            nombre: 'test'
            , detalle: 'test'
            , iniciales: 'test'
            , rfc: 'test'
	        , key: key
	        , ejercicio: ejercicio
        ).save()
        assertNotNull cCosto

        def categoria = new Categoria(
            nombre: 'test'
        ).save()
    	
    	def seccion = new Seccion(
            descripcion: 'test'
            , maximo: '1'
            , minimo: '1'
            , rango_academico: '1'
            , categoria: categoria
	    ).save()
        
    	def puesto = new Puesto(
            nombre: 'test$1'
            , maximo: 1
            , minimo: 1
            , seccion: seccion
        ).save()
	    assertNotNull puesto

        for(i in 1..20) {
        	def empleadoPuesto = new EmpleadoPuesto(
        	    id: 2
        	    , empleado: empleado
                , ejercicio: ejercicio
	            , centroCosto: cCosto
	            , puesto: puesto
	            , turno: 0.0
                , status: 'I'
		    ).save()
    		assertNotNull empleadoPuesto
        }

		def currentUser = springSecurityService.currentUser
        def controller = new EmpleadoPuestoController()
        controller.springSecurityService = springSecurityService
        controller.index()

        assertEquals '/empleadoPuesto/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.empleadoPuestos

        assertEquals 10, model.empleadoPuestos.size()
        assert 20 <= model.totalDeEmpleadoPuestos
        /*
        */
    }
/*
    @Test
    void CrearEmpleadoPuesto() {
    	authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

		def controller = new EmpleadoPuestoController()
        controller.springSecurityService = springSecurityService
        
        def model = controller.nuevo()
        assert model
        assert model.empleadoPuesto
        controller.params.nombre = 'test$i' 
        controller.params.ejercio = ejercicio
        controller.params.centroCosto = centroCosto
        controller.params.puesto = puesto
        controller.params.turno = 0.0
        controller.params.status = 'A'
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleadoPuesto/ver')
    }
    
    @Test
    void ModificarEmpleadoPuesto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser

    	def empleadoPuesto = new EmpleadoPuesto(
            nombre: 'test$i' 
            ejercio: ejercicio
            centroCosto: centroCosto
            puesto: puesto
            turno: 0.0
            status: 'A'
	    ).save()
		assertNotNull empleadoPuesto
    		
        def controller = new EmpleadoPuestoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoPuesto.id
        def model = controller.ver()
        assert model.empleadoPuesto
        assertEquals "test", model.empleadoPuesto.nombre

        controller.params.id = empleadoPuesto.id
        model = controller.edita()
        assert model.empleadoPuesto
        assertEquals "test", model.empleadoPuesto.nombre

        controller.params.nombre = 'test1'
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleadoPuesto/ver')

        empleadoPuesto.refresh()
        assertEquals 'test1', empleadoPuesto.nombre
    }
    
    @Test
    void EliminarEmpleadoPuesto() {
        authenticateAdmin()
		
		def currentUser = springSecurityService.currentUser
    	
    	def empleadoPuesto = new EmpleadoPuesto(
            nombre: 'test$i' 
            ejercio: ejercicio
            centroCosto: centroCosto
            puesto: puesto
            turno: 0.0
            status: 'A'
	    ).save()
		assertNotNull empleadoPuesto
        
        def controller = new EmpleadoPuestoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = empleadoPuesto.id
        def model = controller.ver()
        assert model.empleadoPuesto
        assertEquals "test", model.empleadoPuesto.nombre

        controller.params.id = empleadoPuesto.id
        controller.elimina()
        assert controller.response.redirectedUrl.startsWith("/empleadoPuesto/lista")

        model = EmpleadoPuesto.get(empleadoPuesto.id)
        assert !model
    }
    */
}
