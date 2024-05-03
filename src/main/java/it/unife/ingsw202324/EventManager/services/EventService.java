package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    private EventsRepository eventsRepository;

    public void addEvent(Events event) {
        eventsRepository.save(event);
    }

}
