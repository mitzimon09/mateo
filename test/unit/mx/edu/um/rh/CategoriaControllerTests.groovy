package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(CategoriaController)
@Mock(Categoria)
class CategoriaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/categoria/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.categoriaInstanceList.size() == 0
        assert model.categoriaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.categoriaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.categoriaInstance != null
        assert view == '/categoria/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/categoria/show/1'
        assert controller.flash.message != null
        assert Categoria.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/categoria/list'


        def categoria = new Categoria()

        // TODO: populate domain properties

        assert categoria.save() != null

        params.id = categoria.id

        def model = controller.show()

        assert model.categoriaInstance == categoria
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/categoria/list'


        def categoria = new Categoria()

        // TODO: populate valid domain properties

        assert categoria.save() != null

        params.id = categoria.id

        def model = controller.edit()

        assert model.categoriaInstance == categoria
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/categoria/list'

        response.reset()


        def categoria = new Categoria()

        // TODO: populate valid domain properties

        assert categoria.save() != null

        // test invalid parameters in update
        params.id = categoria.id

        controller.update()

        assert view == "/categoria/edit"
        assert model.categoriaInstance != null

        categoria.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/categoria/show/$categoria.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/categoria/list'

        response.reset()

        def categoria = new Categoria()

        // TODO: populate valid domain properties
        assert categoria.save() != null
        assert Categoria.count() == 1

        params.id = categoria.id

        controller.delete()

        assert Categoria.count() == 0
        assert Categoria.get(categoria.id) == null
        assert response.redirectedUrl == '/categoria/list'


    }


}