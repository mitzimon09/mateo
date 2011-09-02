package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(TipoDependienteController)
@Mock(TipoDependiente)
class TipoDependienteControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/tipoDependiente/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.tipoDependienteInstanceList.size() == 0
        assert model.tipoDependienteInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.tipoDependienteInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.tipoDependienteInstance != null
        assert view == '/tipoDependiente/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/tipoDependiente/show/1'
        assert controller.flash.message != null
        assert TipoDependiente.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDependiente/list'


        def tipoDependiente = new TipoDependiente()

        // TODO: populate domain properties

        assert tipoDependiente.save() != null

        params.id = tipoDependiente.id

        def model = controller.show()

        assert model.tipoDependienteInstance == tipoDependiente
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDependiente/list'


        def tipoDependiente = new TipoDependiente()

        // TODO: populate valid domain properties

        assert tipoDependiente.save() != null

        params.id = tipoDependiente.id

        def model = controller.edit()

        assert model.tipoDependienteInstance == tipoDependiente
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDependiente/list'

        response.reset()


        def tipoDependiente = new TipoDependiente()

        // TODO: populate valid domain properties

        assert tipoDependiente.save() != null

        // test invalid parameters in update
        params.id = tipoDependiente.id

        controller.update()

        assert view == "/tipoDependiente/edit"
        assert model.tipoDependienteInstance != null

        tipoDependiente.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/tipoDependiente/show/$tipoDependiente.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDependiente/list'

        response.reset()

        def tipoDependiente = new TipoDependiente()

        // TODO: populate valid domain properties
        assert tipoDependiente.save() != null
        assert TipoDependiente.count() == 1

        params.id = tipoDependiente.id

        controller.delete()

        assert TipoDependiente.count() == 0
        assert TipoDependiente.get(tipoDependiente.id) == null
        assert response.redirectedUrl == '/tipoDependiente/list'


    }


}