package ru.todolist.manager.todolistmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.todolist.manager.todolistmanager.dto.CreateTaskRequest;
import ru.todolist.manager.todolistmanager.dto.TaskDto;
import ru.todolist.manager.todolistmanager.entity.Task;
import ru.todolist.manager.todolistmanager.mapper.TaskMapper;
import ru.todolist.manager.todolistmanager.repositories.TaskRepository;
import ru.todolist.manager.todolistmanager.service.TaskService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        Task task = taskMapper.to(request);
        return taskMapper.from(taskRepository.save(task));
    }

    @Override
    public TaskDto updateTask(int id, CreateTaskRequest request) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setDate(request.getDate());
        existingTask.setCompleted(request.isCompleted());
        return taskMapper.from(taskRepository.save(existingTask));
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(int id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new NullPointerException("Пользователь не найден"));
        return taskMapper.from(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::from).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByStatus(boolean completed) {
        List<Task> tasks = taskRepository.findByCompleted(completed);
        return tasks.stream().map(taskMapper::from).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByDateAndStatus(Date date, boolean completed) {
        List<Task> tasks = taskRepository.findByDateAndCompleted(date, completed);
        return tasks.stream().map(taskMapper::from).collect(Collectors.toList());
    }
}