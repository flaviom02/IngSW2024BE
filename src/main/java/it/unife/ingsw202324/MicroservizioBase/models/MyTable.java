package it.unife.ingsw202324.MicroservizioBase.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "my_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTable {
    @Id
    private Long id;
    private String description;

}
