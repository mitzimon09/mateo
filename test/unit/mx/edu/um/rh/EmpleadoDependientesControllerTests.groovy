package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(EmpleadoDependientesController)
@Mock(EmpleadoDependientes)
class EmpleadoDependientesControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/empleadoDependientes/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.empleadoDependientesInstanceList.size() == 0
        assert model.empleadoDependientesInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.empleadoDependientesInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.empleadoDependientesInstance != null
        assert view == '/empleadoDependientes/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/empleadoDependientes/show/1'
        assert controller.flash.message != null
        assert EmpleadoDependientes.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoDependientes/list'


        populateValidParams(params)
        def empleadoDependientes = new EmpleadoDependientes(params)

        assert empleadoDependientes.save() != null

        params.id = empleadoDependientes.id

        def model = controller.show()

        assert model.empleadoDependientesInstance == empleadoDependientes
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoDependientes/list'


        populateValidParams(params)
        def empleadoDependientes = new EmpleadoDependientes(params)

        assert empleadoDependientes.save() != null

        params.id = empleadoDependientes.id

        def model = controller.edit()

        assert model.empleadoDependientesInstance == empleadoDependientes
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empleadoDependientes/list'

        response.reset()


        populateValidParams(params)
        def empleadoDependientes = new EmpleadoDependientes(params)

        assert empleadoDependientes.save() != null

        // test invalid parameters in update
        params.id = empleadoDependientes.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/empleadoDependientes/edit"
        assert model.empleadoDependientesInstance != null

        empleadoDependientes.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/empleadoDependientes/show/$empleadoDependientes.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        empleadoDependientes.clearErrors()

        populateValidParams(params)
        params.id = empleadoDependientes.id
        params.version = -1
        controller.update()

        assert view == "/empleadoDependientes/edit"
        assert model.empleadoDependientesInstance != null
        assert model.empleadoDependientesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/empleadoDependientes/list'

        response.reset()

        populateValidParams(params)
        def empleadoDependientes = new EmpleadoDependientes(params)

        assert empleadoDependientes.save() != null
        assert EmpleadoDependientes.count() == 1

        params.id = empleadoDependientes.id

        controller.delete()

        assert EmpleadoDependientes.count() == 0
        assert EmpleadoDependientes.get(empleadoDependientes.id) == null
        assert response.redirectedUrl == '/empleadoDependientes/list'
    }
}
