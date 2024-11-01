package fr.aem.TravailMathieu.api;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aem.TravailMathieu.exceptions.ChambreNotFoundException;
import fr.aem.TravailMathieu.models.Chambre;
import fr.aem.TravailMathieu.repositories.ChambreRepository;
import fr.aem.TravailMathieu.requests.ChambreRequest;
import fr.aem.TravailMathieu.responses.ChambreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/chambre")
@RequiredArgsConstructor
public class chambreApiController {
    private final chambreRepository chambreReposi;

    @GetMapping
    public List<chambreResponse> listechambre() {
        return this.chambreReposi.findAll().stream().map(this::convert).toList();
    }

    @GetMapping("/{id}")
    public chambreResponse getById(@RequestParam int idchambre) {
        chambre unchambre = this.chambreReposi.findById(idchambre).orElseThrow(ChambreNotFoundException::new);
        return this.convert(unchambre);
    }

    @PostMapping("/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public int ajouterchambre(@RequestBody chambreRequest requete) {
        chambre unchambre = new chambre();
        BeanUtils.copyProperties(requete, unchambre);
        this.chambreReposi.save(unchambre);
        return unchambre.getId();
    }

    public chambreResponse convert(chambre unchambre) {
        chambreResponse response = chambreResponse.builder().build();
        BeanUtils.copyProperties(unchambre, response);
        return response;
    }
}
