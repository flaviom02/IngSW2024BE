CREATE DATABASE  IF NOT EXISTS `events_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `events_db`;
-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: events_db
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,_binary '\0','Musica'),(2,_binary '','Teatro'),(3,_binary '\0','Film'),(4,_binary '','Pop'),(5,_binary '\0','Techno'),(6,_binary '\0','Food'),(7,_binary '\0','Convegni');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_categories`
--

DROP TABLE IF EXISTS `event_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_categories` (
  `category_id` int NOT NULL,
  `event_id` bigint NOT NULL,
  KEY `FK25gan1thxtb38jlco3w9pjsdo` (`event_id`),
  KEY `FKr1pvd2finvdolgyyntti13d3x` (`category_id`),
  CONSTRAINT `FK25gan1thxtb38jlco3w9pjsdo` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`),
  CONSTRAINT `FKr1pvd2finvdolgyyntti13d3x` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_categories`
--

LOCK TABLES `event_categories` WRITE;
/*!40000 ALTER TABLE `event_categories` DISABLE KEYS */;
INSERT INTO `event_categories` VALUES (4,37),(1,37),(6,42),(1,42),(5,41),(1,41);
/*!40000 ALTER TABLE `event_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_id_gen`
--

DROP TABLE IF EXISTS `event_id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_id_gen` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_id_gen`
--

LOCK TABLES `event_id_gen` WRITE;
/*!40000 ALTER TABLE `event_id_gen` DISABLE KEYS */;
INSERT INTO `event_id_gen` VALUES (43);
/*!40000 ALTER TABLE `event_id_gen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `event_id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `url_img` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (37,'Stadio Olimpico Roma','2024-07-12',' I Coldplay sono un gruppo alternative rock britannico formatosi a Londra nel 1997.','Coldplay World Tour 2024','Terminato','21:00:00.000000','https://www.ticketone.it/obj/media/IT-eventim/galery/kuenstler/c/coldplay-2022-02.jpg'),(41,'Cocoricò / Riccione','2024-08-02','In consolle GIGI D’AGOSTINO.Pronti a scatenarvi con lui sotto la magica piramide?','GIGI D\'AGOSTINO','Confermato','23:30:00.000000','https://cdn.prod.www.spiegel.de/images/3b671a7d-c7ee-4ff1-92d9-6c770a5c0531_w256_r1_fpx53_fpy24.png'),(42,'Adria','2024-08-09','Negozi aperti, danza, musica, e altro!','Adria D\'Estate','Confermato','18:00:00.000000','');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_table`
--

DROP TABLE IF EXISTS `my_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_table` (
  `id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_table`
--

LOCK TABLES `my_table` WRITE;
/*!40000 ALTER TABLE `my_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `my_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizer_categories`
--

DROP TABLE IF EXISTS `organizer_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizer_categories` (
  `organizer_id` bigint NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`organizer_id`,`category_id`),
  KEY `FKav17rdntqfduty9twi8xjrxt1` (`category_id`),
  CONSTRAINT `FKav17rdntqfduty9twi8xjrxt1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `FKtk61fcfhigd7ijgldr13sbwcn` FOREIGN KEY (`organizer_id`) REFERENCES `organizers` (`organizer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizer_categories`
--

LOCK TABLES `organizer_categories` WRITE;
/*!40000 ALTER TABLE `organizer_categories` DISABLE KEYS */;
INSERT INTO `organizer_categories` VALUES (1,1),(4,1),(5,1),(3,2),(1,3),(5,3),(4,4),(5,6),(3,7);
/*!40000 ALTER TABLE `organizer_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizers`
--

DROP TABLE IF EXISTS `organizers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizers` (
  `organizer_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`organizer_id`),
  UNIQUE KEY `UK_3507nf5g1ylv098m0dkd2cruk` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizers`
--

LOCK TABLES `organizers` WRITE;
/*!40000 ALTER TABLE `organizers` DISABLE KEYS */;
INSERT INTO `organizers` VALUES (1,'flavio.marangon@edu.unife.it'),(4,'francesco.teti@edu.unife.it'),(5,'michalandrea.borella@edu.unife.it'),(3,'pierpaolo.paio@edu.unife.it');
/*!40000 ALTER TABLE `organizers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_id_gen`
--

DROP TABLE IF EXISTS `ticket_id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_id_gen` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_id_gen`
--

LOCK TABLES `ticket_id_gen` WRITE;
/*!40000 ALTER TABLE `ticket_id_gen` DISABLE KEYS */;
INSERT INTO `ticket_id_gen` VALUES (61);
/*!40000 ALTER TABLE `ticket_id_gen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickettypes`
--

DROP TABLE IF EXISTS `tickettypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickettypes` (
  `ticket_id` bigint NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `event_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FKju9x1ems1bakx6jxinc6fi689` (`event_id`),
  CONSTRAINT `FKju9x1ems1bakx6jxinc6fi689` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickettypes`
--

LOCK TABLES `tickettypes` WRITE;
/*!40000 ALTER TABLE `tickettypes` DISABLE KEYS */;
INSERT INTO `tickettypes` VALUES (17,23.00,'2',NULL),(55,90.00,'PRIMO SETTORE',37),(56,80.00,'SECONDO SETTORE',37),(57,120.00,'INTERO PIT',37),(58,35.00,'PREV. UNISEX',41),(59,20.00,'PREV. RESIDENTI',41),(60,0.00,'GRATUITO',42);
/*!40000 ALTER TABLE `tickettypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-01 22:22:30
