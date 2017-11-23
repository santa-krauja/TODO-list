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
        tmpToDo.settask(todo.gettask());
        tmpToDo.setProgress(todo.getProgress());
        return toDoRepository.save(tmpToDo);
    }

    public void deleteAllToDos() {
        toDoRepository.deleteAll();
    }
}
