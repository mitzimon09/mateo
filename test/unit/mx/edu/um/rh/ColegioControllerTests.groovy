package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(ColegioController)
@Mock(Colegio)
class ColegioControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/colegio/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.colegioInstanceList.size() == 0
        assert model.colegioInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.colegioInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.colegioInstance != null
        assert view == '/colegio/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/colegio/show/1'
        assert controller.flash.message != null
        assert Colegio.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/colegio/list'


        def colegio = new Colegio()

        // TODO: populate domain properties

        assert colegio.save() != null

        params.id = colegio.id

        def model = controller.show()

        assert model.colegioInstance == colegio
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/colegio/list'


        def colegio = new Colegio()

        // TODO: populate valid domain properties

        assert colegio.save() != null

        params.id = colegio.id

        def model = controller.edit()

        assert model.colegioInstance == colegio
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/colegio/list'

        response.reset()


        def colegio = new Colegio()

        // TODO: populate valid domain properties

        assert colegio.save() != null

        // test invalid parameters in update
        params.id = colegio.id

        controller.update()

        assert view == "/colegio/edit"
        assert model.colegioInstance != null

        colegio.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/colegio/show/$colegio.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/colegio/list'

        response.reset()

        def colegio = new Colegio()

        // TODO: populate valid domain properties
        assert colegio.save() != null
        assert Colegio.count() == 1

        params.id = colegio.id

        controller.delete()

        assert Colegio.count() == 0
        assert Colegio.get(colegio.id) == null
        assert response.redirectedUrl == '/colegio/list'


    }


}