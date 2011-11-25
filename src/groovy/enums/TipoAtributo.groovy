/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enums

/**
 *
 * @author blackdeath
 */
enum TipoAtributo {
    BASICA (1,"BASICA","Percepcion Basica","B"),
    PREVISION_SOCIAL (2,"PREVISION_SOCIAL","Se acumula el monto calculado en prevision social","PS"),
    DIEZMO (3,"DIEZMO","Se calcula el diezmo ","D"),
    SOBRESUELDO (4,"SOBRESUELDO","Indica que el total de la percepcion debe grabarse en sobresueldo","S"),
    ISPT (5,"ISPT","Se calcula el ISPT","I"),
    NOMINA (6,"NOMINA","Indica que se deben generar movimientos en calculo nomina","N"),
    BASE_NOMINA (7,"BASE_NOMINA","Indica que el porcentaje de la formula se acumulara para el calculo de sobresueldo","BN"),
    PORCENTAJE_NOMINA (8,"PORCENTEAJE_NOMINA","Indica que para calcular la per/ded se requiere tener la suma del porcentaje de nomina del empleado","%"),
    VALOR (9,"VALOR","Indica que esta per/ded contiene un valor que es requerido por otra percepcion/deduccion","V"),
    PALABRA_RESERVADA (99,"PALABRA_RESERVADA","Palabra Reservada","PR"),
    PERCEPCION_RH (100,"PERCEPCION_RH","Percepciones de la hoja de alta de salarios y prestaciones","RH")

    Integer id
    String nombre
    String descripcion
    String simbolo

    TipoAtributo(Integer id, String nombre, String descripcion, String simbolo){
        this.id = id
        this.nombre=nombre
        this.descripcion=descripcion
        this.simbolo=simbolo
    }

    static list(){
        [BASICA, PREVISION_SOCIAL, DIEZMO, SOBRESUELDO, ISPT, NOMINA, BASE_NOMINA, PORCENTAJE_NOMINA, VALOR, PALABRA_RESERVADA, PERCEPCION_RH]
    }

    String toString(){
        return nombre
    }
}