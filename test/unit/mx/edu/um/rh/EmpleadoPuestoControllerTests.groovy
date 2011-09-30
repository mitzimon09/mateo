package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(EmpleadoPuestoController)
@Mock(EmpleadoPuesto)
class EmpleadoPuestoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/empleadoPuesto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.empleadoPuestoInstanceList.size() == 0
        assert model.empleadoPuestoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.empleadoPuestoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.empleadoPuestoInstance != null
        assert view == '/empleadoPuesto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/empleadoPuesto/show/1'
        assert controller.flash.message != null
        assert EmpleadoPuesto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPuesto/list'


        def empleadoPuesto = new EmpleadoPuesto()

        // TODO: populate domain properties

        assert empleadoPuesto.save() != null

        params.id = empleadoPuesto.id

        def model = controller.show()

        assert model.empleadoPuestoInstance == empleadoPuesto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPuesto/list'


        def empleadoPuesto = new EmpleadoPuesto()

        // TODO: populate valid domain properties

        assert empleadoPuesto.save() != null

        params.id = empleadoPuesto.id

        def model = controller.edit()

        assert model.empleadoPuestoInstance == empleadoPuesto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPuesto/list'

        response.reset()


        def empleadoPuesto = new EmpleadoPuesto()

        // TODO: populate valid domain properties

        assert empleadoPuesto.save() != null

        // test invalid parameters in update
        params.id = empleadoPuesto.id

        controller.update()

        assert view == "/empleadoPuesto/edit"
        assert model.empleadoPuestoInstance != null

        empleadoPuesto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/empleadoPuesto/show/$empleadoPuesto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPuesto/list'

        response.reset()

        def empleadoPuesto = new EmpleadoPuesto()

        // TODO: populate valid domain properties
        assert empleadoPuesto.save() != null
        assert EmpleadoPuesto.count() == 1

        params.id = empleadoPuesto.id

        controller.delete()

        assert EmpleadoPuesto.count() == 0
        assert EmpleadoPuesto.get(empleadoPuesto.id) == null
        assert response.redirectedUrl == '/empleadoPuesto/list'


    }


}