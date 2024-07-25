package it.unife.ingsw202324.EventManager.repositories;

import it.unife.ingsw202324.EventManager.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    List<Categories> findByDeleted(boolean deleted);
}
