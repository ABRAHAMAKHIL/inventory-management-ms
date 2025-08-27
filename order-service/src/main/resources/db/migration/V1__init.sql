CREATE TABLE `orders` (
  `id` BIGINT(26) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(255) DEFAULT NULL,
  `sku_code` VARCHAR(255),
  `price` DECIMAL(19,2),
  `quantity` INT(11),
  PRIMARY KEY (`id`)
);