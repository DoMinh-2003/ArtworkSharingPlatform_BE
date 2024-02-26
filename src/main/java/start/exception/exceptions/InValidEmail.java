package start.exception.exceptions;

public class InValidEmail extends RuntimeException{
    public InValidEmail(String message) {
        super(message);
    }
}
