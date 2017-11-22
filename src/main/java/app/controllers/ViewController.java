package app.controllers;

import app.model.ToDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class ViewController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }



}
