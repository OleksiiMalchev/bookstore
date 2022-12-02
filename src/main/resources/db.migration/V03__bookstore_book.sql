CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `cover` varchar(128) DEFAULT NULL,
  `publishing_house` varchar(128) DEFAULT NULL,
  `year_of_publication` date DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `bar_code` int DEFAULT NULL,
  `pages` int DEFAULT NULL,
  `isbn` int DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `cost` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `my_first_constraint` (`author_id`),
  CONSTRAINT `my_first_constraint` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
