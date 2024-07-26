package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.models.TicketTypes;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import it.unife.ingsw202324.EventManager.repositories.EventsRepository;
import it.unife.ingsw202324.EventManager.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventsRepository eventsRepository;

    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    public Events updateEvent(Long id, Events eventDetails) {
        Optional<Events> optionalEvent = eventsRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Events event = optionalEvent.get();
            event.setUrl_img(eventDetails.getUrl_img());
            event.setName(eventDetails.getName());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            event.setStatus(eventDetails.getStatus());
            event.setAddress(eventDetails.getAddress());
            event.setTickets(eventDetails.getTickets());
            return eventsRepository.save(event);
        } else {
            return null; // oppure puoi lanciare un'eccezione
        }
    }

    public void deleteEvent(Long id) {
        eventsRepository.deleteById(id);
    }

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Events createEvent(Events event) {
        // Validazione aggiuntiva se necessaria
        for (Categories category : event.getCategories()) {
            if (category.getCategory_id() != null) {
                Categories existingCategory = categoriesRepository.findById(category.getCategory_id()).orElse(null);
                if (existingCategory != null) {
                    event.getCategories().add(existingCategory);
                }
            }
        }

        for (TicketTypes ticket : event.getTickets()) {
            ticket.setEvent(event);
        }

        return eventsRepository.save(event);
    }

    public Optional<Events> getEventById(Long id) {
        return eventsRepository.findById(id);
    }
}

