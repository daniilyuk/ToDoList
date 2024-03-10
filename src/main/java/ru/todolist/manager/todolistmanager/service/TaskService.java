package ru.todolist.manager.todolistmanager.service;

import ru.todolist.manager.todolistmanager.dto.CreateTaskRequest;
import ru.todolist.manager.todolistmanager.dto.TaskDto;
import java.util.Date;
import java.util.List;

public interface TaskService {
    TaskDto createTask(CreateTaskRequest request);
    TaskDto updateTask(int id, CreateTaskRequest request);
    void deleteTask(int id);
    TaskDto getTaskById(int id);
    List<TaskDto> getAllTasks();
    List<TaskDto> getTasksByStatus(boolean completed);
    List<TaskDto> getTasksByDateAndStatus(Date date, boolean completed);
}
