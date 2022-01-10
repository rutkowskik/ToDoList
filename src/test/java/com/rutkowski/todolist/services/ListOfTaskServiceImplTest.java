package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.converter.ListCommandToList;
import com.rutkowski.todolist.command.converter.ListToListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListOfTaskServiceImplTest {

    ListOfTaskServiceImpl listOfTaskService;
    @Mock
    ListCommandToList listCommandToList;
    @Mock
    ListToListCommand listToListCommand;
    @Mock
    ListOfTasksRepository listOfTasksRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        listOfTaskService = new ListOfTaskServiceImpl(listOfTasksRepository, listCommandToList, listToListCommand);
    }

    @Test
    void getListsOfTasks() {

        ListOfTasks list = new ListOfTasks();
        HashSet<ListOfTasks> listData = new HashSet<>();
        listData.add(list);

        when (listOfTasksRepository.findAll()).thenReturn(listData);

        Set<ListOfTasks> listOfTasks = listOfTaskService.getListsOfTasks();

        assertEquals(listOfTasks.size(), 1);
        verify(listOfTasksRepository, times(1)).findAll();

    }
}