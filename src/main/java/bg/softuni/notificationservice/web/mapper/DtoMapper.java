package bg.softuni.notificationservice.web.mapper;

import bg.softuni.notificationservice.model.NotificationPreference;
import bg.softuni.notificationservice.web.dto.PreferenceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static PreferenceResponse from(NotificationPreference preference) {

        return PreferenceResponse.builder()
                                 .type(preference.getType())
                                 .contactInfo(preference.getContactInfo())
                                 .build();

    }
}
