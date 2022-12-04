CREATE TABLE `warehouse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_quantity` int DEFAULT NULL,
  `initional_price` bigint  DEFAULT NULL,
  `reserve` int DEFAULT NULL,
  `sale` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
