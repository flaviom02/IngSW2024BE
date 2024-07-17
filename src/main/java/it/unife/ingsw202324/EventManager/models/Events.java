package it.unife.ingsw202324.EventManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    @Id
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
