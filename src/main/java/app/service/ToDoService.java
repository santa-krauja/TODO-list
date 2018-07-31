package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import app.model.ToDo;
import app.repository.ToDoRepository;

@Service
public class ToDoService {


    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo addToDo(ToDo todo) {
        toDoRepository.save(todo);
        return todo;
    }

    public void deleteToDo(int id) {
        toDoRepository.deleteById(id);
    }

    public Iterable<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo updateToDo(int todoId, ToDo todo) {
        ToDo tmpToDo = toDoRepository.findById(todoId).orElse(addToDo(new ToDo()));
        tmpToDo.setTask(todo.getTask());
        tmpToDo.setProgress(todo.getProgress());
        tmpToDo.setAssignee(todo.getAssignee());
        return toDoRepository.save(tmpToDo);
    }

    public ToDo updateTaskProgress(int todoId, ToDo progress) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(todoId);
        ToDo tmpToDo = optionalToDo.orElseGet(() -> optionalToDo.orElseThrow(ItemNotFoundException::new));
        tmpToDo.setProgress(progress.getProgressEnumConstant());
        return toDoRepository.save(tmpToDo);
    }

    public ToDo getOneToDo(int id) {
        return toDoRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
