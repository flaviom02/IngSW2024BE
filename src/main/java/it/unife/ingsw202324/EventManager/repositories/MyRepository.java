package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.MyTable;
import org.springframework.data.jpa.repository.JpaRepository;
/* Classe che definisce il repository (database)  */
public interface MyRepository extends JpaRepository<MyTable, Long> {

}
