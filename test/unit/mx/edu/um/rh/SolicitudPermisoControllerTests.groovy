package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(SolicitudPermisoController)
@Mock(SolicitudPermiso)
class SolicitudPermisoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitudPermiso/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitudPermisoInstanceList.size() == 0
        assert model.solicitudPermisoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.solicitudPermisoInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.solicitudPermisoInstance != null
        assert view == '/solicitudPermiso/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitudPermiso/show/1'
        assert controller.flash.message != null
        assert SolicitudPermiso.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudPermiso/list'


        populateValidParams(params)
        def solicitudPermiso = new SolicitudPermiso(params)

        assert solicitudPermiso.save() != null

        params.id = solicitudPermiso.id

        def model = controller.show()

        assert model.solicitudPermisoInstance == solicitudPermiso
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudPermiso/list'


        populateValidParams(params)
        def solicitudPermiso = new SolicitudPermiso(params)

        assert solicitudPermiso.save() != null

        params.id = solicitudPermiso.id

        def model = controller.edit()

        assert model.solicitudPermisoInstance == solicitudPermiso
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudPermiso/list'

        response.reset()


        populateValidParams(params)
        def solicitudPermiso = new SolicitudPermiso(params)

        assert solicitudPermiso.save() != null

        // test invalid parameters in update
        params.id = solicitudPermiso.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitudPermiso/edit"
        assert model.solicitudPermisoInstance != null

        solicitudPermiso.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitudPermiso/show/$solicitudPermiso.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitudPermiso.clearErrors()

        populateValidParams(params)
        params.id = solicitudPermiso.id
        params.version = -1
        controller.update()

        assert view == "/solicitudPermiso/edit"
        assert model.solicitudPermisoInstance != null
        assert model.solicitudPermisoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitudPermiso/list'

        response.reset()

        populateValidParams(params)
        def solicitudPermiso = new SolicitudPermiso(params)

        assert solicitudPermiso.save() != null
        assert SolicitudPermiso.count() == 1

        params.id = solicitudPermiso.id

        controller.delete()

        assert SolicitudPermiso.count() == 0
        assert SolicitudPermiso.get(solicitudPermiso.id) == null
        assert response.redirectedUrl == '/solicitudPermiso/list'
    }
}
