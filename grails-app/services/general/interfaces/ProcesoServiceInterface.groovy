package general.interfaces

import general.Compra

interface ProcesoServiceInterface {
	
    def enviar(params);

    def aprobar(params);

    def rechazar(params);
    
    def cancelar(params);
    
}
