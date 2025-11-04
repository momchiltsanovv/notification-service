package bg.softuni.notificationservice.web;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.service.NotificationService;
import bg.softuni.notificationservice.web.dto.NotificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {


    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request) {


        Notification notification =  notificationService.send(request);

        return ResponseEntity.ok(null);
    }

}
