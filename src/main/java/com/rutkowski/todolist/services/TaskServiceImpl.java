package com.rutkowski.todolist.services;

import com.rutkowski.todolist.model.Task;
import com.rutkowski.todolist.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private final TasksRepository tasksRepository;

    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }
    @Override
    public Set<Task> getListOfAllTasks(){

        Set<Task> tasks = new HashSet<>();
        tasksRepository.findAll().iterator().forEachRemaining(tasks::add);
        return tasks;
    }

    @Override
    public Task findById(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    public Task save(Task task){
        return tasksRepository.save(task);
    }

    public void deleteById(Long id){
        tasksRepository.deleteById(id);
    }

    public Task findByListOfTasksId(Long id){
        return tasksRepository.findByListOfTasksId(id).orElse(null);
    }
}
