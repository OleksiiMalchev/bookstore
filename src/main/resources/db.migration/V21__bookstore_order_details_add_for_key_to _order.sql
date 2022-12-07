ALTER TABLE order_details ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);

