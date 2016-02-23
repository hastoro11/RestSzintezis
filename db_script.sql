CREATE DATABASE  IF NOT EXISTS `activity` /*!40100 DEFAULT CHARACTER SET latin2 COLLATE latin2_hungarian_ci */;
USE `activity`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: activity
-- ------------------------------------------------------
-- Server version	5.5.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin2_hungarian_ci NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cat_idx` (`parentid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (1,'Munka',0);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (2,'Otthon',NULL);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (3,'Hobbi',NULL);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (4,'Dokumentálás',1);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (5,'Értekezlet',1);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (6,'Ház körüli munkák',2);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (7,'Játék',2);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (8,'Sport',3);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (9,'Kirándulás',3);
INSERT INTO `categories` (`id`, `name`, `parentid`) VALUES (10,'Egyéb',NULL);

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start` varchar(17) COLLATE latin2_hungarian_ci NOT NULL,
  `end` varchar(17) COLLATE latin2_hungarian_ci NOT NULL,
  `name` varchar(45) COLLATE latin2_hungarian_ci NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cat_idx` (`category_id`),
  CONSTRAINT `fk_act_cat` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (33,'2016.02.23. 15:30','2016.02.23. 16:0','1',4);
INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (34,'2016.02.23. 16:38','2016.02.23. 16:39','ee',4);
INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (35,'2016.02.23. 17:38','2016.02.23. 18:38','ww',6);
INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (36,'2016.02.22. 16:41','2016.02.22. 17:41','Foci',8);
INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (37,'2016.02.22. 17:49','2016.02.22. 18:49','Kosár',8);
INSERT INTO `activity` (`id`, `start`, `end`, `name`, `category_id`) VALUES (38,'2016.02.23. 19:34','2016.02.23. 19:36','Ég?csere',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
