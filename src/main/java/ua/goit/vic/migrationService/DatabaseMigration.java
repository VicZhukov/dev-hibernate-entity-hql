package ua.goit.vic.migrationService;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {
    public void initDB(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./testDB", null, null)
                .load();
        flyway.migrate();
    }
}

