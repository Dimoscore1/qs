package fr.aem.TravailMathieu.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ChambreResponse {

    private Long id; // Identifiant unique de la chambre

    private String type; // Type de chambre (simple, double, suite, etc.)

    private int nombreDeLits; // Nombre de lits dans la chambre

    private int capacite; // Capacité d'accueil (nombre maximal de personnes)

    private double prixParNuit; // Prix par nuit pour la chambre
}
