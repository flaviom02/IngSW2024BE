package it.unife.ingsw202324.EventManager.services;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoriesService.class);

    @Value("${spring.properties.serviceUrls.categories}")
    private String categoryUrl;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(int id) {
        return categoriesRepository.findById(id);
    }

    public List<Categories> getCategoryByDeleted(boolean deleted) {
        return categoriesRepository.findByDeleted(deleted);
    }

    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(int id, Categories categoryDetails) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Categories category = optionalCategory.get();
            category.setName(categoryDetails.getName());
            return categoriesRepository.save(category);
        } else {
            return null;
        }
    }

    // Permette di sincronizzare le categorie con il servizio remoto
    public String syncCategories() {
        String errorMessage = "Errore nella sincronizzazione delle categorie";
        String okMessage = "Sincronizzazione completata";

        logger.info("Avvio sincronizzazione categorie");
        
        // Chiamata al servizio remoto per ottenere le categorie
        String response;
        try {
            response = TemplateRestConsumer.callREST("categories/", categoryUrl, false);

            logger.debug("Risposta dal REST service: {}", response); // Log the response for debugging

            // Eseguo il parsing della risposta JSON e salvo le categorie nel database
            ObjectMapper mapper = new ObjectMapper();
            List<Categories> categoriesList = mapper.readValue(response, new TypeReference<>() {
            });

            categoriesRepository.saveAll(categoriesList); // Salvo le categorie nel database

            logger.info(okMessage);
            return okMessage;

        } catch (Exception e) {
            logger.error(errorMessage, e);
            return errorMessage + ": " + e.getMessage();
        }

    }

    // Esegue la sincronizzazione delle categorie con il servizio remoto ad intervalli regolari
    @Scheduled(fixedDelayString = "${spring.properties.scheduleInterval.categories}")
    private void syncCategoriesScheduled() {
        syncCategories();
    }

    public void deleteCategory(int id) {
        categoriesRepository.deleteById(id);
    }

    public static Set<Categories> prepareCategories(Set<Categories> categories, CategoriesRepository categoriesRepository) {

        Set<Categories> checkedCategories = new HashSet<>();

        Iterator<Categories> iterator = categories.iterator();
        while (iterator.hasNext()) {
            Categories category = iterator.next();
            if (category.getCategory_id() != 0) {
                Categories existingCategory = categoriesRepository.findById(category.getCategory_id()).orElse(null);
                if (existingCategory != null) {
                    checkedCategories.add(existingCategory);
                }
            }
        }
        return checkedCategories;
    }
}
