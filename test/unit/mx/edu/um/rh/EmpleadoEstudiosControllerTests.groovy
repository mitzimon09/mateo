package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(EmpleadoEstudiosController)
@Mock(EmpleadoEstudios)
class EmpleadoEstudiosControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/empleadoEstudios/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.empleadoEstudiosInstanceList.size() == 0
        assert model.empleadoEstudiosInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.empleadoEstudiosInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.empleadoEstudiosInstance != null
        assert view == '/empleadoEstudios/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/empleadoEstudios/show/1'
        assert controller.flash.message != null
        assert EmpleadoEstudios.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoEstudios/list'


        def empleadoEstudios = new EmpleadoEstudios()

        // TODO: populate domain properties

        assert empleadoEstudios.save() != null

        params.id = empleadoEstudios.id

        def model = controller.show()

        assert model.empleadoEstudiosInstance == empleadoEstudios
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoEstudios/list'


        def empleadoEstudios = new EmpleadoEstudios()

        // TODO: populate valid domain properties

        assert empleadoEstudios.save() != null

        params.id = empleadoEstudios.id

        def model = controller.edit()

        assert model.empleadoEstudiosInstance == empleadoEstudios
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoEstudios/list'

        response.reset()


        def empleadoEstudios = new EmpleadoEstudios()

        // TODO: populate valid domain properties

        assert empleadoEstudios.save() != null

        // test invalid parameters in update
        params.id = empleadoEstudios.id

        controller.update()

        assert view == "/empleadoEstudios/edit"
        assert model.empleadoEstudiosInstance != null

        empleadoEstudios.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/empleadoEstudios/show/$empleadoEstudios.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoEstudios/list'

        response.reset()

        def empleadoEstudios = new EmpleadoEstudios()

        // TODO: populate valid domain properties
        assert empleadoEstudios.save() != null
        assert EmpleadoEstudios.count() == 1

        params.id = empleadoEstudios.id

        controller.delete()

        assert EmpleadoEstudios.count() == 0
        assert EmpleadoEstudios.get(empleadoEstudios.id) == null
        assert response.redirectedUrl == '/empleadoEstudios/list'


    }


}