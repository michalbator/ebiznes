# --- !Ups
# --- !Downs
CREATE DATABASE IF NOT EXISTS biblioteka;
use biblioteka;

CREATE TABLE `users` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `service` varchar(30) NOT NULL,
 `key` varchar(50) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `countries`
(
  `id`         int(11)     NOT NULL AUTO_INCREMENT,
  `name`       varchar(50) NOT NULL,
  `short_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `authors`
(
  `id`      int(11)     NOT NULL AUTO_INCREMENT,
  `name`    varchar(50) NOT NULL,
  `country` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `country` (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `books`
(
  `id`     int(11)      NOT NULL AUTO_INCREMENT,
  `isbn`   varchar(13)  NOT NULL,
  `title`  varchar(100) NOT NULL,
  `author` int(11)      NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `orders`
(
  `id`     int(11)     NOT NULL AUTO_INCREMENT,
  `user`   varchar(255)    NOT NULL,
  `book`    int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `book` (`book`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- CREATE TABLE `bookOrders`
-- (
--   `id`      int(11) NOT NULL,
--   `book`    int(11) NOT NULL,
--   `idOrder` int(11) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `idOrder` (`idOrder`),
--   KEY `book` (`book`)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_0900_ai_ci;

# --- !Downs

-- DROP TABLE IF EXISTS `countries`;
-- DROP TABLE IF EXISTS `authors`;
-- DROP TABLE IF EXISTS `books`;
-- DROP TABLE IF EXISTS `users`;
-- -- DROP TABLE IF EXISTS `bookOrders`;
-- DROP TABLE IF EXISTS `orders`;