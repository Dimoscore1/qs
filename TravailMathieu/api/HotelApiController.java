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
import fr.aem.TravailMathieu.exceptions.InvalidDataException;
import fr.aem.TravailMathieu.models.Hotel;
import fr.aem.TravailMathieu.repositories.HotelRepository;
import fr.aem.TravailMathieu.requests.HotelRequest;
import fr.aem.TravailMathieu.responses.HotelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelApiController {
    private final HotelRepository HotelReposi;

    @GetMapping
    public List<HotelResponse> listeHotel() {
        return this.HotelReposi.findAll().stream().map(this::convert).toList();
    }

    @GetMapping("/{id}")
    public HotelResponse getById(@RequestParam int idHotel) {
        Hotel unHotel = this.HotelReposi.findById(idHotel).orElseThrow(HotelNotFoundException::new);
        return this.convert(unHotel);
    }

    @PostMapping("/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public int ajouterHotel(@RequestBody HotelRequest requete) {
        Hotel unHotel = new Hotel();
        BeanUtils.copyProperties(requete, unhotel);

        return unHotel.getId();
    }

    public HotelResponse convert(Hotel unHotel) {

        HotelResponse response = HotelResponse.builder().build();

        BeanUtils.copyProperties(unHotel, response);
        return response;
    }
}
