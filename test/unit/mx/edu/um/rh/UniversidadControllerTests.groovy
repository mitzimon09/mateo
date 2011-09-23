package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(UniversidadController)
@Mock(Universidad)
class UniversidadControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/universidad/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.universidadInstanceList.size() == 0
        assert model.universidadInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.universidadInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.universidadInstance != null
        assert view == '/universidad/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/universidad/show/1'
        assert controller.flash.message != null
        assert Universidad.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/universidad/list'


        def universidad = new Universidad()

        // TODO: populate domain properties

        assert universidad.save() != null

        params.id = universidad.id

        def model = controller.show()

        assert model.universidadInstance == universidad
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/universidad/list'


        def universidad = new Universidad()

        // TODO: populate valid domain properties

        assert universidad.save() != null

        params.id = universidad.id

        def model = controller.edit()

        assert model.universidadInstance == universidad
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/universidad/list'

        response.reset()


        def universidad = new Universidad()

        // TODO: populate valid domain properties

        assert universidad.save() != null

        // test invalid parameters in update
        params.id = universidad.id

        controller.update()

        assert view == "/universidad/edit"
        assert model.universidadInstance != null

        universidad.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/universidad/show/$universidad.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/universidad/list'

        response.reset()

        def universidad = new Universidad()

        // TODO: populate valid domain properties
        assert universidad.save() != null
        assert Universidad.count() == 1

        params.id = universidad.id

        controller.delete()

        assert Universidad.count() == 0
        assert Universidad.get(universidad.id) == null
        assert response.redirectedUrl == '/universidad/list'


    }


}