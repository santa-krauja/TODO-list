package app.controllers;

import app.model.ToDo;
import app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
