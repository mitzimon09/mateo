package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*


@TestFor(VacacionesController)
@Mock(Vacaciones)
class VacacionesControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/vacaciones/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.vacacionesInstanceList.size() == 0
        assert model.vacacionesInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.vacacionesInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.vacacionesInstance != null
        assert view == '/vacaciones/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/vacaciones/show/1'
        assert controller.flash.message != null
        assert Vacaciones.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/vacaciones/list'


        def vacaciones = new Vacaciones()

        // TODO: populate domain properties

        assert vacaciones.save() != null

        params.id = vacaciones.id

        def model = controller.show()

        assert model.vacacionesInstance == vacaciones
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/vacaciones/list'


        def vacaciones = new Vacaciones()

        // TODO: populate valid domain properties

        assert vacaciones.save() != null

        params.id = vacaciones.id

        def model = controller.edit()

        assert model.vacacionesInstance == vacaciones
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/vacaciones/list'

        response.reset()


        def vacaciones = new Vacaciones()

        // TODO: populate valid domain properties

        assert vacaciones.save() != null

        // test invalid parameters in update
        params.id = vacaciones.id

        controller.update()

        assert view == "/vacaciones/edit"
        assert model.vacacionesInstance != null

        vacaciones.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/vacaciones/show/$vacaciones.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/vacaciones/list'

        response.reset()

        def vacaciones = new Vacaciones()

        // TODO: populate valid domain properties
        assert vacaciones.save() != null
        assert Vacaciones.count() == 1

        params.id = vacaciones.id

        controller.delete()

        assert Vacaciones.count() == 0
        assert Vacaciones.get(vacaciones.id) == null
        assert response.redirectedUrl == '/vacaciones/list'


    }


}