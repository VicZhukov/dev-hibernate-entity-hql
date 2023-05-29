package ua.goit.vic;

import ua.goit.vic.clients.Client;
import ua.goit.vic.clients.ClientCrudService;
import ua.goit.vic.migrationService.DatabaseMigration;
import ua.goit.vic.planets.Planet;
import ua.goit.vic.planets.PlanetCrudService;

public class SpaceTravelApp {
    public static void main(String[] args) {
        new DatabaseMigration().initDB();

        Client client = new Client();
        client.setName("Vadim");

        ClientCrudService ccs = new ClientCrudService();
        ccs.createClient(client);
        ccs.deleteClient(client);

        Planet planet = new Planet();
        planet.setId("PLU");
        planet.setName("pluto");

        PlanetCrudService pcs = new PlanetCrudService();
        pcs.createPlanet(planet);
        pcs.deletePlanet(planet);
    }
}
