package ua.goit.vic.clients;


import jakarta.persistence.*;
import lombok.Data;
import ua.goit.vic.tickets.Ticket;

import java.util.List;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_name", length = 200, nullable = false)
    private String name;

    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
