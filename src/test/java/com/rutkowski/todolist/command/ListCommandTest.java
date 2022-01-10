package com.rutkowski.todolist.command;

import com.rutkowski.todolist.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    ListCommand listCommand;
    Long ID = 1L;

    @BeforeEach
    void setUp() {
        listCommand = new ListCommand();
    }

    @Test
    void getId() {
        listCommand.setId(ID);

        assertEquals(ID, listCommand.getId());
    }

    @Test
    void getTasks() {
        Task task = new Task();
        HashSet<Task> tasks = new HashSet<>();
        tasks.add(task);

        assertEquals(1, tasks.size());

    }
}