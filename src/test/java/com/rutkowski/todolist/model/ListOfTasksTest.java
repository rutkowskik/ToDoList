package com.rutkowski.todolist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ListOfTasksTest {

    ListOfTasks listOfTasks;

    @BeforeEach
    public void setUp(){
        listOfTasks = new ListOfTasks();
    }

    @Test
    void getId() {
        Long idValue = 4L;

        listOfTasks.setId(idValue);

        assertEquals(idValue, listOfTasks.getId());
    }

    @Test
    void getTasks() {
        Set<Task> tasks = new HashSet<>();

        listOfTasks.setTasks(tasks);

        assertEquals(tasks.size(), listOfTasks.getTasks().size());
    }
}