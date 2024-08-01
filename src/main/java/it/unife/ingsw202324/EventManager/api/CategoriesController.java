package it.unife.ingsw202324.EventManager.api;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
//@CrossOrigin(origins = "http://localhost:8081")  // Permette richieste dal frontend Vue.js
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    // Restituisce tutte le categorie
    @GetMapping("/getall")
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    // Restituisce lista di categorie con possibilità di filtrare quelle eliminate
    @GetMapping("/getlist")
    public List<Categories> getCategories(@RequestParam(name="deleted", defaultValue="false") String deleted) {
        return switch (deleted.toLowerCase()) {
            case "true" -> categoriesService.getCategoryByDeleted(true);
            case "false" -> categoriesService.getCategoryByDeleted(false);
            case "all" -> categoriesService.getAllCategories();
            default -> throw new IllegalArgumentException("Invalid value for 'deleted': " + deleted);
        };
    }

    // Permette di avviare la sincronizzazione delle categorie con il servizio remoto manualmente
    @RequestMapping("/sync")
    public String syncCategories() {
        return categoriesService.syncCategories();
    }

    // Restituisce una categoria dato il suo id
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable int id) {
        Optional<Categories> category = categoriesService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Categories createCategory(@RequestBody Categories category) {
        return categoriesService.createCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable int id, @RequestBody Categories categoryDetails) {
        Categories updatedCategory = categoriesService.updateCategory(id, categoryDetails);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
