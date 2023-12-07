-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema coffee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema coffee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coffee` DEFAULT CHARACTER SET utf8 ;
USE `coffee` ;

-- -----------------------------------------------------
-- Table `coffee`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffee`.`client` (
  `cpf` VARCHAR(14) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `birthDate` VARCHAR(10) NOT NULL,
  `estate` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `isAdmin` BOOLEAN NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cpf`));


-- -----------------------------------------------------
-- Table `coffee`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffee`.`stock` (
  `id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `coffeeType` VARCHAR(45) NOT NULL,
  `coffeeCupping` DOUBLE NOT NULL,
  `client_cpf` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_stock_client_idx` (`client_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_stock_client`
    FOREIGN KEY (`client_cpf`)
    REFERENCES `coffee`.`client` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
