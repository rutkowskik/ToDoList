package com.rutkowski.todolist.services;

import com.rutkowski.todolist.model.Task;

import java.util.Set;

public interface TaskService {

    Set<Task> getListOfAllTasks();

    Task findById(Long id);
}
