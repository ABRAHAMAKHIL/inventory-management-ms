package com.Mangrove.notification_service.service;

import com.Mangrove.order_service.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private final JavaMailSender javaMailSender;

  @KafkaListener(topics = "order-placed")
  public void listen(OrderPlacedEvent orderPlacedEvent) {



    MimeMessagePreparator mimeMessagePreparator =
        mimeMessage -> {
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

          helper.setFrom("akhilabraham34@gmail.com");
          helper.setTo(orderPlacedEvent.getEmail());
          helper.setSubject("Order Confirmation");
          helper.setText("Hi, your order has been placed successfully.", true);
        };

    try {

      javaMailSender.send(mimeMessagePreparator);
      log.info("Email Delivered");


    } catch (MailException e) {

    }
  }
}
