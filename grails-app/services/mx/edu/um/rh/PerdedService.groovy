package mx.edu.um.rh
import mx.edu.um.rh.interfaces.*
import mx.edu.um.Constantes

class PerdedService {

   /**
     * Obtiene el Map de formulas del Grupo X (id=6)
     * En este punto, las formulas contienen el simbolo de % que se sustituira luego por el porcentaje adecuado de la formula
     * Map<(String)ClavePercepcion,(String)formulaPercepcion>
     **/
    Map<String,String> getMapFormulasGrupoX() throws NullPointerException{
        log.debug 'Entro a getMapGrupoX'
        Map<String,String> mapGX = new TreeMap<String,String>()

        List<Porcentaje> porcentajes = Porcentaje.findAllByGrupo(Grupo.findByNombre("X"))
//        log.debug "porcentajes.size ${porcentajes.size()}"
        List<PerDed> perdedsGX = new ArrayList<PerDed>()
        for(Porcentaje p : porcentajes){
//            log.debug "porcentaje: ${p}"
//            log.debug "p.perded_id: ${p.perded}"
            perdedsGX.add(p.perded)
        }

        log.debug "perdedsGX.size ${perdedsGX.size()}"

        for(PerDed p : perdedsGX){
            log.debug "percepcion: ${p} | ${p.clave}"
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
        log.debug "porcentajes.size(): ${porcentajes.size()}"

        for(Porcentaje porcentaje : porcentajes){
            if(mapGX.containsKey(porcentaje.perded.clave)){
                log.debug "Sustituyendo percepcion: ${porcentaje.perded.clave}"

                log.debug "porcentaje: ${porcentaje}"

                String formulaOriginal = mapGX.get(porcentaje.perded.clave)
                log.debug "formulaOriginal: ${formulaOriginal}"

                String formulaSustituida = formulaOriginal.replaceAll("%", porcentaje.valor.toString())
                formulaSustituida = formulaSustituida.replaceAll("&", porcentaje.valorDos.toString())

                log.debug "formulaSustituida: ${formulaSustituida}"

                mapGX.put(porcentaje.perded.clave, formulaSustituida)
            }
        }

        log.debug "mapGrupoX (sustituido): ${mapGX}"
        log.debug "Map Global (GX) sustituido.size: ${mapGX.size()}"
        return mapGX
    }

    /**
     * Devuelve un Map<(String)ClavePercepcion,(String)formulaPerecepcion> de las Percepciones que tengan como Atributo "Palabra Reservada"
     **/
    Map<String,String> getMapPerdedsReservadas(){
        Map<String,String> perdedsReservadasMap = new HashMap<String,String>()
        //Traer todas las Perdeds

        Atributo atributo = Atributo.findByNombre(Constantes.ATRIBUTO_PALABRA_RESERVADA)
        log.debug "atributo: ${atributo}"

        List<PerDed> perdedsReservadasList = getPerDedsByAtributo(atributo)

        log.debug "perdedsReservadasList.size() --> ${perdedsReservadasList.size()}"
        
        for(PerDed perded : perdedsReservadasList){
            log.debug "percepcion: ${perded} | ${perded.clave}"
            perdedsReservadasMap.put(perded.clave,perded.formula)
        }
        log.debug "perdedsReservadas (sin sustituir): ${perdedsReservadasMap}}"
        log.debug "perdedsReservadasMap.size() --> ${perdedsReservadasMap.size()}"
        return perdedsReservadasMap
    }

    /**
     * Crea una Nueva Percepcion con al menos un Atributo. En caso de que ya exista la Percepcion, simplemente le agrega el Atributo
     **/
    Boolean crearPerDed(PerDed perded, Atributo atributo){
        agregarAtributoToPerded(perded, atributo)
    }

    /**
     * Agrega un atributo a una Perded. Las relaciones estan asi --> PerDed --> PerdDedAtributo --> Atributo
     * Por lo que este metodo, crea en realidad la relaicon PerDedAtributo
    **/
    Boolean agregarAtributoToPerded(PerDed perded, Atributo atributo){
        PerDedAtributo pda = new PerDedAtributo(
            atributo : atributo,
            perded : perded
        )
        perded.addToAtributos(pda)

        return perded.save() ? true : false

    }

    /**
     * Devuelve una lista con las percepciones(PerDed) que contengan el atributo indicado
    **/
    List<PerDed> getPerDedsByAtributo(Atributo atributo){
        List<PerDed> perdedsWithAtributoEspecificado = new ArrayList<PerDed>()

        List<PerDedAtributo> pdaList = PerDedAtributo.findAllByAtributo(atributo)

        for(p in pdaList){
            perdedsWithAtributoEspecificado.add(p.perded)
        }

        log.debug "perdedsFilterByAtributo: ${perdedsWithAtributoEspecificado.size()}"
        return perdedsWithAtributoEspecificado
    }
}