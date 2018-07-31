package app.controllers

import static org.springframework.http.HttpMethod.DELETE
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import javax.annotation.Resource

import app.model.ToDo
import app.repository.ToDoRepository
import app.service.ToDoService

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class RestControllerTest extends Specification {
    private final static int ID_TWO = 2

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

        todo2.setId(ID_TWO)
        todo2.setTask("Work")
        todo2.setProgress("IN_PROGRESS")
        todo2.setAssignee("Santa")

        repository.save(todo)
        repository.save(todo2)
    }

    def cleanup() {
        repository.deleteAll()
    }

    def "GET /list returns expected list"() {
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

    def "GET /list returns expected object"() {
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

    def "DELETE deletes object"() {
        given:
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when:
        def response = testRestTemplate.exchange(URI.create(link + 2), DELETE, entity, String.class)
        then:
        with(response) {
            statusCodeValue == 200
        }
        repository.findAll().size() == 1
    }

    def "create new task"() {
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
        repository.findById(todo3.getId()).get() == todo3
    }

    def "Update task progress providing id in uri parameters"() {
        given:
        final oldToDoJson = todo.toJsonString()
        todo.setProgress("DONE")
        headers.setContentType(MediaType.APPLICATION_JSON)
        final HttpEntity<String> entity = new HttpEntity<String>(todo.toJsonEnumString(), headers)

        when:
        def response = testRestTemplate.exchange(URI.create(link + todo.getId()), PUT, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == todo.toJsonString()
        }
        repository.findAll().size() == 2
        repository.findById(todo.getId()).get().toJsonString() != oldToDoJson
    }

    def "/taskProgress endpoint return all task enum- value pairs"() {
        given:
        final HttpEntity<String> entity = new HttpEntity<String>(null, headers)
        final enumJson = "{\"NOT_STARTED\":\"Not started\",\"IN_PROGRESS\":\"In progress\",\"DONE\":\"Done\",\"WONT_DO\":\"Won't do\"}"

        when:
        def response = testRestTemplate.exchange(URI.create(link + "taskProgress"), GET, entity, String.class)

        then:
        with(response) {
            statusCodeValue == 200
            body == enumJson
        }
    }
}
