package bg.softuni.notificationservice.web.dto;

import bg.softuni.notificationservice.model.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreferenceResponse {

    private NotificationType type;

    private String contactInfo;


}
