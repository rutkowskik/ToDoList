package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.services.ListOfTaskService;
import com.rutkowski.todolist.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class TaskController {

    private final TaskService taskService;
    private final ListOfTaskService listOfTaskService;

    public TaskController(TaskService taskService, ListOfTaskService listOfTaskService) {
        this.taskService = taskService;
        this.listOfTaskService = listOfTaskService;
    }

    @GetMapping
    @RequestMapping("/list/{listId}/tasks")
    public String getTaskPage(Model model, @PathVariable String listId){

        model.addAttribute("list", listOfTaskService.findCommandById(Long.valueOf(listId)));

        return "task/show";
    }

    @GetMapping
    @RequestMapping("list/{listId}/task/new")
    public String addNewTask(@PathVariable String listId, Model model){

        //make sure we have id value
        ListCommand listCommand = listOfTaskService.findCommandById(Long.valueOf(listId));
        //todo raise exception
        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setListId(Long.valueOf(listId));
        model.addAttribute("task", taskCommand);
        return "task/taskform";
    }

    @GetMapping
    @RequestMapping("list/{listId}/task/{taskId}")
    public String updateTask(@PathVariable String listId, @PathVariable String taskId, Model model){

        model.addAttribute("task", taskService.findByListIdAndTaskId(Long.valueOf(listId), Long.valueOf(taskId)));
        return "task/taskform";
    }

    @PostMapping("/list/{listId}/task")
    public String saveOrUpdate(@ModelAttribute TaskCommand command){
        TaskCommand savedCommand = taskService.saveTaskCommand(command);
        return "redirect:/list/" + command.getListId() + "/tasks";
    }

    @GetMapping
    @RequestMapping("list/{listId}/task/{taskId}/delete")
    public String deleteById(@PathVariable String listId, @PathVariable String taskId){

        taskService.deleteById(Long.valueOf(taskId));
        return "redirect:/list/" + listId + "/tasks";
    }

}
