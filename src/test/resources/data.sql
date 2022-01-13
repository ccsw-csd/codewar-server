



-- ***********************
-- * Master Data - Start *
-- ***********************
INSERT INTO challenge_status(id, code, name) VALUES(1, 'PND', 'Borrador');
INSERT INTO challenge_status(id, code, name) VALUES(2, 'ACT', 'Activado');
INSERT INTO challenge_status(id, code, name) VALUES(3, 'CLO', 'Cerrado');

INSERT INTO tag (id, code, name) VALUES(1, 'FACIL', 'Problema fácil');
INSERT INTO tag (id, code, name) VALUES(2, 'MEDIO', 'Problema medio');
INSERT INTO tag (id, code, name) VALUES(3, 'DIFICIL', 'Problema dificil');

INSERT INTO `role` (id, code, `role`) VALUES(1, 'ADMIN', 'Administrador');
INSERT INTO `role` (id, code, `role`) VALUES(2, 'MANAGER', 'Gestor');
INSERT INTO `role` (id, code, `role`) VALUES(3, 'DEVELOP', 'Programador');

INSERT INTO parameter_type (id, code, name) VALUES(1, 'String', 'String');
INSERT INTO parameter_type (id, code, name) VALUES(2, 'long', 'long');
INSERT INTO parameter_type (id, code, name) VALUES(3, 'String[]', 'String[]');
INSERT INTO parameter_type (id, code, name) VALUES(4, 'long[]', 'long[]');

INSERT INTO `user` (id, username, `role`, first_name, last_name, email, date_creation) 
VALUES(1, 'admin', (select id from role where code = 'ADMIN'), 'Admin', 'Admin', 'admin@email.com', '2021-05-03 02:00:00.0');

INSERT INTO `user` (id, username, `role`, first_name, last_name, email, date_creation) 
VALUES(2, 'gestor', (select id from role where code = 'MANAGER'), 'Gestor', 'Gestor', 'gestor@email.com', '2021-05-03 02:00:00.0');

INSERT INTO `user` (id, username, `role`, first_name, last_name, email, date_creation) 
VALUES(3, 'user', (select id from role where code = 'DEVELOP'), 'User', 'User', 'user@email.com', '2021-05-03 02:00:00.0');

-- *********************
-- * Master Data - End *
-- *********************



commit;