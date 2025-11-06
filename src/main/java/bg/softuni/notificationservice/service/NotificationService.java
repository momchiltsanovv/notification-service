package bg.softuni.notificationservice.service;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.model.NotificationStatus;
import bg.softuni.notificationservice.repository.NotificationRepository;
import bg.softuni.notificationservice.web.dto.NotificationRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

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
}
