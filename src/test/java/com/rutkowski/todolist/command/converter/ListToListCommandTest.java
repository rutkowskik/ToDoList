package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListToListCommandTest {
    public static final String DESCRIPTION = "descriprion";
    public static final Long LONG_VALUE = 1L;

    ListToListCommand converter;

    @BeforeEach
    void setUp() {
        converter = new ListToListCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new ListOfTasks()));
    }

    @Test
    void convert() {
        //given
        ListOfTasks listOfTasks = new ListOfTasks();
        listOfTasks.setId(LONG_VALUE);
        listOfTasks.setDescription(DESCRIPTION);
        //when
        ListCommand command = converter.convert(listOfTasks);
        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}