package app.controllers

import app.model.ToDo
import app.repository.ToDoRepository
import app.service.ToDoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import javax.annotation.Resource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class ListRestControllerTest extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate
    def headers = new HttpHeaders()

    @Resource
    ToDoRepository repository

    def service = Mock(ToDoService)
    def controller = new ToDoController(service)
    def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def todo = new ToDo()
    def todo2 = new ToDo()

    def setup() {
        todo.setId(1)
        todo.setTask("Go home")
        todo.setProgress("IN_PROGRESS")
        todo.setAssignee("Santa")

        todo2.setId(2)
        todo2.setTask("Work")
        todo2.setProgress("IN_PROGRESS")
        todo2.setAssignee("Santa")

        repository.save(todo)
        repository.save(todo2)
    }

    def cleanup() {
        repository.deleteAll()
    }

    def "GET /list must return JSON string with all ToDos"() {
        given:
        HttpEntity<String> entity = new HttpEntity<String>(null, headers)
        def expected = "[" + todo.toJsonString() + "," + todo2.toJsonString() + "]"

        when:
        def response = testRestTemplate.exchange(URI.create("/list"), HttpMethod.GET, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == expected
        }

    }

    def "DELETE /list/{id} must delete ToDo with specified Id"() {
        given:
        HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when:
        def response = testRestTemplate.exchange(URI.create("/list/" + todo2.getId()), HttpMethod.DELETE, entity, String.class)


        then:

        with(response) {
            statusCodeValue == 200
        }
        repository.findAll().size() == 1
        repository.findOne(todo2.getId()) == null



    }


}
