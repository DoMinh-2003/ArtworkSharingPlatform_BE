package start.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import start.exception.exceptions.RefuseAuthorizeOrder;

@ControllerAdvice
public class RefuseAuthorizeOrderHandler {

    @ExceptionHandler(RefuseAuthorizeOrder.class)
    public ResponseEntity<?> refuseAuthorizeOrder(RefuseAuthorizeOrder  exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
