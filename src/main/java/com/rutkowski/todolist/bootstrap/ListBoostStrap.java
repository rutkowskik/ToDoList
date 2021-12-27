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

   private ListOfTasksRepository listOfTasksRepository;
   private TasksRepository tasksRepository;

    public ListBoostStrap(ListOfTasksRepository listOfTasksRepository, TasksRepository tasksRepository) {
        this.listOfTasksRepository = listOfTasksRepository;
        this.tasksRepository = tasksRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ListOfTasks homeList = new ListOfTasks();
        homeList.setTitle("Cleaning");
        homeList.setDescription("You have to clean your home regularly!");
        homeList.setDone(false);

        Task vacuum = new Task();
        vacuum.setTitle("vacuum");
        vacuum.setDescription("Vacuum all!");
        vacuum.setDone(false);
        vacuum.setListOfTasks(homeList);

        tasksRepository.save(vacuum);
        listOfTasksRepository.save(homeList);

        ListOfTasks workList = new ListOfTasks();
        workList.setTitle("Working");
        workList.setDescription("You have to work!");
        workList.setDone(false);

        Task coding = new Task();
        coding.setTitle("Coding");
        coding.setDescription("Code until death!");
        coding.setDone(false);
        coding.setListOfTasks(workList);

        tasksRepository.save(coding);
        listOfTasksRepository.save(workList);

        log.debug("Loading Bootstrap Data");
        System.out.println("---------Data loading...");

    }
}
