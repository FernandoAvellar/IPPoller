-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ippoller
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ippoller
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ippoller` DEFAULT CHARACTER SET utf8 ;
USE `ippoller` ;

-- -----------------------------------------------------
-- Table `ippoller`.`ipstatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ippoller`.`ipstatus` (
  `host` VARCHAR(15) NOT NULL,
  `status` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`host`),
  UNIQUE INDEX `host_UNIQUE` (`host` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
