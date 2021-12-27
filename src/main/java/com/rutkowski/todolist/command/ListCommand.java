package com.rutkowski.todolist.command;


import com.rutkowski.todolist.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ListCommand {
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private Set<Task> tasks = new HashSet<>();
}



