package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/todo")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String checkingFacebookConnection() {
        return "redirect:/fc";
    }

}
