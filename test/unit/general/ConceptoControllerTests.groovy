package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ConceptoController)
@Mock(Concepto)
class ConceptoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/concepto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.conceptoInstanceList.size() == 0
        assert model.conceptoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.conceptoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.conceptoInstance != null
        assert view == '/concepto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/concepto/show/1'
        assert controller.flash.message != null
        assert Concepto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/concepto/list'


        def concepto = new Concepto()

        // TODO: populate domain properties

        assert concepto.save() != null

        params.id = concepto.id

        def model = controller.show()

        assert model.conceptoInstance == concepto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/concepto/list'


        def concepto = new Concepto()

        // TODO: populate valid domain properties

        assert concepto.save() != null

        params.id = concepto.id

        def model = controller.edit()

        assert model.conceptoInstance == concepto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/concepto/list'

        response.reset()


        def concepto = new Concepto()

        // TODO: populate valid domain properties

        assert concepto.save() != null

        // test invalid parameters in update
        params.id = concepto.id

        controller.update()

        assert view == "/concepto/edit"
        assert model.conceptoInstance != null

        concepto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/concepto/show/$concepto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/concepto/list'

        response.reset()

        def concepto = new Concepto()

        // TODO: populate valid domain properties
        assert concepto.save() != null
        assert Concepto.count() == 1

        params.id = concepto.id

        controller.delete()

        assert Concepto.count() == 0
        assert Concepto.get(concepto.id) == null
        assert response.redirectedUrl == '/concepto/list'


    }


}