package com.crud.project.notification;

import com.crud.project.domain.Client;
import com.crud.project.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ClientObserver implements Observer {
    private final JavaMailSender mailSender;

    public ClientObserver(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void update(Order order) {
        Client client = order.getClient();
        if (client != null && client.getMail() != null) {
            sendEmail(client, order);
        }
    }

    private void sendEmail(Client client, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(client.getMail());
        message.setSubject("Order Completion Notification");
        message.setText("Dear " + client.getName() + ",\n\nYour order with reference: " + order.getOrderReference() +
                " has been successfully completed.\n\nThank you for using our service!");

        mailSender.send(message);
        System.out.println("Email sent to: " + client.getMail());
    }
}
