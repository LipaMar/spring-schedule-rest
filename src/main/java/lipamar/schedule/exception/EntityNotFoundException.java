package lipamar.schedule.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.parser.Entity;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity not found");
    }
    public EntityNotFoundException(String className){
        super("Requested entity of class:"+className+" was not found");
    }
}
