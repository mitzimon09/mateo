package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(SolicitudRHController)
@Mock(SolicitudRH)
class SolicitudRHControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/solicitudRH/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.solicitudRHInstanceList.size() == 0
        assert model.solicitudRHInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.solicitudRHInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.solicitudRHInstance != null
        assert view == '/solicitudRH/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/solicitudRH/show/1'
        assert controller.flash.message != null
        assert SolicitudRH.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudRH/list'


        def solicitudRH = new SolicitudRH()

        // TODO: populate domain properties

        assert solicitudRH.save() != null

        params.id = solicitudRH.id

        def model = controller.show()

        assert model.solicitudRHInstance == solicitudRH
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudRH/list'


        def solicitudRH = new SolicitudRH()

        // TODO: populate valid domain properties

        assert solicitudRH.save() != null

        params.id = solicitudRH.id

        def model = controller.edit()

        assert model.solicitudRHInstance == solicitudRH
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudRH/list'

        response.reset()


        def solicitudRH = new SolicitudRH()

        // TODO: populate valid domain properties

        assert solicitudRH.save() != null

        // test invalid parameters in update
        params.id = solicitudRH.id

        controller.update()

        assert view == "/solicitudRH/edit"
        assert model.solicitudRHInstance != null

        solicitudRH.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/solicitudRH/show/$solicitudRH.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudRH/list'

        response.reset()

        def solicitudRH = new SolicitudRH()

        // TODO: populate valid domain properties
        assert solicitudRH.save() != null
        assert SolicitudRH.count() == 1

        params.id = solicitudRH.id

        controller.delete()

        assert SolicitudRH.count() == 0
        assert SolicitudRH.get(solicitudRH.id) == null
        assert response.redirectedUrl == '/solicitudRH/list'


    }


}