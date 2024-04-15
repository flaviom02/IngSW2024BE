package it.unife.ingsw202324.EventManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/* Modello della taballa
* Le annotation indicano che questa classe è un entity bean,
* mappa una tabella che ha un nome fisico "my_table",
* che i costruttori sono generici e auto creati dal plugin lombok,
* e che lombok creerà anche tutti i getter e setter */
@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    @Id /* Annotation per definire la primary key della tabella  */
    @SequenceGenerator(name="EVID_GEN", sequenceName="EVENT_ID_GEN", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVID_GEN")
    private Long event_id;

    private String name;
    private String description;
    private Date date;
    private Time time;
    private String status;
    private String address;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private List<Categories> categories = new ArrayList<>();

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TicketTypes> tickets = new ArrayList<>();
}