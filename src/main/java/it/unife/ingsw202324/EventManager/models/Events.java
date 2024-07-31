package it.unife.ingsw202324.EventManager.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(name="EVID_GEN", sequenceName="EVENT_ID_GEN", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVID_GEN")
    private Long event_id;

    @Column(columnDefinition = "TEXT")
    private String url_img;

    private String name;
    private String description;
    private Date date;
    private Time time;
    private String status;
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "event_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Categories> categories = new HashSet<>();


    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<TicketTypes> tickets = new HashSet<>();

}
