package ma.mycomp.demo.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Data
@NoArgsConstructor
public class ExceptionDetails {

    // Getters
    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String description;
    private String developerMessage;

    // Private constructor to enforce builder usage
    public ExceptionDetails(LocalDateTime timestamp, int status, String title, String description, String developerMessage) {
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
        this.description = description;
        this.developerMessage = developerMessage;
    }

    public static ExceptionDetails.ExceptionDetailsBuilder builder() {
        return new ExceptionDetails.ExceptionDetailsBuilder();
    }

    public static class ExceptionDetailsBuilder {
        protected LocalDateTime timestamp;
        protected int status;
        protected String title;
        protected String description;
        protected String developerMessage;

        public ExceptionDetails.ExceptionDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionDetails.ExceptionDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ExceptionDetails.ExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ExceptionDetails.ExceptionDetailsBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ExceptionDetails.ExceptionDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ExceptionDetails build() {
            return new ExceptionDetails(this.timestamp, this.status, this.title, this.description, this.developerMessage);
        }
    }
}
