package com.rutkowski.todolist.repositories;

import com.rutkowski.todolist.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TasksRepository extends CrudRepository<Task, Long> {

    Optional<Task> findByDescription(String description);
    Optional<Task> findByListOfTasksId(Long id);


}
