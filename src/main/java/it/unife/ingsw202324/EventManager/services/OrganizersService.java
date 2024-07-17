package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Organizers;
import it.unife.ingsw202324.EventManager.repositories.OrganizersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizersService {

    @Autowired
    private OrganizersRepository organizersRepository;

    public List<Organizers> getAllOrganizers() {
        return organizersRepository.findAll();
    }

    public Optional<Organizers> getOrganizerById(Long id) {
        return organizersRepository.findById(id);
    }

    public Organizers createOrganizer(Organizers organizer) {
        return organizersRepository.save(organizer);
    }

    public Organizers updateOrganizer(Long id, Organizers organizerDetails) {
        Optional<Organizers> optionalOrganizer = organizersRepository.findById(id);
        if (optionalOrganizer.isPresent()) {
            Organizers organizer = optionalOrganizer.get();
            organizer.setName(organizerDetails.getName());
            organizer.setEmail(organizerDetails.getEmail());
            organizer.setCategories(organizerDetails.getCategories());
            return organizersRepository.save(organizer);
        } else {
            return null;
        }
    }

    public void deleteOrganizer(Long id) {
        organizersRepository.deleteById(id);
    }
}
