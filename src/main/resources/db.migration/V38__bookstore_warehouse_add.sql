UPDATE warehouse SET product_id=1 WHERE id=2;
UPDATE warehouse SET product_id=2 WHERE id=3;
UPDATE warehouse SET product_id=3 WHERE id=4;
UPDATE warehouse SET product_id=4 WHERE id=5;
UPDATE warehouse SET product_id=5 WHERE id=6;
UPDATE warehouse SET product_id=6 WHERE id=7;
ALTER TABLE warehouse ADD FOREIGN KEY (product_id) REFERENCES product (id);


