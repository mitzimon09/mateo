package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.*
import org.junit.*
import java.util.regex.*
import general.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(NominaController)
class NominaControllerIntegrationTests extends BaseIntegrationTest {
    def empleadoService
    def nominaService
    def perdedService
    def springSecurityService
    
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

        Map<String,String> mapGX = perdedService.getMapFormulasGrupoX()
        assertNotNull mapGX

        List<Porcentaje> porcentajes = Porcentaje.findAllByGrupo(Grupo.get(6))
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

        Map<String,String> mapGX = perdedService.getMapFormulasGrupoX()
        assertNotNull mapGX

        Map<String,String> mapGXSustituido = perdedService.getMapGrupoXSustituido()
        assertNotNull mapGXSustituido

        assertTrue mapGX.size() == mapGXSustituido.size()

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

        def clave = '9800397'
        def empleado = empleadoService.getEmpleado(clave)
        System.out.println(empleado)

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
        def clave = '9800040'
        def empleado = empleadoService.getEmpleado(clave)
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
        for(String f : valuesMapGXSustituido){
            Matcher m = p.matcher(f)
            assertTrue !m.find()
        }
    }

    @Test
    void debieraRegresarmeValorNumericoFromEvaluador(){
            
    }

}
