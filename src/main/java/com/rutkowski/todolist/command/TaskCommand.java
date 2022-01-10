package com.rutkowski.todolist.command;

import com.rutkowski.todolist.model.ListOfTasks;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TaskCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 3, max= 255)
    private String description;

    private Boolean done = false;
    private ListOfTasks listOfTasks;
    private Long listId;

    public Long getListId() {
        return listId;
    }
}
