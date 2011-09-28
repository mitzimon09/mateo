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
interface PerdedServiceInt {

    /**
     * Obtiene el Map de formulas del Grupo X (id=6)
     * En este punto, las formulas contienen el simbolo de % que se sustituira luego por el porcentaje adecuado de la formula
     * Map<(String)ClavePercepcion,(String)formulaPercepcion>
    **/
    Map<String,String> getMapFormulasGrupoX() throws NullPointerException

    /**
     * Obtiene el Map de formulas del Grupo X (id=6) con las formulas ya sutituidas con su valor correspondiente
     * Map<(String)ClavePercepcion,(String)formulaPercepcionSustituida>
    **/
    Map<String,String> getMapGrupoXSustituido() throws NullPointerException
}

