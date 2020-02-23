INSERT INTO company (id, name, address, post_code, city, phone, email, tax_number, account_number)
values (1, 'Firma KRZAK', 'testowa 1', '60001', 'Poznań', '600000000', 'test@gmail.com', '777-77-77-777',
        '49 0000 0000 0000 00000');

INSERT INTO role (id, authority)
values (nextval('role_seq'), 'ADMIN');
INSERT INTO role (id, authority)
values (nextval('role_seq'), 'SUPERUSER');
INSERT INTO role (id, authority)
values (nextval('role_seq'), 'USER');

INSERT INTO user (username, password, first_name, last_name, email, role_id, creation_date, active, activation_date,
                  deleted, deleting_date, token)
values ('admin', '$2a$10$IPJizmA0rxJBq9incE/PWub6B2nIDGm3z/2cIz6hAs/hgvKlQ5F9q', 'Pan', 'Administrator',
        'admin@test.com', 1, '2020-02-15', true, '2020-02-15', false, null, null);
INSERT INTO user (username, password, first_name, last_name, email, role_id, creation_date, active, activation_date,
                  deleted, deleting_date, token)
values ('user', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', 'Jan', 'Kowalski', 'test@wp.pl', 3,
        '2020-02-15', true, '2020-02-15', false, null, null);

INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'bracelets', 'bransoletki');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'necklaces', 'naszyjniki');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'sets', 'komplety');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'pendants', 'wisiory');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'earrings', 'kolczyki');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'brooches', 'broszki');
INSERT INTO type (id, name, name_pl)
values (nextval('type_seq'), 'decorations', 'ozdoby świąteczne');


INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'TOHO beads', 'koraliki TOHO');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'glass crystals', 'szklane kryształki');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'pearls, mother of pearl', 'perły, masa perłowa');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Shamballa beads', 'Koraliki Shamballa');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Swarovski crystals', 'Kryształki Swarovski');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'wooden beads', 'koraliki drewniane');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'glass beads', 'koraliki szklane');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'plastic beads', 'koraliki plastikowe');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Cube beads', 'koraliki Cube');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Riso beads', 'koraliki Riso');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Superduo beads', 'koraliki Superduo');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Magatama beads', 'koraliki Magatama');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Rose petals beads', 'koraliki Rose petals');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Hex beads', 'koraliki Hex');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Treasure beads', 'koraliki Treasure');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Triangle beads', 'koraliki Triangle');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Les perles par Puca beads', 'koraliki Les perles par Puca');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Rulla beads', 'koraliki Rulla');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'Preciosa beads', 'koraliki Preciosa');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'gemstones', 'kamienie juliberskie');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'others', 'inne');

INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'white', 'biały');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'beige', 'beżowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'blue', 'błękitny');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'cosmos', 'benzynowy (albo cosmos albo nebula)');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'brown', 'brązowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'red', 'czerwony');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'black', 'czarny');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'violet', 'fioletowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'graphite', 'grafitowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'navy blue', 'granatowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'jeans', 'jeansowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'inky', 'atramentowy (modrakowy)');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'heavenly', 'niebieski');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'pink', 'różowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'silver', 'srebrny');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'gray', 'szary');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'green', 'zielony');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'golden', 'złoty');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'yellow', 'żółty');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'transparent', 'przejrzysty ( lub transparentny)');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'mint', 'miętowy');

INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 1, true, '24h');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 1, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 2, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 2, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 2, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 2, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 2, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 3, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 4, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 5, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 5, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 5, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 5, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 5, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 6, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 7, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 8, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 9, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 10, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 10, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 10, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 10, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 11, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 11, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 11, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 11, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 12, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 13, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 14, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 1, true, '24h');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 15, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 1, true, '24h');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 16, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 17, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 17, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 17, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 17, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 17, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 1, true, '24h');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 4, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 9, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 10, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 11, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 12, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 13, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 14, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 15, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 16, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 17, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 18, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 19, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 20, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 18, 21, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 19, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 19, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 19, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 19, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 20, 8, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 1, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 2, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 3, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 5, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 6, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 7, true, '1-2 dni');
INSERT INTO color_per_material (id, material_color_id, material_id, active, availability)
values (nextval('color_per_material_seq'), 21, 8, true, '1-2 dni');

INSERT INTO fastening_type(id, name, name_pl)
values (nextval('fastening_type_seq'), 'carbine/clip', 'karabinek');
INSERT INTO fastening_type(id, name, name_pl)
values (nextval('fastening_type_seq'), 'magnetic', 'magnesowe');
INSERT INTO fastening_type(id, name, name_pl)
values (nextval('fastening_type_seq'), 'without', 'brak');

INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'silver plated', 'posrebrzany');
INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'gilded', 'pozłacany');
INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'silver', 'srebrny');
INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'golden', 'złoty');
INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'black', 'czarny');
INSERT INTO fastening_color (id, name, name_pl)
values (nextval('fastening_color_seq'), 'copper', 'miedziany');

INSERT INTO making_technique(id, name, name_pl)
values (nextval('making_technique_seq'), 'needle sewn', 'szyte igłą');
INSERT INTO making_technique(id, name, name_pl)
values (nextval('making_technique_seq'), 'crochet method', 'metoda szydełkowa');
INSERT INTO making_technique(id, name, name_pl)
values (nextval('making_technique_seq'), 'other', 'inna');


INSERT INTO rule (id, name, name_pl, description, description_pl)
values (nextval('rule_seq'), 'regulations', 'regulamin','Here we put regulations in the future','Tutaj będzie regulamin....');
INSERT INTO rule (id, name, name_pl, description, description_pl)
values (nextval('rule_seq'), 'payment methods', 'formy płatności','Now we accept only standard transfer','Na tą chwilą jedyną formą płatności jest standardowy przelew na kont: ');
INSERT INTO rule (id, name, name_pl, description, description_pl)
values (nextval('rule_seq'), 'shipping costs', 'koszty wysyłki','We can send you shipments by DHL service only','Korzystamy z usług firmy DHL');
INSERT INTO rule (id, name, name_pl, description, description_pl)
values (nextval('rule_seq'), 'return rules', 'zasady zwrotu','Please send me message by form below, if you would like to return bought goods','Jeżeli chcesz zwrócić zakupiony towar, proszę skontaktuj się ze mną poprzez formularz kontaktowy poniżej');


INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czerwonymi kryształkami.', 1, 6, 190, 7, 1, 3, 2, 2, 15,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Naszyjnik z źółtymi kryształkami.', 2, 19, 190, 7, 1, 3, 1, 2, 20,
        'Delikatna Naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Naszyjnik z niebieskimi kryształkami.', 2, 13, 190, 7, 1, 3, 1, 2, 32,
        'Delikatny naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czarnymi kryształkami.', 1, 7, 190, 7, 1, 3, 1, 2, 23,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czarnymi kryształkami.', 1, 7, 190, 7, 1, 3, 1, 2, 23,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Komplet Bransoletka + naszyjnik z czarnymi kryształkami.', 3, 7, 190, 7, 1, 3, 1, 2,
        23,
        'Komplet bransoleta + naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Wisior z czarnymi kryształkami.', 4, 7, 190, 7, 1, 3, 1, 2, 23,
        'Wisior dla każdego z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Kolczyki z czarnymi kryształkami.', 5, 7, 190, 7, 1, 3, 1, 2, 23,
        'Kolczyki marzeń z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Broszka z czarnymi kryształkami.', 6, 7, 190, 7, 1, 3, 1, 2, 23,
        'Delikatna broszka z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');


commit;