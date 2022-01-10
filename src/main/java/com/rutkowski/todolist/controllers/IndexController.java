package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.services.ListOfTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return "index2";
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
        return "listform2";
    }


    @PostMapping("/list")
    public String saveOrUpdate(@Valid @ModelAttribute("list") ListCommand listCommand, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "listform2";
        }
        ListCommand savedCommand = listOfTaskService.saveListCommand(listCommand);
        return "redirect:/list/" + savedCommand.getId() + "/tasks";
    }

    @GetMapping
    @RequestMapping("/list/{id}/update")
    public String updateList(@PathVariable String id, Model model){
        model.addAttribute("list", listOfTaskService.findCommandById(Long.valueOf(id)));
        return "listform2";
    }

}
