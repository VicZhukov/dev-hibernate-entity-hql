package ua.goit.vic.planets;

import jakarta.persistence.*;
import lombok.Data;
import ua.goit.vic.tickets.Ticket;

import java.util.List;


@Entity
@Data
@Table(name = "planet")
public class Planet {
    @Id
    private String id;

    @Column(name = "planet_name", length = 500, nullable = false)
    private String name;

    @OneToMany(mappedBy = "fromPlanetId", cascade = CascadeType.ALL)
    private List<Ticket> departureTickets;

    @OneToMany(mappedBy = "toPlanetId", cascade = CascadeType.ALL)
    private List<Ticket> arrivalTickets;
}
