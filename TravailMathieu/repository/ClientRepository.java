package fr.aem.TravailMathieu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.aem.springhotel.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}