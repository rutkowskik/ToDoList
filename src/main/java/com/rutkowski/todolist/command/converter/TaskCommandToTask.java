package com.rutkowski.todolist.command.converter;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.model.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandToTask implements Converter<TaskCommand, Task> {


    @Nullable
    @Override
    public Task convert(TaskCommand source) {
        if(source ==null){
            return null;
        }

        final Task task = new Task();
        task.setId(source.getId());
        if (source.getListId() !=null){
            ListOfTasks list = new ListOfTasks();
            list.setId(source.getListId());
            task.setListOfTasks(list);
            list.addTask(task);
        }
        task.setTitle(source.getTitle());
        task.setDescription(source.getDescription());
        task.setDone(source.getDone());
        task.setListOfTasks(source.getListOfTasks());
        return task;
    }
}
