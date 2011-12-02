package mx.edu.um.rh

import static org.junit.Assert.*
import org.junit.*
import general.*
import mx.edu.um.Constantes


import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class VacacionesControllerIntegrationTests extends BaseIntegrationTest{
	def springSecurityService

    @Test
    void debieraMostrarListaDeVacaciones() {
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
			new Vacaciones (
				empleado: empleado
				, usuario: currentUser
				, descripcion: "test"
				, empresa: empresa
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
    void debieraCrearVacaciones() {
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
		

		def controller = new VacacionesController()
        
        def model = controller.nueva()
        assert model
        assert model.vacaciones
        controller.params.empleado = empleado
        controller.params.usuario = currentUser
        controller.params.dateCreated = new Date()
        controller.params.descripcion = "test"
        controller.params.empresa = empresa
        
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/vacaciones/ver')
    }
}
