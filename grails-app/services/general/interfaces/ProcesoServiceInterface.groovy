package general.interfaces

import general.Compra

interface ProcesoServiceInterface {

    def enviar(Compra compra);

    def aprobar(Compra compra);

    def rechazar(Compra compra);
    
}
