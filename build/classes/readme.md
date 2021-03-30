# WEB API

## Running Task2WebServletsAppHybris

## 1.Start MySQL database:

  Create datedase mysqldb:
  CREATE SCHEMA `mysqldb` DEFAULT CHARACTER SET utf8 ;

  CREATE TABLES:

  1.CREATE TABLE `mysqldb`.`products` (
      `id` INT NOT NULL AUTO_INCREMENT UNIQUE` (`order_id` ASC) VISIBLE; ,
      `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
      `price` INT NOT NULL,
      `status` ENUM('OUT_OF_STOCK', 'IN_STOCK', 'RUNNING_LOW') CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
      `created_at` DATETIME NULL,
      PRIMARY KEY (`id`));

  2.CREATE TABLE `mysqldb`.`orders` (
      `user_id` INT NOT NULL AUTO_INCREMENT,
      `id` INT NOT NULL,
      `create_at` DATETIME NOT NULL,
      `status` ENUM('OUT_OF_STOCK', 'IN_STOCK', 'RUNNING_LOW') CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
      PRIMARY KEY (`user_id`));

  3.CREATE TABLE `mysqldb`.`order_items` (
      `order_id` INT NOT NULL,
      `product_id` INT NOT NULL,
      `quantity` INT NOT NULL,
      INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
      INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
      CONSTRAINT `order_id`
        FOREIGN KEY (`order_id`)
        REFERENCES `mysqldb`.`orders` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `product_id`
        FOREIGN KEY (`product_id`)
        REFERENCES `mysqldb`.`products` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

## 2. Database configuration :

           driver-class-name: com.mysql.cj.jdbc.Driver
           url: jdbc:mysql://localhost:3306/mysqldb
           username: root
           password: root

##3. Start application:

  Plugin: Smart Tomcat
  Run: task2
  Server version name:   Apache Tomcat/9.0.44
  Run: task2










