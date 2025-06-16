package fr.formation.controller;

import fr.formation.annotation.Controller;
import fr.formation.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
