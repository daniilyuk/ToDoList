package ru.todolist.manager.todolistmanager.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.todolist.manager.todolistmanager.dto.CreateTaskRequest;


import java.util.Date;

@Component
public class TaskValidator implements Validator {

    private CreateTaskRequest request;

    public TaskValidator() {
    }

    public TaskValidator(CreateTaskRequest request) {
        this.request = request;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTaskRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateTaskRequest request=(CreateTaskRequest) target;



        if (request.getDate() == null) {
            errors.rejectValue("date", "NotEmpty", "Date is required");
            throw new NullPointerException();
        } else {
            Date currentDate = new Date();
            if (request.getDate().before(currentDate)) {
                errors.rejectValue("date", "Invalid", "Date must be in the future");
            }
        }
    }
}
