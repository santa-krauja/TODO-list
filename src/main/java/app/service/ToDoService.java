package app.service;

import app.model.ToDo;
import app.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public ToDo addToDo(ToDo todo) {
        toDoRepository.save(todo);
        return todo;
    }

    public void deleteToDo(int id) {
        toDoRepository.delete(id);
    }

    public Iterable<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public ToDo updateToDo(int todoId, ToDo todo) {
        ToDo tmpToDo = toDoRepository.findOne(todoId);
        tmpToDo.setTask(todo.getTask());
        tmpToDo.setProgress(todo.getProgress());
        tmpToDo.setAssignee(todo.getAssignee());
        return toDoRepository.save(tmpToDo);
    }

    public ToDo updateTaskProgress(int todoId, ToDo progress) {
        ToDo tmpToDo = toDoRepository.findOne(todoId);
        tmpToDo.setProgress(progress.getProgressEnumConstant());
        return toDoRepository.save(tmpToDo);
    }

    public void deleteAllToDos() {
        toDoRepository.deleteAll();
    }
}
