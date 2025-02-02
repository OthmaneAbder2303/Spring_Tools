package ma.mycomp.demo.exception;

import java.time.LocalDateTime;

public class ResourceNotFoundDetails {

    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String description;
    private String developerMessage;

    // Private constructor to enforce builder usage
    private ResourceNotFoundDetails(LocalDateTime timestamp, int status, String title, String description, String developerMessage) {
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

    public static ResourceNotFoundDetailsBuilder builder() {
        return new ResourceNotFoundDetailsBuilder();
    }

    public static class ResourceNotFoundDetailsBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String description;
        private String developerMessage;

        public ResourceNotFoundDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ResourceNotFoundDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ResourceNotFoundDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ResourceNotFoundDetailsBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ResourceNotFoundDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            return new ResourceNotFoundDetails(this.timestamp, this.status, this.title, this.description, this.developerMessage);
        }
    }
}
