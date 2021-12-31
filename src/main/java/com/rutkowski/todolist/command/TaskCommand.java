package com.rutkowski.todolist.command;

import com.rutkowski.todolist.model.ListOfTasks;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskCommand {
    private Long id;
    @NotNull
    private String title;
    private String description;
    private Boolean done = false;
    private ListOfTasks listOfTasks;
    private Long listId;

    public Long getListId() {
        return listId;
    }
}
