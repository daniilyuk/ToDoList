package ru.todolist.manager.todolistmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class TaskDto {
    private String title;
    private String description;
    private Date date;
    private boolean completed;
}
