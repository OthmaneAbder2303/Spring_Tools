package ma.mycomp.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ma.mycomp.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException e) {

        ExceptionDetails details = ResourceNotFoundDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .description(e.getMessage())
                .developerMessage(e.getClass().getName()).build();

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationExceptionDetails details = ValidationExceptionDetails.validationBuilder()  // Use custom builder
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Validation Failed")
                .description(e.getMessage())
                .developerMessage(e.getClass().getName())
                .field(fields)
                .fieldMessage(fieldsMessage)
                .build();

        log.info("Fields error{}", e.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }


}

