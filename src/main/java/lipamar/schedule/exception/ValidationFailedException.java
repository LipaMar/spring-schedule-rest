package lipamar.schedule.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationFailedException extends RuntimeException {
    private final List<ObjectError> errors;
    public ValidationFailedException() {
        super("Validation failed");
        errors = new ArrayList<>();
    }

    public ValidationFailedException(List<ObjectError> errors) {
        this.errors = errors;
    }
    public String getErrorsAsJSON(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(errors);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
