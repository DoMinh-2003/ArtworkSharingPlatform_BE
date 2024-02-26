package start.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import start.exception.exceptions.InValidEmail;

@ControllerAdvice
public class InValidEmailHandler {
    @ExceptionHandler(InValidEmail.class)
    public ResponseEntity<?> invalid(InValidEmail exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
