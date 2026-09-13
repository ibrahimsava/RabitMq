package com.example.rabitmq_project.controller;

import com.example.rabitmq_project.Entity.Utilisateurs;
import com.example.rabitmq_project.Producer.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rabbitmq")
public class ProducerController {

    private final Producer producer;

    // Injection de votre Producer
    public ProducerController(Producer producer) {
        this.producer = producer;
    }

    // Endpoint pour envoyer l'utilisateur
    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody Utilisateurs user) {
        producer.sendMessage(user);
        return ResponseEntity.ok("Message envoyé avec succès à RabbitMQ !");
    }
}
