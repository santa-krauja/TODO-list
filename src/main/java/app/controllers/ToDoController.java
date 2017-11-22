package app.controllers;

import app.model.ToDo;
import app.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class ToDoController {


    public ToDoService toDoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Collection<ToDo> getToDos() {
        toDoService = new ToDoService();
        return toDoService.getToDoList();
    }

}
