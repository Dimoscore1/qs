package fr.aem.TravailMathieu.responses;

import fr.aem.springhotel.models.Individu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LocataireResponse {
    private String uuid;
    private String username;
    private String email;
    private Individu individu;
}