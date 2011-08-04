package general



import org.junit.*
import grails.test.mixin.*


@TestFor(PermisoController)
@Mock(Permiso)
class PermisoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/permiso/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.permisoInstanceList.size() == 0
        assert model.permisoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.permisoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.permisoInstance != null
        assert view == '/permiso/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/permiso/show/1'
        assert controller.flash.message != null
        assert Permiso.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/permiso/list'


        def permiso = new Permiso()

        // TODO: populate domain properties

        assert permiso.save() != null

        params.id = permiso.id

        def model = controller.show()

        assert model.permisoInstance == permiso
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/permiso/list'


        def permiso = new Permiso()

        // TODO: populate valid domain properties

        assert permiso.save() != null

        params.id = permiso.id

        def model = controller.edit()

        assert model.permisoInstance == permiso
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/permiso/list'

        response.reset()


        def permiso = new Permiso()

        // TODO: populate valid domain properties

        assert permiso.save() != null

        // test invalid parameters in update
        params.id = permiso.id

        controller.update()

        assert view == "/permiso/edit"
        assert model.permisoInstance != null

        permiso.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/permiso/show/$permiso.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/permiso/list'

        response.reset()

        def permiso = new Permiso()

        // TODO: populate valid domain properties
        assert permiso.save() != null
        assert Permiso.count() == 1

        params.id = permiso.id

        controller.delete()

        assert Permiso.count() == 0
        assert Permiso.get(permiso.id) == null
        assert response.redirectedUrl == '/permiso/list'


    }


}