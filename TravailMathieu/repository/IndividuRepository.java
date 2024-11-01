package fr.aem.TravailMathieu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.aem.TravailMathieu.models.Individu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class IndividuRepository {
    @PersistenceContext
    private EntityManager em;

    // retourner tous les individus de la base
    public List<Individu> findAll() {
        return this.em
        .createQuery("select i from Individu i", Individu.class)
        .getResultList();
    }

    // retourner un individu selon un id
    public Optional<Individu> findById(int unId) {
        try {
            Individu unIndividu = this.em.find(Individu.class, unId);
            return Optional.ofNullable(unIndividu);
        }
        catch(Exception ex) {
            return Optional.empty();
        }
    }

    // ajouter un individu dans la base
    @Transactional
    public Individu save(Individu unIndividu) {
        this.em.persist(unIndividu);
        return unIndividu;
    }

    // supprimer un individu de la base
    @Transactional
    public void delete(Individu unIndividu) {
        this.em.remove(unIndividu);
    }
}