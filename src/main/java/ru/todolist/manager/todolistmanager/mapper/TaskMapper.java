package ru.todolist.manager.todolistmanager.mapper;

import org.springframework.stereotype.Component;
import ru.todolist.manager.todolistmanager.dto.CreateTaskRequest;
import ru.todolist.manager.todolistmanager.dto.TaskDto;
import ru.todolist.manager.todolistmanager.entity.Task;

@Component
public class TaskMapper {
//    public TaskDto to(CreateTaskRequest request){
//        return TaskDto.builder()
//                .title(request.getTitle())
//                .description(request.getDescription())
//                .completed(request.isCompleted())
//                .date(request.getDate())
//                .build();
//    }
    public TaskDto from(Task task){
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .date(task.getDate())
                .completed(task.isCompleted())
                .build();
    }
    public Task to(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDate(request.getDate());
        task.setCompleted(request.isCompleted());
        return task;
    }
}
