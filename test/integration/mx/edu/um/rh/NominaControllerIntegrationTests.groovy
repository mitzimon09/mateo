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
    def springSecurityService
    
    @Test
    void debieraLeerPerDedNominaEmpresaYTipo(){
        //Leer Percepciones por Empresa y tipo
    }    
    @Test
    void debieraLeerPerDedNominaByEmpleado(){     
        //Leer Percepciones por Empleado
    }    
    @Test
    void debieraLeerPerDedNominaByRango(){     
        //Leer Percepciones por Rango
    }    
    @Test
    void debieraConvertirFormulaCorrecta(){        
        //Conversionde Formulas
    }
}
