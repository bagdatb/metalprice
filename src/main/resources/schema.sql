--Below DROPs are for testing purpose only
--DROP TABLE IF EXISTS price;
--DROP TABLE IF EXISTS metal;

CREATE TABLE metal (
  id INT PRIMARY KEY,
  name VARCHAR(500) NOT NULL
);

CREATE TABLE price (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  price_date DATE NOT NULL,
  metal_id INT NOT NULL,
  value DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (metal_id) REFERENCES metal (id)
);

CREATE UNIQUE INDEX uniquePriceIndex ON price (price_date, metal_id);
CREATE INDEX metalIDIndex ON price (metal_id);
CREATE INDEX priceDateIndex ON price (price_date DESC);