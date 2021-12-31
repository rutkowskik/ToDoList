package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.command.converter.ListCommandToList;
import com.rutkowski.todolist.command.converter.ListToListCommand;
import com.rutkowski.todolist.exception.NotFoundException;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class ListOfTaskServiceImpl implements ListOfTaskService {

    private final ListOfTasksRepository listOfTasksRepository;
    private final ListCommandToList listCommandToList;
    private final ListToListCommand listToListCommand;



    public ListOfTaskServiceImpl(ListOfTasksRepository listOfTasksRepository, ListCommandToList listCommandToList, ListToListCommand listToListCommand) {
        this.listOfTasksRepository = listOfTasksRepository;
        this.listCommandToList = listCommandToList;
        this.listToListCommand = listToListCommand;
    }

    @Override
    public Set<ListOfTasks> getListsOfTasks() {

        Set<ListOfTasks> listOfTasks = new HashSet<>();

        listOfTasksRepository.findAll().iterator().forEachRemaining(listOfTasks::add);
        return listOfTasks;
    }

    public ListOfTasks save(ListOfTasks listOfTasks){
        return listOfTasksRepository.save(listOfTasks);
    }

    public void deleteById(Long id){
        listOfTasksRepository.deleteById(id);
    }

    @Override
    public ListCommand saveListCommand(ListCommand listCommand) {
        ListOfTasks detachList = listCommandToList.convert(listCommand);

        ListOfTasks saveList =listOfTasksRepository.save(detachList);
        log.debug("Saved listID: " + saveList.getId());
        return listToListCommand.convert(saveList);

    }
    @Override
    public ListOfTasks findById(Long id) {
        Optional<ListOfTasks> listOfTasksOptional = listOfTasksRepository.findById(id);

        if(!listOfTasksOptional.isPresent()){

            throw new NotFoundException("List not found: "+ id.toString());
        }
        return listOfTasksOptional.get();

    }

    @Override
    @Transactional
    public ListCommand findCommandById(Long l) {
        return listToListCommand.convert(findById(l));
    }


}