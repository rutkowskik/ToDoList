package com.rutkowski.todolist.bootstrap;

import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.model.Task;
import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import com.rutkowski.todolist.repositories.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ListBoostStrap implements ApplicationListener<ContextRefreshedEvent> {

   private final ListOfTasksRepository listOfTasksRepository;
   private final TasksRepository tasksRepository;

    public ListBoostStrap(ListOfTasksRepository listOfTasksRepository, TasksRepository tasksRepository) {
        this.listOfTasksRepository = listOfTasksRepository;
        this.tasksRepository = tasksRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ListOfTasks homeList = new ListOfTasks();
        homeList.setTitle("Preload list");
        homeList.setDescription("Description of List");
        homeList.setDone(false);

        Task vacuum = new Task();
        vacuum.setTitle("Task title");
        vacuum.setDescription("Simple description of task.");
        vacuum.setDone(false);
        vacuum.setListOfTasks(homeList);

        tasksRepository.save(vacuum);
        listOfTasksRepository.save(homeList);

        ListOfTasks workList = new ListOfTasks();
        workList.setTitle("Preload list 2");
        workList.setDescription("Description of List 2");
        workList.setDone(false);

        Task coding = new Task();
        coding.setTitle("Task Title");
        coding.setDescription("Simple description of task.");
        coding.setDone(false);
        coding.setListOfTasks(workList);

        tasksRepository.save(coding);
        listOfTasksRepository.save(workList);

        log.debug("Loading Bootstrap Data");
        System.out.println("---------Data loading...");

    }
}
