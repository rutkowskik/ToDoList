package com.rutkowski.todolist.repositories;

import com.rutkowski.todolist.model.ListOfTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ListOfTasksRepository extends CrudRepository<ListOfTasks, Long> {

    Optional<ListOfTasks> findByDescription(String description);

    void deleteById(Long id);

}
