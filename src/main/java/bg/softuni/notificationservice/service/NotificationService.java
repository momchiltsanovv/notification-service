package bg.softuni.notificationservice.service;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.model.NotificationStatus;
import bg.softuni.notificationservice.repository.NotificationRepository;
import bg.softuni.notificationservice.web.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static bg.softuni.notificationservice.model.NotificationType.EMAIL;

@Slf4j
@Service
public class NotificationService {

    public static final String SEMESTER_START_NOTIFICATION = "Semester Start Notification";
    public static final String SEMESTER_START_BODY = "Dear Student,\n\n" +
            "We are excited to welcome you to the new semester! " +
            "Get ready for an enriching academic journey filled with opportunities to learn and grow.\n\n" +
            "If you have any old books or items from previous semester or want new one cheaper from other students for this one be sure to check out our website.\n\n" +
            "Best regards,\n" +
            "The University Team";
    private final NotificationRepository notificationRepository;
    private final MailSender mailSender;

    public NotificationService(NotificationRepository notificationRepository,
                               JavaMailSenderImpl mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    public Notification send(NotificationRequest request) {

        Notification notification = Notification.builder()
                                                .subject(request.getSubject())
                                                .body(request.getBody())
                                                .contactInfo(request.getContactInfo())
                                                .type(request.getType())
                                                .userId(request.getUserId())
                                                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getContactInfo());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getBody());

        try {
            mailSender.send(mailMessage);
            notification.setStatus(NotificationStatus.SUCCEEDED);
        } catch (Exception e) {
            log.error("Failed email die to: {}", e.getMessage());
            notification.setStatus(NotificationStatus.FAILED);

        }

        return notificationRepository.save(notification);
    }

    @Scheduled(cron = "0 0 9 20 9 ?")
    protected void sendSemesterStartNotifications() {
        Map<UUID, String> users = notificationRepository.findAll()
                                                        .stream()
                                                        .collect(Collectors.toMap(Notification::getUserId,
                                                                                  Notification::getContactInfo,
                                                                                  (existing, replacement) -> existing));


        users.forEach(this::request);
        log.info("maps id and email correctly");
    }

    //TODO make method for midterm notifications

    private void request(UUID userId, String contactInfo) {
        NotificationRequest request = NotificationRequest.builder()
                                                         .userId(userId)
                                                         .contactInfo(contactInfo)
                                                         .type(EMAIL)
                                                         .subject(SEMESTER_START_NOTIFICATION)
                                                         .body(SEMESTER_START_BODY)
                                                         .build();

        send(request);
    }


}
