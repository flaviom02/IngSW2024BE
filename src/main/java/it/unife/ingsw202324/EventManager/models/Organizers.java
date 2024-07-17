package it.unife.ingsw202324.EventManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizer_id;

    private String name;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "organizer_categories",
            joinColumns = @JoinColumn(name = "organizer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Categories> categories = new ArrayList<>();
}
