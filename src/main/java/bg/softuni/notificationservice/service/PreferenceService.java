package bg.softuni.notificationservice.service;

import bg.softuni.notificationservice.model.NotificationPreference;
import bg.softuni.notificationservice.model.NotificationType;
import bg.softuni.notificationservice.repository.NotificationPreferenceRepository;
import bg.softuni.notificationservice.web.dto.PreferenceRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PreferenceService {

    private final NotificationPreferenceRepository preferenceRepository;

    public PreferenceService(NotificationPreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public NotificationPreference upsert(PreferenceRequest request) {

        Optional<NotificationPreference> preferenceOptional = preferenceRepository.findByUserId(request.getUserId());

        if (preferenceOptional.isPresent()) {
            NotificationPreference preference = preferenceOptional.get();
            preference.setContactInfo(request.getContactInfo());
            return preferenceRepository.save(preference);
        }

        NotificationPreference preference = NotificationPreference.builder()
                                                                  .userId(request.getUserId())
                                                                  .type(NotificationType.EMAIL)
                                                                  .contactInfo(request.getContactInfo())
                                                                  .build();
        return preferenceRepository.save(preference);
    }

    public NotificationPreference getByUserId(UUID userId) {
        return preferenceRepository.findByUserId(userId)
                                   .orElseThrow(() -> new RuntimeException("Preference for this user does not exist"));
    }
}
