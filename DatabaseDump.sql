CREATE DATABASE  IF NOT EXISTS `mit_fahr_zentrale` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mit_fahr_zentrale`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mit_fahr_zentrale
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `id` int(11) NOT NULL auto_increment,
  `starting_point` varchar(60) NOT NULL,
  `destination` varchar(60) NOT NULL,
  `price` double NOT NULL,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `username` varchar(80) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_offers_users_idx` (`username`),
  CONSTRAINT `fk_offers_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (31,'München','Innsbruck',30,'2016-10-09 11:45:00','espendennis'),(32,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(33,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(34,'München','Innsbruck',30,'2016-10-07 11:45:00','espendennis'),(35,'Paderborn','München',26,'2016-08-26 12:34:00','espendennis'),(36,'Paderborn','München',23,'2016-08-19 11:45:00','espendennis'),(37,'München','Innsbruck',23,'2016-08-19 11:45:00','espendennis'),(38,'München','Innsbruck',23,'2016-08-19 11:45:00','espendennis'),(39,'München','München',23,'2016-08-19 11:45:00','espendennis'),(40,'Innsbruck','Bludenz',23,'2016-08-19 11:45:00','espendennis'),(41,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(42,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(43,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(44,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(45,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(46,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(47,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(48,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(49,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(50,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(51,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(52,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(53,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(54,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(55,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(56,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(60,'München','Inn',30,'2016-10-11 11:45:00','espendennis'),(61,'München','Innsbruck',30,'2016-10-11 11:45:00','espendennis'),(62,'München','Zürich',23,'2016-08-28 16:13:13','espendennis'),(63,'München','Zürich',23,'2016-08-28 16:13:13','espendennis'),(65,'Berlin','München',40,'2016-10-11 11:45:00','espendennis');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(80) NOT NULL,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `authority` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(30) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Admin','Admin','Admin','admin@example.com','ROLE_ADMIN','$2a$10$dyYNLMB1l1FQeyOORqIXxub.t0HfNRMMTb4Yxt17qj8eNJnpzQPsW','01715648561'),('espendennis','Dennis','Espen','dennis.espen@hotmail.com','ROLE_ADMIN','$2a$10$A.W/SPZMPvGoGutMvuRFJu5Jv4Lalwi100e9Mqhclz.tuGP6A9KuO','12334');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-28 20:22:41
