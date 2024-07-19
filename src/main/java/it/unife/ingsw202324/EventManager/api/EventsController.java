package it.unife.ingsw202324.EventManager.api;

import it.unife.ingsw202324.EventManager.models.Events;
import it.unife.ingsw202324.EventManager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")

public class EventsController {

    @Autowired
    private EventService eventsService;

    @RequestMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping
    public List<Events> getAllEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable Long id) {
        Optional<Events> event = eventsService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Events createEvent(@RequestBody Events event) {
        return eventsService.createEvent(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events eventDetails) {
        Events updatedEvent = eventsService.updateEvent(id, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventsService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
