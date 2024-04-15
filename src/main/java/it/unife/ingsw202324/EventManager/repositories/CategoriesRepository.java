package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

/* Classe che definisce il repository (database)  */
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
