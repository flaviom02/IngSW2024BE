package it.unife.ingsw202324.EventManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    private String name;
    private boolean deleted;
    /*
    @ManyToMany
    @JoinTable(
            name = "event_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonIgnore
    private List<Events> events = new ArrayList<>();
    /*/

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Organizers> organizers = new ArrayList<>();

    public Categories(int category_id) {
        this.category_id = category_id;
    }
}
