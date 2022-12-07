CREATE TABLE `order_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `name_status` varchar(128) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `order_id_constraint` (`order_id`),
   CONSTRAINT `order_id_constraint` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
