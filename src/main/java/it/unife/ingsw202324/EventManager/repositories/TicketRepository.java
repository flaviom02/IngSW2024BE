package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.TicketTypes;
import org.springframework.data.jpa.repository.JpaRepository;


/* Classe che definisce il repository (database)  */
public interface TicketRepository extends JpaRepository<TicketTypes, Long> {

}
