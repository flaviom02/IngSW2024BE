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
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoriesService.class);

    @Value("${spring.properties.sericeUrls.categories}")
    private String categoryUrl;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Long id) {
        return categoriesRepository.findById(id);
    }

    public List<Categories> getCategoryByDeleted(boolean deleted) {
        return categoriesRepository.findByDeleted(deleted);
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

    // Permette di sincronizzare le categorie con il servizio remoto
    public String syncCategories() {
        String errorMessage = "Errore nella sincronizzazione delle categorie";
        String okMessage = "Sincronizzazione completata";

        logger.info("Avvio sincronizzazione categorie");

        String response;
        try {
            response = TemplateRestConsumer.callREST("categories/", categoryUrl, false);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            return errorMessage + ": " + e.getMessage();
        }

        // Eseguo il parsing della risposta JSON e salvo le categorie nel database
        ObjectMapper mapper = new ObjectMapper();
        if (response != null) {
            try {
                List<Categories> categoriesList = mapper.readValue(response, new TypeReference<>() {
                });
                categoriesRepository.saveAll(categoriesList);
            } catch (Exception e) {
                logger.error(errorMessage, e);
                return errorMessage + ": " + e.getMessage();
            }
        } else {
            errorMessage = errorMessage + ": risposta nulla";
            logger.error(errorMessage);
            return errorMessage;
        }

        logger.info(okMessage);
        return okMessage;
    }

    // Esegue la sincronizzazione delle categorie con il servizio remoto ad intervalli regolari
    @Scheduled(fixedDelayString = "${spring.properties.scheduleInterval.categories}")
    private void syncCategoriesScheduled() {
        syncCategories();
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }
}
