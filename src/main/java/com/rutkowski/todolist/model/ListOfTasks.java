package com.rutkowski.todolist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "lists")
public class ListOfTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listOfTasks")
    private Set<Task> tasks = new HashSet<>();

    public ListOfTasks() {
    }

    public ListOfTasks(Long id, String title, String description, Boolean done, Set<Task> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.tasks = tasks;
    }

    public ListOfTasks addTask(Task task){
        task.setListOfTasks(this);
        this.tasks.add(task);
        return this;
    }

}
