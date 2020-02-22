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

INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'bracelets', 'bransoletki');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'necklaces', 'naszyjniki');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'sets', 'komplety');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'pendants', 'wisiory');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'earrings', 'kolczyki');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'brooches', 'broszki');
INSERT INTO type (id, name, name_Pl)
values (nextval('type_seq'), 'decorations', 'ozdoby świąteczne');


INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'TOHO beads', 'koraliki TOHO');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'glass crystals', 'szklane kryształki');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'pearls, mother of pearl', 'perły, masa perłowa');
INSERT INTO material (id, name, name_pl)
values (nextval('material_seq'), 'mix', 'mix');
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

INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czerwonymi kryształkami.', 1, 'czerwony', 190, 7, 1, 'srebrny', 1, 2, 15,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Naszyjnik z źółtymi kryształkami.', 2, 'żółty', 190, 7, 1, 'srebrny', 1, 2, 20,
        'Delikatna Naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Naszyjnik z niebieskimi kryształkami.', 2, 'niebieski', 190, 7, 1, 'srebrny', 1, 2, 32,
        'Delikatny naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czarnymi kryształkami.', 1, 'czarny', 190, 7, 1, 'srebrny', 1, 2, 23,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Bransoletka z czarnymi kryształkami.', 1, 'czarny', 190, 7, 1, 'srebrny', 1, 2, 23,
        'Delikatna bransoleta z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Komplet Bransoletka + naszyjnik z czarnymi kryształkami.', 3, 'czarny', 190, 7, 1,
        'srebrny', 1, 2, 23,
        'Komplet bransoleta + naszyjnik z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Wisior z czarnymi kryształkami.', 4, 'czarny', 190, 7, 1, 'srebrny', 1, 2, 23,
        'Wisior dla każdego z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Kolczyki z czarnymi kryształkami.', 5, 'czarny', 190, 7, 1, 'srebrny', 1, 2, 23,
        'Kolczyki marzeń z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');
INSERT INTO product (id, name, type_id, color, length, width, fastening_type_id, fastening_color, material_id,
                     making_technique_id, price, description)
values (nextval('product_seq'), 'Broszka z czarnymi kryształkami.', 6, 'czarny', 190, 7, 1, 'srebrny', 1, 2, 23,
        'Delikatna broszka z koralików TOHO w połączeniu ze szklanymi kryształkami w kolorze czerwonym, baza bransoletki z koralików w kolorze srebrnym. Zapięcie na karabinek w kolorze srebrnym. .');


commit;