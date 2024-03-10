package ru.todolist.manager.todolistmanager.exception;

public class InValidDataException extends RuntimeException{
    public InValidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
