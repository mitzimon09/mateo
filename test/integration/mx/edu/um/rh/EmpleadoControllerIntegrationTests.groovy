package mx.edu.um.rh

import general.*
import grails.test.*
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestMixin(GrailsUnitTestMixin)
//@TestFor(EmpleadoController)
class EmpleadoControllerIntegrationTests extends BaseIntegrationTest{

    def springSecurityService
    def empleadoService
    
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
        
        for(i in 1..20) {
	        Empleado empleado = new Empleado(
                clave : "98000$i"
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
            
    	Empleado empleado = new Empleado(
            clave : "9800001"
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
            
    	Empleado empleado = new Empleado(
            clave : "9800001"
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
	
	@Test
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
        
		assertEquals controller.params.clave, "1110002"        
  	}
	
    Empleado crearEmpleadoPrueba(String claveEmpleado){
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
        
	    def grupoPrueba = new Grupo(
            nombre : "A"
            , minimo : 103
            , maximo : 141
        ).save()
        assertNotNull grupoPrueba
        
	    def tipoEmpleado = new TipoEmpleado(
            descripcion : "DENOMINACIONAL"
            , prefijo : "980"
        ).save()
        assertNotNull tipoEmpleado

        //Agregando las Percepciones NOTA: Se traen directo de la BD porque son catalogos y se espera que no cambien, pero podria necesitarse
        //a futuro que se hicieran unas de prueba asi como el Empleado mismo, para no depender de la BD
        List<PerDed> ps = new ArrayList<PerDed>()

        ps.add(PerDed.findByClave("PD003"))
        ps.add(PerDed.findByClave("PD004"))
        ps.add(PerDed.findByClave("PD005"))
        ps.add(PerDed.findByClave("PD006"))

        List<EmpleadoPerded> eps = new ArrayList<EmpleadoPerded>()
        for(int i = 0; i < 4; i++){
            EmpleadoPerded ep= new EmpleadoPerded(
                perded : ps.get(i),
                importe : i + 100,
                tipoImporte : "%",
                atributos : "N",
                otorgado : false,
                isEditableByNOM : true,
                //empleado : empleado
                )
            //ep.save()
            eps.add(ep)
            assertNotNull ep
        }

        Empleado empleado = new Empleado(
            clave : claveEmpleado,
            nombre : "TESTA",
            apPaterno : "TESTA",
            apMaterno : "TESTA",
            genero : "FM",
            fechaNacimiento : new Date(),
            direccion : "TEST",
            status : "A",
            //Map perdeds
            tipo : tipoEmpleado,
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
            //perdedsList : eps
        ).save()
        assertNotNull empleado

        List<Empleado> empleadoList = Empleado.findAll()
        println "empleados: ${empleadoList.size()}"
        println "en BD: ${Empleado.count()}}"
        Empleado e = empleadoList.get(0)
        println "empleado en lista: ${e.clave}"
        println "empleado en lista attr: ${e}"

        empleado.perdedsList = eps
        assertNotNull empleado.perdedsList

        Map<String,EmpleadoPerded> perdedsEmpleado = empleado.perdeds
        assertNotNull perdedsEmpleado
        println "empleado.perdeds.size: ${perdedsEmpleado.size()}"

        return empleado
    }

    /*
     *Esta Prueba no corre en el controller prueba la funcionalidad del service
     */

    /*@Test
    void debieraTraerUnEmpleadoPorClaveANivelService(){
        log.debug "test EmpleadoByClave"
        def clave="9800052"
        def empleado=empleadoServiceInt.getEmpleado(clave)
        //notThrown(NullPointerException)
        assertEquals 'ISMAEL',empleado.nombre
        assertEquals 'CASTILLO',empleado.apPaterno
        assertEquals 'OSUNA',empleado.apMaterno
        assertEquals 139,empleado.id
    }

    @Test
    void debieraMarcarErrorEmpleadoPorClaveANivelService(){
        log.debug "test EmpleadoByClave"
        def clave="9999999"
        try{
            def empleado=empleadoServiceInt.getEmpleado(clave)
        }catch (NullPointerException npe){
            assertEquals "empleado.inexistente",npe.message
        }
    }

    @Test
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
    }

    @Test
    void debieraTraerEmpleadosByTipo(){
        log.debug "debieraTraerEmpleadosByTipo"
        def tipo=TipoEmpleado.get(1)
        assertEquals tipo.descripcion,"DENOMINACIONAL"
        def empleados=empleadoServiceInt.getEmpleadosByTipo(tipo)
        for(Empleado empleado in empleados){
            assertEquals empleado.tipo.id,1
        }
        assertEquals 432,empleados.size()
    }

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

    /*
    @Test
    void debieraTraerEmpleadosPorRango(){
        //Este aun no funciona porque no funciona el metodo crearEmpleado(String clave)
        String claveUno = "9800001"
        String calveDos = "9800002"
        String calveTres = "9800003"
        Empleado empleado = crearEmpleadoPrueba(claveUno)
        assertNotNull empleado


        //Debiera traer 3 empleados (9800001, 9800002 y 9800003)
        def empleados = empleadoService.getEmpleadosByRango(claveUno, claveDos)
        assertNotNull empleados
        assertEquals 3,empleados.size()

    }*/
    
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
