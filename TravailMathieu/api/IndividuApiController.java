package fr.aem.TravailMathieu.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.aem.TravailMathieu.models.Individu;
import fr.aem.TravailMathieu.repositories.IndividuRepository;
import fr.aem.TravailMathieu.requests.IndividuRequest;
import lombok.RequiredArgsConstructor;

import fr.aem.TravailMathieu.responses.IndividuResponse;

@RestController
@RequestMapping("/api/individu")
@RequiredArgsConstructor
public class IndividuApiController {
    private final IndividuRepository individuReposi;

    @GetMapping
    public List<IndividuResponse> listeIndividus() {
        return this.individuReposi.findAll().stream().map(this::convert).toList();
    }

    @PostMapping("/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public Individu ajouterIndividu(@RequestBody IndividuRequest unIndividuRequest) {
        Individu unIndividu = Individu.builder().build();
        BeanUtils.copyProperties(unIndividuRequest, unIndividu);
        this.individuReposi.save(unIndividu);
        return unIndividu;
    }

    public IndividuResponse convert(Individu unIndividu) {
        IndividuResponse response = IndividuResponse.builder().build();
        BeanUtils.copyProperties(unIndividu, response);
        return response;
    }
}
