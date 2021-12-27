package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.ListCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ListToListCommand implements Converter<ListOfTasks, ListCommand> {
    @Override
    public ListCommand convert(ListOfTasks source) {
        if(source == null){
            return null;
        }

        final ListCommand listCommand = new ListCommand();

        listCommand.setId(source.getId());
        listCommand.setTitle(source.getTitle());
        listCommand.setDescription(source.getDescription());
        listCommand.setDone(source.getDone());
        listCommand.setTasks(source.getTasks());
        return listCommand;
    }
}
