package start.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import start.exception.exceptions.NotLikeException;


@ControllerAdvice
public class LikeExceptionHandler {

    @ExceptionHandler(NotLikeException.class)
    public ResponseEntity<?> LikeHandler(NotLikeException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
