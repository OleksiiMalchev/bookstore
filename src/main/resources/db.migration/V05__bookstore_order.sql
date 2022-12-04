CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `order_status_id` bigint NOT NULL,
  `shipment_id` bigint NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `change_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `my_first_constraint` (`customer_id`),
  CONSTRAINT `my_first_constraint` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  KEY `my_second_constraint` (`order_status_id`),
  CONSTRAINT `my_second_constraint` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`),
  KEY `my_third_constraint` (`shipment_id`),
  CONSTRAINT `my_third_constraint` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
