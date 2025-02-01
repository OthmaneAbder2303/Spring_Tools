package ma.mycomp.demo.handler;

import ma.mycomp.demo.exception.ResourceNotFoundDetails;
import ma.mycomp.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // this is advice for multiple controllers, you're telling controllers that if they find what we're gonna to print here just type this as the default
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException e) {
        ResourceNotFoundDetails details = ResourceNotFoundDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .description(e.getMessage())
                .developerMessage(e.getClass().getName()).build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
