package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(GrupoController)
@Mock(Grupo)
class GrupoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/grupo/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.grupoInstanceList.size() == 0
        assert model.grupoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.grupoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.grupoInstance != null
        assert view == '/grupo/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/grupo/show/1'
        assert controller.flash.message != null
        assert Grupo.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'


        def grupo = new Grupo()

        // TODO: populate domain properties

        assert grupo.save() != null

        params.id = grupo.id

        def model = controller.show()

        assert model.grupoInstance == grupo
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'


        def grupo = new Grupo()

        // TODO: populate valid domain properties

        assert grupo.save() != null

        params.id = grupo.id

        def model = controller.edit()

        assert model.grupoInstance == grupo
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'

        response.reset()


        def grupo = new Grupo()

        // TODO: populate valid domain properties

        assert grupo.save() != null

        // test invalid parameters in update
        params.id = grupo.id

        controller.update()

        assert view == "/grupo/edit"
        assert model.grupoInstance != null

        grupo.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/grupo/show/$grupo.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'

        response.reset()

        def grupo = new Grupo()

        // TODO: populate valid domain properties
        assert grupo.save() != null
        assert Grupo.count() == 1

        params.id = grupo.id

        controller.delete()

        assert Grupo.count() == 0
        assert Grupo.get(grupo.id) == null
        assert response.redirectedUrl == '/grupo/list'


    }


}