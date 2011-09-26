package mx.edu.um.rh

class SolicitudSalidaService {

    SolicitudSalida getSolicitudSalida(String folio) throws NullPointerException{
        def solicitudSalida=SolicitudSalida.findByFolio(folio)
        if(!solicitudSlida){
            throw new NullPointerException("SolicitudSalida.inexistente")
        }
        return solicitudSalida
    }
}
