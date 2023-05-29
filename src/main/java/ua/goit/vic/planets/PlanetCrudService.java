package ua.goit.vic.planets;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.goit.vic.hibernate_utils.HibernateUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class PlanetCrudService {
    HibernateUtil hUtil = new HibernateUtil();

    public void createPlanet(Planet planet){
        if(!planet.getId().matches("^[A-Z0-9]*$")){
            System.out.println("Not valid input, planet id must contain capital letters without special characters!");
        } else if (planet.getName().length() <= 3) {
            System.out.println("Not valid input, please insert name longer then 3 characters!");
        }else{
            try(Session session = hUtil.getSessionFactory().openSession()){
                Transaction trn = session.beginTransaction();
                planet = session.get(Planet.class, planet.getId());
                if(planet != null){
                    System.out.println("Planet with id: " + planet.getId() + " already exists!");
                }else {
                    Planet nPlanet = new Planet();
                    nPlanet.setId(planet.getId());
                    nPlanet.setName(planet.getName());
                    session.persist(nPlanet);
                    System.out.println("New planet: " + planet.getName() + " has been created" + ".\n" + planet);
                    trn.commit();
                }
            }
        }
    }

    public void getPlanetById (String id){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Planet planet = session.get(Planet.class, id);
            if(planet == null){
                throw new NoSuchElementException("Planet with id: " + planet.getId() + " is not exists!");
            }else {
                System.out.println(planet);
            }
        }
    }

    public void updatePlanet (Planet planet){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Transaction trn = session.beginTransaction();
            planet = session.get(Planet.class, planet.getId());
            if(planet == null){
                throw new NoSuchElementException("Planet with id: " + planet.getId() + " is not exists!");
            }else {
                planet.setName(planet.getName());
                session.persist(planet);
                System.out.println("Planet with id: " + planet.getId() + " was successfully updated to: " +
                        planet.getName() + ".\n" + planet);
                trn.commit();
            }
        }
    }

    public void deletePlanet(Planet planet){
        try(Session session = hUtil.getSessionFactory().openSession()){
            Transaction trn = session.beginTransaction();
            planet = session.get(Planet.class, planet.getId());
            if(planet == null){
                throw new NoSuchElementException("Planet with id: " + planet.getId() + " does not exist!");
            }else {
                session.remove(planet);
                trn.commit();
                System.out.println("Planet with id: " + planet.getId() + " was successfully removed!");
            }
        }
    }

    public List<Planet> getAllPlanets(){
        List<Planet> planets;
        try(Session session = hUtil.getSessionFactory().openSession()){
            Query<Planet> planetQuery = session.createQuery("from Planet", Planet.class);
            planets = planetQuery.list();
        }
        return planets;
    }
}
