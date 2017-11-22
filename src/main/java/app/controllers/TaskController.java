package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TaskController {

    @RequestMapping("/list")
    public String list() {
        return "Greetings from Spring Boot!";
    }



}
