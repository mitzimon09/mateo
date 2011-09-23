package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ArticuloController)
@Mock(Articulo)
class ArticuloControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/articulo/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.articuloInstanceList.size() == 0
        assert model.articuloInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.articuloInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.articuloInstance != null
        assert view == '/articulo/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/articulo/show/1'
        assert controller.flash.message != null
        assert Articulo.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/articulo/list'


        def articulo = new Articulo()

        // TODO: populate domain properties

        assert articulo.save() != null

        params.id = articulo.id

        def model = controller.show()

        assert model.articuloInstance == articulo
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/articulo/list'


        def articulo = new Articulo()

        // TODO: populate valid domain properties

        assert articulo.save() != null

        params.id = articulo.id

        def model = controller.edit()

        assert model.articuloInstance == articulo
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/articulo/list'

        response.reset()


        def articulo = new Articulo()

        // TODO: populate valid domain properties

        assert articulo.save() != null

        // test invalid parameters in update
        params.id = articulo.id

        controller.update()

        assert view == "/articulo/edit"
        assert model.articuloInstance != null

        articulo.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/articulo/show/$articulo.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/articulo/list'

        response.reset()

        def articulo = new Articulo()

        // TODO: populate valid domain properties
        assert articulo.save() != null
        assert Articulo.count() == 1

        params.id = articulo.id

        controller.delete()

        assert Articulo.count() == 0
        assert Articulo.get(articulo.id) == null
        assert response.redirectedUrl == '/articulo/list'


    }


}