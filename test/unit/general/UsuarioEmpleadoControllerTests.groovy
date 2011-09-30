package general



import org.junit.*
import grails.test.mixin.*


@TestFor(UsuarioEmpleadoController)
@Mock(UsuarioEmpleado)
class UsuarioEmpleadoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/usuarioEmpleado/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.usuarioEmpleadoInstanceList.size() == 0
        assert model.usuarioEmpleadoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.usuarioEmpleadoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.usuarioEmpleadoInstance != null
        assert view == '/usuarioEmpleado/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/usuarioEmpleado/show/1'
        assert controller.flash.message != null
        assert UsuarioEmpleado.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioEmpleado/list'


        def usuarioEmpleado = new UsuarioEmpleado()

        // TODO: populate domain properties

        assert usuarioEmpleado.save() != null

        params.id = usuarioEmpleado.id

        def model = controller.show()

        assert model.usuarioEmpleadoInstance == usuarioEmpleado
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioEmpleado/list'


        def usuarioEmpleado = new UsuarioEmpleado()

        // TODO: populate valid domain properties

        assert usuarioEmpleado.save() != null

        params.id = usuarioEmpleado.id

        def model = controller.edit()

        assert model.usuarioEmpleadoInstance == usuarioEmpleado
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioEmpleado/list'

        response.reset()


        def usuarioEmpleado = new UsuarioEmpleado()

        // TODO: populate valid domain properties

        assert usuarioEmpleado.save() != null

        // test invalid parameters in update
        params.id = usuarioEmpleado.id

        controller.update()

        assert view == "/usuarioEmpleado/edit"
        assert model.usuarioEmpleadoInstance != null

        usuarioEmpleado.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/usuarioEmpleado/show/$usuarioEmpleado.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioEmpleado/list'

        response.reset()

        def usuarioEmpleado = new UsuarioEmpleado()

        // TODO: populate valid domain properties
        assert usuarioEmpleado.save() != null
        assert UsuarioEmpleado.count() == 1

        params.id = usuarioEmpleado.id

        controller.delete()

        assert UsuarioEmpleado.count() == 0
        assert UsuarioEmpleado.get(usuarioEmpleado.id) == null
        assert response.redirectedUrl == '/usuarioEmpleado/list'


    }


}