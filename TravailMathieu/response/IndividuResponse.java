package fr.aem.TravailMathieu.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class IndividuResponse {
    private int id;
    private String nom;
    private String prenom;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String dateNaissance;
}
