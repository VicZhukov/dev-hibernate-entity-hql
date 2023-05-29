package ua.goit.vic.tickets;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.goit.vic.clients.Client;
import ua.goit.vic.hibernate_utils.HibernateUtil;
import ua.goit.vic.planets.Planet;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

public class TicketCrudService {
    HibernateUtil hUtil = new HibernateUtil();

    public void createTicket(Timestamp createdAt, int clientId, String departurePlanet, String arrivalPlanet){
        if(!departurePlanet.matches("[A-Z0-9]*$") || !arrivalPlanet.matches("[A-Z0-9]*$")){
            System.out.println("Not valid input, planet id must contain capital letters without special characters!");
        } else {
            try(Session session = hUtil.getSessionFactory().openSession()){
                Transaction trn = session.beginTransaction();
                Client client = session.get(Client.class, clientId);
                Planet planetFrom = session.get(Planet.class, departurePlanet);
                Planet planetTo = session.get(Planet.class, arrivalPlanet);
                if(client == null){
                    System.out.println("Client with id: " + client.getId() + " does not exists!");
                }
                if(planetFrom == null){
                    System.out.println("Departure planet with id: " + planetFrom.getId() + " does not exists!");
                }
                if(planetTo == null){
                    System.out.println("Arrival planet with id: " + planetTo.getId() + " does not exists!");
                }

                Ticket nTicket = new Ticket();
                nTicket.setCreatedAt(createdAt);
                nTicket.setClientId(client);
                nTicket.setFromPlanetId(planetFrom);
                nTicket.setToPlanetId(planetTo);
                session.persist(nTicket);
                System.out.println("New ticket has been created! .\n" + nTicket);
                trn.commit();
            }
        }
    }

    public void getTicketById(int ticketId){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Ticket ticket = session.get(Ticket.class, ticketId);
            if(ticket == null){
                System.out.println("Ticket with id: " + ticket.getId() + " is not exists!");
            }else{
                System.out.println(ticket);
            }
        }
    }

    public void updateTicket(int ticketId, String departurePlanet, String arrivalPlanet){
        if(!departurePlanet.matches("[A-Z0-9]*$") || !arrivalPlanet.matches("[A-Z0-9]*$")){
            System.out.println("Not valid input, planet id must contain capital letters without special characters!");
        }else {
            try(Session session = hUtil.getSessionFactory().openSession()){
                Transaction trn = session.beginTransaction();
                Ticket ticket = session.get(Ticket.class, ticketId);
                Planet planetFrom = session.get(Planet.class, departurePlanet);
                Planet planetTo = session.get(Planet.class, arrivalPlanet);
                if(ticket == null){
                    throw new NoSuchElementException("Ticket with id: " + ticket.getId() + " is not exists!");
                }else if(planetFrom == null){
                    throw new NoSuchElementException("Departure planet with id: " + departurePlanet + " is not exists!");
                }else if(planetTo == null){
                    throw new NoSuchElementException("Arrival planet with id: " + arrivalPlanet + " is not exists!");
                }else {
                    ticket.setFromPlanetId(planetFrom);
                    ticket.setToPlanetId(planetTo);
                    session.persist(ticket);
                    System.out.println("Ticket with id: " + ticketId + " was updated!");
                    trn.commit();
                }
            }
        }
    }

    public void deleteTicketById(int tickedId){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Transaction trn = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, tickedId);
            if(ticket == null){
                throw new NoSuchElementException("Ticket with id: " + ticket.getId() + " is not exists!");
            }else {
                session.remove(ticket);
                System.out.println("Ticket with id: " + tickedId + " was successfully deleted!");
                trn.commit();
            }
        }
    }

    public List<Ticket> getAllTickets(){
        List<Ticket> tickets;
        try(Session session = hUtil.getSessionFactory().openSession()){
            Query<Ticket> query = session.createQuery("from Ticket", Ticket.class);
            tickets = query.list();
            return tickets;
        }
    }
}
