package com.rutkowski.todolist.controllers;

import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.services.ListOfTaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    ListOfTaskService listOfTaskService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        indexController = new IndexController(listOfTaskService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //given
        Set<ListOfTasks> listOfTasks = new HashSet<>();
        listOfTasks.add(new ListOfTasks());
        listOfTasks.add(new ListOfTasks());

        when(listOfTaskService.getListsOfTasks()).thenReturn(listOfTasks);

        ArgumentCaptor<Set<ListOfTasks>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        assertEquals("index",viewName);
        verify(listOfTaskService, times(1)).getListsOfTasks();
        verify(model, times(1)).addAttribute(eq("lists"), argumentCaptor.capture());
        Set<ListOfTasks> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }
}