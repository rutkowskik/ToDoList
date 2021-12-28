package com.rutkowski.todolist.services;

import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListOfTaskServiceImplTest {

    ListOfTaskServiceImpl listOfTaskService;

    @Mock
    ListOfTasksRepository listOfTasksRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

            }

    @Test
    void getListsOfTasks() {
    }
}