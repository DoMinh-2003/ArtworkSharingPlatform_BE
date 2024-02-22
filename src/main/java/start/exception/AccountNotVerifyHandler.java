package start.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import start.exception.exceptions.AccountNotVerify;

@ControllerAdvice
public class AccountNotVerifyHandler {
    @ExceptionHandler(AccountNotVerify.class)
    public ResponseEntity<?> duplicate(AccountNotVerify   exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
