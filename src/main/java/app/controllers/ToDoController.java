package app.controllers;

import app.model.TaskProgress;
import app.model.ToDo;
import app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.util.Collections.unmodifiableMap;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/list")
public class ToDoController {

//TODO- validation

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping()
    public Iterable<ToDo> getToDos() {
        return toDoService.getAllToDos();
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        int todoId = parseInt(id);
        toDoService.deleteToDo(todoId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ToDo createToDo(@RequestBody ToDo todo ){
        return toDoService.addToDo(todo);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ToDo updateProgress(@PathVariable String id, @RequestBody ToDo todo){
        int todoId = parseInt(id);
        return toDoService.updateTaskProgress(todoId, todo);
    }

    @GetMapping("/taskProgress")
    public Map<TaskProgress, String> getProgressValues() {
        Map<TaskProgress, String> result = new HashMap<>();
        for (final TaskProgress taskProgress : TaskProgress.values()) {
            result.put(taskProgress, taskProgress.toString());
        }
        return  unmodifiableMap(new HashMap<TaskProgress, String>(result));
    }

}