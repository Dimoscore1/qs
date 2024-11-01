package fr.aem.TravailMathieu.api;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aem.TravailMathieu.exceptions.InvalidDataException;
import fr.aem.TravailMathieu.models.Individu;
import fr.aem.TravailMathieu.models.Locataire;
import fr.aem.TravailMathieu.repositories.IndividuRepository;
import fr.aem.TravailMathieu.repositories.LocataireRepository;
import fr.aem.TravailMathieu.requests.InscriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/utilisateur")
@RequiredArgsConstructor
@Log4j2
public class LocataireApiController {
    private final LocataireRepository LocataireReposi;
    private final IndividuRepository individuReposi;
    private final PasswordEncoder pwdEncoder;

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED) // code 201 (created) lorque le nouvel utilisateur est ajouté
    public String inscription(@RequestBody InscriptionRequest uneRequete) {
        log.info("Requête d'inscription...");
        Individu unIndividu = new Individu();
        Locataire unLocataire = new Locataire();

        if(uneRequete.getUsername() == null || uneRequete.getUsername() == "") {
            log.error("Username invalide.");
            throw new InvalidDataException();
        }
        if(uneRequete.getPassword() == null || uneRequete.getPassword() == "") {
            log.error("Password invalide.");
            throw new InvalidDataException();
        }
        if(uneRequete.getEmail() == null || uneRequete.getEmail() == "") {
            log.error("Email invalide.");
            throw new InvalidDataException();
        }
        if(uneRequete.getNom() == null || uneRequete.getNom() == "") {
            log.error("Nom invalide.");
            throw new InvalidDataException();
        }
        if(uneRequete.getPrenom() == null || uneRequete.getPrenom() == "") {
            log.error("Prénom invalide.");
            throw new InvalidDataException();
        }
        if(uneRequete.getDateNaissance() == null || uneRequete.getDateNaissance() == "") {
            log.error("Date de naissance invalide.");
            throw new InvalidDataException();
        }

        BeanUtils.copyProperties(uneRequete, unIndividu); // nom, prenom, dateNaissance
        unIndividu.setDateNaissance(LocalDate.parse(uneRequete.getDateNaissance()));
        BeanUtils.copyProperties(uneRequete, unLocataire); // username, password, email
        unLocataire.setPassword(this.pwdEncoder.encode(uneRequete.getPassword()));

        this.individuReposi.save(unIndividu);
        unUtilisateur.setIndividu(unIndividu);
        this.LocataireReposi.save(unLocataire);
        return unLocataire.getUuid();
    }
}