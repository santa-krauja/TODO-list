package app.controllers

import app.Application
import app.service.ToDoService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTest extends Specification {
    def toDoService = Mock(ToDoService)
    def underTest = new ToDoController(toDoService: toDoService)
    def mockMvc = MockMvcBuilders.standaloneSetup(underTest).build()


    def 'getToDos() forwards to service and returns result as a JSON'() {
       /* when:
        def response = mockMvc.perform()*/

    }

}
