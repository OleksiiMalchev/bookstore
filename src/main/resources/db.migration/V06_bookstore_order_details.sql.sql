CREATE TABLE IF NOT EXISTS `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `my_first_constraint` (`order_id`),
  CONSTRAINT `my_first_constraint` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
  KEY `my_second_constraint` (`product_id`),
  CONSTRAINT `my_second_constraint` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
