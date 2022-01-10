package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskToTaskCommandTest {
    public static final String DESCRIPTION = "descriprion";
    public static final Long LONG_VALUE = 1L;

    TaskToTaskCommand converter;

    @BeforeEach
    void setUp() {
        converter = new TaskToTaskCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Task()));
    }

    @Test
    void convert() {
        //given
        Task task = new Task();
        task.setId(LONG_VALUE);
        task.setDescription(DESCRIPTION);
        //when
        TaskCommand command = converter.convert(task);
        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}