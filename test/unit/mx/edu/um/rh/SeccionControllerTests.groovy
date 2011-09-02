package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(SeccionController)
@Mock(Seccion)
class SeccionControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/seccion/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.seccionInstanceList.size() == 0
        assert model.seccionInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.seccionInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.seccionInstance != null
        assert view == '/seccion/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/seccion/show/1'
        assert controller.flash.message != null
        assert Seccion.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/seccion/list'


        def seccion = new Seccion()

        // TODO: populate domain properties

        assert seccion.save() != null

        params.id = seccion.id

        def model = controller.show()

        assert model.seccionInstance == seccion
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seccion/list'


        def seccion = new Seccion()

        // TODO: populate valid domain properties

        assert seccion.save() != null

        params.id = seccion.id

        def model = controller.edit()

        assert model.seccionInstance == seccion
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/seccion/list'

        response.reset()


        def seccion = new Seccion()

        // TODO: populate valid domain properties

        assert seccion.save() != null

        // test invalid parameters in update
        params.id = seccion.id

        controller.update()

        assert view == "/seccion/edit"
        assert model.seccionInstance != null

        seccion.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/seccion/show/$seccion.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/seccion/list'

        response.reset()

        def seccion = new Seccion()

        // TODO: populate valid domain properties
        assert seccion.save() != null
        assert Seccion.count() == 1

        params.id = seccion.id

        controller.delete()

        assert Seccion.count() == 0
        assert Seccion.get(seccion.id) == null
        assert response.redirectedUrl == '/seccion/list'


    }


}