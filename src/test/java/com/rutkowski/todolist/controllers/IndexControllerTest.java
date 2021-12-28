package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.services.ListOfTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController controller;

    @Mock
    ListOfTaskService listOfTaskService;
    @Mock
    Model model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new IndexController(listOfTaskService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testIndexPage() throws Exception{

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }
    @Test
    void getIndexPage() {

        //given
        Set<ListOfTasks> list = new HashSet<>();
        list.add(new ListOfTasks());
        list.add(new ListOfTasks());

        when(listOfTaskService.getListsOfTasks()).thenReturn(list);

        ArgumentCaptor<Set<ListOfTasks>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index",viewName);
        verify(listOfTaskService, times(1)).getListsOfTasks();
        verify(model, times(1)).addAttribute(eq("lists"), argumentCaptor.capture());
        Set<ListOfTasks> setController = argumentCaptor.getValue();
        assertEquals(2, setController.size());
    }

    @Test
    void testNewList() throws Exception {

        mockMvc.perform(get("/list/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("listform"));
    }

    @Test
    void updateList() throws Exception{

        ListCommand listCommand = new ListCommand();

        //when
        when(listOfTaskService.findCommandById(anyLong())).thenReturn(listCommand);

        //then
        mockMvc.perform(get("/list/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("listform"));
    }

    @Test
    void testSaveOrUpdate() throws Exception {

        ListCommand command = new ListCommand();
        command.setId(3L);

        when(listOfTaskService.saveListCommand(any())).thenReturn(command);

        mockMvc.perform(post("/list")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

    }

    @Test
    void testDelete() throws Exception {

           mockMvc.perform(get("/list/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

           verify(listOfTaskService, times(1)).deleteById(anyLong());
    }

}