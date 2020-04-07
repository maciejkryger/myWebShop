INSERT INTO company (id, name, address, post_code, city, phone, email, tax_number, account_number)
values (1, 'Firma KRZAK', 'testowa 1', '60001', 'Poznań', '600000000', 'test@gmail.com', '777-77-77-777',
        '49 0000 0000 0000 00000');

INSERT INTO role (id, authority)
values (nextval('role_seq'), 'ADMIN');
INSERT INTO role (id, authority)
values (nextval('role_seq'), 'SUPERUSER');
INSERT INTO role (id, authority)
values (nextval('role_seq'), 'USER');

INSERT INTO user (id, username, password, first_name, last_name, email, role_id, creation_date, active, activation_date,
                  deleted, deleting_date, token)
values (nextval('user_seq'),'admin', '$2a$10$IPJizmA0rxJBq9incE/PWub6B2nIDGm3z/2cIz6hAs/hgvKlQ5F9q', 'Maciej', 'Nowak',
        'admin@test.com', 1, '2020-02-15', true, '2020-02-15', false, null, null);
INSERT INTO user (id,username, password, first_name, last_name, email, role_id, creation_date, active, activation_date,
                  deleted, deleting_date, token)
values (nextval('user_seq'),'superuser', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', 'Sylwia', 'Kowalska', 'test@wp.pl', 2,
        '2020-02-15', true, '2020-02-15', false, null, null);
INSERT INTO user (id,username, password, first_name, last_name, email, role_id, creation_date, active, activation_date,
                  deleted, deleting_date, token)
values (nextval('user_seq'),'user', '$2a$10$qbTMXJNSEgCBTTgLJzU9Iu.mWlwRMFwh12cCkBDdPhUc4HJJmfFku', 'Agnieszka', 'Walc', 'test@wp.pl', 3,
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
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic gold', 'metaliczne złoto');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic silver', 'metaliczne srebro');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic bronze', 'metaliczny brąz');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'mix', 'mix');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic ink', 'metaliczny atramentowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic purple', 'metaliczny fioletowy');
INSERT INTO material_color (id, name, name_pl)
values (nextval('material_color_seq'), 'metallic navy blue', 'metaliczny granatowy');

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
values (nextval('making_technique_seq'), 'turkish rope', 'sznur turecki');
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

-- ***********PRODUKTY**********
-- bransoletki
INSERT INTO product (id, name, name_pl,type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description, description_pl, active)
values (nextval('product_seq'), '','Bransoletka Caprice', 1, 23, 190, 40, 1, 2, 1, 1, 40, '',
        'Bransoletka z koralików TOHO w połączeniu z kryształkami. Możliwe zapięcie na karabinek, zapięcie magnesowe lub wsuwana na rękę ( tutaj należy zmierzyć szerokość dłoni). Dostępne różne kombinacje kolorystyczne. Tutaj eleganckie połączenie kryształków w 3 kolorach: czarny, srebrny i biały.', true);
INSERT INTO product (id, name,name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description, description_pl, active)
values (nextval('product_seq'),'', 'Bransoletka Jeżyk', 1, 15, 190, 15, 1, 2, 1, 2, 25,'',
        'Ręcznie robiona bransoletka techniką szydełkową. Wykonana z wysokiej jakości japońskich koralików TOHO oraz RISO. Bransoletka jest lekka i wygodna w noszeniu.Długość 18-20 cm, możliwość wykonania bransoletki w dowolnym rozmiarze. Szerokość bransoletki ok 1,5cm. Wykończona metalowymi elementami w kolorze srebrnym, zapięcie na karabinek. Bransoletki dostępne są rónież w innych kolorach.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Elegancka bransoletka z kryształków i koralików Toho', 1, 15, 190, 10, 1, 2, 2, 2, 25,
        'Bransoletka wykonana z koralików TOHO połączonych ze szklanymi kryształkami. Koraliki TOHO uważane są za najrówniejsze na świecie co w przypadku ręcznie robionej biżuterii niejednokrotnie ma olbrzymie znaczenie. Dzięki użytej metodzie bransoletka jest niezwykle estetyczna i elegancka. Bardzo ładnie prezentują się i w codziennym noszeniu i jest również pięknym prezentem, z możliwością domówienia naszyjnika. W obecnej ofercie mogę zaproponować kilkanaście kolorów w zależności od upodobania. Wykończone metalowymi elementami w kolorze srebrnym z zapięciem na karabinek.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka z kryształków', 1, 26, 190, 10, 1, 2, 1, 2, 20,
        'Ręcznie robiona bransoletka z maleńkich szklanych, szlifowanych kryształków w kolorze atrametowym (metalicznym).  Okrągłe metalowe zapięcie z karabinkiem. Bransoletka bardzo ładnie się prezentuje. Wykonana metodą szydełkową. Długość 19 cm lub na zamówienie, szerokość ok 1cm.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka sznur turecki', 1, 1, 190, 9, 1, 2, 1, 3, 20,
        'Bransoletka wykonana techniką sznur turecki, użyte koraliki Toho i szklane kryształki. Możliwe wykonanie bransoletki w dowolnym kolorze.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Delikatna bransoletka z Toho i kryształków', 1, 9, 190, 7, 1, 2, 1, 2, 15,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze srebrnym, baza bransoletki z koralików w kolorze grafitowym. Zapięcie na karabinek w kolorze srebrnym.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka w kwiaty', 1, 1, 190, 7, 1, 2, 1, 2, 15,
        'Bransoletka wykonana metodą szydełkową z koralików Toho.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'),'Bransoletka z zapięciem Shamballa', 1, 7, 190, 7, 1, 2, 1, 2, 15,
        'Delikatna bransoletka z koralików TOHO. Grubość ok 7mm. Zapięcie na magnes - Shamballa. Biżuteria wykonana z koralików TOHO jest delikatna i wygodna w noszeniu. Bransoletka dostępna w różnych wersjach kolorystycznych',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka wężyk', 1, 7, 190, 15, 1, 2, 1, 2, 15,
        'Bransoletka z koralików TOHO, użyte zostały dwie wielkości koralików.Bransoletka jest lekka, delikatna i wygodna w noszeniu',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka TOHO', 1, 7, 190, 15, 1, 2, 1, 2, 15,
        'Bransoletka z koralików Toho wykonana techniką szydełkową. Użyte są dwa kolory. Dostępne są inne opcje kolorystyczne.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Szeroka bransoletka Superduo', 1, 25, 190, 30, 1, 2, 1, 2, 20,
        'Bransoeltka wykonana w koralików Superduo w połączeniu z koralikami Toho. Możliwość wykonia bransoletki w innym zestawie kolorów.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka z perełek, Toho i kryształków', 1, 25, 190, 10, 1, 2, 1, 1, 20,
        'Bransoletka wykonana z perełek w połączeniu z koralikami Toho i kryształkami. Możliwość wykonania bransoletki w innym zestawie kolorów.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka z zapięciem magnetycznym', 1, 25, 190, 8, 1, 1, 1, 2, 20,
        'Bransoletka wykonana z koralików Toho, rozmiar 8o. Zapięcie magnetyczne, możliwe zrobienie bransoletki w innym kolorze.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Bransoletka gąsienica', 1, 25, 190, 10, 1, 2, 1, 2, 20,
        'Dwustronna, płaska bransoletka wykonana z wysokiej jakości koralików TOHO oraz Magatama.  Bransoletka wykonana w kolorze jasno złotym i srebrnym. Boki w kolorze metalicznego brązu. Elementy wykończeniowe w kolorze złotym.  Metoda wykonania daje możliwość noszenia jej w zależności od potrzeb kolorystycznych. Bardzo wygodna w noszeniu. Nadaje się i do pracy i na imprezę. Do kompletu możliwe jest wykonanie naszyjnika ( cena 60 pln). ',true);

-- komplety
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Komplet naszyjnik i bransoletka sznur turecki', 3, 1, 25, 0, 1, 2, 1, 3, 55,
        'Komplet naszyjnik i bransoletka wykonane techniką sznur turecki, użyte koraliki Toho i szklane kryształki. Możliwe wykonanie kompletu w dowolnym  kolorze. Długość naszyjnika ok 47 cm, długość bransoletki ok 19cm. Długość do uzgodnienia',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Komplet naszyjnik i bransoletka Toho', 3, 25, 0, 0, 1, 2, 1, 2, 35,
        'Komplet naszyjnik i bransoletka z koralików Toho wykonane techniką szydełkową. Użyte są dwa kolory. Dostępne są inne opcje kolorystyczne. Długość naszyjnika ok 47 cm, długość bransoletki ok 19cm. Długość do uzgodnienia',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'komplet naszyjnik i bransoletka kwiaty', 3, 25, 0, 0, 1, 2, 1, 2, 60,
        'Naszyjnik i bransoletka wykonane metodą szydełkową z koralików Toho. Długość naszyjnika ok 47 cm, długość bransoletki ok 19cm. Długość do uzgodnienia',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'komplet naszyjnik i kolczyki', 3, 25, 0, 0, 1, 2, 1, 1, 35,
        'Naszyjnik i kolczyki wykonane z Toho i kryształków. Długość naszynika ok 47 cm, kolczyki ok 5cm.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'komplet bransoletka i kolczyki', 3, 25, 0, 0, 1, 2, 1, 1, 25,
        'Bransoletka i kolczyki wykonane z Toho i kryształków, bransoletka długość ok 19 cm, kolczyki ok 5cm',true);

-- naszyjniki
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Naszyjnik w kwiaty', 2, 25, 470, 15, 1, 2, 1, 2, 45,
        'Naszyjnik z koralików Toho, wzór maki',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Naszyjnik muszelki', 2, 25, 470, 25, 1, 2, 1, 2, 60,
        'Ręcznie wykonany naszyjnik z trzech wielkości koralików TOHO daje efekt muszelek. Możliwość wykonania dowolnego zestawu kolorystycznego. Długość ok 47 cm, szerokość 2,5 - 3cm.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Naszyjnik sznur turecki', 2, 25, 470, 9, 1, 2, 1, 3, 40,
        'Naszyjnik wykonana techniką sznur turecki, użyte koraliki Toho i szklane kryształki. Możliwe wykonanie naszyjnika w dowolnym kolorze.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Naszyjnik TOHO', 2, 25, 470, 7, 1, 2, 1, 2, 30,
        'Naszyjnik wykonany metodą szydełkową, możliwość wykonania w innym kolorze, lub w połączeniu z innym kolorem w paski lub kropki.',true);

-- wisiorki
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Wisiorek', 4, 25, 0, 0, 3, 2, 1, 2, 19,
        'Ręcznie wykonany wisior z koralików TOHO - japońskich szklanych koralików uważanych za jedne z najrówniejszych. Długość wisiora ok 10 cm ( z uwzględnieniem końcówki z krawatką). Polecam serdecznie do noszenia na rzemyku lub łańcuszku. Istnieje możliwość domówienia naszyjnika w kolorze wisiora. Kolor czarno biały. Na zamówienie mogę wykonać dowolną długość oraz zestaw kolorów. Na chwilę obecną jbardziej popularne zestawienia to czarno biały, czarno srebrny, grafitowy :)',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Wisiorek', 4, 25, 0, 0, 3, 2, 1, 1, 15,
        'Wisiorek wykonanay z koralików Toho, Rulla i kryształków, Średnica ok 3 cm.',true);
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Wisiorek', 4, 25, 0, 0, 3, 2, 1, 1, 15,
        'Wisiorek to kaboszon obszyty koralikami Toho, ok 3,5 cm x 2cm',true);

-- broszki
INSERT INTO product (id, name_pl, type_id, material_color_id, length, width, fastening_type_id, fastening_color_id,
                     material_id,
                     making_technique_id, price, description_pl, active)
values (nextval('product_seq'), 'Broszka', 6, 25, 0, 0, 3, 2, 1, 1, 15,
        'Broszka to kaboszon obszyty koralikami Toho. Wysokość ok 3 cm. ',true);

commit;