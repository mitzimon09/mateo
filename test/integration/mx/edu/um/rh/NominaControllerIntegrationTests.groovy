package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.*
import org.junit.*
import java.util.regex.*
import general.*
import mx.edu.um.Constantes
import mx.edu.um.common.evaluador.*
import enums.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestFor(NominaController)
class NominaControllerIntegrationTests extends BaseIntegrationTest {

    def empleadoService
    def nominaService
    def perdedService
    def springSecurityService

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

    /**
     * Este metodo crea unos Atributos de Prueba para que se puedan ocupar en las pruebas
     **/
    public crearAtributos(){
        Atributo atrA = new Atributo(
            nombre: "ATR_A",
            descripcion : "ATR_A",
            simbolo : "_A"
        ).save()
        assert atrA

        Atributo atrB = new Atributo(
            nombre: "ATR_B",
            descripcion : "ATR_B",
            simbolo : "_B"
        ).save()
        assert atrB

        Atributo atrC = new Atributo(
            nombre: "ATR_C",
            descripcion : "ATR_C",
            simbolo : "_C"
        ).save()
        assert atrC

        Atributo atrD = new Atributo(
            nombre: "ATR_D",
            descripcion : "ATR_D",
            simbolo : "_D"
        ).save()
        assert atrD

        Atributo palabraReservada = Atributo.findByNombre(Constantes.ATRIBUTO_PALABRA_RESERVADA)
//        println "palabraReservada: ${palabraReservada}"
        if(!palabraReservada){
            palabraReservada = new Atributo(
                nombre: Constantes.ATRIBUTO_PALABRA_RESERVADA,
                descripcion : Constantes.ATRIBUTO_PALABRA_RESERVADA,
                simbolo : "PR"
            ).save(flush:true)
            assert palabraReservada
//            println "palabraReservada: ${palabraReservada}"
        }
        assert palabraReservada
    }

    /**
     * Este metodo crea algunas Percepciones (PerDed) con sus respectivos atributos(PerDedAtributo)
     **/
    List<PerDed> crearPerdeds(){
        println "Creando Perdeds"
        Atributo atrA = Atributo.findByNombre("ATR_A")
        assert atrA
        Atributo atrB = Atributo.findByNombre("ATR_B")
        assert atrB
        Atributo atrC = Atributo.findByNombre("ATR_C")
        assert atrC
        Atributo palabraReservada = Atributo.findByNombre(Constantes.ATRIBUTO_PALABRA_RESERVADA)
        assert palabraReservada


        List<PerDed> perdedsCreadas = new ArrayList<PerDed>()

        //Creando Percepcion de Prueba PD101
        PerDed PD101 = new PerDed(
            clave: "PD101",
            nombre: "PERCEPCION UNO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%"
        )
        //agregarle sus atributos a PD101
        PerDedAtributo paPD101_A = new PerDedAtributo(
            atributo : atrA,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_A)

        PerDedAtributo paPD101_B = new PerDedAtributo(
            atributo : atrB,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_B)

        PerDedAtributo paPD101_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_PR)

        PD101.save()
        assert PD101
        perdedsCreadas.add(PD101)
        assertEquals 3 , PD101.atributos.size()

        //Creando Percepcion de Prueba PD102
        PerDed PD102 = new PerDed(
            clave: "PD102",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
        )
        //agregarle sus atributos a PD102
        PerDedAtributo paPD102_A = new PerDedAtributo(
            atributo : atrA,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_A)

        PerDedAtributo paPD102_B = new PerDedAtributo(
            atributo : atrB,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_B)

        PerDedAtributo paPD102_C = new PerDedAtributo(
            atributo : atrC,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_C)

        PerDedAtributo paPD102_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD101
        )
        PD101.addToAtributos(paPD102_PR)

        PD102.save()
        assert PD102
        perdedsCreadas.add(PD102)
        assertEquals 4 , PD101.atributos.size()

        //Creando Percepcion de Prueba PD103
        PerDed PD103 = new PerDed(
            clave: "PD103",
            nombre: "PERCEPCION TRES",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD101 * PD107"
        )
        //agregarle sus atributos a PD103
        PerDedAtributo paPD103_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD103
        )
        PD103.addToAtributos(paPD103_PR)
        PD103.save()
        assert PD103
        perdedsCreadas.add(PD103)

        //Creando Percepcion de Prueba PD104
        PerDed PD104 = new PerDed(
            clave: "PD104",
            nombre: "TPERCEPCION CUATRO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD102"
        )
        //agregarle sus atributos a PD104
        PerDedAtributo paPD104_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD104
        )
        PD104.addToAtributos(paPD104_PR)
        PD104.save()
        assert PD104
        perdedsCreadas.add(PD104)

        //Creando Percepcion de Prueba PD105
        PerDed PD105 = new PerDed(
            clave: "PD105",
            nombre: "PERCEPCION CINCO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD102"
        )
        //agregarle sus atributos a PD105
        PerDedAtributo paPD105_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD105
        )
        PD105.addToAtributos(paPD105_PR)
        PD105.save()
        assert PD105
        perdedsCreadas.add(PD105)

        //Creando Percepcion de Prueba PD106
        PerDed PD106 = new PerDed(
            clave: "PD106",
            nombre: "PERCEPCION SEIS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD101"
        )
        //agregarle sus atributos a PD106
        PerDedAtributo paPD106_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD106
        )
        PD106.addToAtributos(paPD106_PR)
        PD106.save()
        assert PD106
        perdedsCreadas.add(PD106)

        //Creando Percepcion de Prueba PD107
        PerDed PD107 = new PerDed(
            clave: "PD107",
            nombre: "PERCEPCION SIETE",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "0"
        )
        //agregarle sus atributos a PD107
        PerDedAtributo paPD107_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD107
        )
        PD107.addToAtributos(paPD107_PR)
        PD107.save()
        assert PD107
        println "PD107: ${PD107}"
        println "perdedsCreadas: ${perdedsCreadas}"
        perdedsCreadas.add(PD107)

        return perdedsCreadas
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

    /**
     * Crea un Empleado de Prueba que contiene todas sus Percepciones
     **/
    Empleado crearEmpleadoPrueba(String claveEmpleado){
        println "Creando Empleado Prueba"

        println "empleados en BD: ${Empleado.count()}"

        def empleado = new Empleado(
            empresa: Empresa.findByCodigo("CTL"),
            clave : claveEmpleado,
            nombre : "TESTA",
            apPaterno : "TESTA",
            apMaterno : "TESTA",
            genero : Constantes.GENERO_MASCULINO,
            fechaNacimiento : new Date(),
            direccion : "TEST",
            status : Constantes.STATUS_ACTIVO,
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
            modalidad : Constantes.MODALIDAD_APOYO,
            ife : "123456789012",
            rango : "SR",
            adventista : true,
            fechaAntiguedadBase : new Date(),
            antiguedadBase : new BigDecimal(0.00),
            antiguedadFiscal : new BigDecimal(0.00),
            grupo : Grupo.findByNombre("A"),
            padre : "TESTP",
            madre: "TESTM",
            estadoCivil : Constantes.ESTADO_CIVIL_SOLTERO,
            conyuge : "TESTC",
            fechaMatrimonio : new Date(),
            iglesia : "TESTI",
            responsabilidad : "TESTR"//,
        ).save(flush:true)
        assert empleado

        println "empleados en BD: ${Empleado.count()}"

        EmpleadoPerded ep1= new EmpleadoPerded(
            perded : PerDed.findByClave("PD103"),
            importe : new BigDecimal("101.00"),
            tipoImporte : "%",
            atributos : "N",
            otorgado : false,
            isEditableByNOM : true,
            empleado : empleado
        )
        empleado.addToPerdedsList(ep1)

        EmpleadoPerded ep2= new EmpleadoPerded(
            perded : PerDed.findByClave("PD104"),
            importe : new BigDecimal("102.00"),
            tipoImporte : "%",
            atributos : "N",
            otorgado : false,
            isEditableByNOM : true,
            empleado : empleado
        )
        empleado.addToPerdedsList(ep2)

        EmpleadoPerded ep3= new EmpleadoPerded(
            perded : PerDed.findByClave("PD105"),
            importe : new BigDecimal("103.00"),
            tipoImporte : "%",
            atributos : "N",
            otorgado : false,
            isEditableByNOM : true,
            empleado : empleado
        )
        empleado.addToPerdedsList(ep3)

        EmpleadoPerded ep4= new EmpleadoPerded(
            perded : PerDed.findByClave("PD106"),
            importe : new BigDecimal("104.00"),
            tipoImporte : "%",
            atributos : "N",
            otorgado : false,
            isEditableByNOM : true,
            empleado : empleado
        )
        empleado.addToPerdedsList(ep4)

        empleado.save()

        Map<String,EmpleadoPerded> perdedsEmpleado = empleado.perdeds
        assertNotNull perdedsEmpleado
        assertEquals 4 , perdedsEmpleado.size()

        empleado = Empleado.findByClave(claveEmpleado)
        println "empleado guardado a la BD: ${empleado}"
        println "empleados en BD: ${Empleado.count()}"
        return empleado
    }

    /**
     * Devuelve un Map<(String)ClavePercepcion,(String)formulaPerecepcion> de las Percepciones que tengan como Atributo "Palabra Reservada"
     * A estas Perceciones no se les sustituyen las formulas ya que no tienen valores a sustituir
     **/
    @Test
    void debieraArmarMapPercepcionesReservadas(){
        println 'debieraArmarMapPercepcionesReservadas'
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        Atributo atributo = Atributo.findByNombre(Constantes.ATRIBUTO_PALABRA_RESERVADA)
        List<PerDed> percepcionesReservadasList = perdedService.getPerDedsByAtributo(atributo)
        assert percepcionesReservadasList

        Map<String,String> percepcionesReservadasMap = perdedService.getMapPerdedsReservadas()
        assert percepcionesReservadasMap.values().size() > 7 //Valida que al menos se traigan las Percepciones de Prueba (crearPerdeds())

        //Valida que el map contenga todas las percepciones de la lista y las formulas sean correctas
        for(PerDed p : percepcionesReservadasList){
            assert percepcionesReservadasMap.containsKey(p.clave)
            assert percepcionesReservadasMap.get(p.clave).equals(p.formula)
        }
    }

    @Test
    void debieraSustituirPorcentajesEnMapPercepcionesReservadas(){
        println 'debieraSustituirPorcentajesEnMapPercepcionesReservadas'
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        Map<String,String> mapConPorcentajesSustituidos = perdedService.getMapPerdedsReservadasWithPorcenatjaesSustituidos()
        assert mapConPorcentajesSustituidos.values().size() > 7

        List<String> mapConPorcentajesSustituidosList = new ArrayList<String>()
        mapConPorcentajesSustituidosList.addAll(mapConPorcentajesSustituidos.values())
        assert mapConPorcentajesSustituidosList.size() != 0

        Pattern p = Pattern.compile("%") //Valida que no exista el simbolo % en ningun String de la formula
        for(String f : mapConPorcentajesSustituidosList){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }

        p = Pattern.compile("&") //Valida que no exista el simbolo & en ningun String de la formula
        for(String f : mapConPorcentajesSustituidosList){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }
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
        println 'debieraArmarMapFormulasGrupoX'
        crearGrupos()
        crearAtributos()

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
        println 'debieraSustituirPorcentajesEnFormulasGrupoX'
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        Map<String,String> mapGX = perdedService.getMapFormulasGrupoX()
        assertNotNull mapGX

        Map<String,String> mapGXSustituido = perdedService.getMapGrupoXSustituido()
        assertNotNull mapGXSustituido

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
        println 'debieraArmarMapDeFormulasPorEmpleado'

        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)

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
        println 'debieraSustituirPorcentajesEnFormulasDeEmpleado'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()
        
        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)
        //System.out.println(empleado)

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


    /**
     * Evalua una formula con el Evaluador. Verifica que regrese un numero.
     **/
    @Test
    void debieraRegresarmeValorNumericoFromEvaluador(){
        println 'debieraRegresarmeValorNumericoFromEvaluador'
        Evaluador evaluador = new Evaluador();

        String formula = "(12000 + 1000 - 3000) * 10"

        String formulaEvaluada = evaluador.evaluaExpresion(formula.toString())
        println "formulaEvaluada: ${formulaEvaluada}"
        assertEquals "100000" , formulaEvaluada
    }

    /**
     * Regresa una lista con las percepciones de un Empleado, donde el primer valor(0) es la clave del Empleado y el resto son los valores
     * de las percepciones usando el siguiente formato:
     * NombrePercepcion(String) , ValorPercepcion(String)
     *
     * Esta nomina es MENSUAL
     *
     * Lo que debe regresar este metodo segun los valores que se metieron en el Empleado de Prueba
     * PD001 = 2
     * PD002 = 4
     * PD003 = 0
     * PD004 = 4
     * PD005 = 6 x 4 = 24
     * PD006 = 8 x 2 = 16
     * PD007 = 0
     *
    **/
    @Test
    void debieraRegresarNominaDeUnEmpleado(){
        println 'debieraRegresarNominaDeUnEmpleado'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)
        //System.out.println(empleado)

        TipoNomina tipoNomina = TipoNomina.MENSUAL

        List<String> nominaEmpleado = nominaService.getNominaEmpleado(empleado, tipoNomina)
        assertNotNull nominaEmpleado
        
        assertEquals 5 , nominaEmpleado.size()

        println "Nomina Empleado"
        for(String n : nominaEmpleado){
            println n
        }

        // Esto no se puede validar porque  las percepciones siempre vienen en orden diferente
//        println "Primer elmento de la lista de nominaEmpleado -- > ${nominaEmpleado.get(1)}"
//        assertEquals "PD004,4.00000" , nominaEmpleado.get(1)
    }

    /**
     * Regresa la nomina de todos los empleados en el rango especificado por las claves
     * Esta Nomina es MENSUAL
    **/
    @Test
    void debieraRegresarNominaPorRangosDeEmpleados(){
        println 'debieraRegresarNominaPorRangosDeEmpleados'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980900"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            //println "claveConcatenada ${claveConcatenada}"
            empleadosPorRango.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleadosPorRango.size() == 10
        String claveInicial = "9809000"
        String claveFinal = "9809004"

        TipoNomina tipoNomina = TipoNomina.MENSUAL

        List<String> nominaEmpleadosPorRango = nominaService.getNominaEmpleadosPorRango(claveInicial, claveFinal, tipoNomina)
        assertTrue nominaEmpleadosPorRango.size() == 5

        println "Nomina nominaEmpleadosPorRango" //Aqui es una lista dentro de otra lista, deberia validar de otra manera
        for(String strNomina : nominaEmpleadosPorRango){
            println strNomina
        }

        //Esto deberia probar los valores de la Nomina, pero, resulta que cada vez los valores se regresan de una manera diferente. Habria que
        //regresar ordenadas las Percepciones, desde el Empleado, creo yo, o que pues de alguna manera todas fueran en el mismo orden o, en
        //caso contrario, devolver un Map
//        println "Primer elmento de la lista de nominaEmpleadosPorRango -- > ${(nominaEmpleadosPorRango.get(0)).get(1)}"
//        assertEquals "PD005,24.0000000000" , (nominaEmpleadosPorRango.get(0)).get(1)

    }

    /**
     * Regresa la nomina de todos los empleados que coincidan con el tipo especificado
     * Esta Nomina es MENSUAL
    **/
    @Test
    void debieraRegresarNominaPorTipoDeEmpleado(){
        println "debieraRegresarNominaPorTipoDeEmpleado"
        
//        crearTipoEmpleados()
//        crearGrupos()
//        crearAtributos()
//        crearPerdeds()
//        crearPorcentajes()

//
//        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()
//
//        //Creando 10 empleados
//        String claveGenerica = "980900"
//        for(int i = 0; i < 10; i++){
//            def claveConcatenada = claveGenerica + i.toString()
//            empleadosPorRango.add(crearEmpleadoPrueba(claveConcatenada))
//        }
//        assertTrue empleadosPorRango.size() == 10

        TipoEmpleado tipoEmpleado = TipoEmpleado.findByDescripcion("DENOMINACIONAL")
        assertNotNull tipoEmpleado

        TipoNomina tipoNomina = TipoNomina.MENSUAL

        List<String> nominaEmpleadosPorTipo = nominaService.getNominaEmpleadosPorTipo(tipoEmpleado, tipoNomina)
        println "nominaEmpleadosPorTipo.size: ${nominaEmpleadosPorTipo.size()}"
//        assertTrue nominaEmpleadosPorTipo.size() == 10

        println "Nomina Empleados Por Tipo"
        for(String strNomina : nominaEmpleadosPorTipo){
            println strNomina
        }

        //Esto deberia probar los valores de la Nomina, pero, resulta que cada vez los valores se regresan de una manera diferente. Habria que
        //regresar ordenadas las Percepciones, desde el Empleado, creo yo, o que pues de alguna manera todas fueran en el mismo orden o, en
        //caso contrario, devolver un Map
//        println "Primer elmento de la lista de nominaEmpleadosPorTipo -- > ${(nominaEmpleadosPorTipo.get(0)).get(1)}"
//        assertEquals "PD004,4.00000" , (nominaEmpleadosPorTipo.get(0)).get(1)
    }

    /**
     * Regresa un String con el valor de la Percepcion especificada de un Empleado
     * PD001 = 2
    **/
    @Test
    void debieraRegresarPercecionEspecificadaUnEmpleado(){
        println 'debieraRegresarPercecionEspecificadaUnEmpleado'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)
        assertNotNull empleado

        PerDed percepcionPD001 = PerDed.findByClave("PD001")

        String valorPercepcion = nominaService.getPercepcionEspecificaEmpleado(percepcionPD001, empleado)

        assertEquals "PD001 | 2.00000" , valorPercepcion
        
    }

   /**
     * Regresa una Lista de Strings con la percepcion especifica por cada empleado en el rango especificado
    **/
    @Test
        void debieraRegresarPercecionEspecificadaPorRangoDeEmpleados(){
        println 'debieraRegresarPercecionEspecificadaPorRangoDeEmpleados'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980000"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            //println "claveConcatenada ${claveConcatenada}"
            empleadosPorRango.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleadosPorRango.size() == 10

        PerDed percepcionPD001 = PerDed.findByClave("PD001")
        String claveInicio = "9809000"
        String claveFinal = "9809004"

        List<String> percepcionEspecificaByRangoEmpleados = nominaService.getPercepcionEspecificaEmpleadosByRango(percepcionPD001, claveInicio, claveFinal)
        assertNotNull percepcionEspecificaByRangoEmpleados
        assertTrue percepcionEspecificaByRangoEmpleados.size() == 5

        for(String str : percepcionEspecificaByRangoEmpleados){
            println "str: ${str.substring(0,5)}"
            assertEquals "PD001" , str.substring(0,5)
        }
        
    }

    /**
     * Regresa una Lista de Strings con la percepcion especifica de los empleados del tipo especificado
    **/
    @Test
    void debieraRegresarPercecionEspecificadaPorTipoDeEmpleados(){
        println 'debieraRegresarPercecionEspecificadaPorTipoDeEmpleados'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        List<Empleado> empleados = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980900"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            //println "claveConcatenada ${claveConcatenada}"
            empleados.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleados.size() == 10

        PerDed percepcionPD001 = PerDed.findByClave("PD001")
        TipoEmpleado tipoEmpleado = TipoEmpleado.findByDescripcion("DENOMINACIONAL")
        assertNotNull tipoEmpleado

        List<String> nominaEmpleadosByType = nominaService.getPercepcionEspecificaEmpleadosByType(percepcionPD001, tipoEmpleado)
        assertTrue nominaEmpleadosByType.size() == 10

        println "Nomina Empleados"
        for(String strNomina : nominaEmpleadosByType){
            println strNomina
        }
    }


    /**
     * Regresa una lista con las percepciones de un Empleado, donde el primer valor(0) es la clave del Empleado y el resto son los valores
     * de las percepciones usando el siguiente formato:
     * NombrePercepcion(String) , ValorPercepcion(String)
     *
     * Esta Nomina es DIARIA
     *
     * Lo que debe regresar este metodo segun los valores que se metieron en el Empleado de Prueba
     * PD001 = 2 / 30 = 0.666666
     * PD002 = 4 / 30 = 0.133333
     * PD003 = 0 / 30 = 0
     * PD004 = 4 / 30 = 0.133333
     * PD005 = 6 x 4 = 24 /30 = 0.800000
     * PD006 = 8 x 2 = 16 /30 = 0.533333
     * PD007 = 0 /30 = 0
     *
     **/
    @Test
    void debieraRegresarNominaDiariaDeUnEmpleado(){
        println 'debieraRegresarNominaDiariaDeUnEmpleado'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        def clave = "9800000"
        Empleado empleado = crearEmpleadoPrueba(clave)
        assertNotNull empleado

        //PRUEBAS DE COMO FUNCIONA EL ENUM DE TipoNomina
//        TipoNomina tipoNomina = TipoNomina.DIARIA
//
//        if(tipoNomina == TipoNomina.DIARIA){
//            println "Validacion aceptada, la nomina SI ES DIARIA, xD!"
//        }
//
//        String nombreTipoNomina = tipoNomina.name()
//        println "nombreTipoNomina: ${nombreTipoNomina}"
//
//        TipoNomina.values().each(){
//            println it.name()
//        }
//
//        String nombreTipoNominaString = "DIARIA"
//        println "TipoNomina[tipoNomina] --> ${TipoNomina[nombreTipoNominaString]}"
//        println TipoNomina."$nombreTipoNominaString"

        TipoNomina tipoNomina = TipoNomina.DIARIA

        List<String> nominaDiariaDeUnEmpleado = nominaService.getNominaEmpleado(empleado, tipoNomina)
        assertNotNull nominaDiariaDeUnEmpleado

        assertEquals 5 , nominaDiariaDeUnEmpleado.size()

        println "Nomina Diaria Empleado"
        for(String n : nominaDiariaDeUnEmpleado){
            println n
        }

        // Esto no se puede validar porque  las percepciones siempre vienen en orden diferente
//        println "Primer elmento de la lista de nominaDiariaDeUnEmpleado -- > ${nominaDiariaDeUnEmpleado.get(1)}"
//        assertEquals "PD005,0.800000" , nominaDiariaDeUnEmpleado.get(1)

    }

   /**
     * Regresa la nomina de todos los empleados en el rango especificado por las claves
     *
     * Formato: NombrePercepcion(String) , ValorPercepcion(String)
     *
     * Esta Nomina es DIARIA
    **/
    @Test
    void debieraRegresarNominaDiariaDeUnRangoDeEmpleados(){
        println 'debieraRegresarNominaDiariaDeUnRangoDeEmpleados'
        crearTipoEmpleados()
        crearGrupos()
        crearAtributos()
        crearPerdeds()
        crearPorcentajes()

        List<Empleado> empleadosPorRango = new ArrayList<Empleado>()

        //Creando 10 empleados
        String claveGenerica = "980900"
        for(int i = 0; i < 10; i++){
            def claveConcatenada = claveGenerica + i.toString()
            empleadosPorRango.add(crearEmpleadoPrueba(claveConcatenada))
        }
        assertTrue empleadosPorRango.size() == 10

        String claveInicial = "9809000"
        String claveFinal = "9809004"

        TipoNomina tipoNomina = TipoNomina.DIARIA

        List<List> nominaEmpleadosPorRango = nominaService.getNominaEmpleadosPorRango(claveInicial, claveFinal, tipoNomina)
        assertTrue nominaEmpleadosPorRango.size() == 5

        println "Nomina Empleados"
        for(String strNomina : nominaEmpleadosPorRango){
            println strNomina
        }

        //Esto deberia probar los valores de la Nomina, pero, resulta que cada vez los valores se regresan de una manera diferente. Habria que
        //regresar ordenadas las Percepciones, desde el Empleado, creo yo, o que pues de alguna manera todas fueran en el mismo orden o, en
        //caso contrario, devolver un Map
//        println "Primer elmento de la lista de nominaEmpleadosPorRango -- > ${(nominaEmpleadosPorRango.get(0)).get(0)}"
//        assertEquals "PD001 , 0.666666" , nominaEmpleadosPorRango.get(0)
    }
}
