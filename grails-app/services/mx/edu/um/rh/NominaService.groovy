package mx.edu.um.rh

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.regex.*
import mx.edu.um.rh.interfaces.*
import mx.edu.um.common.evaluador.*
import enums.TipoNomina


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
     * Devuelve un Map con las Percepciones del Empleado con las formulas sustituidas. Aqui el Map ya contiene las Percepciones Globales y
     * las propias del Empleado
    **/
    Map<String,String> getMapPercepcionesSustituidasEmpleado(Empleado empleado) throws NullPointerException{
        log.debug 'Entro a getMapPercepcionesSustituidasEmpleado'
        //Obteniendo el Map de las formulas del Empleado, faltando solo de sustituir las formulas de las percepciones personalizadas del Empleado
        Map<String,String> percepcionesEmpleado = getMapPercepcionesEmpleado(empleado)

        //Como sustitituyo las formulas del Empleado? Igual que con las Globales
        List<Porcentaje> porcentajes = new ArrayList<Porcentaje>()

        porcentajes = Porcentaje.findAll()

        for(Porcentaje porcentaje : porcentajes){
            log.debug "Percepcion: ${porcentaje.perded.clave} | ${porcentaje.perded.formula}"
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

    /**
     * Devuelve una Lista con la Nomina del Empleado, donde cada valor de la lista tiene el formato: "percepcion , formulaEvaluada"
     * El valor de tipoNomina se usa para determinar el tipo de la nomina (diaria, semanal, quincenal o mensual), donde la nomina "normal"
     * es mensual, por lo que la diaria, simplemente se toma el valor "normal" y se divide por 30 para determinar la nomina diaria, la
     * quincenal se divide por 15 y asi sucesivamente
    **/
    List<String> getNominaEmpleado(Empleado e, TipoNomina tipoNomina){
        log.debug "Obteniendo Nomina del Empleado: ${e.clave}"
        Evaluador evaluador = new Evaluador();
        List<String> percepcionesValoresEmpleado = new ArrayList<String>()
        List<EmpleadoPerded> perdedsEmpleado = new ArrayList<EmpleadoPerded>()
        perdedsEmpleado.addAll(e.perdeds.values())

        Map<String,String> percepcionesMap = getMapPercepcionesSustituidasEmpleado(e)
        log.debug "percepciones sustituidas en el Map: ${percepcionesMap}"

        percepcionesValoresEmpleado.add(e.clave)

        for(EmpleadoPerded ep : perdedsEmpleado){
            String percepcion = ep.perded.clave
            log.debug "percepcion a evaluar: ${percepcion}"
            StringBuffer formula = new StringBuffer("")
            String formulaEvaluada = ""
            String idTemp;
            String temp;
            int pos=0;

            if(percepcionesMap.containsKey(percepcion)){
                formula = new StringBuffer(percepcionesMap.get(percepcion))
                log.debug "formula: ${formula}"
		pos = formula.indexOf("PD");
		while(pos != -1){
			idTemp=formula.substring(pos,pos+5);
                        log.debug "idTemp: ${idTemp}"
			temp=(String)percepcionesMap.get(idTemp);
                        log.debug "tmp: ${temp}"
			formula.replace(pos,pos+5,temp);
			pos=formula.indexOf("PD");
		}
                formulaEvaluada = evaluador.evaluaExpresion(formula.toString())
                log.debug "evaluada: ${formulaEvaluada}"
            }
            else{
                log.debug "No se encuentra la percepcion: ${percepcion} en el mapa de las percepciones sustituidas: ${percepcionesMap.getProperties()}." +
                "La formula de la percepcion es: ${ep.perded.formula}"
            }

            MathContext mc = new MathContext(6, RoundingMode.HALF_EVEN);
            BigDecimal valorPercepcion = new BigDecimal(formulaEvaluada)
            BigDecimal valorPercepcionPorTipoDeNomina = null
            //Devolviendo la nomina de acuerdo el tipo
            //TODO - Como le hago para que siempre se guarden al menos 6 ceros???
            if(tipoNomina== TipoNomina.DIARIA){
                valorPercepcionPorTipoDeNomina = new BigDecimal((valorPercepcion.divide(new BigDecimal("30"),mc)).toString(),mc)
                log.debug "Lo que meto a la lista: ${percepcion + "," + valorPercepcionPorTipoDeNomina.toString()}"
                percepcionesValoresEmpleado.add(percepcion + "," + valorPercepcionPorTipoDeNomina.toString())
            }
            else if(tipoNomina == TipoNomina.SEMANAL){
                valorPercepcionPorTipoDeNomina = new BigDecimal((valorPercepcion.divide(new BigDecimal("4"),mc)).toString(),mc)
                log.debug "Lo que meto a la lista: ${percepcion + "," + valorPercepcionPorTipoDeNomina.toString()}"
                percepcionesValoresEmpleado.add(percepcion + "," + valorPercepcionPorTipoDeNomina.toString())
            }
            else if(tipoNomina == TipoNomina.QUINCENAL){
                valorPercepcionPorTipoDeNomina = new BigDecimal((valorPercepcion.divide(new BigDecimal("2"),mc)).toString(),mc)
                log.debug "Lo que meto a la lista: ${percepcion + "," + valorPercepcionPorTipoDeNomina.toString()}"
                percepcionesValoresEmpleado.add(percepcion + "," + valorPercepcionPorTipoDeNomina.toString())
            }
            else if(tipoNomina == TipoNomina.MENSUAL){
                log.debug "Lo que meto a la lista: ${percepcion + "," + formulaEvaluada}"
                percepcionesValoresEmpleado.add(percepcion + "," + formulaEvaluada)
            }
        }

        log.debug "cantidad percepciones: ${percepcionesValoresEmpleado.size()}"
        return percepcionesValoresEmpleado
    }

    /**
     * Devuelve una lista con las nominas de un rango especifico de empleados.
     * Cada elemento en la lista, es a su vez una lista conteniendo las percepciones de cada empleado en el siguiente formato:
     * "percepcion , formulaEvaluada"
    **/
    List<List> getNominaEmpleadosPorRango(String claveInicial, String claveFinal, TipoNomina tipoNomina){
//        List <Empleado> empleadosTotalesEnBD = Empleado.findAll()
//        log.debug "empleadosTotales: ${empleadosTotalesEnBD.size()}"
//
//        log.debug "empleados - claves"
//        for(Empleado e : empleadosTotalesEnBD){
//            log.debug "clave: ${e.clave}"
//        }

        List<List> nominaEmpleadosByRango = new ArrayList<List>()

        List<Empleado> empleadosFilterByRango = empleadoService.getEmpleadosByRango(claveInicial, claveFinal)
        log.debug "empleados: ${empleadosFilterByRango.size()}"

        for(Empleado e: empleadosFilterByRango){
            nominaEmpleadosByRango.add(getNominaEmpleado(e, tipoNomina))
        }

        return nominaEmpleadosByRango
    }

    /**
     * Devuelve una lista con las nominas de un tipo especifico de empleados.
     * Cada elemento en la lista, es a su vez una lista conteniendo las percepciones de cada empleado en el siguiente formato:
     * "percepcion , formulaEvaluada"
    **/
    List<String> getNominaEmpleadosPorTipo(TipoEmpleado tipoEmpleado, TipoNomina tipoNomina){
        List<List> nominaEmpleadosByTipo = new ArrayList<List>()

        List<Empleado> empleadosFilterByType = empleadoService.getEmpleadosByTipo(tipoEmpleado)
        log.debug "empleadosFilterByType: ${empleadosFilterByType.size()}"

        for(Empleado e: empleadosFilterByType){
            nominaEmpleadosByTipo.add(getNominaEmpleado(e, tipoNomina))
        }

        return nominaEmpleadosByTipo
    }

    /**
     * Devuelve un String con el valor de la Percepcion especificada de un empleado especifico
    **/
    String getPercepcionEspecificaEmpleado(PerDed percepcionEmp, Empleado empleado){
        Evaluador evaluador = new Evaluador();
        Map<String,String> percepcionesMap = getMapPercepcionesSustituidasEmpleado(empleado)

            String percepcion = percepcionEmp.clave
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
            log.debug "Percepcion evaluada: ${percepcionEmp.clave} | ${formulaEvaluada}"

        return "${percepcionEmp.clave} | ${formulaEvaluada}"
    }

    /**
     * Devuelve una lista, donde cada elemento es el valor de una percepcion especifica de cada empleado en un rango especificado
    **/
    List<String> getPercepcionEspecificaEmpleadosByRango(PerDed percepcion, String claveInicio, String claveFinal){

        List<String> percepcionesEspecificasEmpleadosByRango = new ArrayList<String>()
        List<Empleado> empleadosFilterByRango = empleadoService.getEmpleadosByRango(claveInicio, claveFinal)
        log.debug "empleadosFilterByRango: ${empleadosFilterByRango.size()}"

        for(Empleado e: empleadosFilterByRango){
            String percepcionEspecifica = getPercepcionEspecificaEmpleado(percepcion, e)
            log.debug "percepcionEspecificaEmpleado: ${percepcionEspecifica}"
            percepcionesEspecificasEmpleadosByRango.add("${percepcionEspecifica} | e.clave")
        }

        return percepcionesEspecificasEmpleadosByRango
    }

    /**
     * Devuelve una lista, donde cada elemento es el valor de una percepcion especifica de cada empleado de un tipo especifico
    **/
    List<String> getPercepcionEspecificaEmpleadosByType(PerDed percepcion, TipoEmpleado tipoEmpleado){

        List<String> percepcionesEspecificasEmpleadosByType = new ArrayList<String>()
        List<Empleado> empleadosFilterByType = empleadoService.getEmpleadosByTipo(tipoEmpleado)
        log.debug "empleadosFilterByType: ${empleadosFilterByType.size()}"

        for(Empleado e: empleadosFilterByType){
            String percepcionEspecifica = getPercepcionEspecificaEmpleado(percepcion, e)
            log.debug "percepcionEspecificaEmpleado: ${percepcionEspecifica} | ${e.clave}"
            percepcionesEspecificasEmpleadosByType.add("${percepcionEspecifica} | e.clave")
        }

        log.debug "percepcionesEspecificasEmpleadosByType.size() - ${percepcionesEspecificasEmpleadosByType.size()}"
        return percepcionesEspecificasEmpleadosByType
    }

}
