package fr.aem.TravailMathieu.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class HotelResponse {
    private int id;
    private String nom;
    private String adresse;
    private string taille;
}
