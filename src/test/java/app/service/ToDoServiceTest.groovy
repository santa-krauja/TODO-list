package app.service

import app.model.ToDo
import app.repository.ToDoRepository
import spock.lang.Specification

class ToDoServiceTest extends Specification {

    final toDoRepository = Mock(ToDoRepository)
    final toDoService = new ToDoService(toDoRepository)

    def todo = new ToDo()

    def setup() {
        todo.setTask("Do nothing")
        todo.setId(1)
        todo.setProgress("DONE")
        todo.setAssignee("Roberts")
    }

    def cleanup() {

    }

    def "Add ToDo"() {
        when:
        toDoService.addToDo(todo)

        then:
        1 * toDoRepository.save(todo)
    }

    def "DeleteToDo"() {
        when:
        toDoService.deleteToDo(todo.getId())

        then:
        1* toDoRepository.delete(todo.getId())
    }

    def "Get All Todos"() {
        given:
        def mockToDos = []
        mockToDos << todo
        toDoRepository.findAll() >> mockToDos

        when:
        def toDoList = toDoService.getAllToDos()

        then:
        toDoList.size() == 1
        toDoList.first() == todo
    }

    def "Update ToDo progress"() {
        given:
        toDoRepository.findOne(1) >> todo
        def oldProgress = todo.getProgress()
        todo.setProgress("WONT_DO")
        toDoRepository.save(todo) >> todo

        when:
        def updatedToDO = toDoService.updateTaskProgress(1, todo)
        then:
        updatedToDO.getProgress() != oldProgress
    }
}
