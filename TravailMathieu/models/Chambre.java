package fr.aem.TravailMathieu.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la chambre

    private String type; // Type de chambre (simple, double, suite, etc.)

    private int nombreDeLits; // Nombre de lits dans la chambre

    private int capacite; // Capacité d'accueil (nombre maximal de personnes)

    private double prixParNuit; // Prix par nuit pour la chambre

}
