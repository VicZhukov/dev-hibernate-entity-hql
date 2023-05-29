package ua.goit.vic.clients;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.goit.vic.hibernate_utils.HibernateUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientCrudService {
    HibernateUtil hUtil = new HibernateUtil().getInstance();

    public void createClient (Client client){
        if(client.getName().length() <= 3){
            System.out.println("Not valid input, please insert name longer then 3 characters!");
        }else{
            try(Session session = hUtil.getSessionFactory().openSession()){
                Transaction trn = session.beginTransaction();
                Client nClient = new Client();
                nClient.setName(client.getName());
                session.persist(nClient);
                trn.commit();
                System.out.println("New client was created successfully!");
            }
        }
    }

    public void getClientById (int id) throws NoSuchElementException{
        try(Session session = hUtil.getSessionFactory().openSession()){
            Client dbClient = session.get(Client.class, id);
            if(dbClient == null){
                throw new NoSuchElementException("Client with id: " + dbClient.getId() + " is not exists!");
            }else{
                System.out.println(dbClient);
            }
        }
    }

    public void updateClient (Client client) throws NoSuchElementException{
        try(Session session = hUtil.getSessionFactory().openSession()){
            Transaction trn = session.beginTransaction();
            client = session.get(Client.class, client.getId());
            if(client == null){
                throw new NoSuchElementException("Client was not found!");
            }else {
                client.setName(client.getName());
                session.persist(client);
                trn.commit();
                System.out.println("Client with id: " + client.getId() + " was updated to: " + client.getName() +
                        " successfully!" + ".\n" + client);
            }
        }
    }

    public void deleteClient (Client client){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Transaction trn = session.beginTransaction();
            Client xstClient = session.get(Client.class, client.getId());
            if(client == null){
                throw new NoSuchElementException("Client with name " + client.getName() + " is not existing!");
            }else{
                session.remove(xstClient);
                trn.commit();
                System.out.println("Client with name " + client.getName() + " , was successfully deleted!");
            }
        }
    }

    public List<Client> getAllClients(){
        List<Client> clients;
        try(Session session = hUtil.getSessionFactory().openSession()){
            Query<Client> clientQuery = session.createQuery("from Client", Client.class);
            clients = clientQuery.list();
        }
        return clients;
    }
}
