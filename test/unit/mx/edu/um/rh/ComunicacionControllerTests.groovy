package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(ComunicacionController)
@Mock(Comunicacion)
class ComunicacionControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/comunicacion/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.comunicacionInstanceList.size() == 0
        assert model.comunicacionInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.comunicacionInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.comunicacionInstance != null
        assert view == '/comunicacion/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/comunicacion/show/1'
        assert controller.flash.message != null
        assert Comunicacion.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comunicacion/list'


        def comunicacion = new Comunicacion()

        // TODO: populate domain properties

        assert comunicacion.save() != null

        params.id = comunicacion.id

        def model = controller.show()

        assert model.comunicacionInstance == comunicacion
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comunicacion/list'


        def comunicacion = new Comunicacion()

        // TODO: populate valid domain properties

        assert comunicacion.save() != null

        params.id = comunicacion.id

        def model = controller.edit()

        assert model.comunicacionInstance == comunicacion
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comunicacion/list'

        response.reset()


        def comunicacion = new Comunicacion()

        // TODO: populate valid domain properties

        assert comunicacion.save() != null

        // test invalid parameters in update
        params.id = comunicacion.id

        controller.update()

        assert view == "/comunicacion/edit"
        assert model.comunicacionInstance != null

        comunicacion.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/comunicacion/show/$comunicacion.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/comunicacion/list'

        response.reset()

        def comunicacion = new Comunicacion()

        // TODO: populate valid domain properties
        assert comunicacion.save() != null
        assert Comunicacion.count() == 1

        params.id = comunicacion.id

        controller.delete()

        assert Comunicacion.count() == 0
        assert Comunicacion.get(comunicacion.id) == null
        assert response.redirectedUrl == '/comunicacion/list'


    }


}