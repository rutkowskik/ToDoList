package com.rutkowski.todolist.services;

import com.rutkowski.todolist.command.TaskCommand;
import com.rutkowski.todolist.command.converter.TaskCommandToTask;
import com.rutkowski.todolist.command.converter.TaskToTaskCommand;
import com.rutkowski.todolist.model.ListOfTasks;
import com.rutkowski.todolist.model.Task;
import com.rutkowski.todolist.repositories.ListOfTasksRepository;
import com.rutkowski.todolist.repositories.TasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    private final TaskCommandToTask taskCommandToTask;
    private final TaskToTaskCommand taskToTaskCommand;

    @Mock
    TasksRepository tasksRepository;

    @Mock
    ListOfTasksRepository listOfTasksRepository;

    @Mock
    TaskService taskService;

    public TaskServiceImplTest(){
        this.taskCommandToTask = new TaskCommandToTask();
        this.taskToTaskCommand = new TaskToTaskCommand();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        taskService = new TaskServiceImpl(tasksRepository, taskCommandToTask,
                taskToTaskCommand, listOfTasksRepository);
    }

    @Test
    void saveTaskCommand() {
        //given
        TaskCommand command = new TaskCommand();
        command.setId(3L);
        command.setListId(2L);

        Optional<ListOfTasks> listOfTasksOptional = Optional.of(new ListOfTasks());

        ListOfTasks savedList = new ListOfTasks();
        savedList.addTask(new Task());
        savedList.getTasks().iterator().next().setId(3L);

        when(listOfTasksRepository.findById(anyLong())).thenReturn(listOfTasksOptional);
        when(listOfTasksRepository.save(any())).thenReturn(savedList);

        //when
        TaskCommand savedCommand = taskService.saveTaskCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(listOfTasksRepository, times(1)).findById(anyLong());
        verify(listOfTasksRepository, times(1)).save(any(ListOfTasks.class));
    }

    @Test
    void findByListIdAndTaskId() {
        ListOfTasks listOfTasks = new ListOfTasks();
        listOfTasks.setId(1L);

        Task task = new Task();
        task.setId(1L);

        Task task1 = new Task();
        task.setId(1L);

        Task task2 = new Task();
        task.setId(3L);

        listOfTasks.addTask(task);
        listOfTasks.addTask(task1);
        listOfTasks.addTask(task2);

        Optional<ListOfTasks> listOfTasksOptional = Optional.of(listOfTasks);
        //when
        when(listOfTasksRepository.findById(anyLong())).thenReturn(listOfTasksOptional);

        TaskCommand taskCommand = taskService.findByListIdAndTaskId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), taskCommand.getId());
        assertEquals(Long.valueOf(1L), taskCommand.getListId());
        verify(listOfTasksRepository, times(1)).findById(anyLong());

    }
}