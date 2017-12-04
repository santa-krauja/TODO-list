package app.service

import app.model.ToDo
import app.repository.ToDoRepository
import spock.lang.Specification

class ToDoServiceTest extends Specification {

    def toDoRepository = Mock(ToDoRepository)
   ToDoService toDoService = new ToDoService(toDoRepository)


    def cleanup() {
        toDoService.deleteAllToDos()
    }

    def "Add ToDo"() {
        setup:
       ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(1)
        todo.setProgress("DONE")
        todo.setAssignee("Roberts")

        when:
        toDoService.addToDo(todo)

        then:
        1 * toDoRepository.save(todo)
    }

    def "DeleteToDo"() {
    }

    def "Get All Todos"() {
        ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(1)
        todo.setProgress("DONE")
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
