package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.*
import org.junit.*
import java.util.regex.*
import general.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(NominaController)
class NominaControllerIntegrationTests extends BaseIntegrationTest {

    def empleadoService
    def nominaService
    def perdedService
    def springSecurityService

    Empleado crearEmpleadoPrueba(){
        def grupoPrueba = new Grupo(
            nombre : "A",
            minimo : 103,
            maximo : 141
        ).save()
        assertNotNull grupoPrueba

        def tipoEmpleado = new TipoEmpleado(
            descripcion : "DENOMINACIONAL",
            prefijo : "980"
        ).save()
        assertNotNull tipoEmpleado

        def organizacion = new Organizacion (
            codigo : 'TST1'
            , nombre : 'TEST-1'
            , nombreCompleto : 'TEST-1'
        ).save()
        assertNotNull organizacion

        def empresa = new Empresa(
            codigo : "123456",
            nombre : "EMPRESA PRUEBA",
            nombreCompleto : "Empresa de Prueba - Nombre Completo",
            organizacion : organizacion
        ).save()
        assertNotNull empresa

        def empleado = new Empleado(
            empresa: empresa,
            clave : "9800001",
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
            //perdedsList : generarPerdedsForEmpleado()
        ).save()
        assertNotNull empleado

        List<Empleado> empleadoList = Empleado.findAll()
        println "empleados: ${empleadoList.size()}"
        println "en BD: ${Empleado.count()}"
        Empleado e = empleadoList.get(0)
        println "empleado en lista: ${e.clave}"
        println "empleado en lista attr: ${e}"

        //Agregando las Percepciones
        //generarPerdedsForEmpleado(empleado)
        List<PerDed> ps = new ArrayList<PerDed>()
        ps.add(PerDed.findByClave("PD003"))
        ps.add(PerDed.findByClave("PD004"))
        ps.add(PerDed.findByClave("PD005"))
        ps.add(PerDed.findByClave("PD006"))

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

        PerDed PD001 = new PerDed(
            clave: "PD001",
            nombre: "PERCEPCION UNO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
            atributos : ["A":"B"]
        ).save()
        assertNotNull PD001

        PerDed PD002 = new PerDed(
            clave: "PD002",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
            atributos : ["A":"B"]
        ).save()
        assertNotNull PD002

        PerDed PD003 = new PerDed(
            clave: "PD003",
            nombre: "PERCEPCION TRES",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD001 * PD007",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD003

        PerDed PD004 = new PerDed(
            clave: "PD004",
            nombre: "TPERCEPCION CUATRO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD002",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD004

        PerDed PD005 = new PerDed(
            clave: "PD005",
            nombre: "PERCEPCION CINCO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD002",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD005

        PerDed PD006 = new PerDed(
            clave: "PD006",
            nombre: "PERCEPCION SEIS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD001",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD006

        PerDed PD007 = new PerDed(
            clave: "PD007",
            nombre: "PERCEPCION SIETE",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "0",
            atributos: ["A":"B"]
        ).save()
        assertNotNull PD007
    }

    public crearPorcentajes(){
        println "Creando Porcentajes"

        Porcentaje porcentajePD001 = new Porcentaje(
            valor : new BigDecimal("2.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD001"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD001

        Porcentaje porcentajePD002 = new Porcentaje(
            valor : new BigDecimal("4.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD002"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD002

        Porcentaje porcentajePD005 = new Porcentaje(
            valor : new BigDecimal("6.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD005"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD005

        Porcentaje porcentajePD006 = new Porcentaje(
            valor : new BigDecimal("8.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD006"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD006

        Porcentaje porcentajePD007 = new Porcentaje(
            valor : new BigDecimal("0.00"),
            valorDos : new BigDecimal("0.00"),
            perded : PerDed.findByClave("PD007"),
            grupo : Grupo.findByNombre("X")
        ).save()
        assertNotNull porcentajePD007
    }


    /**
     * Comprueba que se pueda obtener el Map de Formulas del Grupo X.
     * Map<(String)ClavePercepcion,(String)formulaPercepcion>
     * Las formulas se van a traer de la tabla PERDEDS
     *
     * Query para saber las Percepciones pertenecientes al Grupo 6
     * select aron.PERDEDS.id, aron.PERDEDS.clave, aron.PERDEDS.formula
     * from aron.PERDEDS, (select perded_id from aron.PERDEDS_PORCENTAJES where grupo_id=6) pp
     * where aron.PERDEDS.id=pp.perded_id
    **/
    @Test
    void debieraArmarMapFormulasGrupoX(){
        log.debug 'testArmarMapGrupoX'

        crearGrupos()
        println "grupos: ${Grupo.findAll().size()}"
        assertNotNull Grupo.findAll()
        assertTrue Grupo.findAll().size() != 0

        crearPerdeds()
        println "perdeds: ${PerDed.findAll().size()}"
        assertNotNull PerDed.findAll()
        assertTrue PerDed.findAll().size() != 0

        crearPorcentajes()
        assertNotNull Porcentaje.findAll()
        assertTrue Porcentaje.findAll().size() != 0

        Map<String,String> mapGX = perdedService.getMapFormulasGrupoX()
        assertNotNull mapGX

        List<Porcentaje> porcentajes = Porcentaje.findAllByGrupo(Grupo.findByNombre("X"))
        List<PerDed> perdedsGX = new ArrayList<PerDed>()
        for(Porcentaje p : porcentajes){
            perdedsGX.add(p.perded)
        }

        for(PerDed p : perdedsGX){
            assertTrue mapGX.containsKey(p.clave)
            assertEquals mapGX.get(p.clave), p.formula
        }
    }

    /**
     *Prueba que se sustituyan los valores en las formulas en el Map del GrupoX
     *Para el Pattern y Matcher ver: http://www.programacion.com/articulo/expresiones_regulares_en_java_127
     **/
    @Test
    void debieraSustituirPorcentajesEnFormulasGrupoX(){
        log.debug 'testSustituirPorcentajesEnFormulasGrupoX'

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()

        Map<String,String> mapGX = perdedService.getMapFormulasGrupoX()
        assertNotNull mapGX

        Map<String,String> mapGXSustituido = perdedService.getMapGrupoXSustituido()
        assertNotNull mapGXSustituido

        //assertTrue mapGX.size() == mapGXSustituido.size()

        List<String> valuesMapGX = new ArrayList<String>()
        valuesMapGX.addAll(mapGX.values())
        List<String> valuesMapGXSustituido = new ArrayList<String>()
        valuesMapGXSustituido.addAll(mapGXSustituido.values())

        System.out.println("valuesMapGX.values.size: ${valuesMapGX.size()}")
        System.out.println("valuesMapGXSustituido.values.size: ${valuesMapGXSustituido.size()}")

        assertTrue valuesMapGX.size() == valuesMapGXSustituido.size()

        Pattern p = Pattern.compile("%") //Valida que no exista el simbolo % en ningun String de la formula
        for(String f : valuesMapGXSustituido){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }

        p = Pattern.compile("&") //Valida que no exista el simbolo & en ningun String de la formula
        for(String f : valuesMapGXSustituido){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }
    }

    /**
     * Comprueba que se trainga el Map de Formulas Completo de un Empleado, pero aun sin sustituir las del Empleado
     * Map<(String)ClavePercepcion,(String)formulaPerecepcion>
    **/
    @Test
    void debieraArmarMapDeFormulasPorEmpleado(){ 
        log.debug 'testArmarMapDeFormulasPorEmpleado'

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()

        Empleado empleado = crearEmpleadoPrueba()

        //Sin sustituir las formulas del Empleado (las generales ya estan sustituidas en este paso)
        Map<String,String> mapPerdedsEmpleado = nominaService.getMapPercepcionesEmpleado(empleado)
        assertNotNull mapPerdedsEmpleado

        //Traer las perdes del empleado y validar que se encuentre la percepcion en el map y que su formula sin sustituir este en el map
        Map<String,String> perdedsEmpleado = empleado.perdeds
        List<EmpleadoPerded> listaPercepciones = new ArrayList<EmpleadoPerded>()
        listaPercepciones.addAll(perdedsEmpleado.values())

        for(EmpleadoPerded ep : listaPercepciones){
            assertTrue mapPerdedsEmpleado.containsKey(ep.perded.clave)
            assertEquals mapPerdedsEmpleado.get(ep.perded.clave) , ep.perded.formula
        }
    }

    /**
     *Sustituye en las formulas los porcentajes del Empleado. Cada caracter '%' se sustituye el porcentaje
     *Para el Pattern y Matcher ver: http://www.programacion.com/articulo/expresiones_regulares_en_java_127
    **/
    @Test
    void debieraSustituirPorcentajesEnFormulasDeEmpleado(){

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()
        
        Empleado empleado = crearEmpleadoPrueba()
        System.out.println(empleado)

        Map<String,String> mapPerdesConFormulasSustituidasDelEmpleado = nominaService.getMapPercepcionesSustituidasEmpleado(empleado)
        assertNotNull mapPerdesConFormulasSustituidasDelEmpleado
        System.out.println("mapPerdesConFormulasSustituidasDelEmpleado.size(): " + mapPerdesConFormulasSustituidasDelEmpleado.size())
        List<String> formulasSustituidas = new ArrayList<String>()
        formulasSustituidas.addAll(mapPerdesConFormulasSustituidasDelEmpleado.values())

        System.out.println("Formulas Completas Sustituidas")
        for(String f : formulasSustituidas){
            System.out.println(f)
        }

        Pattern p = Pattern.compile("%")
        //Se concatena en un String todas las formulas y luego se valida que no exista el simbolo %
        for(String f : formulasSustituidas){
            System.out.println(f)
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }

         p = Pattern.compile("&") //Valida que no exista el simbolo & en ningun String de la formula
        for(String f : formulasSustituidas){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }
    }


    /*@Test
    void debieraRegresarmeValorNumericoFromEvaluador(){
        assertError 'IMPLEMENTAME!!!!'
    }*/

    /**
     * Regresa una lista con las percepciones de un Empleado, donde el primer valor(0) es la clave del Empleado y el resto son los valores
     * de las percepciones usando el siguiente formato:
     * NombrePercepcion(String) , ValorPercepcion(String)
    **/
    @Test
    void debieraRegresarNominaDeUnEmpleado(){

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()

        Empleado empleado = crearEmpleadoPrueba()
        System.out.println(empleado)

        List<String> nominaEmpleado = nominaService.getNominaEmpleado(empleado);
        assertNotNull nominaEmpleado
        
        assertEquals nominaEmpleado.size(), 5

        println "Nomina EMpleado"
        for(String n : nominaEmpleado){
            println n
        }
    }

    /**
     *Regresa los movimientos de todos los empleados en el rango especificado por las claves
    **/
    @Test
    void debieraRegresarNominaPorRangosDeEmpleados(){

        crearGrupos()
        crearPerdeds()
        crearPorcentajes()

        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()
        System.out.println(empleadosPorRango.size())

        String claveInicial = "CLAVE UNO"
        String claveFinal = "CLAVE DOS"

        Map<String,List> nominaEmpleadosPorRango = nominaService.getNominaEmpleadosPorRango(claveInicial, claveFinal)

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarNominaPorTipoDeEmpleado(){

    }

    /**
     *tipo nomina=diaria, semanal, quincenal
     *NOTA: En la nomina diaria, validar cuando dieron de alta el empleado para calcularle hasta donde ha trabajado y no mas
    **/
    //@Test
    void debieraRegresarPorTipoDeNominaDeUnEmpleado(){ //esta es la primera prueba que ya esta, no? antes de rango de empleados

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarPorTipoDeNominaDeRangoEmpleados(){

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarPorTipoNominaPorTipoEmpleado(){

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarPercecionEspecificadaUnEmpleado(){

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarPercecionEspecificadaPorRangoDeEmpleados(){

    }

    /**
     *
    **/
    //@Test
    void debieraRegresarPercecionEspecificadaPorTipoDeEmpleados(){

    }
}
