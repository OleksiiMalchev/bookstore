CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` bigint NOT NULL,
  `price` bigint  DEFAULT NULL,
  `discription` text,
  PRIMARY KEY (`id`),
  KEY `constraint_book_id` (`book_id`),
  CONSTRAINT `constraint_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
