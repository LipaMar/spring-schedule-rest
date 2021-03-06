package lipamar.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsRestControllerAdvice {
    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<String> handleValidationError(ValidationFailedException ex){
        return new ResponseEntity<>(ex.getErrorsAsJSON(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
