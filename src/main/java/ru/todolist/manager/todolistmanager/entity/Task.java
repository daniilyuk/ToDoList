package ru.todolist.manager.todolistmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
public class Task {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private Date date;
    private boolean completed;
}
