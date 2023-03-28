package com.example.todoApp.controllers;

import com.example.todoApp.services.ToDoItemSerivice;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ToDoItemSerivice toDoItemSerivice;

    @GetMapping("/")
    public ModelAndView root() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", toDoItemSerivice.getAll());
        return modelAndView;
    }
}
