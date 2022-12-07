CREATE TABLE `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `my_constraint1` (`product_id`),
   CONSTRAINT `my_constraint1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
   KEY `order_id_constraint` (`order_id`),
   CONSTRAINT `order_id_constraint` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
