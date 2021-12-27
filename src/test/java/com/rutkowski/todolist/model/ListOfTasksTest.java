package com.rutkowski.todolist.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListOfTasksTest {

    ListOfTasks listOfTasks;
    @Before
    public void setUp(){
        listOfTasks = new ListOfTasks();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        listOfTasks.setId(idValue);
        assertEquals(idValue,listOfTasks.getId());
    }

    @Test
    public void getTitle() {
    }

    @Test
    public void getDescription() {
    }
}