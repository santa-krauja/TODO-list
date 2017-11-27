package app.controllers;

import app.model.TaskProgress;
import app.model.ToDo;
import app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/list")
public class ToDoController {

//TODO validation

    @Autowired
    public ToDoService toDoService;



    @GetMapping()
    public Iterable<ToDo> getToDos() {
        return toDoService.getAllTodos();
    }

    @GetMapping("/taskProgress")
    public Map<TaskProgress, String> getProgressValues() {
        Map<TaskProgress, String> result = new HashMap<>();
        TaskProgress[] taskProgresses = TaskProgress.values();
        IntStream.range(0, taskProgresses.length).forEach(
                i -> result.put(taskProgresses[i], taskProgresses[i].toString()));

        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        int todoId = Integer.parseInt(id);
        toDoService.deleteToDo(todoId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToDo createToDo(@RequestBody ToDo todo ){
        return toDoService.addToDo(todo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToDo updateToDos(@PathVariable String id, @RequestBody ToDo todo){
        int todoId = Integer.parseInt(id);
        return toDoService.updateToDo(todoId, todo);

    }

}
