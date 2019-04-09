package edu.snippethub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/admin/1")
    public String helloA() { return "hello Admin!"; }

    @GetMapping("/1")
    public String helloB() { return "hello Anonimous!"; }

    @GetMapping("/user/1")
    public String helloC() { return "hello User!"; }
}
