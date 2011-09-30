package mx.edu.um.rh
import mx.edu.um.rh.interfaces.*
import java.util.regex.*
import mx.edu.um.common.evaluador.*

class NominaService {

    def empleadoService
    def perdedService

    /**
     * Obtiene el Map de Formulas Completo de un Empleado, pero aun sin sustituir las formulas del Empleado
     * Map<(String)ClavePercepcion,(String)formulaPerecepcion>
    **/
    Map<String,String> getMapPercepcionesEmpleado(Empleado empleado) throws NullPointerException{
        log.debug 'Entro a getMapPercepcionesEmpleado'

        //Se obtienen primero las formulas Globales. Posteriormente se aniadiran las personales del empelado
        Map<String,String> formulasEmpleado = perdedService.getMapGrupoXSustituido()
        log.debug "map de formulas generales (size) : ${formulasEmpleado.size()}"

        //Estas Perecepciones, son las Percepciones personalizadas del Empleado, que dependen de su Grupo y/o su Empresa
        // (Son diferentes de las Percepciones Globales)
        Map<String,EmpleadoPerded> percepciones = empleado.perdeds
        log.debug "percepciones del empleado (size) : ${percepciones.size()} "

        List<EmpleadoPerded> listaPercepciones = new ArrayList<EmpleadoPerded>()
        listaPercepciones.addAll(percepciones.values())

        //Obtener las formulas del empleado
        for(EmpleadoPerded ep : listaPercepciones){
            log.debug "${ep.perded.clave} ------ ${ep.perded.formula}}"
            formulasEmpleado.put(ep.perded.clave,ep.perded.formula)
        }

        log.debug "Map Global + Percepciones Empleado (size) : ${formulasEmpleado.size()}"
        return formulasEmpleado
    }

    /**
     *
    **/
    Map<String,String> getMapPercepcionesSustituidasEmpleado(Empleado empleado) throws NullPointerException{
        log.debug 'Entro a getMapPercepcionesSustituidasEmpleado'
        //Obteniendo el Map de las formulas del Empleado, faltando solo de sustituir las formulas de las percepciones personalizadas del Empleado
        Map<String,String> percepcionesEmpleado = getMapPercepcionesEmpleado(empleado)

        //Como sustitituyo las formulas del Empleado? Igual que con las Globales
        List<Porcentaje> porcentajes = new ArrayList<Porcentaje>()

        porcentajes = Porcentaje.findAll()

        for(Porcentaje porcentaje : porcentajes){
            if(percepcionesEmpleado.containsKey(porcentaje.perded.clave)){
                String formulaOriginal = percepcionesEmpleado.get(porcentaje.perded.clave)
                log.debug "formulaOriginal: ${formulaOriginal}"

                String formulaSustituida = formulaOriginal.replaceAll("%", porcentaje.valor.toString())
                formulaOriginal.replaceAll("&", porcentaje.valorDos.toString())
                log.debug "formulaSustituida: ${formulaSustituida}"

                percepcionesEmpleado.put(porcentaje.perded.clave, formulaSustituida)
            }
        }
        log.debug "percepcionesEmpleadoSustituidas (size): ${percepcionesEmpleado.size()}"
        return percepcionesEmpleado
    }

    List<String> getNominaEmpleado(Empleado e){
        Evaluador evaluador = new Evaluador();
        List<String> percepcionesValoresEmpleado = new ArrayList<String>()
        List<EmpleadoPerded> perdedsEmpleado = new ArrayList()
        perdedsEmpleado.addAll(e.perdeds.values())

        Map<String,String> percepcionesMap = getMapPercepcionesSustituidasEmpleado(e)

        percepcionesValoresEmpleado.add(e.clave)

        for(EmpleadoPerded ep : perdedsEmpleado){
            String percepcion = ep.perded.clave
            StringBuffer formula = new StringBuffer("")
            String formulaEvaluada = ""
            String idTemp;
            String temp;
            int pos=0;

            if(percepcionesMap.containsKey(percepcion) != null){
                formula = new StringBuffer(percepcionesMap.get(percepcion))
                log.debug "formula: ${formula}"
		pos = formula.indexOf("PD");
		while(pos != -1){
			idTemp=formula.substring(pos,pos+5);
                        log.debug "idTemp: ${idTemp}"
			temp=(String)percepcionesMap.get(idTemp);
                        log.debug "tmp: ${temp}"
                        //log.debug "temp: ${temp}"
			formula.replace(pos,pos+5,temp);
			pos=formula.indexOf("PD");
		}
		//log.debug "formula: ${formula}"
                formulaEvaluada = evaluador.evaluaExpresion(formula.toString())
                log.debug "evaluada: ${formulaEvaluada}"
            }
            log.debug "Lo que meto a la lista: ${percepcion + "," + formulaEvaluada}"
            percepcionesValoresEmpleado.add(percepcion + "," + formulaEvaluada)
        }

        log.debug "cantidad percepciones: ${percepcionesValoresEmpleado.size()}"
        return percepcionesValoresEmpleado
    }

    public getNominaEmpleadosPorRango(claveInicial, claveFinal){



        return null
    }
    
}
