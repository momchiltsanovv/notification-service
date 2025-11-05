package bg.softuni.notificationservice.web.dto;

import bg.softuni.notificationservice.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotificationRequest {

    @NotNull
    private UUID userId;

    @NotBlank
    private String contactInfo;

    @NotNull
    private NotificationType type;

    private String subject;

    private String body;
}
