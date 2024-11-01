package fr.aem.TravailMathieu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.aem.TravailMathieu.models.Chambre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ChambreRepository {
    @PersistenceContext
    private EntityManager em;

    // retourner tous les Chambres de la base
    public List<Chambre> findAll() {
        return this.em
                .createQuery("select a from Chambre a", Chambre.class)
                .getResultList();
    }

    // retourner une Chambre selon un id
    public Optional<Chambre> findById(int unId) {
        try {
            Chambre unChambre = this.em.find(Chambre.class, unId);
            return Optional.ofNullable(unChambre);
        }
        catch(Exception ex) {
            return Optional.empty();
        }
    }

    // ajouter une Chambre dans la base
    @Transactional
    public Chambre save(Chambre unChambre) {
        this.em.persist(unChambre);
        return unChambre;
    }

    // supprimer une Chambre de la base
    @Transactional
    public void delete(Chambre unChambre) {
        this.em.remove(unChambre);
    }
}
