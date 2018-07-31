package app.service

import org.junit.Ignore
import spock.lang.Specification

import app.model.ToDo
import app.repository.ToDoRepository

class ToDoServiceTest extends Specification {

    final mockRepository = GroovyMock(ToDoRepository)
    final toDoService = new ToDoService(mockRepository)

    def todo = new ToDo()

    def setup() {
        todo.setTask("Do nothing")
        todo.setId(1)
        todo.setProgress("DONE")
        todo.setAssignee("Roberts")
    }

    def cleanup() {
        todo = null
    }

    def "Add ToDo"() {
        when:
        toDoService.addToDo(todo)

        then:
        1 * mockRepository.save(todo)
    }

    def "DeleteToDo"() {
        when:
        toDoService.deleteToDo(todo.getId())

        then:
        1* mockRepository.deleteById(todo.getId())
    }

    def "Get All Todos"() {
        given:
        def mockToDos = []
        mockToDos << todo
        mockRepository.findAll() >> mockToDos

        when:
        def toDoList = toDoService.getAllToDos()

        then:
        toDoList.size() == 1
        toDoList.first() == todo
    }

    @Ignore
    def "Update ToDo progress"() {
        given:
        Optional<ToDo> optionalToDo = new Optional<>(todo)
        mockRepository.findById(1) >> optionalToDo
        def oldProgress = todo.getProgress()
        todo.setProgress("WONT_DO")
        mockRepository.save(todo) >> todo

        when:
        def updatedToDO = toDoService.updateTaskProgress(1, todo)
        then:
        updatedToDO.getProgress() != oldProgress
    }
}
