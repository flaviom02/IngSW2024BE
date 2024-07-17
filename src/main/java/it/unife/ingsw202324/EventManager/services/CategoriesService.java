package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Long id) {
        return categoriesRepository.findById(id);
    }

    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories categoryDetails) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Categories category = optionalCategory.get();
            category.setName(categoryDetails.getName());
            category.setEvents(categoryDetails.getEvents());
            category.setOrganizers(categoryDetails.getOrganizers());
            return categoriesRepository.save(category);
        } else {
            return null;
        }
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }
}
