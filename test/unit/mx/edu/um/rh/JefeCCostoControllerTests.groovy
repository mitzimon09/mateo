package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(JefeCCostoController)
@Mock(JefeCCosto)
class JefeCCostoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/jefeCCosto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.jefeCCostoInstanceList.size() == 0
        assert model.jefeCCostoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.jefeCCostoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.jefeCCostoInstance != null
        assert view == '/jefeCCosto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/jefeCCosto/show/1'
        assert controller.flash.message != null
        assert JefeCCosto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/jefeCCosto/list'


        def jefeCCosto = new JefeCCosto()

        // TODO: populate domain properties

        assert jefeCCosto.save() != null

        params.id = jefeCCosto.id

        def model = controller.show()

        assert model.jefeCCostoInstance == jefeCCosto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/jefeCCosto/list'


        def jefeCCosto = new JefeCCosto()

        // TODO: populate valid domain properties

        assert jefeCCosto.save() != null

        params.id = jefeCCosto.id

        def model = controller.edit()

        assert model.jefeCCostoInstance == jefeCCosto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/jefeCCosto/list'

        response.reset()


        def jefeCCosto = new JefeCCosto()

        // TODO: populate valid domain properties

        assert jefeCCosto.save() != null

        // test invalid parameters in update
        params.id = jefeCCosto.id

        controller.update()

        assert view == "/jefeCCosto/edit"
        assert model.jefeCCostoInstance != null

        jefeCCosto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/jefeCCosto/show/$jefeCCosto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/jefeCCosto/list'

        response.reset()

        def jefeCCosto = new JefeCCosto()

        // TODO: populate valid domain properties
        assert jefeCCosto.save() != null
        assert JefeCCosto.count() == 1

        params.id = jefeCCosto.id

        controller.delete()

        assert JefeCCosto.count() == 0
        assert JefeCCosto.get(jefeCCosto.id) == null
        assert response.redirectedUrl == '/jefeCCosto/list'


    }


}