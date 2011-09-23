package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ChequeController)
@Mock(Cheque)
class ChequeControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/cheque/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.chequeInstanceList.size() == 0
        assert model.chequeInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.chequeInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.chequeInstance != null
        assert view == '/cheque/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/cheque/show/1'
        assert controller.flash.message != null
        assert Cheque.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cheque/list'


        def cheque = new Cheque()

        // TODO: populate domain properties

        assert cheque.save() != null

        params.id = cheque.id

        def model = controller.show()

        assert model.chequeInstance == cheque
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cheque/list'


        def cheque = new Cheque()

        // TODO: populate valid domain properties

        assert cheque.save() != null

        params.id = cheque.id

        def model = controller.edit()

        assert model.chequeInstance == cheque
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cheque/list'

        response.reset()


        def cheque = new Cheque()

        // TODO: populate valid domain properties

        assert cheque.save() != null

        // test invalid parameters in update
        params.id = cheque.id

        controller.update()

        assert view == "/cheque/edit"
        assert model.chequeInstance != null

        cheque.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/cheque/show/$cheque.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/cheque/list'

        response.reset()

        def cheque = new Cheque()

        // TODO: populate valid domain properties
        assert cheque.save() != null
        assert Cheque.count() == 1

        params.id = cheque.id

        controller.delete()

        assert Cheque.count() == 0
        assert Cheque.get(cheque.id) == null
        assert response.redirectedUrl == '/cheque/list'


    }


}