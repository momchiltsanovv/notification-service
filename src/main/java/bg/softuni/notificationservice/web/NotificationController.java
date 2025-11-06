package bg.softuni.notificationservice.web;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.service.NotificationService;
import bg.softuni.notificationservice.web.dto.NotificationRequest;
import bg.softuni.notificationservice.web.dto.NotificationResponse;
import bg.softuni.notificationservice.web.mapper.DtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {


    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {

        Notification notification = notificationService.send(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(DtoMapper.from(notification));

    }

//    TODO make a endpoint ot send email when user post an item 
}
