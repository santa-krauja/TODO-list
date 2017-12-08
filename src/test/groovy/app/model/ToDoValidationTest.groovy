package app.model

import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation

class ToDoValidationTest extends Specification {


    def todo = new ToDo()
    final factory = Validation.buildDefaultValidatorFactory()
    final validator = factory.getValidator()

    def setup() {
        todo.setId(1)
        todo.setTask("Go home")
        todo.setProgress("IN_PROGRESS")
        todo.setAssignee("Santa")
    }

    def "set id must return errors with negative and zero values"() {
        when:
        todo.setId(id)
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todo)

        then:
        violations.size() == result

        where:
        result | id
        0      | 1
        1      | -1
        1      | 0
    }

    def "set task must return errors with wrong values"() {
        when:
        todo.setTask(task)
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todo)

        then:
        violations.size() == result

        where:
        result | task
        0      | "Go home"
        1      | ""
        1      | "S".multiply(501)
        1      | "   "
    }

    def "set assignee should return errors with wrong values"() {
        when:
        todo.setAssignee(assignee)
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todo)

        then:
        violations.size() == result

        where:
        result | assignee
        0      | "Santa"
        1      | ""
        1      | "S".multiply(251)
        1      | "   "
    }

}
