/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enums

/**
 *
 * @author blackdeath
 */
enum TipoNomina {
    DIARIA ("DIARIA"),
    SEMANAL ("SEMANAL"),
    QUINCENAL ("QUINCENAL"),
    MENSUAL ("MENSUAL")

    String tipo

    TipoNomina(String tipo){
        this.tipo = tipo
    }

    static list(){
        [DIARIA, SEMANAL, QUINCENAL, MENSUAL]
    }
}