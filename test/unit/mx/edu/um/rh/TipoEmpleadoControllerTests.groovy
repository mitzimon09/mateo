package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(TipoEmpleadoController)
@Mock(TipoEmpleado)
class TipoEmpleadoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/tipoEmpleado/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.tipoEmpleadoInstanceList.size() == 0
        assert model.tipoEmpleadoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.tipoEmpleadoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.tipoEmpleadoInstance != null
        assert view == '/tipoEmpleado/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/tipoEmpleado/show/1'
        assert controller.flash.message != null
        assert TipoEmpleado.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEmpleado/list'


        def tipoEmpleado = new TipoEmpleado()

        // TODO: populate domain properties

        assert tipoEmpleado.save() != null

        params.id = tipoEmpleado.id

        def model = controller.show()

        assert model.tipoEmpleadoInstance == tipoEmpleado
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEmpleado/list'


        def tipoEmpleado = new TipoEmpleado()

        // TODO: populate valid domain properties

        assert tipoEmpleado.save() != null

        params.id = tipoEmpleado.id

        def model = controller.edit()

        assert model.tipoEmpleadoInstance == tipoEmpleado
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEmpleado/list'

        response.reset()


        def tipoEmpleado = new TipoEmpleado()

        // TODO: populate valid domain properties

        assert tipoEmpleado.save() != null

        // test invalid parameters in update
        params.id = tipoEmpleado.id

        controller.update()

        assert view == "/tipoEmpleado/edit"
        assert model.tipoEmpleadoInstance != null

        tipoEmpleado.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/tipoEmpleado/show/$tipoEmpleado.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoEmpleado/list'

        response.reset()

        def tipoEmpleado = new TipoEmpleado()

        // TODO: populate valid domain properties
        assert tipoEmpleado.save() != null
        assert TipoEmpleado.count() == 1

        params.id = tipoEmpleado.id

        controller.delete()

        assert TipoEmpleado.count() == 0
        assert TipoEmpleado.get(tipoEmpleado.id) == null
        assert response.redirectedUrl == '/tipoEmpleado/list'


    }


}