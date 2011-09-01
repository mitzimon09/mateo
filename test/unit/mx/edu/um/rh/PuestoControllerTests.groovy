package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(PuestoController)
@Mock(Puesto)
class PuestoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/puesto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.puestoInstanceList.size() == 0
        assert model.puestoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.puestoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.puestoInstance != null
        assert view == '/puesto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/puesto/show/1'
        assert controller.flash.message != null
        assert Puesto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/puesto/list'


        def puesto = new Puesto()

        // TODO: populate domain properties

        assert puesto.save() != null

        params.id = puesto.id

        def model = controller.show()

        assert model.puestoInstance == puesto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/puesto/list'


        def puesto = new Puesto()

        // TODO: populate valid domain properties

        assert puesto.save() != null

        params.id = puesto.id

        def model = controller.edit()

        assert model.puestoInstance == puesto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/puesto/list'

        response.reset()


        def puesto = new Puesto()

        // TODO: populate valid domain properties

        assert puesto.save() != null

        // test invalid parameters in update
        params.id = puesto.id

        controller.update()

        assert view == "/puesto/edit"
        assert model.puestoInstance != null

        puesto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/puesto/show/$puesto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/puesto/list'

        response.reset()

        def puesto = new Puesto()

        // TODO: populate valid domain properties
        assert puesto.save() != null
        assert Puesto.count() == 1

        params.id = puesto.id

        controller.delete()

        assert Puesto.count() == 0
        assert Puesto.get(puesto.id) == null
        assert response.redirectedUrl == '/puesto/list'


    }


}