package com.example.rabitmq_project.Consumer;

import com.example.rabitmq_project.Entity.Utilisateurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final JavaMailSender mailSender;

    // Injection du service d'envoi d'email de Spring
    public Consumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = {"${app.rabbitmq.queue-name}"})
    public void consume(Utilisateurs user) {
        LOGGER.info(" -> Message reçu de RabbitMQ pour l'envoi d'un email à {}", user.getPrenom());

        try {
            // Configuration de l'email
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getAdresse()); // On utilise le champ adresse (assurez-vous d'y mettre un vrai email dans Postman)
            mail.setSubject("Bienvenue sur notre plateforme !");
            mail.setText("Bonjour " + user.getPrenom() + " " + user.getNom() + ",\n\n" +
                    "Votre compte a été créé avec succès.\n" +
                    "Votre ID utilisateur est : " + user.getId());

            // Envoi de l'email
            mailSender.send(mail);
            LOGGER.info(" => Email de bienvenue envoyé avec succès à : {}", user.getAdresse());

        } catch (Exception e) {
            LOGGER.error(" /!\\ Échec de l'envoi de l'email pour l'utilisateur ID: {} . Erreur : {}", user.getId(), e.getMessage());
        }
    }
}
