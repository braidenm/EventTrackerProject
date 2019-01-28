-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema traveldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `traveldb` ;

-- -----------------------------------------------------
-- Schema traveldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `traveldb` DEFAULT CHARACTER SET utf8 ;
USE `traveldb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NULL,
  `street_2` VARCHAR(45) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  `zip` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `address_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `banned` TINYINT NOT NULL DEFAULT 0,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`),
  INDEX `fk_user_to_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trip` ;

CREATE TABLE IF NOT EXISTS `trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_to_user_idx` (`owner_id` ASC),
  CONSTRAINT `fk_trip_to_user`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity` ;

CREATE TABLE IF NOT EXISTS `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `description` VARCHAR(500) NULL,
  `address_id` INT NULL,
  `owner_id` INT NOT NULL,
  `trip_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_to_address_idx` (`address_id` ASC),
  INDEX `fk_activity_to_user_idx` (`owner_id` ASC),
  INDEX `fk_activity_to_trip_idx` (`trip_id` ASC),
  CONSTRAINT `fk_activity_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_to_user`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_to_trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_trip` ;

CREATE TABLE IF NOT EXISTS `user_trip` (
  `user_id` INT NOT NULL,
  `trip_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trip_id`),
  INDEX `fk_user_trip_to_trip_idx` (`trip_id` ASC),
  CONSTRAINT `fk_user_trip_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_trip_to_trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `picture` ;

CREATE TABLE IF NOT EXISTS `picture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trip_id` INT NOT NULL,
  `activity_id` INT NULL,
  `picture_url` VARCHAR(250) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_picture_to_trip_idx` (`trip_id` ASC),
  INDEX `fk_picture_to_activity_idx` (`activity_id` ASC),
  INDEX `fk_picture_to_user_idx` (`owner_id` ASC),
  CONSTRAINT `fk_picture_to_trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_picture_to_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_picture_to_user`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expenses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expenses` ;

CREATE TABLE IF NOT EXISTS `expenses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `trip_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_expenses_to_trip_idx` (`trip_id` ASC),
  INDEX `fk_expenses_to_activity_idx` (`activity_id` ASC),
  CONSTRAINT `fk_expenses_to_trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expenses_to_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_category` ;

CREATE TABLE IF NOT EXISTS `activity_category` (
  `activity_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `category_id`),
  INDEX `fk_activity)category_to)category_idx` (`category_id` ASC),
  CONSTRAINT `fk_activity_category_to_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity)category_to)category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(150) NOT NULL,
  `user_id` INT NOT NULL,
  `trip_id` INT NULL,
  `picture_id` INT NULL,
  `activity_id` INT NULL,
  `expense_id` INT NULL,
  `created_at` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_to_user_idx` (`user_id` ASC),
  INDEX `fk_comment_to_trip_idx` (`trip_id` ASC),
  INDEX `fk_comment_to_picture_idx` (`picture_id` ASC),
  INDEX `fk_comment_to_activity_idx` (`activity_id` ASC),
  INDEX `fk_comment_to_expense_idx` (`expense_id` ASC),
  CONSTRAINT `fk_comment_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_to_trip`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_to_picture`
    FOREIGN KEY (`picture_id`)
    REFERENCES `picture` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_to_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_to_expense`
    FOREIGN KEY (`expense_id`)
    REFERENCES `expenses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_user` ;

CREATE TABLE IF NOT EXISTS `activity_user` (
  `activity_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `user_id`),
  INDEX `fk_activity_user_to_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_activity_user_to_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_user_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS travel@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'travel'@'localhost' IDENTIFIED BY 'travel';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'travel'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`, `country`, `description`) VALUES (1, '4547 E Yale ave', NULL, 'Denver', 'CO', '80222', 'USA', NULL);
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`, `country`, `description`) VALUES (2, '7400 E Orchard Rd ', NULL, 'Greenwood Village,', 'CO', '80111', 'USA', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `user` (`id`, `last_name`, `first_name`, `username`, `password`, `address_id`, `active`, `banned`, `role`) VALUES (1, 'Admin', 'Admin', 'admin', 'admin', NULL, 1, 0, 'admin');
INSERT INTO `user` (`id`, `last_name`, `first_name`, `username`, `password`, `address_id`, `active`, `banned`, `role`) VALUES (2, 'Miller', 'Braiden', 'braidenm', 'password', 1, 1, 0, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trip`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `trip` (`id`, `name`, `description`, `owner_id`) VALUES (1, 'Test', 'Testing and stuff', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `activity` (`id`, `name`, `start_date`, `end_date`, `description`, `address_id`, `owner_id`, `trip_id`) VALUES (1, 'Test Activity', '2019-01-31', '2019-01-31', 'testing this activity', 2, 2, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Hiking');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Trail Running');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'SnowBoarding');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Skiing');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Rock Climbing');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Ice Climbing');
INSERT INTO `category` (`id`, `name`) VALUES (7, '14ner');
INSERT INTO `category` (`id`, `name`) VALUES (8, 'Beach');
INSERT INTO `category` (`id`, `name`) VALUES (9, 'Snorkling');
INSERT INTO `category` (`id`, `name`) VALUES (10, 'Diving');
INSERT INTO `category` (`id`, `name`) VALUES (11, 'Camping');
INSERT INTO `category` (`id`, `name`) VALUES (12, 'SUP');
INSERT INTO `category` (`id`, `name`) VALUES (13, 'Rafting');
INSERT INTO `category` (`id`, `name`) VALUES (14, 'Paddling');
INSERT INTO `category` (`id`, `name`) VALUES (15, 'Other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_trip`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `user_trip` (`user_id`, `trip_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `activity_user` (`activity_id`, `user_id`) VALUES (1, 2);

COMMIT;

