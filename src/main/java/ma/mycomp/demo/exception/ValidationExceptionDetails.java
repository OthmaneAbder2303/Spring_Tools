package ma.mycomp.demo.exception;

import java.time.LocalDateTime;

public class ValidationExceptionDetails extends ExceptionDetails {

    private String field;
    private String fieldMessage;

    public ValidationExceptionDetails(LocalDateTime timestamp, int status, String title, String detail, String developerMessage, String field, String fieldMessage) {
        super(timestamp, status, title, detail, developerMessage);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }
}

