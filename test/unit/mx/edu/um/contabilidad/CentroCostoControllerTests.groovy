package mx.edu.um.contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(CentroCostoController)
@Mock(CentroCosto)
class CentroCostoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/centroCosto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.centroCostoInstanceList.size() == 0
        assert model.centroCostoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.centroCostoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.centroCostoInstance != null
        assert view == '/centroCosto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/centroCosto/show/1'
        assert controller.flash.message != null
        assert CentroCosto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/centroCosto/list'


        def centroCosto = new CentroCosto()

        // TODO: populate domain properties

        assert centroCosto.save() != null

        params.id = centroCosto.id

        def model = controller.show()

        assert model.centroCostoInstance == centroCosto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/centroCosto/list'


        def centroCosto = new CentroCosto()

        // TODO: populate valid domain properties

        assert centroCosto.save() != null

        params.id = centroCosto.id

        def model = controller.edit()

        assert model.centroCostoInstance == centroCosto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/centroCosto/list'

        response.reset()


        def centroCosto = new CentroCosto()

        // TODO: populate valid domain properties

        assert centroCosto.save() != null

        // test invalid parameters in update
        params.id = centroCosto.id

        controller.update()

        assert view == "/centroCosto/edit"
        assert model.centroCostoInstance != null

        centroCosto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/centroCosto/show/$centroCosto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/centroCosto/list'

        response.reset()

        def centroCosto = new CentroCosto()

        // TODO: populate valid domain properties
        assert centroCosto.save() != null
        assert CentroCosto.count() == 1

        params.id = centroCosto.id

        controller.delete()

        assert CentroCosto.count() == 0
        assert CentroCosto.get(centroCosto.id) == null
        assert response.redirectedUrl == '/centroCosto/list'


    }


}