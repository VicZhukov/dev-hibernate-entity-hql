CREATE TABLE IF NOT EXISTS Client(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200)
    CONSTRAINT client_name_length_ck
    CHECK (LENGTH(name) >= 3) NOT NULL)
;

CREATE TABLE IF NOT EXISTS Planet(
    id VARCHAR(50) UNIQUE
    CONSTRAINT digit_uppercase_ck
    CHECK (id REGEXP '^[A-Z0-9]*$') NOT NULL,
    name VARCHAR(500) CONSTRAINT planet_name_length_ck
    CHECK (LENGTH(name) >= 1) NOT NULL)
;

CREATE TABLE IF NOT EXISTS Ticket(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP (0) with time zone not null,
    client_id INT,
    from_planet_id VARCHAR (50),
    to_planet_id VARCHAR (50),
    foreign key (client_id) references Client(id) ON DELETE CASCADE,
    foreign key (from_planet_id) references Planet(id) ON DELETE CASCADE,
    foreign key (to_planet_id) references Planet(id) ON DELETE CASCADE
)
;