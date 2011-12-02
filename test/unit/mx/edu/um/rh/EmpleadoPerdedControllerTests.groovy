package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(EmpleadoPerdedController)
@Mock(EmpleadoPerded)
class EmpleadoPerdedControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/empleadoPerded/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.empleadoPerdedInstanceList.size() == 0
        assert model.empleadoPerdedInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.empleadoPerdedInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.empleadoPerdedInstance != null
        assert view == '/empleadoPerded/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/empleadoPerded/show/1'
        assert controller.flash.message != null
        assert EmpleadoPerded.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPerded/list'


        populateValidParams(params)
        def empleadoPerded = new EmpleadoPerded(params)

        assert empleadoPerded.save() != null

        params.id = empleadoPerded.id

        def model = controller.show()

        assert model.empleadoPerdedInstance == empleadoPerded
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPerded/list'


        populateValidParams(params)
        def empleadoPerded = new EmpleadoPerded(params)

        assert empleadoPerded.save() != null

        params.id = empleadoPerded.id

        def model = controller.edit()

        assert model.empleadoPerdedInstance == empleadoPerded
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPerded/list'

        response.reset()


        populateValidParams(params)
        def empleadoPerded = new EmpleadoPerded(params)

        assert empleadoPerded.save() != null

        // test invalid parameters in update
        params.id = empleadoPerded.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/empleadoPerded/edit"
        assert model.empleadoPerdedInstance != null

        empleadoPerded.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/empleadoPerded/show/$empleadoPerded.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        empleadoPerded.clearErrors()

        populateValidParams(params)
        params.id = empleadoPerded.id
        params.version = -1
        controller.update()

        assert view == "/empleadoPerded/edit"
        assert model.empleadoPerdedInstance != null
        assert model.empleadoPerdedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/empleadoPerded/list'

        response.reset()

        populateValidParams(params)
        def empleadoPerded = new EmpleadoPerded(params)

        assert empleadoPerded.save() != null
        assert EmpleadoPerded.count() == 1

        params.id = empleadoPerded.id

        controller.delete()

        assert EmpleadoPerded.count() == 0
        assert EmpleadoPerded.get(empleadoPerded.id) == null
        assert response.redirectedUrl == '/empleadoPerded/list'
    }
}
