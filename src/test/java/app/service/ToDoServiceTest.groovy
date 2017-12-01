package app.service

import app.model.ToDo
import app.repository.ToDoRepository
import spock.lang.Specification

class ToDoServiceTest extends Specification {


   ToDoService toDoService = new ToDoService(toDoRepository)

    def toDoRepository = Mock(ToDoRepository)

    def setup() {
        toDoService.toDoRepository = toDoRepository
        toDoService.deleteAllToDos()
    }

    def "AddToDo"() {
        setup:
       ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(0)
        todo.setProgress("None")
        todo.setAssignee("Roberts")

        when:
        toDoService.addToDo(todo)

        then:
        1 * toDoRepository.save(todo)
    }

    def "DeleteToDo"() {
    }

    def "GetAllTodos"() {
        ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(0)
        todo.setProgress("None")
        todo.setAssignee("Roberts")

        def mockToDos =[]
        mockToDos << todo
        toDoRepository.findAll() >> mockToDos


        when:
        def toDoList = toDoService.getAllToDos()

        then:
        toDoList.size() == 1
        toDoList.first() == todo
    }

    def "UpdateToDo"() {
    }
}
