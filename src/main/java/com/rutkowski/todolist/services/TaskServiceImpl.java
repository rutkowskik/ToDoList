package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.command.converter.TaskCommandToTask;
import com.rutkowski.todolist.command.converter.TaskToTaskCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.model.Task;
import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import com.rutkowski.todolist.repositories.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TasksRepository tasksRepository;
    private final TaskCommandToTask taskCommandToTask;
    private final TaskToTaskCommand taskToTaskCommand;
    private final ListOfTasksRepository listOfTasksRepository;

    public TaskServiceImpl(TasksRepository tasksRepository, TaskCommandToTask taskCommandToTask, TaskToTaskCommand taskToTaskCommand, ListOfTasksRepository listOfTasksRepository) {
        this.tasksRepository = tasksRepository;
        this.taskCommandToTask = taskCommandToTask;
        this.taskToTaskCommand = taskToTaskCommand;
        this.listOfTasksRepository = listOfTasksRepository;
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

    @Override
    @Transactional
    public TaskCommand saveTaskCommand(TaskCommand taskCommand) {
        Optional<ListOfTasks> listOfTasksOptional = listOfTasksRepository.findById(taskCommand.getListId());

        if(!listOfTasksOptional.isPresent()){
            log.error("List not found of id:" + taskCommand.getListId());
            return new TaskCommand();
        }else{
            ListOfTasks listOfTasks = listOfTasksOptional.get();

            Optional<Task> taskOptional = listOfTasks
                    .getTasks()
                    .stream()
                    .filter(task -> task.getId().equals(taskCommand.getId()))
                    .findFirst();
            if(taskOptional.isPresent()){
                Task taskFound = taskOptional.get();
                taskFound.setDescription(taskCommand.getDescription());
                taskFound.setTitle(taskCommand.getTitle());
                //can be problem
                taskFound.setListOfTasks(taskCommand.getListOfTasks());
                taskFound.setDone(taskCommand.getDone());
            }else{
                //add new Task
                Task task = taskCommandToTask.convert(taskCommand);
                task.setListOfTasks(listOfTasks);
                listOfTasks.addTask(task);
            }

            ListOfTasks savedListOfTask = listOfTasksRepository.save(listOfTasks);

            Optional<Task> savedTaskOptional = savedListOfTask.getTasks().stream()
                    .filter(task -> task.getId().equals(taskCommand.getId()))
                    .findFirst();

            if(!savedTaskOptional.isPresent()){
                savedTaskOptional = savedListOfTask.getTasks().stream()
                        .filter(task -> task.getDescription().equals(taskCommand.getDescription()))
                        .filter(task -> task.getTitle().equals(taskCommand.getTitle()))
                        .filter(task -> task.getDone().equals(taskCommand.getDone()))
                        .findFirst();
            }

            return taskToTaskCommand.convert(savedTaskOptional.get());
        }

    }
}
