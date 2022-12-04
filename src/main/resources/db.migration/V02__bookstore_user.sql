CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` bigint DEFAULT NULL,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(128) DEFAULT NULL,
  `nick_name` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

