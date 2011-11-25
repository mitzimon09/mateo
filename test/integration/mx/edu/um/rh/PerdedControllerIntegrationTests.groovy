package mx.edu.um.rh

import static org.junit.Assert.*
import general.*
import org.junit.*

class PerdedControllerIntegrationTests extends BaseIntegrationTest {

    def perdedService

    /**
     * Este metodo crea unos Atributos de Prueba para que se puedan ocupar en las pruebas
     **/
    public crearAtributos(){
        Atributo atrA = new Atributo(
            nombre: "ATR_A",
            descripcion : "ATR_A",
            simbolo : "_A"
        ).save()
        assert atrA

        Atributo atrB = new Atributo(
            nombre: "ATR_B",
            descripcion : "ATR_B",
            simbolo : "_B"
        ).save()
        assert atrB

        Atributo atrC = new Atributo(
            nombre: "ATR_C",
            descripcion : "ATR_C",
            simbolo : "_C"
        ).save()
        assert atrC

        Atributo atrD = new Atributo(
            nombre: "ATR_D",
            descripcion : "ATR_D",
            simbolo : "_D"
        ).save()
        assert atrD

        Atributo palabraReservada = Atributo.findByNombre("PALABRA_RESERVADA")
//        println "palabraReservada: ${palabraReservada}"
        if(!palabraReservada){
            palabraReservada = new Atributo(
                nombre: "PALABRA_RESERVADA",
                descripcion : "Palabra Reservada",
                simbolo : "PR"
            ).save(flush:true)
            assert palabraReservada
//            println "palabraReservada: ${palabraReservada}"
        }
        assert palabraReservada
    }

    /**
     * Este metodo crea algunas Percepciones (PerDed) con sus respectivos atributos(PerDedAtributo)
     **/
    List<PerDed> crearPerdeds(){
        println "Creando Perdeds"

        Atributo atrA = Atributo.findByNombre("ATR_A")
        assert atrA
        Atributo atrB = Atributo.findByNombre("ATR_B")
        assert atrB
        Atributo atrC = Atributo.findByNombre("ATR_C")
        assert atrC
        Atributo palabraReservada = Atributo.findByNombre("PALABRA_RESERVADA")
        assert palabraReservada


        List<PerDed> perdedsCreadas = new ArrayList<PerDed>()

        //Creando Percepcion de Prueba PD101
        PerDed PD101 = new PerDed(
            clave: "PD101",
            nombre: "PERCEPCION UNO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%"
        )
        //agregarle sus atributos a PD101
        PerDedAtributo paPD101_A = new PerDedAtributo(
            atributo : atrA,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_A)

        PerDedAtributo paPD101_B = new PerDedAtributo(
            atributo : atrB,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_B)

        PerDedAtributo paPD101_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD101
        )
        PD101.addToAtributos(paPD101_PR)

        PD101.save()
        assert PD101
        perdedsCreadas.add(PD101)
        assertEquals 3 , PD101.atributos.size()

        //Creando Percepcion de Prueba PD102
        PerDed PD102 = new PerDed(
            clave: "PD102",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
        )
        //agregarle sus atributos a PD102
        PerDedAtributo paPD102_A = new PerDedAtributo(
            atributo : atrA,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_A)

        PerDedAtributo paPD102_B = new PerDedAtributo(
            atributo : atrB,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_B)

        PerDedAtributo paPD102_C = new PerDedAtributo(
            atributo : atrC,
            perded : PD102
        )
        PD102.addToAtributos(paPD102_C)

        PerDedAtributo paPD102_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD101
        )
        PD101.addToAtributos(paPD102_PR)

        PD102.save()
        assert PD102
        perdedsCreadas.add(PD102)
        assertEquals 4 , PD101.atributos.size()

        //Creando Percepcion de Prueba PD103
        PerDed PD103 = new PerDed(
            clave: "PD103",
            nombre: "PERCEPCION TRES",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD101 * PD107"
        )
        //agregarle sus atributos a PD103
        PerDedAtributo paPD103_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD103
        )
        PD103.addToAtributos(paPD103_PR)
        PD103.save()
        assert PD103
        perdedsCreadas.add(PD103)

        //Creando Percepcion de Prueba PD104
        PerDed PD104 = new PerDed(
            clave: "PD104",
            nombre: "TPERCEPCION CUATRO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "PD102"
        )
        //agregarle sus atributos a PD104
        PerDedAtributo paPD104_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD104
        )
        PD104.addToAtributos(paPD104_PR)
        PD104.save()
        assert PD104
        perdedsCreadas.add(PD104)

        //Creando Percepcion de Prueba PD105
        PerDed PD105 = new PerDed(
            clave: "PD105",
            nombre: "PERCEPCION CINCO",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD102"
        )
        //agregarle sus atributos a PD105
        PerDedAtributo paPD105_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD105
        )
        PD105.addToAtributos(paPD105_PR)
        PD105.save()
        assert PD105
        perdedsCreadas.add(PD105)

        //Creando Percepcion de Prueba PD106
        PerDed PD106 = new PerDed(
            clave: "PD106",
            nombre: "PERCEPCION SEIS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "% * PD101"
        )
        //agregarle sus atributos a PD106
        PerDedAtributo paPD106_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD106
        )
        PD106.addToAtributos(paPD106_PR)
        PD106.save()
        assert PD106
        perdedsCreadas.add(PD106)

        //Creando Percepcion de Prueba PD107
        PerDed PD107 = new PerDed(
            clave: "PD107",
            nombre: "PERCEPCION SIETE",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "0"
        )
        //agregarle sus atributos a PD107
        PerDedAtributo paPD107_PR = new PerDedAtributo(
            atributo : palabraReservada,
            perded : PD107
        )
        PD107.addToAtributos(paPD107_PR)
        PD107.save()
        assert PD107
        println "PD107: ${PD107}"
        println "perdedsCreadas: ${perdedsCreadas}"
        perdedsCreadas.add(PD107)

        return perdedsCreadas
    }

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    /**
     * Prueba crear una Percepcion (PerDed) con al menos un atributo (PerDedAtributo)
     **/
    @Test
    void debieraCrearPercepcionWithAlMenosUnAtributo(){
        crearAtributos()
        PerDed perded = new PerDed(
            clave: "PD000",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%"
        )

        Atributo atributoA = Atributo.findByNombre("ATR_A")
        assert atributoA

        Boolean creoPerded = perdedService.crearPerDed(perded, atributoA)

        PerDed perdedUpdated = PerDed.findByClave("PD000")
        assertEquals 1 , perded.atributos.size()
    }

    /**
     * Agrega atributos a una Perded ya creada
     **/
    @Test
    void debieraGuardarAtributosEnPerDed(){
        crearAtributos()
        PerDed perded = new PerDed(
            clave: "PD000",
            nombre: "PERCEPCION DOS",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%"
        )

        Atributo atributoA = Atributo.findByNombre("ATR_A")
        assert atributoA

        Atributo atributoB = Atributo.findByNombre("ATR_B")
        assert atributoB

        Boolean guardoRelacionA = perdedService.agregarAtributoToPerded(perded,atributoA)
        assertTrue guardoRelacionA
        Boolean guardoRelacionB = perdedService.agregarAtributoToPerded(perded,atributoB)
        assertTrue guardoRelacionB

        PerDed perdeUpdated = PerDed.findByClave("PD000")
        assertEquals 2 , perded.atributos.size()

    }

    /**
     * Trae todas las percepciones que contengan un atributo especifico
     **/
    @Test
    void debieraTraerPercepcionesByAtributo(){
        crearAtributos()
        List percepcionesCreadas = crearPerdeds()
        println "percepciones creadas: ${percepcionesCreadas.size()}"
       
        Atributo atributo = Atributo.findByNombre("PALABRA_RESERVADA")

        List<PerDed> percepcionesWithAtributoEspecifico = perdedService.getPerDedsByAtributo(atributo)

        assertEquals 7 , percepcionesWithAtributoEspecifico.size()
    }

    @Test
    void debieraProbarMetodosDePercepcionParaAveriguarSusAtributos(){
        crearAtributos()
        crearPerdeds()

        PerDed perded = PerDed.findByClave("PD101")
        assert perded

        assertEquals true , perded.tieneAtributoPalabraReservada()
    }

}
