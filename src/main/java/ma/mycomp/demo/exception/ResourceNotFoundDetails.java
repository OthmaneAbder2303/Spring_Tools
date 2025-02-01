package ma.mycomp.demo.exception;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundDetails {

    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String description;
    private String developerMessage;
}