CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `change_at` datetime DEFAULT NULL,
  `order_status` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `my_first_constraint` (`customer_id`),
  CONSTRAINT `constraint_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
