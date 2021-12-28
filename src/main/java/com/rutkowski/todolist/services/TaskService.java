package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.model.Task;

import java.util.Set;

public interface TaskService {

    Set<Task> getListOfAllTasks();

    Task findById(Long id);

    TaskCommand saveTaskCommand(TaskCommand taskCommand);

    TaskCommand findByListIdAndTaskId(Long listId, Long taskId);

    void deleteById(Long id);

}
