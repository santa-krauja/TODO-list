package app.controllers

import app.model.ToDo
import app.repository.ToDoRepository
import app.service.ToDoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import javax.annotation.Resource

import static org.springframework.http.HttpMethod.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class ListRestControllerTest extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate
    final headers = new HttpHeaders()
    final link = "/list/"

    @Resource
    ToDoRepository repository

    final service = Mock(ToDoService)
    final controller = new ToDoController(service)
    final mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

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
        final expected = "[" + todo.toJsonString() + "," + todo2.toJsonString() + "]"
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when:
        final response = testRestTemplate.exchange(URI.create(link), GET, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == expected
        }

    }

    def "GET /list with ToDo id must return JSON string with needed ToDo JSON"() {
        given:
        final expected = todo.toJsonString()
        final id = todo.getId()
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when:
        final response = testRestTemplate.exchange(URI.create(link + id), GET, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == expected
        }

    }

    def "DELETE /list/{id} must delete ToDo with specified Id"() {
        given:
        final id = todo2.getId()
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when:
        def response = testRestTemplate.exchange(URI.create(link + id), DELETE, entity, String.class)
        then:
        with(response) {
            statusCodeValue == 200
        }
        repository.findAll().size() == 1
        repository.findOne(todo2.getId()) == null


    }

    def "Create new task thru HTTP POST /list request"() {
        given:
        def todo3 = new ToDo()
        todo3.setId(3)
        todo3.setTask("Go home")
        todo3.setProgress("IN_PROGRESS")
        todo3.setAssignee("Viktors")

        headers.setContentType(MediaType.APPLICATION_JSON)
        final HttpEntity<String> entity = new HttpEntity<String>(todo3.toJsonEnumString(), headers)

        when:
        def response = testRestTemplate.exchange(URI.create(link), POST, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == todo3.toJsonString()
        }
        repository.findAll().size() == 3
        repository.findOne(todo3.getId()) == todo3
    }

    def "Update task progress providing id in uri parameters"() {
        given:
        final oldToDoJson = todo.toJsonString()
        todo.setProgress("DONE")
        headers.setContentType(MediaType.APPLICATION_JSON)
        final HttpEntity<String> entity = new HttpEntity<String>(todo.toJsonEnumString(), headers)

        when:
        def response = testRestTemplate.exchange(URI.create(link + 1), PUT, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == todo.toJsonString()
        }
        repository.findAll().size() == 2
        repository.findOne(todo.getId()).toJsonString() != oldToDoJson

    }

    def "/taskProgress endpoint return all task enum- value pairs"(){
        given:
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)
        final enumJson = "{\"DONE\":\"Done\",\"IN_PROGRESS\":\"In progress\",\"NOT_STARTED\":\"Not started\"}"

        when:
        def response = testRestTemplate.exchange(URI.create(link + "taskProgress"), GET, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == enumJson
        }
    }


}
