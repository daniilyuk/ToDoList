package ru.todolist.manager.todolistmanager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.todolist.manager.todolistmanager.dto.CreateTaskRequest;
import ru.todolist.manager.todolistmanager.dto.TaskDto;
import ru.todolist.manager.todolistmanager.exception.InValidDataException;
import ru.todolist.manager.todolistmanager.service.impl.TaskServiceImpl;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/tasks")
@Validated
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;

    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequest request,
                                              BindingResult result) {
        Date currentDate=new Date();
        if(request.getDate().before(currentDate))
        {
            throw new InValidDataException("Ошибка: некорректная дата! Дата должна быть позже текущей", new Exception());
        }
        if(request.getTitle()==null)
        {
            throw new InValidDataException("Ошибка: Пустой заголовок!", new Exception());
        }
        return ResponseEntity.ok(taskServiceImpl.createTask(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable int id, @RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok(taskServiceImpl.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskServiceImpl.deleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable int id) {
        return taskServiceImpl.getTaskById(id);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskServiceImpl.getAllTasks();
    }

    @GetMapping("/status/{completed}")
    public List<TaskDto> getTasksByStatus(@PathVariable boolean completed) {
        return taskServiceImpl.getTasksByStatus(completed);
    }

    @GetMapping("/date/{date}/status/{completed}")
    public List<TaskDto> getTasksByDateAndStatus(@PathVariable Date date, @PathVariable boolean completed) {
        return taskServiceImpl.getTasksByDateAndStatus(date, completed);
    }
}
