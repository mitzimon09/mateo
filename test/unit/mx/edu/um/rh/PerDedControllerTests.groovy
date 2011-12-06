package mx.edu.um.rh



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(PerDedController)
@Mock(PerDed)
class PerDedControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/perDed/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.perDedInstanceList.size() == 0
        assert model.perDedInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.perDedInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.perDedInstance != null
        assert view == '/perDed/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/perDed/show/1'
        assert controller.flash.message != null
        assert PerDed.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/perDed/list'


        populateValidParams(params)
        def perDed = new PerDed(params)

        assert perDed.save() != null

        params.id = perDed.id

        def model = controller.show()

        assert model.perDedInstance == perDed
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/perDed/list'


        populateValidParams(params)
        def perDed = new PerDed(params)

        assert perDed.save() != null

        params.id = perDed.id

        def model = controller.edit()

        assert model.perDedInstance == perDed
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/perDed/list'

        response.reset()


        populateValidParams(params)
        def perDed = new PerDed(params)

        assert perDed.save() != null

        // test invalid parameters in update
        params.id = perDed.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/perDed/edit"
        assert model.perDedInstance != null

        perDed.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/perDed/show/$perDed.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        perDed.clearErrors()

        populateValidParams(params)
        params.id = perDed.id
        params.version = -1
        controller.update()

        assert view == "/perDed/edit"
        assert model.perDedInstance != null
        assert model.perDedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/perDed/list'

        response.reset()

        populateValidParams(params)
        def perDed = new PerDed(params)

        assert perDed.save() != null
        assert PerDed.count() == 1

        params.id = perDed.id

        controller.delete()

        assert PerDed.count() == 0
        assert PerDed.get(perDed.id) == null
        assert response.redirectedUrl == '/perDed/list'
    }
}
