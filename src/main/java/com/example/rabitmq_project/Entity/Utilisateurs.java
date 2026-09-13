package com.example.rabitmq_project.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Utilisateurs {


    @Id
    private String id;
    private String nom;
    private String prenom;
    private String adresse;


    public Utilisateurs( String nom, String prenom, String adresse) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;

    }


}
