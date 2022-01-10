package com.rutkowski.todolist.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskCommandTest {

    TaskCommand taskCommand;
    Long ID = 1L;

    @BeforeEach
    void setUp() {
        taskCommand = new TaskCommand();
    }

    @Test
    void getId() {
        taskCommand.setId(ID);

        assertEquals(ID, taskCommand.getId());
    }
}