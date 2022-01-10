package com.rutkowski.todolist.command;


import com.rutkowski.todolist.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ListCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String title;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    private Boolean done;
    private Set<Task> tasks = new HashSet<>();
}



