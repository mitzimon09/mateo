package mx.edu.um.rh

class PerDedService {

    Map<String,String> getMapFormulasGrupoX(){
        log.debug 'Entro a getMapGrupoX'
        Map<String,String> mapGX = new TreeMap<String,String>()

        List<PerDed> perdedsGX = PerDed.findAllByGrupo(Grupo.get(6))

        log.debug "perdedsGX.size ${perdedsGX.size()}"

        for(PerDed p : perdedsGX){
            mapGX.put(p.clave,p.formula)
        }
        return mapGX
    }
    
}
