package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(NivelEstudiosController)
@Mock(NivelEstudios)
class NivelEstudiosControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/nivelEstudios/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.nivelEstudiosInstanceList.size() == 0
        assert model.nivelEstudiosInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.nivelEstudiosInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.nivelEstudiosInstance != null
        assert view == '/nivelEstudios/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/nivelEstudios/show/1'
        assert controller.flash.message != null
        assert NivelEstudios.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/nivelEstudios/list'


        def nivelEstudios = new NivelEstudios()

        // TODO: populate domain properties

        assert nivelEstudios.save() != null

        params.id = nivelEstudios.id

        def model = controller.show()

        assert model.nivelEstudiosInstance == nivelEstudios
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/nivelEstudios/list'


        def nivelEstudios = new NivelEstudios()

        // TODO: populate valid domain properties

        assert nivelEstudios.save() != null

        params.id = nivelEstudios.id

        def model = controller.edit()

        assert model.nivelEstudiosInstance == nivelEstudios
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/nivelEstudios/list'

        response.reset()


        def nivelEstudios = new NivelEstudios()

        // TODO: populate valid domain properties

        assert nivelEstudios.save() != null

        // test invalid parameters in update
        params.id = nivelEstudios.id

        controller.update()

        assert view == "/nivelEstudios/edit"
        assert model.nivelEstudiosInstance != null

        nivelEstudios.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/nivelEstudios/show/$nivelEstudios.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/nivelEstudios/list'

        response.reset()

        def nivelEstudios = new NivelEstudios()

        // TODO: populate valid domain properties
        assert nivelEstudios.save() != null
        assert NivelEstudios.count() == 1

        params.id = nivelEstudios.id

        controller.delete()

        assert NivelEstudios.count() == 0
        assert NivelEstudios.get(nivelEstudios.id) == null
        assert response.redirectedUrl == '/nivelEstudios/list'


    }


}