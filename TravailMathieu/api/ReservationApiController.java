package fr.aem.TravailMathieu.api;

import fr.aem.springhotel.exceptions.InvalidDataException;
import fr.aem.TravailMathieu.models.Reservation;
import fr.aem.TravailMathieu.repositories.ReservationRepository;
import fr.aem.TravailMathieu.requests.ReservationRequest;
import fr.aem.TravailMathieu.responses.ReservationResponse;
import fr.aem.TravailMathieu.models.Chambre; // Assurez-vous d'importer le modèle Chambre
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Réservation non trouvée"));
        return convertToResponse(reservation);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse createReservation(@RequestBody ReservationRequest request) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(request, reservation);

        // Récupérer la chambre associée à la réservation
        Chambre chambre = // votre logique pour récupérer la chambre par ID depuis la base de données
                reservation.setChambre(chambre); // Associe la chambre à la réservation

        // Calculez le prix total ici
        reservation.setPrixTotal(calculerPrixTotal(reservation)); // Remplacez par votre logique
        reservationRepository.save(reservation);
        return convertToResponse(reservation);
    }

    @PutMapping("/{id}")
    public ReservationResponse updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Réservation non trouvée"));
        BeanUtils.copyProperties(request, reservation);
        reservationRepository.save(reservation);
        return convertToResponse(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Réservation non trouvée"));
        reservationRepository.delete(reservation);
    }

    private ReservationResponse convertToResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .chambreId(reservation.getChambre().getId())
                .individuId(reservation.getIndividu().getId())
                .dateDebut(reservation.getDateDebut())
                .dateFin(reservation.getDateFin())
                .prixTotal(reservation.getPrixTotal())
                .build();
    }

    private Double calculerPrixTotal(Reservation reservation) {
        // Vérifier que les dates de début et de fin sont valides
        if (reservation.getDateDebut() == null || reservation.getDateFin() == null) {
            return 0.0; // Retourne 0 si les dates ne sont pas valides
        }
        // Calculer le nombre de nuits
        long nuits = java.time.temporal.ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        // Vérifier que le nombre de nuits est positif
        if (nuits < 0) {
            throw new InvalidDataException("La date de fin doit être postérieure à la date de début.");
        }
        // Récupérer le prix par nuit de la chambre
        double coutParNuit = reservation.getChambre().getPrixParNuit();
        // Calculer le prix total
        return nuits * coutParNuit;
    }
}
