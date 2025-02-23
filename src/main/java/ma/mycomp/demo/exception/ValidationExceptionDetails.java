package ma.mycomp.demo.exception;

import lombok.Data;
import lombok.Getter;
import java.time.LocalDateTime;

@Data
public class ValidationExceptionDetails {

    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String description;
    private String developerMessage;
    private String field;
    private String fieldMessage;

    // Constructor
    public ValidationExceptionDetails(LocalDateTime timestamp, int status, String title,
                                      String description, String developerMessage, String field,
                                      String fieldMessage) {
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
        this.description = description;
        this.developerMessage = developerMessage;
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

    // Static builder class
    public static class ValidationExceptionDetailsBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String description;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        // Builder methods for each field
        public ValidationExceptionDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidationExceptionDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ValidationExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ValidationExceptionDetailsBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ValidationExceptionDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ValidationExceptionDetailsBuilder field(String field) {
            this.field = field;
            return this;
        }

        public ValidationExceptionDetailsBuilder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationExceptionDetails build() {
            return new ValidationExceptionDetails(timestamp, status, title, description, developerMessage, field, fieldMessage);
        }
    }

    public static ValidationExceptionDetailsBuilder validationBuilder() {
        return new ValidationExceptionDetailsBuilder();
    }
}
