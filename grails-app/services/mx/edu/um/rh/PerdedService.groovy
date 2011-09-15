package mx.edu.um.rh
import mx.edu.um.rh.interfaces.*

class PerdedService implements PerdedServiceInt{

   /**
     * Obtiene el Map de formulas del Grupo X (id=6)
     * En este punto, las formulas contienen el simbolo de % que se sustituira luego por el porcentaje adecuado de la formula
     * Map<(String)ClavePercepcion,(String)formulaPercepcion>
     **/
    Map<String,String> getMapFormulasGrupoX() throws NullPointerException{
        log.debug 'Entro a getMapGrupoX'
        Map<String,String> mapGX = new TreeMap<String,String>()

        List<Porcentaje> porcentajes = Porcentaje.findAllByGrupo(Grupo.get(6))
//        log.debug "porcentajes.size ${porcentajes.size()}"
        List<PerDed> perdedsGX = new ArrayList<PerDed   >()
        for(Porcentaje p : porcentajes){
//            log.debug "porcentaje: ${p}"
//            log.debug "p.perded_id: ${p.perded}"
            perdedsGX.add(p.perded)
        }

        log.debug "perdedsGX.size ${perdedsGX.size()}"

        for(PerDed p : perdedsGX){
            log.debug "percepcion: ${p}"
            mapGX.put(p.clave,p.formula)
        }
        
        log.debug "mapGX.size ${mapGX.size()}"
        return mapGX
    }

   /**
     * Obtiene el Map de formulas del Grupo X (id=6) con las formulas ya sutituidas con su valor correspondiente
     * Map<(String)ClavePercepcion,(String)formulaPercepcionSustituida>
     **/
    Map<String,String> getMapGrupoXSustituido() throws NullPointerException {
        log.debug 'Entro a getMapGrupoXSustituido'

        Map<String,String> mapGX = getMapFormulasGrupoX()

        List<Porcentaje> porcentajes = new ArrayList<Porcentaje>()

        porcentajes = Porcentaje.findAll()

        for(Porcentaje porcentaje : porcentajes){
            if(mapGX.containsKey(porcentaje.perded.clave)){
                String formulaOriginal = mapGX.get(porcentaje.perded.clave)
                log.debug "formulaOriginal: ${formulaOriginal}"

                String formulaSustituida = formulaOriginal.replaceAll("%", porcentaje.valor.toString())
                formulaOriginal.replaceAll("&", porcentaje.valorDos.toString())

                log.debug "formulaSustituida: ${formulaSustituida}"

                mapGX.put(porcentaje.perded.clave, formulaSustituida)
            }
        }

        log.debug "Map Global (GX) sustituido.size: ${mapGX.size()}"
        return mapGX
    }
}