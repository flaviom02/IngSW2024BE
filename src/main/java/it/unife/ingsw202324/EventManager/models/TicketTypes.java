package it.unife.ingsw202324.EventManager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/* Modello della taballa
* Le annotation indicano che questa classe è un entity bean,
* mappa una tabella che ha un nome fisico "my_table",
* che i costruttori sono generici e auto creati dal plugin lombok,
* e che lombok creerà anche tutti i getter e setter */
@Entity
@Table(name = "tickettypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypes {
    @Id /* Annotation per definire la primary key della tabella  */
    @SequenceGenerator(name="TICKID_GEN", sequenceName="TICKET_ID_GEN", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKID_GEN")
    private Long ticket_id;

    private String type;
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Events event;

    public String toString() {
        return "TicketTypes{" +
                "ticket_id=" + ticket_id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}