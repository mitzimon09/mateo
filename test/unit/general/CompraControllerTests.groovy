package general



import org.junit.*
import grails.test.mixin.*


@TestFor(CompraController)
@Mock(Compra)
class CompraControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/compra/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.compraInstanceList.size() == 0
        assert model.compraInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.compraInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.compraInstance != null
        assert view == '/compra/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/compra/show/1'
        assert controller.flash.message != null
        assert Compra.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'


        def compra = new Compra()

        // TODO: populate domain properties

        assert compra.save() != null

        params.id = compra.id

        def model = controller.show()

        assert model.compraInstance == compra
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'


        def compra = new Compra()

        // TODO: populate valid domain properties

        assert compra.save() != null

        params.id = compra.id

        def model = controller.edit()

        assert model.compraInstance == compra
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        response.reset()


        def compra = new Compra()

        // TODO: populate valid domain properties

        assert compra.save() != null

        // test invalid parameters in update
        params.id = compra.id

        controller.update()

        assert view == "/compra/edit"
        assert model.compraInstance != null

        compra.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/compra/show/$compra.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        response.reset()

        def compra = new Compra()

        // TODO: populate valid domain properties
        assert compra.save() != null
        assert Compra.count() == 1

        params.id = compra.id

        controller.delete()

        assert Compra.count() == 0
        assert Compra.get(compra.id) == null
        assert response.redirectedUrl == '/compra/list'


    }


}