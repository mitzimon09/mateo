package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(RelacionController)
@Mock(Relacion)
class RelacionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/relacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.relacionInstanceList.size() == 0
        assert model.relacionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.relacionInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.relacionInstance != null
        assert view == '/relacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/relacion/show/1'
        assert controller.flash.message != null
        assert Relacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/relacion/list'


        populateValidParams(params)
        def relacion = new Relacion(params)

        assert relacion.save() != null

        params.id = relacion.id

        def model = controller.show()

        assert model.relacionInstance == relacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/relacion/list'


        populateValidParams(params)
        def relacion = new Relacion(params)

        assert relacion.save() != null

        params.id = relacion.id

        def model = controller.edit()

        assert model.relacionInstance == relacion
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/relacion/list'

        response.reset()


        populateValidParams(params)
        def relacion = new Relacion(params)

        assert relacion.save() != null

        // test invalid parameters in update
        params.id = relacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/relacion/edit"
        assert model.relacionInstance != null

        relacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/relacion/show/$relacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        relacion.clearErrors()

        populateValidParams(params)
        params.id = relacion.id
        params.version = -1
        controller.update()

        assert view == "/relacion/edit"
        assert model.relacionInstance != null
        assert model.relacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/relacion/list'

        response.reset()

        populateValidParams(params)
        def relacion = new Relacion(params)

        assert relacion.save() != null
        assert Relacion.count() == 1

        params.id = relacion.id

        controller.delete()

        assert Relacion.count() == 0
        assert Relacion.get(relacion.id) == null
        assert response.redirectedUrl == '/relacion/list'
    }
}
