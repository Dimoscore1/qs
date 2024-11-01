package fr.aem.TravailMathieu.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.aem.TravailMathieu.models.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class HotelRepository {
    @PersistenceContext
    private EntityManager em;

    // retourner tous les hotels de la base
    public List<Hotel> findAll() {
        return this.em
                .createQuery("select v from Hotel v", Hotel.class)
                .getResultList();
    }

    // retourner un hotel selon un id
    public Optional<Hotel> findById(int unId) {
        try {
            Hotel unHotel = this.em.find(Hotel.class, unId);
            return Optional.ofNullable(unHotel);
        }
        catch(Exception ex) {
            return Optional.empty();
        }
    }

    // ajouter un hotel dans la base
    @Transactional
    public Hotel save(Hotel unHotel) {
        this.em.persist(unHotel);
        return unHotel;
    }

    // supprimer un hotel de la base
    @Transactional
    public void delete(Hotel unHotel) {
        this.em.remove(unHotel);
    }
}
