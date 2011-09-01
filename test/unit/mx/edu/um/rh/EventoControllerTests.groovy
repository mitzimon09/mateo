package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(EventoController)
@Mock(Evento)
class EventoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/evento/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.eventoInstanceList.size() == 0
        assert model.eventoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.eventoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.eventoInstance != null
        assert view == '/evento/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/evento/show/1'
        assert controller.flash.message != null
        assert Evento.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/evento/list'


        def evento = new Evento()

        // TODO: populate domain properties

        assert evento.save() != null

        params.id = evento.id

        def model = controller.show()

        assert model.eventoInstance == evento
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/evento/list'


        def evento = new Evento()

        // TODO: populate valid domain properties

        assert evento.save() != null

        params.id = evento.id

        def model = controller.edit()

        assert model.eventoInstance == evento
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/evento/list'

        response.reset()


        def evento = new Evento()

        // TODO: populate valid domain properties

        assert evento.save() != null

        // test invalid parameters in update
        params.id = evento.id

        controller.update()

        assert view == "/evento/edit"
        assert model.eventoInstance != null

        evento.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/evento/show/$evento.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/evento/list'

        response.reset()

        def evento = new Evento()

        // TODO: populate valid domain properties
        assert evento.save() != null
        assert Evento.count() == 1

        params.id = evento.id

        controller.delete()

        assert Evento.count() == 0
        assert Evento.get(evento.id) == null
        assert response.redirectedUrl == '/evento/list'


    }


}