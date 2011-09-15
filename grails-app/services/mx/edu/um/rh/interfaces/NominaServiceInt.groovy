/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.um.rh.interfaces
import mx.edu.um.rh.*
import general.*
/**
 *
 * @author eder
 */
interface NominaServiceInt {

    /**
     * Obtiene el Map de Formulas Completo de un Empleado, pero aun sin sustituir las formulas del Empleado, es decir, devuelve un Map
     * con las percepciones Globales mas las Percepciones especificas del Empleado
     * Map<(String)ClavePercepcion,(String)formulaPerecepcion>
    **/
    Map<String,String> getMapPercepcionesEmpleado(Empleado empleado) throws NullPointerException

        /**
     * Obtiene el Map de Formulas Completo de un Empleado, con todas las formulas ya sustituidas
     * Map<(String)ClavePercepcion,(String)formulaPerecepcion>
    **/
    Map<String,String> getMapPercepcionesSustituidasEmpleado(Empleado empleado) throws NullPointerException
}

