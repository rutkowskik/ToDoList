package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.services.ListOfTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IndexController {

    private final ListOfTaskService listOfTaskService;


    public IndexController(ListOfTaskService listOfTaskService) {
        this.listOfTaskService = listOfTaskService;
    }

    @RequestMapping
    public String getIndexPage(Model model){

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

}
