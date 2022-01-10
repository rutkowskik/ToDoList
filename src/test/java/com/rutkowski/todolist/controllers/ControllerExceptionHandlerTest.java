//package com.rutkowski.todolist.controllers;
//
//import com.rutkowski.todolist.exception.NotFoundException;
//import com.rutkowski.todolist.services.ListOfTaskService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//class ControllerExceptionHandlerTest {
//
//    @Mock
//    ListOfTaskService listOfTaskService;
//
//    IndexController indexController;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        indexController = new IndexController(listOfTaskService);
//        mockMvc = MockMvcBuilders.standaloneSetup(indexController)
//                .setControllerAdvice(new ControllerExceptionHandler())
//                .build();
//    }
//
//    @Test
//    void handeNotFound() throws Exception{
//
//        when(listOfTaskService.findById(anyLong())).thenThrow(NotFoundException.class);
//
//        mockMvc.perform(get("/list/1/tasks"))
//                .andExpect(status().isNotFound())
//                .andExpect(view().name("404error"));
//    }
//    @Test
//    void handeNumberFormat() throws Exception {
//        mockMvc.perform(get("/list/asd/tasks"))
//                .andExpect(status().isBadRequest())
//                .andExpect(view().name("400error"));
//    }
//}