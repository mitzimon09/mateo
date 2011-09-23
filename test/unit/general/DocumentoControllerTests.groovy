package general



import org.junit.*
import grails.test.mixin.*


@TestFor(DocumentoController)
@Mock(Documento)
class DocumentoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/documento/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.documentoInstanceList.size() == 0
        assert model.documentoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.documentoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.documentoInstance != null
        assert view == '/documento/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/documento/show/1'
        assert controller.flash.message != null
        assert Documento.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/documento/list'


        def documento = new Documento()

        // TODO: populate domain properties

        assert documento.save() != null

        params.id = documento.id

        def model = controller.show()

        assert model.documentoInstance == documento
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/documento/list'


        def documento = new Documento()

        // TODO: populate valid domain properties

        assert documento.save() != null

        params.id = documento.id

        def model = controller.edit()

        assert model.documentoInstance == documento
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/documento/list'

        response.reset()


        def documento = new Documento()

        // TODO: populate valid domain properties

        assert documento.save() != null

        // test invalid parameters in update
        params.id = documento.id

        controller.update()

        assert view == "/documento/edit"
        assert model.documentoInstance != null

        documento.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/documento/show/$documento.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/documento/list'

        response.reset()

        def documento = new Documento()

        // TODO: populate valid domain properties
        assert documento.save() != null
        assert Documento.count() == 1

        params.id = documento.id

        controller.delete()

        assert Documento.count() == 0
        assert Documento.get(documento.id) == null
        assert response.redirectedUrl == '/documento/list'


    }


}