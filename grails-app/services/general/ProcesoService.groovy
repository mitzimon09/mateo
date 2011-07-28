package general

import general.interfaces.ProcesoServiceInterface
import general.Compra

class ProcesoService implements ProcesoServiceInterface{

    def enviar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "ENVIADA"
        log.debug "compra + " + compra
        return compra
    }

    def aprobar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "APROBADA"
        log.debug "compra + " + compra
        return compra
    }

    def rechazar(Compra compra){
        log.info "service enviar"
        log.debug "compra + " + compra
			  compra.status = "RECHAZADA"
        log.debug "compra + " + compra
        return compra
    }
}
