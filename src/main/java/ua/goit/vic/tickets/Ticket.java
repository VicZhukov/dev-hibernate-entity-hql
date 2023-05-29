package ua.goit.vic.tickets;

import jakarta.persistence.*;
import lombok.Data;
import ua.goit.vic.clients.Client;
import ua.goit.vic.planets.Planet;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_name")
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "from_planet_name")
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name = "to_planet_name")
    private Planet toPlanetId;
}
