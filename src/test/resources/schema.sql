CREATE SCHEMA codewar; 
use codewar;

CREATE TABLE challenge_status (
  id int(11) IDENTITY NOT NULL PRIMARY KEY,
  code varchar(3) NOT NULL,
  name varchar(70) NOT NULL,
  UNIQUE KEY type_UNIQUE (code),
  UNIQUE KEY id_UNIQUE (id)
);



CREATE TABLE `parameter_type` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `code` varchar(8) NOT NULL,
  `name` varchar(40) NOT NULL,
  UNIQUE KEY `types_un` (`code`)
);



CREATE TABLE `role` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `code` varchar(10) NOT NULL,
  `role` varchar(70) NOT NULL,
  UNIQUE KEY `code_UNIQUE` (`code`)
);





CREATE TABLE `tag` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `code` varchar(30) DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL
);


CREATE TABLE `user` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `username` varchar(45) NOT NULL,
  `role` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  UNIQUE KEY `username_unique` (`username`),
  UNIQUE KEY `email_unique` (`email`),
  INDEX (`role`),
  FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `challenge` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `user_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `description` text,
  `base_code` text,
  `function_name` varchar(25) DEFAULT NULL,
  `class_name` varchar(25) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `tries` tinyint(1) DEFAULT NULL,
  INDEX (`status_id`),
  INDEX (`user_id`),
  FOREIGN KEY (`status_id`) REFERENCES `challenge_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE `challenge_parameter` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `challenge_id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `parameter_type_id` int(11) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `is_input` tinyint(1) DEFAULT '1',
  UNIQUE KEY `challenge_parameter_UNIQUE` (`challenge_id`,`order`),
  INDEX (`challenge_id`),
  INDEX (`parameter_type_id`),
  FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`parameter_type_id`) REFERENCES `parameter_type` (`id`)
);


CREATE TABLE `challenge_tag` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `challenge_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  INDEX (`challenge_id`),
  INDEX (`tag_id`),
  FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);





CREATE TABLE `challenge_test` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `challenge_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `max_time` int(11) DEFAULT '0',
  `is_visible` tinyint(1) DEFAULT '0',
  `is_performance` tinyint(1) DEFAULT '0',
  UNIQUE KEY `challenge_test_UNIQUE` (`challenge_id`,`order`),
  INDEX (`challenge_id`),
  FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `challenge_test_value` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `challenge_test_id` int(11) NOT NULL,
  `challenge_parameter_id` int(11) NOT NULL,
  `value` longtext NOT NULL,
  INDEX (`challenge_test_id`),
  INDEX (`challenge_parameter_id`),
  FOREIGN KEY (`challenge_parameter_id`) REFERENCES `challenge_parameter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`challenge_test_id`) REFERENCES `challenge_test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE `participation` (
  `id` int(11) IDENTITY NOT NULL PRIMARY KEY,
  `user_id` int(11) NOT NULL,
  `challenge_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `code` text,
  `total_score` float DEFAULT '0',
  `correction_score` int(11) DEFAULT '0',
  `efficiency_score` int(11) DEFAULT '0',
  `execution_time` int(11) DEFAULT NULL,
  INDEX (`challenge_id`),
  INDEX (`user_id`),
  FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);







