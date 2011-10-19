package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(SolicitudVacacionesController)
@Mock(SolicitudVacaciones)
class SolicitudVacacionesControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitudVacaciones/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitudVacacionesInstanceList.size() == 0
        assert model.solicitudVacacionesInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.solicitudVacacionesInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.solicitudVacacionesInstance != null
        assert view == '/solicitudVacaciones/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitudVacaciones/show/1'
        assert controller.flash.message != null
        assert SolicitudVacaciones.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudVacaciones/list'


        populateValidParams(params)
        def solicitudVacaciones = new SolicitudVacaciones(params)

        assert solicitudVacaciones.save() != null

        params.id = solicitudVacaciones.id

        def model = controller.show()

        assert model.solicitudVacacionesInstance == solicitudVacaciones
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudVacaciones/list'


        populateValidParams(params)
        def solicitudVacaciones = new SolicitudVacaciones(params)

        assert solicitudVacaciones.save() != null

        params.id = solicitudVacaciones.id

        def model = controller.edit()

        assert model.solicitudVacacionesInstance == solicitudVacaciones
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudVacaciones/list'

        response.reset()


        populateValidParams(params)
        def solicitudVacaciones = new SolicitudVacaciones(params)

        assert solicitudVacaciones.save() != null

        // test invalid parameters in update
        params.id = solicitudVacaciones.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitudVacaciones/edit"
        assert model.solicitudVacacionesInstance != null

        solicitudVacaciones.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitudVacaciones/show/$solicitudVacaciones.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitudVacaciones.clearErrors()

        populateValidParams(params)
        params.id = solicitudVacaciones.id
        params.version = -1
        controller.update()

        assert view == "/solicitudVacaciones/edit"
        assert model.solicitudVacacionesInstance != null
        assert model.solicitudVacacionesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitudVacaciones/list'

        response.reset()

        populateValidParams(params)
        def solicitudVacaciones = new SolicitudVacaciones(params)

        assert solicitudVacaciones.save() != null
        assert SolicitudVacaciones.count() == 1

        params.id = solicitudVacaciones.id

        controller.delete()

        assert SolicitudVacaciones.count() == 0
        assert SolicitudVacaciones.get(solicitudVacaciones.id) == null
        assert response.redirectedUrl == '/solicitudVacaciones/list'
    }
}
