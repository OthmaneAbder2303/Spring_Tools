package ma.mycomp.demo.exception;

import java.time.LocalDateTime;

public class ResourceNotFoundDetails extends ExceptionDetails {

    public ResourceNotFoundDetails(LocalDateTime timestamp, int status, String title, String detail, String developerMessage) {
        super(timestamp, status, title, detail, developerMessage);
    }

}

