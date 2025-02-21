package ma.mycomp.demo.handler;

import lombok.extern.slf4j.Slf4j;
import ma.mycomp.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // this is advice for multiple controllers,
// you're telling controllers that if they find what we're gonna to print here just type this as the default
@Slf4j
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ValidationExceptionDetails details = ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Resource Not Found")
                .description(e.getMessage())
                .developerMessage(e.getClass().getName()).build();

        log.info("Fields error{}",e.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
