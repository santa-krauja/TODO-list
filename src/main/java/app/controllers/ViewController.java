package app.controllers;

import app.model.TaskProgress;
import app.model.ToDo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller

public class ViewController {

    @RequestMapping("/")
    public String index(Model model) {
        List<TaskProgress> taskProgresses = new ArrayList<>(Arrays.asList(TaskProgress.values()));
        model.addAttribute("TaskProgress", TaskProgress.values());
        return "index";
    }

}
