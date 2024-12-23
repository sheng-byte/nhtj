-- MySQL dump 10.13  Distrib 5.6.49, for Win64 (x86_64)
--
-- Host: localhost    Database: nhtj-tree
-- ------------------------------------------------------
-- Server version	5.6.49-log

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
-- Table structure for table `tree`
--

DROP TABLE IF EXISTS `tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tree` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(100) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `tfl` text,
  `tcolor` varchar(100) DEFAULT NULL,
  `ttime` varchar(100) DEFAULT NULL,
  `spring` int(11) DEFAULT '0',
  `summer` int(11) DEFAULT '0',
  `autumn` int(11) DEFAULT '0',
  `winter` int(11) DEFAULT '0',
  `aid` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree`
--

LOCK TABLES `tree` WRITE;
/*!40000 ALTER TABLE `tree` DISABLE KEYS */;
INSERT INTO `tree` VALUES (1,'梨花',1,'梨子','白色','1-3月',1,0,0,0,1,'32479476_1395812465851.jpg'),(2,'荷花',1,'荷','白色','2-5月',1,0,0,0,1,'41f6d505-0609-4340-baa4-8195399491a5.jpg'),(5,'1',1,'<p>11</p>','1','1',1,0,0,0,NULL,'j3mf8fd2.png'),(6,'4',1,'<p>1</p>','4','4',0,0,1,1,NULL,'41f6d505-0609-4340-baa4-8195399491a5.jpg'),(7,'5',1,'<p>1</p>','5','5',1,0,0,0,NULL,'9950a8ec-2980-4e0e-86dc-c287f5bd6dc4.jpg'),(8,'1',1,'<p>111111</p>','1','1',0,1,1,0,NULL,'1b7b067b-c3c6-4b42-bc98-2663e7bd0514.jpg'),(10,'3',1,'<p>123123123</p>','3','3',1,0,0,0,NULL,'100b6d21-133c-4f7b-a29c-714def14bb31.jpg'),(11,'123',1,'','123','1',1,0,0,0,NULL,'beb07274-a91b-47eb-a024-2d1df6339e96.jpg'),(12,'qe',1,'<p>123123</p>','123','123123',1,1,1,0,NULL,'21534U109-25.jpg'),(13,'22',1,'<p>2123</p>','22','23',1,1,0,0,NULL,'tupc3lu1.png'),(14,'123',1,'<p>153</p>','15','5',0,1,0,0,NULL,'7e0757ba-3aca-4512-88fd-7765681ead55.jpg'),(15,'2',1,'<p>z</p>','2','2',0,0,1,0,NULL,'85096607-4ac6-4855-8e07-b6b45f14fa0f.jpg'),(16,'51',1,'<p>4</p>','22','22',1,0,0,0,NULL,'9a423868-d7f0-4726-bea5-39230a96f614.jpg'),(17,'855556',1,'<p>22</p>','4','5',1,1,0,0,NULL,'e3f7fba9-b7db-49eb-811b-27856228d2a7.jpg'),(18,'131123123123',1,'<p>123</p>','312','123123',1,1,0,0,NULL,'4e5ba9ee-e9e1-49fb-9395-0f42a6f72621.jpg'),(19,'11',1,'<p>11111</p>','11','111',1,0,0,0,NULL,'d3fc0efe-bfba-430b-9c6b-bd3098185c4f.jpg'),(20,'大列巴',0,'<p>干干脆脆，健康美味</p>','20','5',0,0,0,1,NULL,'07edc04b-4850-48c1-b0ef-b9ef8c02bef7.jpg'),(21,'荞麦列巴',0,'<p>美味又低脂</p>','20','8',0,1,0,0,NULL,'31ea723e-6c98-4360-99de-56610e3947c2.jpg'),(22,'11',1,'<p>1</p>','2','1',0,1,0,0,NULL,'fa9e38d5-0f85-402f-b491-b30fd91f01c6.jpg');
/*!40000 ALTER TABLE `tree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nhtj-tree'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-20 13:54:51
