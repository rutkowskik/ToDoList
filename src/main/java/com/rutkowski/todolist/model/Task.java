package com.rutkowski.todolist.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Setter
@Getter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean done = false;
    @ManyToOne
    private ListOfTasks listOfTasks;


    public Task(){
    }

    public Task(Long id, String title, String description, Boolean done, ListOfTasks listOfTasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.listOfTasks = listOfTasks;

    }

}
