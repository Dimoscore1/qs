package fr.aem.TravailMathieu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import fr.aem.TravailMathieu.models.Réservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RéservationRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Réservation> findAll() {
        return this.em.createQuery("select r from Réservation r", Réservation.class)
                .getResultList();
    }

    public Optional<Réservation> findById(int id) {
        return Optional.ofNullable(this.em.find(Réservation.class, id));
    }

    @Transactional
    public Réservation save(Réservation réservation) {
        this.em.persist(réservation);
        return réservation;
    }

    @Transactional
    public void delete(Réservation réservation) {
        this.em.remove(réservation);
    }
}