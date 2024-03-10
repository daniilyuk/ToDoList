package ru.todolist.manager.todolistmanager.exception;








import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InValidDataException.class)
    public ResponseEntity<Response> handleException(InValidDataException e){
        Response response=new Response(e.getMessage());
        ResponseEntity<Response> responseEntity= new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
