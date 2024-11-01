package fr.aem.TravailMathieu.responses;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class AuthResponse {
    private boolean success;
    private String message;
    private String uuid;
    private boolean estAdmin;
    private String username;
    private String token;
}