package com.example.todoApp.controllers;

import com.example.todoApp.models.ToDoItem;
import com.example.todoApp.services.ToDoItemSerivice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemSerivice toDoItemSerivice;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createToDoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {
        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsComplete(toDoItem.getIsComplete());

        toDoItemSerivice.save(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletetoDoItem(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemSerivice.getbyId(id).orElseThrow(() -> new IllegalArgumentException("todo item not found: " + id));

        toDoItemSerivice.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemSerivice.getbyId(id).orElseThrow(() -> new IllegalArgumentException("todo item not found: " + id));
        model.addAttribute("todo", toDoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid ToDoItem todoItem, BindingResult result, Model model) {

        ToDoItem item = toDoItemSerivice.getbyId(id).orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        toDoItemSerivice.save(item);

        return "redirect:/";
    }
}

