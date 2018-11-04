package phuocly.springrestapiwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloPageController {

    @RequestMapping(path = "/hello")
    public String sayHello(Model model) {
        model.addAttribute("msg", "Hello!!! Spring Boot application");
        return "HelloPageView";
    }

}
