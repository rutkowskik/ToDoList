package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.exception.NotFoundException;
import com.rutkowski.todolist.services.ListOfTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController{

    private final ListOfTaskService listOfTaskService;


    public IndexController(ListOfTaskService listOfTaskService) {
        this.listOfTaskService = listOfTaskService;
    }

    @RequestMapping
    public String getIndexPage(Model model){
        log.debug("Getting index page with all lists");

        model.addAttribute("lists", listOfTaskService.getListsOfTasks());
        return "index";
    }

    @GetMapping
    @RequestMapping("/list/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting by ID:" + id);

        listOfTaskService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
    @GetMapping
    @RequestMapping("/list/new")
    public String newRecipe(Model model){
        model.addAttribute("list", new ListCommand());
        return "listform";
    }


    @PostMapping("/list")
    public String saveOrUpdate(@ModelAttribute ListCommand listCommand){
        ListCommand savedCommand = listOfTaskService.saveListCommand(listCommand);

        return "redirect:/";
    }

    @GetMapping
    @RequestMapping("/list/{id}/update")
    public String updateList(@PathVariable String id, Model model){
        model.addAttribute("list", listOfTaskService.findCommandById(Long.valueOf(id)));
        return "listform";
    }

}
