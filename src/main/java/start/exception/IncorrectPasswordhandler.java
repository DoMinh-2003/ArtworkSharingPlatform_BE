package start.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import start.exception.exceptions.IncorrectPassword;


@ControllerAdvice
public class IncorrectPasswordhandler {

    @ExceptionHandler(IncorrectPassword.class)
    public ResponseEntity<?> Incorrect(IncorrectPassword exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
