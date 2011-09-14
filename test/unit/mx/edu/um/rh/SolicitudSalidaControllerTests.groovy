package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(SolicitudSalidaController)
@Mock(SolicitudSalida)
class SolicitudSalidaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/solicitudSalida/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.solicitudSalidaInstanceList.size() == 0
        assert model.solicitudSalidaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.solicitudSalidaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.solicitudSalidaInstance != null
        assert view == '/solicitudSalida/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/solicitudSalida/show/1'
        assert controller.flash.message != null
        assert SolicitudSalida.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudSalida/list'


        def solicitudSalida = new SolicitudSalida()

        // TODO: populate domain properties

        assert solicitudSalida.save() != null

        params.id = solicitudSalida.id

        def model = controller.show()

        assert model.solicitudSalidaInstance == solicitudSalida
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudSalida/list'


        def solicitudSalida = new SolicitudSalida()

        // TODO: populate valid domain properties

        assert solicitudSalida.save() != null

        params.id = solicitudSalida.id

        def model = controller.edit()

        assert model.solicitudSalidaInstance == solicitudSalida
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudSalida/list'

        response.reset()


        def solicitudSalida = new SolicitudSalida()

        // TODO: populate valid domain properties

        assert solicitudSalida.save() != null

        // test invalid parameters in update
        params.id = solicitudSalida.id

        controller.update()

        assert view == "/solicitudSalida/edit"
        assert model.solicitudSalidaInstance != null

        solicitudSalida.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/solicitudSalida/show/$solicitudSalida.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudSalida/list'

        response.reset()

        def solicitudSalida = new SolicitudSalida()

        // TODO: populate valid domain properties
        assert solicitudSalida.save() != null
        assert SolicitudSalida.count() == 1

        params.id = solicitudSalida.id

        controller.delete()

        assert SolicitudSalida.count() == 0
        assert SolicitudSalida.get(solicitudSalida.id) == null
        assert response.redirectedUrl == '/solicitudSalida/list'


    }


}