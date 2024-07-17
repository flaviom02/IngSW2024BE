package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.repositories.EventsRepository;
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

    public Optional<Events> getEventById(Long id) {
        return eventsRepository.findById(id);
    }

    public Events createEvent(Events event) {
        return eventsRepository.save(event);
    }

    public Events updateEvent(Long id, Events eventDetails) {
        Optional<Events> optionalEvent = eventsRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Events event = optionalEvent.get();
            event.setName(eventDetails.getName());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            event.setStatus(eventDetails.getStatus());
            event.setAddress(eventDetails.getAddress());
            event.setCategories(eventDetails.getCategories());
            event.setTickets(eventDetails.getTickets());
            return eventsRepository.save(event);
        } else {
            return null; // oppure puoi lanciare un'eccezione
        }
    }

    public void deleteEvent(Long id) {
        eventsRepository.deleteById(id);
    }
}

