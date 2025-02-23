package ma.mycomp.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExceptionDetails {

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

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public static ExceptionDetails.ExceptionDetailsBuilder builder() {
        return new ExceptionDetails.ExceptionDetailsBuilder();
    }

    public static class ExceptionDetailsBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String description;
        private String developerMessage;

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
