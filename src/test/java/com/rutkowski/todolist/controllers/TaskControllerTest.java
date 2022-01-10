package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.services.ListOfTaskService;
import com.rutkowski.todolist.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.print.attribute.standard.Media;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    TaskController controller;

    @Mock
    TaskService taskService;

    @Mock
    ListOfTaskService listOfTaskService;

    @Mock
    Model model;


    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new TaskController(taskService, listOfTaskService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getTaskPage() throws Exception{
        mockMvc.perform(get("/list/1/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/show2"));

    }

    @Test
    void addNewTask() throws Exception {
        //given
        ListCommand listCommand = new ListCommand();
        listCommand.setId(1L);

        //when
        when(listOfTaskService.findCommandById(anyLong())).thenReturn(listCommand);

        //then
        mockMvc.perform(get("/list/1/task/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/taskform2"))
                .andExpect(model().attributeExists("task"));

        verify(listOfTaskService, times(1)).findCommandById(anyLong());
    }

    @Test
    void updateTask() throws Exception {
        //given
        TaskCommand taskCommand = new TaskCommand();
        //when
        when(taskService.findByListIdAndTaskId(anyLong(),anyLong())).thenReturn(taskCommand);

        //then

        mockMvc.perform(get("/list/1/task/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/taskform2"))
                .andExpect(model().attributeExists("task"));
    }
    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/list/1/task/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/list/1/tasks"));

        verify(taskService, times(1)).deleteById(anyLong());
    }

    @Test
    void testPostNewTask() throws Exception{
        TaskCommand command = new TaskCommand();
        command.setId(3L);
        command.setListId(2L);

        //when
        when(taskService.saveTaskCommand(any())).thenReturn(command);

        //then
        mockMvc.perform(post("/list/2/task")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("title", "some title")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/list/2/tasks"));
    }

    @Test
    void testPostNewTaskFail() throws Exception {
        TaskCommand command = new TaskCommand();
        command.setId(3L);
        command.setListId(2L);

        //when
        when(taskService.saveTaskCommand(any())).thenReturn(command);


        //tehn
        mockMvc.perform(post("/list/2/task")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("task/taskform2"));
    }
}