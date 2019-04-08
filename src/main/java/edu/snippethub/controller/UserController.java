package edu.snippethub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @GetMapping("/login")
    public String hello() {
        return "logn";
    }

    @GetMapping("/admin/1")
    public String helloA() { return "hello Admin!"; }
}
