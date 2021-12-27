package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import java.util.Set;

public interface ListOfTaskService {

    Set<ListOfTasks> getListsOfTasks();
    void deleteById(Long id);

    ListCommand saveListCommand(ListCommand listCommand);
}
