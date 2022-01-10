package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandToListTest {

    public static final String DESCRIPTION = "descriprion";
    public static final Long LONG_VALUE = 1L;

    ListCommandToList converter;

    @BeforeEach
    void setUp() {
        converter = new ListCommandToList();
    }

    @Test
    void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new ListCommand()));
    }

    @Test
    void convert() {
        //given
        ListCommand command = new ListCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        ListOfTasks list = converter.convert(command);

        //then
        assertNotNull(list);
        assertEquals(LONG_VALUE, list.getId());
        assertEquals(DESCRIPTION, list.getDescription());
    }
}