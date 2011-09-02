package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(NacionalidadesController)
@Mock(Nacionalidades)
class NacionalidadesControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/nacionalidades/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.nacionalidadesInstanceList.size() == 0
        assert model.nacionalidadesInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.nacionalidadesInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.nacionalidadesInstance != null
        assert view == '/nacionalidades/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/nacionalidades/show/1'
        assert controller.flash.message != null
        assert Nacionalidades.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/nacionalidades/list'


        def nacionalidades = new Nacionalidades()

        // TODO: populate domain properties

        assert nacionalidades.save() != null

        params.id = nacionalidades.id

        def model = controller.show()

        assert model.nacionalidadesInstance == nacionalidades
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/nacionalidades/list'


        def nacionalidades = new Nacionalidades()

        // TODO: populate valid domain properties

        assert nacionalidades.save() != null

        params.id = nacionalidades.id

        def model = controller.edit()

        assert model.nacionalidadesInstance == nacionalidades
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/nacionalidades/list'

        response.reset()


        def nacionalidades = new Nacionalidades()

        // TODO: populate valid domain properties

        assert nacionalidades.save() != null

        // test invalid parameters in update
        params.id = nacionalidades.id

        controller.update()

        assert view == "/nacionalidades/edit"
        assert model.nacionalidadesInstance != null

        nacionalidades.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/nacionalidades/show/$nacionalidades.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/nacionalidades/list'

        response.reset()

        def nacionalidades = new Nacionalidades()

        // TODO: populate valid domain properties
        assert nacionalidades.save() != null
        assert Nacionalidades.count() == 1

        params.id = nacionalidades.id

        controller.delete()

        assert Nacionalidades.count() == 0
        assert Nacionalidades.get(nacionalidades.id) == null
        assert response.redirectedUrl == '/nacionalidades/list'


    }


}