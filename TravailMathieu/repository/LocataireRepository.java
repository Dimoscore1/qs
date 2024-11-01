package fr.aem.TravailMathieu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.aem.TravailMathieu.models.Individu;
import fr.aem.TravailMathieu.models.Locataire;
import java.util.Optional;

@Repository
public interface LocataireRepository extends JpaRepository<Locataire, String> {
    public Optional<Locataire> findByUsername(String username);

    public Optional<Locataire> findByIndividu(Individu individu);

    public Optional<Locataire> findByUuid(String uuid);
}