package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Classe che definisce il repository (database)  */
@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {

}
