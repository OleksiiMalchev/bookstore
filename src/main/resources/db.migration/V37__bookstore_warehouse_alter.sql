ALTER TABLE warehouse DROP FOREIGN KEY warehouse_ibfk_1;
ALTER TABLE warehouse  CHANGE book_id product_id bigint NOT NULL;
ALTER TABLE warehouse  CHANGE initional_price initial_price bigint NOT NULL;
ALTER TABLE product  CHANGE discription description text;


