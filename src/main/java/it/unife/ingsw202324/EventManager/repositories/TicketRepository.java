package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.models.TicketTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* Classe che definisce il repository (database)  */
public interface TicketRepository extends JpaRepository<TicketTypes, Long> {


    Iterable<Object> findAllByEvent(Events event);
}
