package be.pxl.services.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Lomnok zorgt voor zaken als getter sen setters
public class Notification {
    private Long id;

    private String from;

    private String subject;

    private String message;
}