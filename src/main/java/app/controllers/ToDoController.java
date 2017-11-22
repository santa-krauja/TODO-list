package app.controllers;

import app.model.ToDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ToDoController {


    public List<ToDo> todoList() {
        List<ToDo> list = new ArrayList<ToDo>();
        list.add(new ToDo(1, "Ask for help"));
        list.add(new ToDo(2, "Watch 9gag"));
        return list;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ToDo> getToDos() {
        return todoList();
    }

}
