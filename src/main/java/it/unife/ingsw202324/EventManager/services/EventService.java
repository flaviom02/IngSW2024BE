package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.models.TicketTypes;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import it.unife.ingsw202324.EventManager.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventsRepository eventsRepository;

    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    public Events updateEvent(Long id, Events eventDetails) {
        System.out.println("Eventi ricevuti : "+ eventDetails);
        System.out.println("id : "+ id);
        Optional<Events> optionalEvent = eventsRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Events event = optionalEvent.get();
            event.setEvent_id(id);
            event.setUrl_img(eventDetails.getUrl_img());
            event.setName(eventDetails.getName());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            event.setStatus(eventDetails.getStatus());
            event.setAddress(eventDetails.getAddress());
            event.setCategories(eventDetails.getCategories());
            if (eventDetails.getTickets() != null) {
                for (TicketTypes ticket : eventDetails.getTickets()) {
                    ticket.setEvent(event);
                }
                event.getTickets().clear();
                event.getTickets().addAll(eventDetails.getTickets());
            }
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


    public Events createEvent(Events event) {

        System.out.println("Eventi ricevuti : "+ event);
        // Validazione aggiuntiva se necessaria
        Set<Categories> checkedCategories = new HashSet<>();

        Iterator<Categories> iterator = event.getCategories().iterator();
        while (iterator.hasNext()) {
            Categories category = iterator.next();
            if (category.getCategory_id() != 0) {
                Categories existingCategory = categoriesRepository.findById(category.getCategory_id()).orElse(null);
                if (existingCategory != null) {
                    checkedCategories.add(existingCategory);
                }
            }
        }

        event.setCategories(checkedCategories);

        return eventsRepository.save(event);
    }

    public Optional<Events> getEventById(Long id) {
        return eventsRepository.findById(id);
    }
}

