package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.models.Organizers;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import it.unife.ingsw202324.EventManager.repositories.OrganizersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static it.unife.ingsw202324.EventManager.services.CategoriesService.prepareCategories;

@Service
public class OrganizersService {

    @Autowired
    private OrganizersRepository organizersRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Organizers> getAllOrganizers() {
        return organizersRepository.findAll();
    }

    public Optional<Organizers> getOrganizerById(Long id) {
        return organizersRepository.findById(id);
    }

    public Organizers createOrganizer(Organizers organizer) {

        organizer.setCategories(prepareCategories(organizer.getCategories(), categoriesRepository));

        return organizersRepository.save(organizer);
    }

    public Organizers updateOrganizer(Long id, Organizers organizerDetails) {
        Optional<Organizers> optionalOrganizer = organizersRepository.findById(id);
        if (optionalOrganizer.isPresent()) {
            Organizers organizer = optionalOrganizer.get();
            organizer.setEmail(organizerDetails.getEmail());
            organizer.setCategories(prepareCategories(organizerDetails.getCategories(), categoriesRepository));
            return organizersRepository.save(organizer);
        } else {
            return null;
        }
    }

    public void deleteOrganizer(Long id) {
        organizersRepository.deleteById(id);
    }


}
