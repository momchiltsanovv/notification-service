package bg.softuni.notificationservice.web;

import bg.softuni.notificationservice.model.NotificationPreference;
import bg.softuni.notificationservice.service.PreferenceService;
import bg.softuni.notificationservice.web.dto.PreferenceRequest;
import bg.softuni.notificationservice.web.dto.PreferenceResponse;
import bg.softuni.notificationservice.web.mapper.DtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @PostMapping
    public ResponseEntity<PreferenceResponse> upsertPreference(@RequestBody PreferenceRequest request) {

        NotificationPreference preference = preferenceService.upsert(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(DtoMapper.from(preference));
    }

}
