package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(DiasFeriadosController)
@Mock(DiasFeriados)
class DiasFeriadosControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/diasFeriados/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.diasFeriadosInstanceList.size() == 0
        assert model.diasFeriadosInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.diasFeriadosInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.diasFeriadosInstance != null
        assert view == '/diasFeriados/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/diasFeriados/show/1'
        assert controller.flash.message != null
        assert DiasFeriados.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/diasFeriados/list'


        def diasFeriados = new DiasFeriados()

        // TODO: populate domain properties

        assert diasFeriados.save() != null

        params.id = diasFeriados.id

        def model = controller.show()

        assert model.diasFeriadosInstance == diasFeriados
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/diasFeriados/list'


        def diasFeriados = new DiasFeriados()

        // TODO: populate valid domain properties

        assert diasFeriados.save() != null

        params.id = diasFeriados.id

        def model = controller.edit()

        assert model.diasFeriadosInstance == diasFeriados
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/diasFeriados/list'

        response.reset()


        def diasFeriados = new DiasFeriados()

        // TODO: populate valid domain properties

        assert diasFeriados.save() != null

        // test invalid parameters in update
        params.id = diasFeriados.id

        controller.update()

        assert view == "/diasFeriados/edit"
        assert model.diasFeriadosInstance != null

        diasFeriados.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/diasFeriados/show/$diasFeriados.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/diasFeriados/list'

        response.reset()

        def diasFeriados = new DiasFeriados()

        // TODO: populate valid domain properties
        assert diasFeriados.save() != null
        assert DiasFeriados.count() == 1

        params.id = diasFeriados.id

        controller.delete()

        assert DiasFeriados.count() == 0
        assert DiasFeriados.get(diasFeriados.id) == null
        assert response.redirectedUrl == '/diasFeriados/list'


    }


}