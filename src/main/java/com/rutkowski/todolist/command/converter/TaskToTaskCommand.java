package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.model.Task;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskCommand implements Converter<Task, TaskCommand> {

    @Synchronized
    @Nullable
    @Override
    public TaskCommand convert(Task source) {
        if(source == null){
            return null;
        }
        final TaskCommand taskCommand = new TaskCommand();
        taskCommand.setId(source.getId());
        if(source.getListOfTasks()!=null){
            taskCommand.setListId(source.getListOfTasks().getId());
        }
        taskCommand.setTitle(source.getTitle());
        taskCommand.setDescription(source.getDescription());
        taskCommand.setDone(source.getDone());
        taskCommand.setListOfTasks(source.getListOfTasks());
        return taskCommand;
    }
}
