package it.unife.ingsw202324.EventManager.api;

import it.unife.ingsw202324.EventManager.models.Organizers;
import it.unife.ingsw202324.EventManager.services.OrganizersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizers")

public class OrganizersController {

    @Autowired
    private OrganizersService organizersService;

    @GetMapping
    public List<Organizers> getAllOrganizers() {
        return organizersService.getAllOrganizers();
    }

    // Restituisce un organizzatore dato il suo id
    @GetMapping("/{id}")
    public ResponseEntity<Organizers> getOrganizerById(@PathVariable Long id) {
        Optional<Organizers> organizer = organizersService.getOrganizerById(id);
        if (organizer.isPresent()) {
            return ResponseEntity.ok(organizer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/create")
    public Organizers createOrganizer(@RequestBody Organizers organizer) {
        return organizersService.createOrganizer(organizer);
    }

    // Aggiorna un organizzatore dato il suo id e i dettagli dell'organizzatore nel body della richiesta
    @PutMapping("/{id}")
    public ResponseEntity<Organizers> updateOrganizer(@PathVariable Long id, @RequestBody Organizers organizerDetails) {
        Organizers updatedOrganizer = organizersService.updateOrganizer(id, organizerDetails);
        if (updatedOrganizer != null) {
            return ResponseEntity.ok(updatedOrganizer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Long id) {
        organizersService.deleteOrganizer(id);
        return ResponseEntity.noContent().build();
    }
}
