package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.model.Task;
import org.aspectj.lang.annotation.DeclareError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCommandToTaskTest {
    public static final String DESCRIPTION = "descriprion";
    public static final Long LONG_VALUE = 1L;

    TaskCommandToTask converter;

    @BeforeEach
    void setUp() {
        converter = new TaskCommandToTask();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new TaskCommand()));
    }

    @Test
    void convert() {
        //given
        TaskCommand command = new TaskCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);
        //when
        Task task = converter.convert(command);
        //given
        assertNotNull(task);
        assertEquals(LONG_VALUE, task.getId());
        assertEquals(DESCRIPTION, task.getDescription());
    }
}