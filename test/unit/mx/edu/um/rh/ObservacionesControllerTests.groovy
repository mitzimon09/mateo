package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(ObservacionesController)
@Mock(Observaciones)
class ObservacionesControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/observaciones/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.observacionesInstanceList.size() == 0
        assert model.observacionesInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.observacionesInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.observacionesInstance != null
        assert view == '/observaciones/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/observaciones/show/1'
        assert controller.flash.message != null
        assert Observaciones.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/observaciones/list'


        populateValidParams(params)
        def observaciones = new Observaciones(params)

        assert observaciones.save() != null

        params.id = observaciones.id

        def model = controller.show()

        assert model.observacionesInstance == observaciones
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/observaciones/list'


        populateValidParams(params)
        def observaciones = new Observaciones(params)

        assert observaciones.save() != null

        params.id = observaciones.id

        def model = controller.edit()

        assert model.observacionesInstance == observaciones
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/observaciones/list'

        response.reset()


        populateValidParams(params)
        def observaciones = new Observaciones(params)

        assert observaciones.save() != null

        // test invalid parameters in update
        params.id = observaciones.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/observaciones/edit"
        assert model.observacionesInstance != null

        observaciones.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/observaciones/show/$observaciones.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        observaciones.clearErrors()

        populateValidParams(params)
        params.id = observaciones.id
        params.version = -1
        controller.update()

        assert view == "/observaciones/edit"
        assert model.observacionesInstance != null
        assert model.observacionesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/observaciones/list'

        response.reset()

        populateValidParams(params)
        def observaciones = new Observaciones(params)

        assert observaciones.save() != null
        assert Observaciones.count() == 1

        params.id = observaciones.id

        controller.delete()

        assert Observaciones.count() == 0
        assert Observaciones.get(observaciones.id) == null
        assert response.redirectedUrl == '/observaciones/list'
    }
}
