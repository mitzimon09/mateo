package mx.edu.um.rh
import mx.edu.um.rh.interfaces.*

class NominaService implements NominaServiceInt{

    def empleadoServiceInt

    /**
     *
    **/
    Map<String,String> getMapPercepcionesEmpleado(Empleado empleado) throws NullPointerException{
        
        Map<String,String> mapWithClaveAndFormula = new TreeMap<String,String>()

        Map<String,EmpleadoPerded> percepciones = empleado.perdeds

        List<EmpleadoPerded> lista = new ArrayList<EmpleadoPerded>()
        lista.addAll(percepciones.values())

        for(EmpleadoPerded ep : lista){
            mapWithClaveAndFormula.put(ep.perded.clave,ep.perded.formula)
        }

        return mapWithClaveAndFormula
    }

}
