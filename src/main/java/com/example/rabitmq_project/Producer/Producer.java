package com.example.rabitmq_project.Producer;


import com.example.rabitmq_project.Entity.Utilisateurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class Producer {

   private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Value("${app.rabbitmq.exchange-name}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    public void sendMessage( Utilisateurs user ){
        LOGGER.info("Envoi de l'événement de commande vers RabbitMQ -> ID: {}, Nom: {}, Prénom: {}, Adresse: {}",
                user.getId(), user.getNom(), user.getPrenom(), user.getAdresse());

        // convertAndSend s'occupe de transformer l'objet en JSON et de l'expédier
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }





}
