package mx.edu.um.rh

import general.*
import grails.test.*
import grails.test.mixin.*
import org.junit.*
import mx.edu.um.Constantes

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestMixin(GrailsUnitTestMixin)
//@TestFor(EmpleadoController)
class EmpleadoControllerIntegrationTests extends BaseIntegrationTest{

    def springSecurityService
    def empleadoService

    Empleado crearEmpleadoPrueba(String claveEmpleado){
        def grupoPrueba = new Grupo(
            nombre : "A",
            minimo : 103,
            maximo : 141
        ).save()
        assertNotNull grupoPrueba

        def empleado = new Empleado(
            empresa: Empresa.findByCodigo("CTL"),
            clave : claveEmpleado,
            nombre : "TESTA",
            apPaterno : "TESTA",
            apMaterno : "TESTA",
            genero : "FM",
            fechaNacimiento : new Date(),
            direccion : "TEST",
            status : Constantes.STATUS_ACTIVO,
            //Map perdeds
            tipo : TipoEmpleado.findByDescripcion("DENOMINACIONAL"),
            curp : "TEST123",
            rfc : "ABC-1234567890",
            cuenta : "123456789",
            imms : "123456789012345",
            escalafon : 75,
            turno : 100,
            fechaAlta : new Date(),
            fechaBaja : new Date(),
            experienciaFueraUM : new BigDecimal(0.00),
            modalidad : "A",
            ife : "123456789012",
            rango : "SR",
            adventista : true,
            fechaAntiguedadBase : new Date(),
            antiguedadBase : new BigDecimal(0.00),
            antiguedadFiscal : new BigDecimal(0.00),
            grupo : grupoPrueba ,
            padre : "TESTP",
            madre: "TESTM",
            estadoCivil : "S",
            conyuge : "TESTC",
            fechaMatrimonio : new Date(),
            iglesia : "TESTI",
            responsabilidad : "TESTR"//,
        ).save()
        assertNotNull empleado

        List<Empleado> empleadoList = Empleado.findAll()
        println "empleados: ${empleadoList.size()}"
        println "en BD: ${Empleado.count()}"

        //Agregando las Percepciones
        List<PerDed> ps = new ArrayList<PerDed>()
        ps.add(PerDed.findByClave("PD103"))
        ps.add(PerDed.findByClave("PD104"))
        ps.add(PerDed.findByClave("PD105"))
        ps.add(PerDed.findByClave("PD106"))

        List<EmpleadoPerded> eps = new ArrayList<EmpleadoPerded>()
        //3,4,5,6
        for(int i = 0; i < 4; i++){
            EmpleadoPerded ep= new EmpleadoPerded(
                perded : ps.get(i),
                importe : i + 100,
                tipoImporte : "%",
                atributos : "N",
                otorgado : false,
                isEditableByNOM : true,
                empleado : empleado
                )
            ep.save()
            eps.add(ep)
            assertNotNull ep
        }

        empleado.perdedsList = eps
        assertNotNull empleado.perdedsList

        Map<String,EmpleadoPerded> perdedsEmpleado = empleado.perdeds
        assertNotNull perdedsEmpleado

        return empleado
    }

    public crearTipoEmpleados(){
        println "Creando TipoEmpleados"

        TipoEmpleado tipoEmpleadoDENOMINACIONAL = new TipoEmpleado(
            descripcion : "DENOMINACIONAL",
            prefijo : "980"
        ).save()
        assertNotNull tipoEmpleadoDENOMINACIONAL
    }

    public crearGrupos(){
        println "Creando Grupos"
        Grupo grupoA = new Grupo(
            nombre: "A",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoA

        Grupo grupoB = new Grupo(
            nombre: "B",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoB

        Grupo grupoC = new Grupo(
            nombre: "C",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoC

        Grupo grupoD = new Grupo(
            nombre: "D",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoD

        Grupo grupoE = new Grupo(
            nombre: "E",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoE

        Grupo grupoX = new Grupo(
            nombre: "X",
            maximo: "10",
            minimo: "5"
        ).save()
        assertNotNull grupoX
    }

    public crearPerdeds(){
        println "Creando Perdeds"

        PerDed PD101 = new PerDed(
            clave: "PD101",
            nombre: "PERCEPCION UNO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
            atributos : ["A":"B"]
        ).save()
        assertNotNull PD101

        PerDed PD102 = new PerDed(
            clave: "PD102",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
            atributos : ["A":"B"]
        ).save()
        assertNotNull PD102

        PerDed PD103 = new PerDed(
            clave: "PD103",
            nombre: "PERCEPCION TRES",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD101 * PD107",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD103

        PerDed PD104 = new PerDed(
            clave: "PD104",
            nombre: "TPERCEPCION CUATRO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD102",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD104

        PerDed PD105 = new PerDed(
            clave: "PD105",
            nombre: "PERCEPCION CINCO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD102",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD105

        PerDed PD106 = new PerDed(
            clave: "PD106",
            nombre: "PERCEPCION SEIS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD101",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD106

        PerDed PD107 = new PerDed(
            clave: "PD107",
            nombre: "PERCEPCION SIETE",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "0",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD107
    }

    public crearPorcentajes(){
        println "Creando Porcentajes"

        Porcentaje porcentajePD101 = new Porcentaje(
            valor : new BigDecimal("2.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD101"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD101

        Porcentaje porcentajePD102 = new Porcentaje(
            valor : new BigDecimal("4.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD102"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD102

        Porcentaje porcentajePD105 = new Porcentaje(
            valor : new BigDecimal("6.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD105"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD105

        Porcentaje porcentajePD106 = new Porcentaje(
            valor : new BigDecimal("8.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD106"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD106

        Porcentaje porcentajePD107 = new Porcentaje(
            valor : new BigDecimal("0.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD107"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD107
    }

    @Test
    void MostrarListaDeEmpleados() {
		authenticateAdmin()
		
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
        
        for(i in 1..20) {
	        Empleado empleado = new Empleado(
                clave : "11100$i"
                , nombre : "test"
                , apPaterno : "test"
                , apMaterno : "test"
                , genero : "FM"
                , fechaNacimiento : new Date()
                , direccion : "test"
                , status : "A"
                , empresa: empresa
                , curp : "test123"
                , rfc : "ABC-1234567890"
                , escalafon : 75
                , turno : 100
                , fechaAlta : new Date()
                , modalidad : "A"
                , antiguedadBase : new BigDecimal(0.00)
                , antiguedadFiscal : new BigDecimal(0.00)
                , padre : "test"
                , madre: "test"
                , estadoCivil : "S"
                , tipo: tipoEmpleado
            ).save()
            assertNotNull empleado
        }
        def controller = new EmpleadoController()
        controller.index()

        assertEquals '/empleado/lista', controller.response.redirectedUrl

		def model = controller.lista()
		assertNotNull model
		assertNotNull model.empleados

        assertEquals 10, model.empleados.size()
        assert 20 <= model.totalDeEmpleados
	}
	
	@Test
    void crearEmpleado(){
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
        
        def controller = new EmpleadoController()
        controller.empleadoService = empleadoService
        controller.params.nombre = "test"
        controller.params.apPaterno = "test"
        controller.params.apMaterno = "test"
        controller.params.genero = "fm"
        controller.params.fechaNacimiento = new Date()
        controller.params.direccion = "test"
        controller.params.status = "23"
        controller.params.empresa = empresa
        controller.params.curp = "1232"
        controller.params.rfc = "12345678901234"
        controller.params.escalafon = 3
        controller.params.turno = 1
        controller.params.modalidad = "tt"
        controller.params.antiguedadBase = new BigDecimal(0.00)
        controller.params.antiguedadFiscal = new BigDecimal(0.00)
        controller.params.fechaAlta = new Date()
        controller.params.padre = "test"
        controller.params.madre = "test"
        controller.params.estadoCivil = "3e"
        controller.params.tipo = tipoEmpleado
        controller.crea()

        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleado/ver')
    }


    @Test
    void ModificarEmpleado(){

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
            , genero : "fm"
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : "A"
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : "A"
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : "S"
            , tipo: tipoEmpleado
        ).save()
        assertNotNull empleado

        def controller = new EmpleadoController()
        controller.params.id = empleado.id
        def model = controller.ver()
        assert model.empleado
        assertEquals "test", model.empleado.nombre
        assertEquals "test", model.empleado.apPaterno
        assertEquals "test", model.empleado.apMaterno
        assertEquals "fm", model.empleado.genero

        controller.params.id = empleado.id
        model = controller.edita()
        assert model.empleado
        assertEquals "test", model.empleado.nombre

        controller.params.nombre = "another"
        controller.params.apPaterno = "another"
        controller.params.apMaterno = "another"
        controller.actualiza()
        assert controller.response.redirectedUrl.startsWith('/empleado/ver')

        empleado.refresh()
        assertEquals "another", model.empleado.nombre
        assertEquals "another", model.empleado.apPaterno
        assertEquals "another", model.empleado.apMaterno
        assertEquals "fm", model.empleado.genero
    }

    @Test
    void debieraCambiarEstatusDeEmpleado(){

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

		def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado        
            
    	Empleado empleado = new Empleado(
            clave : "1110001"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : "fm"
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : "A"
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : "A"
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : "S"
            , tipo: tipoEmpleado
        ).save()
        assertNotNull empleado

        def controller = new EmpleadoController()
        controller.params.id = empleado.id
        def model = controller.ver()
        assert model.empleado

        assertEquals "A", empleado.status
        controller.params.id = empleado.id
        model = controller.edita()
        controller.delete()

        assertEquals "I", empleado.status
        assert controller.response.redirectedUrl.startsWith('/empleado/ver')
    }

    //@Test
    void AsignarSiguienteNumeroDeClaveDisponible() {
    
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
        
        def tipoEmpleado = new TipoEmpleado (
    		descripcion: "test"
    		, prefijo: "111"
    	).save()
    	assertNotNull tipoEmpleado
    	
    	Empleado empleado = new Empleado(
            clave : "1110008"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : "fm"
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : "A"
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : "A"
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : "S"
            , tipo: tipoEmpleado
        ).save()
        assertNotNull empleado
        
        Empleado empleado1 = new Empleado(
            clave : "1110010"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : "fm"
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : "A"
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : "A"
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : "S"
            , tipo: tipoEmpleado
        ).save()
        assertNotNull empleado1
        
        Empleado empleado2 = new Empleado(
            clave : "1110011"
            , nombre : "test"
            , apPaterno : "test"
            , apMaterno : "test"
            , genero : "fm"
            , fechaNacimiento : new Date()
            , direccion : "test"
            , status : "A"
            , empresa: empresa
            , curp : "test123"
            , rfc : "ABC-1234567890"
            , escalafon : 75
            , turno : 100
            , fechaAlta : new Date()
            , modalidad : "A"
            , antiguedadBase : new BigDecimal(0.00)
            , antiguedadFiscal : new BigDecimal(0.00)
            , padre : "test"
            , madre: "test"
            , estadoCivil : "S"
            , tipo: tipoEmpleado
        ).save()
        assertNotNull empleado2
        
		def controller = new EmpleadoController()
        controller.empleadoService = empleadoService
        controller.params.nombre = "test"
        controller.params.apPaterno = "test"
        controller.params.apMaterno = "test"
        controller.params.genero = "fm"
        controller.params.fechaNacimiento = new Date()
        controller.params.direccion = "test"
        controller.params.status = "23"
        controller.params.empresa = empresa
        controller.params.curp = "1232"
        controller.params.rfc = "12345678901234"
        controller.params.escalafon = 3
        controller.params.turno = 1
        controller.params.modalidad = "tt"
        controller.params.antiguedadBase = new BigDecimal(0.00)
        controller.params.antiguedadFiscal = new BigDecimal(0.00)
        controller.params.fechaAlta = new Date()
        controller.params.padre = "test"
        controller.params.madre = "test"
        controller.params.estadoCivil = "3e"
        controller.params.tipo = tipoEmpleado
        controller.crea()
        
        assert controller
        assert controller.response.redirectedUrl.startsWith('/empleado/ver')
        
		assertEquals controller.params.clave, "1110001"
  	}
	
    /**
     * Trae un Empleado segun la clave
    **/

    @Test
    void debieraTraerUnEmpleadoPorClave(){
        println "debieraTraerUnEmpleadoPorClaveANivelService"

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        def clave = "9800052"
        Empleado empleado = crearEmpleadoPrueba(clave)

        def empleadoFromDB = empleadoService.getEmpleado(clave)
        assertNotNull empleadoFromDB
        assertEquals '9800052' , empleadoFromDB.clave
    }

    /**
     * Falla si se busca un Empleado por una clave inexistente
     **/
    @Test
    void debieraMarcarErrorAlConsultarEmpleadoInexistente(){
        println "debieraMarcarErrorAlConsultarEmpleadoInexistente"

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        def clave = "9800052"
        Empleado empleado = crearEmpleadoPrueba(clave)

        def claveErronea = "9800055"

        try{
            def empleadoFromDB = empleadoService.getEmpleado(claveErronea)
        }catch (NullPointerException npe){
            assertEquals "empleado.inexistente",npe.message
        }
    }

    /*@Test
    void debieraTraerEmpleadosByEmpresaAndTipo(){
        log.debug "debieraTraerEmpleadosByEmpresaAndTipo"
        //def empresaId=102
        //def tipoId=1
        def empresa=Empresa.get(102)
        def tipo=TipoEmpleado.get(1)
        assertEquals tipo.descripcion,"DENOMINACIONAL"
        assertEquals empresa.nombre,"CENTRAL"
        def empleados=empleadoServiceInt.getEmpleadosByEmpresaAndTipo(empresa,tipo)
        for(Empleado empleado in empleados){
            assertEquals 1,empleado.tipo.id
            assertEquals 102,empleado.empresa.id
        }
        assertEquals 432,empleados.size()
    }
    @Test
    void debieraTraerCeroEmpleadosByEmpresaAndTipo(){
        log.debug "debieraTraerEmpleadosByEmpresaAndTipo"
        //def empresaId=102
        //def tipoId=1
        def empresa=Empresa.get(121)
        def tipo=TipoEmpleado.get(1)
        assertEquals tipo.descripcion,"DENOMINACIONAL"
        assertEquals empresa.nombre,"Prueba"
        def empleados=empleadoServiceInt.getEmpleadosByEmpresaAndTipo(empresa,tipo)
        for(Empleado empleado in empleados){
            assertEquals empleado.tipo.id,1
            assertEquals empleado.empresa.id,121
        }
        assertEquals 0,empleados.size()
    }*/

    @Test
    void debieraTraerEmpleadosByTipo(){
        log.debug "debieraTraerEmpleadosByTipo"

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        List<Empleado> empleados = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980000"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            empleados.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleados.size() == 10
        println "empleados en BD: ${empleados.size()}"

//        for(Empleado emp : empleados){
//            println "emp.tipo.descripcion: ${emp.tipo.descripcion}"
//        }
        
        def tipoEmpleado = TipoEmpleado.findByDescripcion("DENOMINACIONAL")
        assertNotNull tipoEmpleado
        
        def empleadosFilterByType = empleadoService.getEmpleadosByTipo(tipoEmpleado)

        println "empleados filtrados by type.size(): ${empleadosFilterByType.size()}"
        println "empleados filtrados by type"
        for(Empleado e : empleadosFilterByType){
            println "${e.clave}"
        }

        for(Empleado e in empleadosFilterByType){
            assertEquals e.tipo.descripcion , "DENOMINACIONAL"
        }
        assertEquals  10 , empleadosFilterByType.size()
    }

    /*
    @Test
    void debieraTraerEmpleadosByEmpresa(){
        def empresa=Empresa.get(102)
        assertEquals empresa.nombre,"CENTRAL"
        def empleados=empleadoServiceInt.getEmpleadosByEmpresa(empresa)
        for(Empleado empleado in empleados){
            assertEquals empleado.empresa.id,102
        }
        assertEquals 601,empleados.size()
    }*/

    /*
    @Test
    void debieraTraerEmpleadosPorRangoAndEmpresaAndTipo(){
        //50 empleados
        //select * from (select * from aron.empleado where clave between '9800001' and '9800093' and status='A'  and empresa_id='102')emp,
        //(select * from aron.empleadolaborales where id_tipoempleado=1 )lab where emp.id=lab.id
        //Empresa y tipo
        def claveUno="9800001"
        def claveDos="9800093"
        Empresa empresa=Empresa.get(102)
        assertEquals empresa.nombre,"CENTRAL"
        TipoEmpleado tipo=TipoEmpleado.get(1)
        assertEquals tipo.descripcion,"DENOMINACIONAL"
        def empleados=empleadoService.getEmpleadosByRangoEmpresaAndTipo(empresa,tipo,claveUno,claveDos)
        assertNotNull empleados
        assertEquals 47,empleados.size()
        for(Empleado emp:empleados){
            assertEquals 1,emp.empleado.tipo.id
            assertEquals 102,emp.empresa.id
        }
    }*/

    
    @Test
    void debieraTraerEmpleadosPorRango(){

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980000"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            //println "claveConcatenada ${claveConcatenada}"
            empleadosPorRango.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleadosPorRango.size() == 10
        println "empleados en BD: ${empleadosPorRango.size()}"

        String claveInicial = "9800000"
        String claveFinal = "9800009"

        empleadosPorRango = empleadoService.getEmpleadosByRango(claveInicial, claveFinal)

        println "empleados filtrados by rango.size(): ${empleadosPorRango.size()}"
        println "empleados filtrados by rango"
        for(Empleado e : empleadosPorRango){
            println "${e.clave}"
        }

        assertNotNull empleadosPorRango
        assertEquals 10, empleadosPorRango.size()

    }

    /**
     * Agerga una Percepcion al Empleado. En realidad, guarda la relacion EmpleadoPerded
     * Documentacion acerca de como funcionan las relaciones:
     * http://grails.org/doc/2.0.0.RC1/guide/GORM.html#sets,ListsAndMaps
     **/
    @Test
    void debieraAniadirPercepcionToEmpleado(){
        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        PerDed PD100 = new PerDed(
            clave: "PD100",
            nombre: "PERCEPCION CIEN",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD101 * PD101",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD100

        def clave = "9800052"
        Empleado empleado = crearEmpleadoPrueba(clave)

        def perdedClave = "PD100"
        PerDed perded = PerDed.findByClave(perdedClave)

        BigDecimal importe = new BigDecimal("100.00")
        String tipoImporte = "A"
        String atributos = "TEST"
        Boolean otorgado = true
        Boolean isEditableByNOM = false

        Boolean agregarPercepcion = empleadoService.addPercepcionToEmpleado(empleado, perded, importe, tipoImporte, atributos, otorgado, isEditableByNOM)
        assertTrue agregarPercepcion

        Map<String,EmpleadoPerded> perdedsEmpleadoMap = empleado.perdeds

        List<EmpleadoPerded> perdedsEmpleado = new ArrayList<EmpleadoPerded>()
        perdedsEmpleado.addAll(perdedsEmpleadoMap.values())
        assertEquals 5 , perdedsEmpleado.size() //cuatro que ya tenia + 1 que agregue

        boolean loEncontro = false
        for(EmpleadoPerded ep : perdedsEmpleado){
            println "ep: ${ep.perded.clave}"
            if(ep.perded.clave.equals(perdedClave)){
                loEncontro = true
                println "perded agregada: ${ep}"
            }
        }
        assertTrue loEncontro
    }

    /**
     * Modifica una Percepcion del Empleado. En realidad solo hace un update del EmpleadoPerded
     * Documentacion acerca de como funcionan las relaciones:
     * http://grails.org/doc/2.0.0.RC1/guide/GORM.html#sets,ListsAndMaps
     **/
    @Test
    void debieraModificarPercepcionDeEmpleado(){
        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)

        Map<String,EmpleadoPerded> perdedsEmpleadoMap = empleado.perdeds

        List<EmpleadoPerded> perdedsEmpleado = new ArrayList<EmpleadoPerded>()
        perdedsEmpleado.addAll(perdedsEmpleadoMap.values())
        assertEquals 4 , perdedsEmpleado.size()

        for(EmpleadoPerded epd : perdedsEmpleado){
            println "clave: ${epd.perded.clave}"
        }

        EmpleadoPerded empleadoPerded
        for(EmpleadoPerded ep : perdedsEmpleado){
            if(ep.perded.clave.equals("PD103")){ //Todos los empleadoPerded guardados en Empleado van con el atributo otorgado como falso
                empleadoPerded = ep
            }
        }
        assert empleadoPerded
        assertTrue empleadoPerded.otorgado == false
        
        //Modificando
        empleadoPerded.otorgado = true

        Boolean modificarPerecepcion = empleadoService.updatePercepcionFromEmpleado(empleadoPerded)
        assertTrue modificarPercepcion

        perdedsEmpleadoMap = empleado.perdeds

        perdedsEmpleado.clear()
        perdedsEmpleado.addAll(perdedsEmpleadoMap.values())
        assertEquals 4 , perdedsEmpleado.size()

        EmpleadoPerded empleadoPerdedUpdated
        for(EmpleadoPerded ep : perdedsEmpleado){
            if(ep.perded.clave.equals("PD103")){
                empleadoPerdedUpdated = ep
            }
        }

        assertTrue empleadoPerdedUpdated.otorgado == true

    }

    /**
     * Elimina una Percepcion del Empleado.
     * Documentacion acerca de como funcionan las relaciones:
     * http://grails.org/doc/2.0.0.RC1/guide/GORM.html#sets,ListsAndMaps
     **/
    @Test
    void debieraEliminarPercepcionDeEmpleado(){
        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        crearTipoEmpleados()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)

        Map<String,EmpleadoPerded> perdedsEmpleadoMap = empleado.perdeds

        List<EmpleadoPerded> perdedsEmpleado = new ArrayList<EmpleadoPerded>()
        perdedsEmpleado.addAll(perdedsEmpleadoMap.values())
        assertEquals 4 , perdedsEmpleado.size()

        for(EmpleadoPerded epd : perdedsEmpleado){
            println "clave: ${epd.perded.clave}"
        }

        EmpleadoPerded empleadoPerded
        for(EmpleadoPerded ep : perdedsEmpleado){
            if(ep.perded.clave.equals("PD103")){ 
                empleadoPerded = ep
            }
        }
        assert empleadoPerded

        Boolean eliminoPercepcion = empleadoService.deletePercepcionFromEmpleado(empleado, empleadoPerded)
        println "eliminoPercepcion: ${eliminoPercepcion}"

        perdedsEmpleadoMap.clear()
        perdedsEmpleadoMap = empleado.perdeds

        perdedsEmpleado.clear()
        perdedsEmpleado.addAll(perdedsEmpleadoMap.values())
        assertEquals 3 , perdedsEmpleado.size()

        println "Ya se debio haber eliminado"
        for(EmpleadoPerded epd : perdedsEmpleado){
            println "clave: ${epd.perded.clave}"
        }

        boolean loEncontro = false
        for(EmpleadoPerded ep : perdedsEmpleado){
            if(ep.perded.clave.equals("PD103")){
                loEncontro = true
            }
        }
        assertTrue loEncontro == false

    }

    /*
     *Seccion de Leer Las Perdeds del empleado
     */
    /*@Test
    void debieraLeerPercepcionesDeduccionesEmpleado(){
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        assertNotNull empleado.perdeds
        assertEquals 8,empleado.perdeds.size()
        Map empleadoPerdeds=empleado.perdeds
        assertEquals true,empleadoPerdeds.containsKey('1')
        EmpleadoPerded emperd=empleadoPerdeds.get('1')
        assertEquals 'D,B,I,N,PS,BN',emperd.atributos
        assertEquals 87,emperd.id
        assertEquals 100,emperd.importe
        assertEquals 1,emperd.perded.id
    }

    @Test
    void debieraGuardarPercecionesDeducciones(){
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        def perDed=PerDed.get(1)
        assertEquals 1,perDed.id
        assertEquals "Salario",perDed.nombre
        assertEquals 'C',perDed.naturaleza
        EmpleadoPerded emperd=new EmpleadoPerded()
        emperd.importe=new BigDecimal("0")
        emperd.tipoImporte="%"
        emperd.atributos="D,B,I,N,PS,BN"
        emperd.otorgado=true
        emperd.isEditableByNOM=true
        emperd.empleado=empleado
        emperd.perded=perDed
        assertNull emperd.id
        emperd.save()
        assertNotNull emperd
        assertNotNull emperd.id
        Map empleadoPerdeds=empleado.perdeds
        System.out.println("empleados $empleadoPerdeds")
    }


    @Test
    void debieraModificarPercecionesDeducciones(){
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        Map empleadoPerdeds=empleado.perdeds
        assertEquals true,empleadoPerdeds.containsKey('1')
        EmpleadoPerded emperd=empleadoPerdeds.get('1')
        assertEquals 'D,B,I,N,PS,BN',emperd.atributos
        assertEquals 87,emperd.id
        assertEquals 100,emperd.importe
        assertEquals 1,emperd.perded.id
        emperd.atributos="D,I,PS,BN"
        emperd.importe=new BigDecimal("10000")
        emperd.tipoImporte='$'
        emperd.save()
        emperd=empleadoPerdeds.get('1')
        assertEquals "D,I,PS,BN",emperd.atributos
        assertEquals '$',emperd.tipoImporte
        assertEquals new BigDecimal("10000") ,emperd.importe

    }
    @Test
    void debieraLeerFormula(){
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        Map empleadoPerdeds=empleado.perdeds
        EmpleadoPerded emperd=empleadoPerdeds.get('1')
        assertNotNull emperd
        assertEquals 87,emperd.id
        assertNotNull emperd.perded
        assertEquals 1,emperd.perded.id
        assertNotNull emperd.perded.formula
        assertEquals '-',emperd.perded.formula
    }

    @Test
    void debieraModificarFormula(){
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        assertEquals 139,empleado.id
        Map empleadoPerdeds=empleado.perdeds
        EmpleadoPerded emperd=empleadoPerdeds.get('1')
        assertNotNull emperd
        assertEquals 87,emperd.id
        assertNotNull emperd.perded
        assertEquals 1,emperd.perded.id
        assertNotNull emperd.perded.formula
        assertEquals '-',emperd.perded.formula
        emperd.perded.formula='P000*P200'
        emperd.save()
        emperd=null
        emperd=empleadoPerdeds.get('1')
        assertEquals 'P000*P200',emperd.perded.formula
    }

    @Test
    void debieraValidarTodosEmpleadosNomina(){
        //Escalafon,CuentaDeBanco,Tiempo trabaja completo o asi,grupo, cuenta contable,centro de costo
        //armar mensaje de error en la lista
        def clave="9800052"
        def empleado=empleadoService.getEmpleado(clave)
        assertEquals 139,empleado.id
        List empleados=new ArrayList()
        empleados.add(empleado)
        Map tmp=empleadoServiceInt.validaEmpleados(empleados)
        assertNotNull tmp
        assertEquals 0,tmp.size()
    }
    @Test
    void debieraMostarErrorEscalafonEmpleadosNomina(){
        //Escalafon,CuentaDeBanco,Tiempo trabaja completo o asi,grupo, cuenta contable,centro de costo
        def clave="9810207"
        def empleado=empleadoService.getEmpleado(clave)
        assertEquals 291,empleado.id
        List empleados=new ArrayList()
        empleados.add(empleado)
        Map tmp=empleadoServiceInt.validaEmpleados(empleados)
        assertNotNull tmp
        assertEquals 1,tmp.size()
        assertEquals true,tmp.containsKey("empleado.escalafon.invalido")
        assertEquals "9810207",tmp.get("empleado.escalafon.invalido")
    }


    @Test
    void debieraMostarErrorCuentaBancoEmpleadosNomina(){
        //Escalafon,CuentaDeBanco,Tiempo trabaja completo o asi,grupo, cuenta contable,centro de costo
        def clave="9810213"
        def empleado=empleadoService.getEmpleado(clave)
        assertEquals 281,empleado.id
        List empleados=new ArrayList()
        empleados.add(empleado)
        Map tmp=empleadoServiceInt.validaEmpleados(empleados)
        assertNotNull tmp
        assertEquals 1,tmp.size()
        assertEquals true,tmp.containsKey("empleado.cuentaBanco.invalido")
        assertEquals "9810213",tmp.get("empleado.cuentaBanco.invalido")
    }
    @Test
    void debieraMostarErrorTurnoEmpleadosNomina(){
        //Escalafon,CuentaDeBanco,Tiempo trabaja completo o asi,grupo, cuenta contable,centro de costo
        def clave="9800397"
        def empleado=empleadoService.getEmpleado(clave)
        assertEquals 296,empleado.id
        List empleados=new ArrayList()
        empleados.add(empleado)
        Map tmp=empleadoServiceInt.validaEmpleados(empleados)
        assertNotNull tmp
        assertEquals 1,tmp.size()
        assertEquals true,tmp.containsKey("empleado.turno.invalido")
        assertEquals "9800397",tmp.get("empleado.turno.invalido")
    }
    @Test
    void debieraMostarErrorGrupoEmpleadosNomina(){
        //Escalafon,CuentaDeBanco,Tiempo trabaja completo o asi,grupo, cuenta contable,centro de costo
        def clave="9800104"
        def empleado=empleadoService.getEmpleado(clave)
        assertEquals 306,empleado.id
        List empleados=new ArrayList()
        empleados.add(empleado)
        Map tmp=empleadoServiceInt.validaEmpleados(empleados)
        assertNotNull tmp
        assertEquals 1,tmp.size()
        assertEquals true,tmp.containsKey("empleado.grupo.invalido")
        assertEquals "9800104",tmp.get("empleado.grupo.invalido")
    }


    @Test
    void debieraMostarCuentaContableEmpleadosNomina(){
        //Pruebas Aun no Implementadas
    }
    @Test
    void debieraMostarCentroCostoEmpleadosNomina(){
        //Pruebas Aun no Implementadas
    }
		*/
    

    

   
}
