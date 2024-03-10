package ru.todolist.manager.todolistmanager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.Date;

@Data
public class CreateTaskRequest {
    @NotNull
    @Size(min=1,max = 25, message = "Age should not be less than 18")
    private String title;
    @NotNull
    @Size(min=1,max = 25)
    private String description;
    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotNull
//    @Pattern(regexp = "^(0?[1-9]|1[0-2])\\/(0?[1-9]|1\\d|2\\d|3[01])\\/(19|20)\\d{2}$",
//        message = "Дата должна быть в формате MM/DD/YYYY")
    private Date date;
    private boolean completed;
}
