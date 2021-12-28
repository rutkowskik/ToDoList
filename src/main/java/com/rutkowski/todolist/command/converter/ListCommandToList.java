package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ListCommandToList implements Converter<ListCommand, ListOfTasks> {

    @Synchronized
    @Nullable
    @Override
    public ListOfTasks convert(ListCommand source) {
        if(source == null){
            return null;
        }

        final ListOfTasks listOfTask = new ListOfTasks();
        listOfTask.setId(source.getId());
        listOfTask.setTitle(source.getTitle());
        listOfTask.setDescription(source.getDescription());
        listOfTask.setDone(source.getDone());
        listOfTask.setTasks(source.getTasks());

        return listOfTask;
    }

}
