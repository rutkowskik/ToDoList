package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskIndexController {

    private final TaskService taskService;

    public TaskIndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/task/show{id}")
    public String getTaskPage(Model model, @PathVariable Long id){

        model.addAttribute("tasks", taskService.findById(id));

        return "task/show";
    }

}
