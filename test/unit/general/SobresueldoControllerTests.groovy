package general



import org.junit.*
import grails.test.mixin.*


@TestFor(SobresueldoController)
@Mock(Sobresueldo)
class SobresueldoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/sobresueldo/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.sobresueldoInstanceList.size() == 0
        assert model.sobresueldoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.sobresueldoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.sobresueldoInstance != null
        assert view == '/sobresueldo/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/sobresueldo/show/1'
        assert controller.flash.message != null
        assert Sobresueldo.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/sobresueldo/list'


        def sobresueldo = new Sobresueldo()

        // TODO: populate domain properties

        assert sobresueldo.save() != null

        params.id = sobresueldo.id

        def model = controller.show()

        assert model.sobresueldoInstance == sobresueldo
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/sobresueldo/list'


        def sobresueldo = new Sobresueldo()

        // TODO: populate valid domain properties

        assert sobresueldo.save() != null

        params.id = sobresueldo.id

        def model = controller.edit()

        assert model.sobresueldoInstance == sobresueldo
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/sobresueldo/list'

        response.reset()


        def sobresueldo = new Sobresueldo()

        // TODO: populate valid domain properties

        assert sobresueldo.save() != null

        // test invalid parameters in update
        params.id = sobresueldo.id

        controller.update()

        assert view == "/sobresueldo/edit"
        assert model.sobresueldoInstance != null

        sobresueldo.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/sobresueldo/show/$sobresueldo.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/sobresueldo/list'

        response.reset()

        def sobresueldo = new Sobresueldo()

        // TODO: populate valid domain properties
        assert sobresueldo.save() != null
        assert Sobresueldo.count() == 1

        params.id = sobresueldo.id

        controller.delete()

        assert Sobresueldo.count() == 0
        assert Sobresueldo.get(sobresueldo.id) == null
        assert response.redirectedUrl == '/sobresueldo/list'


    }


}