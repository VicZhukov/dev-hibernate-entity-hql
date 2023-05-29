INSERT INTO Client (name) VALUES
    ('Harry'),
    ('Ron'),
    ('Hermione'),
    ('Minerva'),
    ('Rubeus'),
    ('Allbus'),
    ('Severus'),
    ('Tonks'),
    ('MadEye'),
    ('Molly')
;

INSERT INTO Planet (id, name) VALUES
    ('MERC', 'Mercury'),
    ('VEN', 'Venus'),
    ('EARTH', 'Earth'),
    ('MARS', 'Mars'),
    ('JUP', 'Jupiter')
;

INSERT INTO Ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
    ('2022-10-07 12:17:41', 1, 'VEN', 'MARS'),
    ('2021-09-09 22:11:56', 2, 'MARS', 'EARTH'),
    ('2013-03-18 04:34:24', 3, 'EARTH', 'JUP'),
    ('2016-07-08 13:24:54', 4, 'MARS', 'VEN'),
    ('2019-03-04 11:42:23', 5, 'VEN', 'MERC'),
    ('2021-05-28 08:31:00', 6, 'MERC', 'MARS'),
    ('2014-11-16 01:59:35', 7, 'EARTH', 'JUP'),
    ('2018-10-10 23:38:48', 8, 'VEN', 'JUP'),
    ('2015-12-01 14:15:31', 9, 'MERC', 'EARTH'),
    ('2019-02-08 07:44:12', 10, 'MARS', 'MERC');