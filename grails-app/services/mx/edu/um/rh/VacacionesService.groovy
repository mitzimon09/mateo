package mx.edu.um.rh
import mx.edu.um.rh.*

class VacacionesService {

    Vacaciones getVacacionesBySolicitudSalida(String folio) throws NullPointerException{
        def solicitudSalida = SolicitudSalida.findByFolio(folio)
        Vacaciones vacaciones = new Vacaciones()
        vacaciones.solicitudSalida = solicitudSalida
        vacaciones = (Vacaciones) Vacaciones.vacacionesParametros(vacaciones).list().get(0)
        if(!vacaciones){
            throw new NullPointerException("vacaciones.inexistente")
        }
        return vacaciones
    }
}

