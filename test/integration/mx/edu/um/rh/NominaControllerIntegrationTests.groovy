package mx.edu.um.rh


import grails.test.mixin.*
import grails.test.*
import org.junit.*
import general.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(NominaController)
class NominaControllerIntegrationTests extends BaseIntegrationTest {
    def empleadoServiceInt
    def nominaServiceInt
    def perdedServiceInt
    def springSecurityService
    
   //Preparar Ambiente Formulas 
   //Formulas Globales
   //Grupo Global X Sustituir
   //Porcentaje Lleva grupo empresa clave percepcion

    /**
     * Comprueba que se pueda obtener el Map del Grupo X.
     * Map<(String)ClavePercepcion,(String)formulaPercepcion>
    **/
    @Test
    void debieraArmarMapGrupoX(){
        log.debug 'testArmarMapGrupoX'

        Map<String,String> mapGX = perdedServiceInt.getMapGrupoX()
        assertNotNull mapGX

        List<PerDed> perdesGX = PerDed.findAllByGrupo(Grupo.get(6))

        for(PerDed p : perdesGX){
            assertTrue mapGX.containsKey(p.clave)
            assertEquals mapGX.get(p.clave), p.formula
        }
        
    }

    /**
     *Comprueba que se trainga el Map de un Empleado.
     *Map<(String)ClavePercepcion,(String)formulaPerecepcion>
    **/
    @Test
    void debieraArmarMapDeFormulasPorEmpleado(){
        log.debug 'testArmarMapDeFormulasPorEmpleado'

        def clave = '9800397'
        def empleado = empleadoServiceInt.getEmpleado(clave)
        System.out.println(empleado)
        Map<String,String> mapPerdedsEmpleado = nominaServiceInt.getMapPercepcionesEmpleado(empleado)

        assertNotNull mapPerdedsEmpleado
        
        List<EmpleadoPerded> percepcionesEmpleado = new ArrayList<EmpleadoPerded>()
        percepcionesEmpleado.addAll(empleado.perdeds.values())

        for(EmpleadoPerded ep : percepcionesEmpleado){
            assertTrue mapPerdedsEmpleado.containsKey(ep.perded.clave)
            assertEquals mapPerdedsEmpleado.get(ep.perded.clave), ep.perded.formula
        }
    }
    
}
