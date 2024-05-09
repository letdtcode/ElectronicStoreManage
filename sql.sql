-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: electronic_store_manage
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_hsu7w3m7wxvplg49ip7g0v5rr` (`brand_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'anonymousUser','2024-05-05 17:12:01.000000','anonymousUser','2024-05-05 17:12:01.000000',0,'Microsoft'),(2,'anonymousUser','2024-05-05 17:12:05.000000','anonymousUser','2024-05-05 17:12:05.000000',0,'Samsung'),(3,'anonymousUser','2024-05-05 17:12:09.000000','anonymousUser','2024-05-05 17:12:09.000000',0,'LG'),(4,'anonymousUser','2024-05-05 17:12:15.000000','anonymousUser','2024-05-05 17:12:15.000000',0,'Sony'),(5,'anonymousUser','2024-05-05 17:12:23.000000','anonymousUser','2024-05-05 17:12:23.000000',0,'Corsair'),(6,'anonymousUser','2024-05-05 17:12:27.000000','anonymousUser','2024-05-05 17:12:27.000000',0,'ASUS'),(7,'anonymousUser','2024-05-05 17:12:32.000000','anonymousUser','2024-05-05 17:12:32.000000',0,'Intel'),(8,'anonymousUser','2024-05-05 17:12:37.000000','anonymousUser','2024-05-05 17:12:37.000000',0,'AMD'),(9,'anonymousUser','2024-05-05 17:12:41.000000','anonymousUser','2024-05-05 17:12:41.000000',0,'Apple'),(10,'anonymousUser','2024-05-05 17:12:45.000000','anonymousUser','2024-05-05 17:12:45.000000',0,'Dell'),(11,'anonymousUser','2024-05-05 17:12:48.000000','anonymousUser','2024-05-05 17:12:48.000000',0,'HP '),(12,'anonymousUser','2024-05-05 17:41:16.000000','anonymousUser','2024-05-05 17:41:16.000000',0,'Trung Quốc'),(13,'anonymousUser','2024-05-05 18:28:03.000000','anonymousUser','2024-05-05 18:28:03.000000',0,'DareU'),(14,'anonymousUser','2024-05-06 14:50:05.000000','anonymousUser','2024-05-06 14:50:05.000000',0,'Pasion ');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'anonymousUser','2024-05-05 11:22:04.000000','anonymousUser','2024-05-05 11:22:04.000000',0,'Chuột '),(2,'anonymousUser','2024-05-05 11:22:42.000000','anonymousUser','2024-05-05 11:22:42.000000',0,'Bàn phím'),(3,'anonymousUser','2024-05-05 11:22:52.000000','anonymousUser','2024-05-05 11:22:52.000000',0,'Màn hình'),(4,'anonymousUser','2024-05-05 11:23:00.000000','anonymousUser','2024-05-05 11:23:00.000000',0,'Tai nghe '),(5,'anonymousUser','2024-05-05 11:23:06.000000','anonymousUser','2024-05-05 11:23:06.000000',0,'Loa '),(6,'anonymousUser','2024-05-05 11:23:17.000000','anonymousUser','2024-05-05 11:23:17.000000',0,'Ổ cứng SSD'),(7,'anonymousUser','2024-05-05 11:23:23.000000','anonymousUser','2024-05-05 11:23:23.000000',1,'RAM '),(8,'anonymousUser','2024-05-05 11:23:29.000000','anonymousUser','2024-05-05 11:23:29.000000',1,'GPU'),(9,'anonymousUser','2024-05-05 11:23:49.000000','anonymousUser','2024-05-05 11:23:49.000000',1,'Card đồ họa'),(10,'anonymousUser','2024-05-05 11:23:54.000000','anonymousUser','2024-05-05 11:23:54.000000',1,' Bo mạch chủ'),(11,'anonymousUser','2024-05-05 11:24:04.000000','anonymousUser','2024-05-05 11:24:04.000000',0,'Nguồn máy tính '),(12,'anonymousUser','2024-05-05 11:24:10.000000','anonymousUser','2024-05-05 11:24:10.000000',0,'Bộ xử lý CPU'),(13,'anonymousUser','2024-05-05 11:24:17.000000','anonymousUser','2024-05-05 11:24:17.000000',0,' Ổ đĩa cứng HDD'),(14,'anonymousUser','2024-05-05 11:24:27.000000','anonymousUser','2024-05-05 11:24:27.000000',0,'Card mạng'),(15,'anonymousUser','2024-05-05 11:24:35.000000','anonymousUser','2024-05-05 11:24:35.000000',0,'Tản nhiệt CPU'),(16,'anonymousUser','2024-05-05 11:24:44.000000','anonymousUser','2024-05-05 11:24:44.000000',1,'Bộ nhớ Flash Drive'),(17,'anonymousUser','2024-05-05 11:24:51.000000','anonymousUser','2024-05-05 11:24:51.000000',0,'Webcam'),(18,'anonymousUser','2024-05-05 11:25:00.000000','anonymousUser','2024-05-05 11:25:00.000000',0,'USB'),(19,'anonymousUser','2024-05-05 11:25:10.000000','anonymousUser','2024-05-05 11:25:10.000000',0,'Sạc dự phòng'),(20,'anonymousUser','2024-05-06 00:37:59.000000','anonymousUser','2024-05-06 00:37:59.000000',0,'Sạc, cáp'),(21,'anonymousUser','2024-05-06 00:38:27.000000','anonymousUser','2024-05-06 00:38:27.000000',0,'Micro'),(22,'anonymousUser','2024-05-06 00:39:26.000000','anonymousUser','2024-05-06 00:39:26.000000',0,'Thẻ nhớ'),(23,'anonymousUser','2024-05-06 00:39:46.000000','anonymousUser','2024-05-06 00:39:46.000000',0,'Camera'),(24,'anonymousUser','2024-05-06 15:47:48.000000','anonymousUser','2024-05-06 15:47:48.000000',0,'Máy chiếu'),(25,'anonymousUser','2024-05-06 15:59:04.000000','anonymousUser','2024-05-06 15:59:04.000000',0,'Giá đỡ điện thoại');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `color_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_7jag82tp83ne6q9lbjr0lf1q1` (`color_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'anonymousUser','2024-05-05 17:08:48.000000','anonymousUser','2024-05-05 17:08:48.000000',0,'Đen '),(2,'anonymousUser','2024-05-05 17:08:51.000000','anonymousUser','2024-05-05 17:08:51.000000',0,'Trắng '),(3,'anonymousUser','2024-05-05 17:08:57.000000','anonymousUser','2024-05-05 17:08:57.000000',0,'Xám '),(4,'anonymousUser','2024-05-05 17:09:01.000000','anonymousUser','2024-05-05 17:09:01.000000',0,'Bạc '),(5,'anonymousUser','2024-05-05 17:09:06.000000','anonymousUser','2024-05-05 17:09:06.000000',0,'Vàng'),(6,'anonymousUser','2024-05-05 17:09:11.000000','anonymousUser','2024-05-05 17:09:11.000000',0,'Đỏ '),(7,'anonymousUser','2024-05-05 17:09:16.000000','anonymousUser','2024-05-05 17:09:16.000000',0,'Xanh dương'),(8,'anonymousUser','2024-05-05 17:09:21.000000','anonymousUser','2024-05-05 17:09:21.000000',0,'Xanh lá '),(9,'anonymousUser','2024-05-05 17:09:25.000000','anonymousUser','2024-05-05 17:09:25.000000',0,'Violet '),(10,'anonymousUser','2024-05-05 17:09:30.000000','anonymousUser','2024-05-05 17:09:30.000000',0,'Cam '),(11,'anonymousUser','2024-05-05 17:09:34.000000','anonymousUser','2024-05-05 17:09:34.000000',0,'Nâu'),(12,'anonymousUser','2024-05-05 17:09:39.000000','anonymousUser','2024-05-05 17:09:39.000000',0,'Hồng '),(13,'anonymousUser','2024-05-05 17:09:43.000000','anonymousUser','2024-05-05 17:09:43.000000',0,'Tím '),(14,'anonymousUser','2024-05-05 17:09:49.000000','anonymousUser','2024-05-05 17:09:49.000000',0,'Xanh da trời'),(15,'anonymousUser','2024-05-05 17:09:55.000000','anonymousUser','2024-05-05 17:09:55.000000',0,'Hồng phấn'),(16,'anonymousUser','2024-05-05 17:10:07.000000','anonymousUser','2024-05-05 17:10:07.000000',0,'Đen huyền'),(17,'anonymousUser','2024-05-05 17:10:11.000000','anonymousUser','2024-05-05 17:10:11.000000',0,'Xám titan'),(18,'anonymousUser','2024-05-05 17:10:16.000000','anonymousUser','2024-05-05 17:10:16.000000',0,'Xanh lá cây'),(19,'anonymousUser','2024-05-05 17:10:23.000000','anonymousUser','2024-05-05 17:10:23.000000',0,'Cam đất'),(20,'anonymousUser','2024-05-05 17:10:28.000000','anonymousUser','2024-05-05 17:10:28.000000',0,'Xanh lam');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `sex` enum('MALE','FEMALE') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_rosd2guvs3i1agkplv5n8vu82` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'anonymousUser','2024-05-06 01:07:51.000000','anonymousUser','2024-05-06 01:07:51.000000',0,'Quận 4, Hồ Chí Minh','TranTam0304@gmail.com','Trần Minh Tâm','0343523965','MALE','ACTIVE'),(2,'anonymousUser','2024-05-06 01:08:35.000000','anonymousUser','2024-05-06 01:08:35.000000',0,'Quận 10, Hồ Chí Minh','LeNhi1403@gmail.com','Lê Thảo Nhi','0353868913','FEMALE','ACTIVE'),(3,'anonymousUser','2024-05-06 01:09:24.000000','anonymousUser','2024-05-06 01:09:24.000000',0,'Quận Thủ Đức, Hồ Chí Minh','NguyenHuuTam@gmail.com','Nguyễn Hữu Tâm','0374253986','MALE','ACTIVE'),(4,'anonymousUser','2024-05-06 01:09:58.000000','anonymousUser','2024-05-06 01:09:58.000000',0,'Quận 1, Hồ Chí Minh','TranTrong1305@gmail.com','Trần Bình Trọng','0374523698','MALE','ACTIVE'),(5,'anonymousUser','2024-05-06 01:10:25.000000','anonymousUser','2024-05-06 01:10:25.000000',0,'Quận 1, Hồ Chí Minh','TienNguyen@gmail.com','Nguyễn Quốc Tiến','0342536789','MALE','ACTIVE'),(6,'anonymousUser','2024-05-06 01:11:03.000000','anonymousUser','2024-05-06 01:11:03.000000',0,'Quận 1, Hồ Chí Minh','HoTien2509@gmail.com','Hồ Thị Cẩm Tiên','0378956351','FEMALE','ACTIVE'),(7,'anonymousUser','2024-05-06 01:12:25.000000','anonymousUser','2024-05-06 01:12:25.000000',0,'Quận 1, Hồ Chí Minh','Khaidi1201@gmail.com','Cao Khải Di','0377489235','MALE','ACTIVE'),(8,'anonymousUser','2024-05-06 01:12:54.000000','anonymousUser','2024-05-06 01:12:54.000000',0,'Quận 2, Hồ Chí Minh','MinhTri0101@gmail.com','Mai Minh Trí','0121853645','MALE','ACTIVE'),(87,'anonymousUser','2024-05-06 01:20:00.000000','anonymousUser','2024-05-06 01:20:00.000000',0,'Quận 9, Hồ Chí Minh','NguyenThiLan@gmail.com','Nguyễn Thị Lan','0341258963','FEMALE','ACTIVE'),(88,'anonymousUser','2024-05-06 01:20:30.000000','anonymousUser','2024-05-06 01:20:30.000000',0,'Quận 10, Hồ Chí Minh','LeThiHoa@gmail.com','Lê Thị Hoa','0369152147','FEMALE','ACTIVE'),(89,'anonymousUser','2024-05-06 01:21:00.000000','anonymousUser','2024-05-06 01:21:00.000000',0,'Quận 11, Hồ Chí Minh','PhanVanNhan@gmail.com','Phan Văn Nhân','0385012369','MALE','ACTIVE'),(90,'anonymousUser','2024-05-06 01:21:30.000000','anonymousUser','2024-05-06 01:21:30.000000',0,'Quận 12, Hồ Chí Minh','TranThiThuTrang@gmail.com','Trần Thị Thu Trang','0320247896','FEMALE','ACTIVE'),(91,'anonymousUser','2024-05-06 01:22:00.000000','anonymousUser','2024-05-06 01:22:00.000000',0,'Quận Tân Bình, Hồ Chí Minh','NguyenVanDat@gmail.com','Nguyễn Văn Đạt','0354704652','MALE','ACTIVE'),(92,'anonymousUser','2024-05-06 01:22:30.000000','anonymousUser','2024-05-06 01:22:30.000000',0,'Quận Bình Thạnh, Hồ Chí Minh','LeThiThao@gmail.com','Lê Thị Thảo','0305852347','FEMALE','ACTIVE'),(93,'anonymousUser','2024-05-06 01:23:00.000000','anonymousUser','2024-05-06 01:23:00.000000',0,'Quận Phú Nhuận, Hồ Chí Minh','PhamVanNhan@gmail.com','Phạm Văn Nhân','0385405963','MALE','ACTIVE'),(94,'anonymousUser','2024-05-06 01:23:30.000000','anonymousUser','2024-05-06 01:23:30.000000',0,'Quận Tân Phú, Hồ Chí Minh','TranThiLan@gmail.com','Trần Thị Lan','0306521476','FEMALE','ACTIVE'),(95,'anonymousUser','2024-05-06 01:24:00.000000','anonymousUser','2024-05-06 01:24:00.000000',0,'Quận Gò Vấp, Hồ Chí Minh','NguyenVanNhan@gmail.com','Nguyễn Văn Nhân','0342580731','MALE','ACTIVE'),(96,'anonymousUser','2024-05-06 01:24:30.000000','anonymousUser','2024-05-06 01:24:30.000000',0,'Quận Tân Bình, Hồ Chí Minh','LeThiLan@gmail.com','Lê Thị Lan','0398852087','FEMALE','ACTIVE'),(97,'anonymousUser','2024-05-06 01:25:00.000000','anonymousUser','2024-05-06 01:25:00.000000',0,'Quận 3, Hồ Chí Minh','PhamVanDat@gmail.com','Phạm Văn Đạt','0385409963','MALE','ACTIVE'),(98,'anonymousUser','2024-05-06 01:25:30.000000','anonymousUser','2024-05-06 01:25:30.000000',0,'Quận 5, Hồ Chí Minh','TranThiThuThao@gmail.com','Trần Thị Thu Thảo','0398510476','FEMALE','ACTIVE'),(99,'anonymousUser','2024-05-06 01:26:00.000000','anonymousUser','2024-05-06 01:26:00.000000',0,'Quận 10, Hồ Chí Minh','LeVanDat@gmail.com','Lê Văn Đạt','0342581131','MALE','ACTIVE'),(100,'anonymousUser','2024-05-06 01:26:30.000000','anonymousUser','2024-05-06 01:26:30.000000',0,'Quận 11, Hồ Chí Minh','NguyenVanDat@gmail.com','Nguyễn Văn Đạt','0361200147','MALE','ACTIVE'),(101,'anonymousUser','2024-05-06 01:27:00.000000','anonymousUser','2024-05-06 01:27:00.000000',0,'Quận 12, Hồ Chí Minh','LeVanNhan@gmail.com','Lê Văn Nhân','0385413963','MALE','ACTIVE'),(102,'anonymousUser','2024-05-06 01:27:30.000000','anonymousUser','2024-05-06 01:27:30.000000',0,'Quận Tân Bình, Hồ Chí Minh','PhamThiLan@gmail.com','Phạm Thị Lan','0391421476','FEMALE','ACTIVE'),(103,'anonymousUser','2024-05-06 01:28:00.000000','anonymousUser','2024-05-06 01:28:00.000000',0,'Quận Bình Thạnh, Hồ Chí Minh','TranVanDat@gmail.com','Trần Văn Đạt','0342515631','MALE','ACTIVE'),(104,'anonymousUser','2024-05-06 01:28:30.000000','anonymousUser','2024-05-06 01:28:30.000000',0,'Quận Phú Nhuận, Hồ Chí Minh','NguyenVanNhan@gmail.com','Nguyễn Văn Nhân','0361116147','MALE','ACTIVE'),(105,'anonymousUser','2024-05-06 01:29:00.000000','anonymousUser','2024-05-06 01:29:00.000000',0,'Quận Tân Phú, Hồ Chí Minh','PhamThiLan@gmail.com','Phạm Thị Lan','0388673612','FEMALE','ACTIVE'),(106,'anonymousUser','2024-05-06 01:29:30.000000','anonymousUser','2024-05-06 01:29:30.000000',0,'Quận Gò Vấp, Hồ Chí Minh','LeVanDat@gmail.com','Lê Văn Đạt','0398518476','MALE','ACTIVE'),(107,'anonymousUser','2024-05-06 01:30:00.000000','anonymousUser','2024-05-06 01:30:00.000000',0,'Quận 9, Hồ Chí Minh','NguyenVanDat@gmail.com','Nguyễn Văn Đạt','0342581931','MALE','ACTIVE'),(108,'anonymousUser','2024-05-06 01:30:30.000000','anonymousUser','2024-05-06 01:30:30.000000',0,'Quận 10, Hồ Chí Minh','LeVanNhan@gmail.com','Lê Văn Nhân','0369332047','MALE','ACTIVE'),(109,'anonymousUser','2024-05-06 01:31:00.000000','anonymousUser','2024-05-06 01:31:00.000000',0,'Quận 11, Hồ Chí Minh','PhamThiLan@gmail.com','Phạm Thị Lan','0385472163','FEMALE','ACTIVE'),(110,'anonymousUser','2024-05-06 01:31:30.000000','anonymousUser','2024-05-06 01:31:30.000000',0,'Quận 12, Hồ Chí Minh','TranVanDat@gmail.com','Trần Văn Đạt','0398522476','MALE','ACTIVE'),(111,'anonymousUser','2024-05-06 01:32:00.000000','anonymousUser','2024-05-06 01:32:00.000000',0,'Quận Tân Bình, Hồ Chí Minh','NguyenThiLan@gmail.com','Nguyễn Thị Lan','0342523631','FEMALE','ACTIVE'),(112,'anonymousUser','2024-05-06 01:32:30.000000','anonymousUser','2024-05-06 01:32:30.000000',0,'Quận Bình Thạnh, Hồ Chí Minh','LeThiHoa@gmail.com','Lê Thị Hoa','0369382012','FEMALE','ACTIVE'),(113,'anonymousUser','2024-05-06 01:33:00.000000','anonymousUser','2024-05-06 01:33:00.000000',0,'Quận Phú Nhuận, Hồ Chí Minh','PhanVanNhan@gmail.com','Phan Văn Nhân','0385472563','MALE','ACTIVE'),(114,'anonymousUser','2024-05-06 01:33:30.000000','anonymousUser','2024-05-06 01:33:30.000000',0,'Quận Tân Phú, Hồ Chí Minh','TranThiThuTrang@gmail.com','Trần Thị Thu Trang','0398522676','FEMALE','ACTIVE'),(115,'anonymousUser','2024-05-06 01:34:00.000000','anonymousUser','2024-05-06 01:34:00.000000',0,'Quận Gò Vấp, Hồ Chí Minh','NguyenVanDat@gmail.com','Nguyễn Văn Đạt','034258317','MALE','ACTIVE'),(116,'anonymousUser','2024-05-06 01:34:30.000000','anonymousUser','2024-05-06 01:34:30.000000',0,'Quận Tân Bình, Hồ Chí Minh','LeThiLan@gmail.com','Lê Thị Lan','0369828147','FEMALE','ACTIVE'),(117,'anonymousUser','2024-05-06 01:35:00.000000','anonymousUser','2024-05-06 01:35:00.000000',0,'Quận 3, Hồ Chí Minh','PhamVanDat@gmail.com','Phạm Văn Đạt','0385298963','MALE','ACTIVE'),(118,'anonymousUser','2024-05-06 01:35:30.000000','anonymousUser','2024-05-06 01:35:30.000000',0,'Quận 5, Hồ Chí Minh','TranThiThuThao@gmail.com','Trần Thị Thu Thảo','0398521306','FEMALE','ACTIVE'),(119,'anonymousUser','2024-05-06 01:36:00.000000','anonymousUser','2024-05-06 01:36:00.000000',0,'Quận 10, Hồ Chí Minh','LeVanDat@gmail.com','Lê Văn Đạt','0342589311','MALE','ACTIVE'),(120,'anonymousUser','2024-05-06 01:36:30.000000','anonymousUser','2024-05-06 01:36:30.000000',0,'Quận 11, Hồ Chí Minh','NguyenVanDat@gmail.com','Nguyễn Văn Đạt','0369322147','MALE','ACTIVE'),(121,'anonymousUser','2024-05-06 01:37:00.000000','anonymousUser','2024-05-06 01:37:00.000000',0,'Quận 12, Hồ Chí Minh','LeVanNhan@gmail.com','Lê Văn Nhân','0385418963','MALE','ACTIVE'),(122,'anonymousUser','2024-05-06 01:37:30.000000','anonymousUser','2024-05-06 01:37:30.000000',0,'Quận Tân Bình, Hồ Chí Minh','PhamThiLan@gmail.com','Phạm Thị Lan','0398521476','FEMALE','ACTIVE'),(123,'anonymousUser','2024-05-06 01:38:00.000000','anonymousUser','2024-05-06 01:38:00.000000',0,'Quận Bình Thạnh, Hồ Chí Minh','TranVanDat@gmail.com','Trần Văn Đạt','0342589631','MALE','ACTIVE'),(124,'anonymousUser','2024-05-06 01:38:30.000000','anonymousUser','2024-05-06 01:38:30.000000',0,'Quận Phú Nhuận, Hồ Chí Minh','NguyenVanNhan@gmail.com','Nguyễn Văn Nhân','0369852147','MALE','ACTIVE'),(125,'anonymousUser','2024-05-06 01:39:00.000000','anonymousUser','2024-05-06 01:39:00.000000',0,'Quận Tân Phú, Hồ Chí Minh','PhamThiLan@gmail.com','Phạm Thị Lan','0385421963','FEMALE','ACTIVE');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `capaign_name` varchar(255) DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_value` double DEFAULT NULL,
  `status` enum('IS_APPLYING','NOT_APPLY') DEFAULT NULL,
  `type_discount` enum('PERCENT','CASH') DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'anonymousUser','2024-05-06 14:38:34.000000','anonymousUser','2024-05-06 14:38:34.000000',0,'Lễ 30/4-1/5','2024-05-01','2024-04-30','Lễ 30/4-1/5 chương trình giảm giá 10% tất cả các mặt hàng',10,'IS_APPLYING','PERCENT'),(2,'anonymousUser','2024-05-06 18:04:36.000000','anonymousUser','2024-05-06 18:04:36.000000',0,'Khuyến mãi Tháng 5','2024-05-15','2024-05-05','Khuyến mãi Tháng 5',15,'IS_APPLYING','PERCENT');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_product`
--

DROP TABLE IF EXISTS `discount_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_product` (
  `discount_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`discount_id`,`product_id`),
  KEY `FKi9j126a6ufxbtntwm4xkr7xlh` (`product_id`),
  CONSTRAINT `FKi9j126a6ufxbtntwm4xkr7xlh` FOREIGN KEY (`product_id`) REFERENCES `product` (`Id`),
  CONSTRAINT `FKmjaxuaj7gveyogt1ipxnp7lwm` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_product`
--

LOCK TABLES `discount_product` WRITE;
/*!40000 ALTER TABLE `discount_product` DISABLE KEYS */;
INSERT INTO `discount_product` VALUES (1,1),(2,1),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20);
/*!40000 ALTER TABLE `discount_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feature` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `feature_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_gqa31umtnrjdnp8r5vrh44doa` (`feature_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
INSERT INTO `feature` VALUES (1,'anonymousUser','2024-05-05 17:10:51.000000','anonymousUser','2024-05-05 17:10:51.000000',0,'Kết nối không dây'),(2,'anonymousUser','2024-05-05 17:10:55.000000','anonymousUser','2024-05-05 17:10:55.000000',0,'Chống nước'),(3,'anonymousUser','2024-05-05 17:11:01.000000','anonymousUser','2024-05-05 17:11:01.000000',0,'Chống bụi '),(4,'anonymousUser','2024-05-05 17:11:08.000000','anonymousUser','2024-05-05 17:11:08.000000',0,'Cảm biến vân tay'),(5,'anonymousUser','2024-05-05 17:11:16.000000','anonymousUser','2024-05-05 17:11:16.000000',0,'Cảm biến nhận diện khuôn mặt'),(6,'anonymousUser','2024-05-05 17:11:21.000000','anonymousUser','2024-05-05 17:11:21.000000',1,'Cảm biến gia tốc'),(7,'anonymousUser','2024-05-05 17:11:35.000000','anonymousUser','2024-05-05 17:11:35.000000',0,'Cảm biến ánh sáng');
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `material_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_l6e14n4o3dyy982r2nkphpsfn` (`material_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'anonymousUser','2024-05-05 11:28:13.000000','anonymousUser','2024-05-05 11:28:13.000000',0,'Nhựa'),(2,'anonymousUser','2024-05-05 11:28:19.000000','anonymousUser','2024-05-05 11:28:19.000000',0,'Kim loại'),(3,'anonymousUser','2024-05-05 11:30:27.000000','anonymousUser','2024-05-05 11:30:27.000000',0,'Thép không gỉ '),(4,'anonymousUser','2024-05-05 11:30:35.000000','anonymousUser','2024-05-05 11:30:35.000000',0,'Nhôm '),(5,'anonymousUser','2024-05-05 17:07:11.000000','anonymousUser','2024-05-05 17:07:11.000000',0,'Cao su'),(6,'anonymousUser','2024-05-05 17:07:38.000000','anonymousUser','2024-05-05 17:07:38.000000',0,'Kim loại phủ mạ'),(7,'anonymousUser','2024-05-05 17:07:51.000000','anonymousUser','2024-05-05 17:07:51.000000',0,'Mica'),(8,'anonymousUser','2024-05-05 17:08:09.000000','anonymousUser','2024-05-05 17:08:09.000000',0,'Thép carbon'),(9,'anonymousUser','2024-05-05 17:08:14.000000','anonymousUser','2024-05-05 17:08:14.000000',0,'Niken '),(10,'anonymousUser','2024-05-05 17:08:19.000000','anonymousUser','2024-05-05 17:08:19.000000',0,'Titan '),(11,'anonymousUser','2024-05-05 18:40:01.000000','anonymousUser','2024-05-05 18:40:01.000000',0,'Kính cường lực');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_association_order_item_order` (`order_id`),
  KEY `fk_association_order_item_product` (`product_id`),
  CONSTRAINT `fk_association_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`Id`),
  CONSTRAINT `fk_association_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,'anonymousUser','2024-05-06 01:28:17.000000','anonymousUser','2024-05-06 01:28:17.000000',0,1,1,1,260000),(2,'anonymousUser','2024-05-06 14:31:36.000000','anonymousUser','2024-05-06 14:31:36.000000',0,2,13,1,1290000),(3,'anonymousUser','2024-05-06 14:31:44.000000','anonymousUser','2024-05-06 14:31:44.000000',0,2,10,1,350000),(4,'anonymousUser','2024-05-06 14:33:09.000000','anonymousUser','2024-05-06 14:33:09.000000',0,3,7,1,2790000),(5,'anonymousUser','2024-05-06 14:33:56.000000','anonymousUser','2024-05-06 14:33:56.000000',0,4,5,1,670000),(6,'anonymousUser','2024-05-06 14:34:03.000000','anonymousUser','2024-05-06 14:34:03.000000',0,4,3,1,150000),(8,'anonymousUser','2024-05-06 14:36:05.000000','anonymousUser','2024-05-06 14:36:05.000000',0,5,10,1,350000),(9,'anonymousUser','2024-05-06 15:17:33.000000','anonymousUser','2024-05-06 15:17:33.000000',0,6,14,1,1990000),(10,'anonymousUser','2024-05-06 15:27:01.000000','anonymousUser','2024-05-06 15:27:01.000000',0,7,7,1,2790000),(11,'anonymousUser','2024-05-06 15:42:19.000000','anonymousUser','2024-05-06 15:42:19.000000',0,8,14,1,1990000),(12,'anonymousUser','2024-05-06 18:02:17.000000','anonymousUser','2024-05-06 18:02:17.000000',0,8,1,1,260000),(13,'anonymousUser','2024-05-06 18:56:25.000000','anonymousUser','2024-05-06 18:56:25.000000',0,10,3,1,150000);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `change_money` double DEFAULT NULL,
  `customer_id` bigint NOT NULL,
  `mode_of_delivery` enum('DIRECT_SALE','OTHER') DEFAULT NULL,
  `mode_of_payment` enum('CASH','CARD_SWIPE','BANK_TRANSFER') DEFAULT NULL,
  `note` text,
  `staff_id` bigint NOT NULL,
  `status` enum('PENDING','PAID','CANCELED') DEFAULT NULL,
  `total_bill` double DEFAULT NULL,
  `total_pay` double DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_association_order_customer` (`customer_id`),
  KEY `fk_association_order_staff` (`staff_id`),
  CONSTRAINT `fk_association_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`Id`),
  CONSTRAINT `fk_association_order_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'anonymousUser','2024-05-06 01:27:33.000000','anonymousUser','2024-05-06 01:27:33.000000',0,300000,1,'DIRECT_SALE','CASH','',1,'PAID',3000000,2700000),(2,'anonymousUser','2024-05-06 14:31:08.000000','anonymousUser','2024-05-06 14:31:08.000000',0,160000,1,'DIRECT_SALE','CASH','',1,'PAID',840000,1000000),(3,'anonymousUser','2024-05-06 14:32:45.000000','anonymousUser','2024-05-06 14:32:45.000000',0,30000,1,'DIRECT_SALE','CASH','',1,'PAID',470000,500000),(4,'anonymousUser','2024-05-06 14:33:34.000000','anonymousUser','2024-05-06 14:33:34.000000',0,100000,1,'DIRECT_SALE','CASH','',1,'PAID',400000,500000),(5,'anonymousUser','2024-05-06 14:35:33.000000','anonymousUser','2024-05-06 14:35:33.000000',0,50000,1,'DIRECT_SALE','CASH','06/05/2024',1,'PAID',450000,500000),(6,'anonymousUser','2024-05-06 15:17:16.000000','anonymousUser','2024-05-06 15:17:16.000000',0,200000,1,'DIRECT_SALE','CARD_SWIPE','Micro 06/05/2024',1,'PAID',300000,500000),(7,'anonymousUser','2024-05-06 15:26:37.000000','anonymousUser','2024-05-06 15:26:37.000000',0,210000,1,'DIRECT_SALE','CASH','',1,'PAID',2790000,2790000),(8,'anonymousUser','2024-05-06 15:42:03.000000','anonymousUser','2024-05-06 15:42:03.000000',0,NULL,1,NULL,NULL,NULL,1,'CANCELED',NULL,NULL),(9,'anonymousUser','2024-05-06 18:54:19.000000','anonymousUser','2024-05-06 18:54:19.000000',0,NULL,1,NULL,NULL,NULL,1,'CANCELED',NULL,NULL),(10,'anonymousUser','2024-05-06 18:56:13.000000','anonymousUser','2024-05-06 18:56:13.000000',0,NULL,1,NULL,NULL,NULL,1,'PENDING',NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `brand_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `import_price` double DEFAULT NULL,
  `material_id` bigint NOT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `path_image` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sale_price` double DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` enum('ON_BUSINESS','STOP_BUSINESS') DEFAULT NULL,
  `warranty_period` int DEFAULT NULL,
  `warranty_period_unit` enum('BY_MONTH','BY_YEAR') DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `weight_unit` enum('KG','G') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_association_product_brand` (`brand_id`),
  KEY `fk_association_product_category` (`category_id`),
  KEY `fk_association_product_material` (`material_id`),
  CONSTRAINT `fk_association_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`Id`),
  CONSTRAINT `fk_association_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`Id`),
  CONSTRAINT `fk_association_product_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'anonymousUser','2024-05-05 17:39:37.000000','anonymousUser','2024-05-05 17:39:37.000000',0,1,1,'9181717278164','Chuột Bluetooth Silent Ugreen MU001 sở hữu thiết kế gọn nhẹ, chất liệu bền bỉ, độ ồn khi click thấp cùng độ phân giải cao cho mọi thao tác nhanh nhạy, đem đến cho người dùng nhiều trải nghiệm tuyệt vời.',200000,1,'Trung Quốc','upload\\images\\chuot-bluetooth-silent-ugreen-mu001-.png','Chuột Bluetooth Silent Ugreen MU001 ',29,260000,'95 x 55 x 38 mm','ON_BUSINESS',1,'BY_YEAR',75,'G'),(2,'anonymousUser','2024-05-05 17:39:48.000000','anonymousUser','2024-05-05 17:39:48.000000',1,1,1,'2684790120575','Chuột Bluetooth Silent Ugreen MU001 sở hữu thiết kế gọn nhẹ, chất liệu bền bỉ, độ ồn khi click thấp cùng độ phân giải cao cho mọi thao tác nhanh nhạy, đem đến cho người dùng nhiều trải nghiệm tuyệt vời.',200000,1,'Trung Quốc','upload\\images\\chuot-bluetooth-silent-ugreen-mu001-.png','Chuột Bluetooth Silent Ugreen MU001 ',30,260000,'95 x 55 x 38 mm','ON_BUSINESS',1,'BY_YEAR',75,'G'),(3,'anonymousUser','2024-05-05 17:42:42.000000','anonymousUser','2024-05-05 17:42:42.000000',0,12,1,'2609092241414','',100000,1,'Trung Quốc','upload\\images\\chuot-khong-day-dareu-lm106g.png','Chuột Không dây DareU LM106G',48,150000,' 95 x 55 x 38 mm','ON_BUSINESS',1,'BY_YEAR',65,'G'),(4,'anonymousUser','2024-05-05 18:26:56.000000','anonymousUser','2024-05-05 18:26:56.000000',0,9,1,'2255422896603','',1700000,1,'Mỹ','upload\\images\\chuot-bluetooth-apple-mk2e3.png','Chuột Bluetooth Apple MK2E3',10,1890000,' 95 x 55 x 25 mm','ON_BUSINESS',1,'BY_YEAR',80,'G'),(5,'anonymousUser','2024-05-05 18:33:02.000000','anonymousUser','2024-05-05 18:33:02.000000',0,13,2,'8346259237777','Bàn Phím Không Dây DareU EK807G có khối lượng nhẹ với kích thước gọn gàng, thiết kế không dây cho phép dùng linh hoạt, để bạn tùy chọn vị trí sử dụng phù hợp nhất cho riêng mình.',510000,1,'Trung Quốc','upload\\images\\ban-phim-khong-day-dareu-ek807g.png','Bàn Phím Không Dây DareU EK807G',24,670000,'460 x 158 x 35 mm','ON_BUSINESS',1,'BY_YEAR',900,'G'),(6,'anonymousUser','2024-05-05 18:34:50.000000','anonymousUser','2024-05-05 18:34:50.000000',0,13,2,'6606560727988','Bàn Phím Cơ Bluetooth Dareu EK75 Pro sở hữu đầy đủ đặc điểm cần có của một mẫu bàn phím cơ cao cấp, đèn LED RGB đa sắc màu giúp không gian làm việc/giải trí nổi bật trong màn đêm',1190000,1,'Trung Quốc','upload\\images\\ban-phim-co-bluetooth-dareu-ek75-pro.png','Bàn Phím Cơ Bluetooth Dareu EK75 Pro',20,1390000,'460 x 158 x 35 mm','ON_BUSINESS',1,'BY_YEAR',900,'G'),(7,'anonymousUser','2024-05-05 18:42:39.000000','anonymousUser','2024-05-05 18:42:39.000000',0,2,1,'6508952132809','Màn hình Samsung S3 S33GC LS24C330GAEXXV 24 inch FHD đến từ thương hiệu Samsung nổi tiếng vừa được lên kệ tại Thế Giới Di Động, sở hữu những tính năng vượt trội phục vụ phần nhìn cùng thiết kế sang trọng trong không gian',2390000,11,'Hàn Quốc','upload\\images\\man-hinh-samsung-s3-s33gc-ls24c330gaexxv-24-inch-fhdips100hz4mshdmifreesync.png','Màn hình Samsung S3 S33GC LS24C330GAEXXV 24 inch FHD/IPS/100Hz/4ms/HDMI/FreeSync',8,2790000,'24 inchFull HD (1920 x 1080)100 Hz','ON_BUSINESS',2,'BY_YEAR',6,'KG'),(8,'anonymousUser','2024-05-05 18:45:42.000000','anonymousUser','2024-05-05 18:45:42.000000',0,2,4,'8973018059815','Tai nghe Bluetooth True Wireless Samsung Galaxy Buds FE R400N là sự kết hợp hoàn hảo của thiết kế và chất âm khi mang diện mạo nhỏ gọn, khả năng tái tạo âm bass mạnh mẽ, công nghệ chống ồn chủ động, hỗ trợ đàm thoại rõ nét',1390000,6,'Hàn Quốc','upload\\images\\tai-nghe-bluetooth-true-wireless-samsung-galaxy-buds-fe-r400n.png','Tai nghe Bluetooth True Wireless Samsung Galaxy Buds FE R400N',50,1490000,'17.0 x 22.5 x 19.2 mm ','ON_BUSINESS',6,'BY_MONTH',45,'G'),(9,'anonymousUser','2024-05-05 18:46:56.000000','anonymousUser','2024-05-05 18:46:56.000000',0,9,4,'6294827842033','Tai nghe Bluetooth AirPods Pro (2nd Gen) USB-C Charge Apple sở hữu thiết kế mang đậm chất thương hiệu Apple, màu sắc sang trọng, đi cùng nhiều công nghệ cho các iFan: chip Apple H2, chống bụi, chống ồn chủ động',5790000,6,'Mỹ','upload\\images\\tai-nghe-bluetooth-airpods-pro-gen-2-magsafe-charge-usb-c-apple-mtjv3-.png','Tai nghe Bluetooth AirPods Pro Gen 2 MagSafe Charge (USB-C) Apple MTJV3 ',40,6200000,'17.0 x 22.5 x 19.2 mm ','ON_BUSINESS',6,'BY_MONTH',45,'G'),(10,'anonymousUser','2024-05-05 18:50:08.000000','anonymousUser','2024-05-05 18:50:08.000000',0,12,5,'6358197193161','Chiếc loa Bluetooth Fenda này sở hữu màu sắc trẻ trung năng động, loa có kích thước nhỏ gọn dễ dàng mang theo trong những chuyến dã ngoại ngoài trời, góp phần tăng thêm bầu không khí cho chuyến đi.',290000,2,'Trung Quốc','upload\\images\\loa-bluetooth-fenda-w5-plus.png','Loa Bluetooth Fenda W5 Plus',48,350000,'150 x 65 x 65 mm','ON_BUSINESS',6,'BY_MONTH',400,'G'),(11,'anonymousUser','2024-05-06 00:30:09.000000','anonymousUser','2024-05-06 00:30:09.000000',0,12,13,'8347032376225','Sức mạnh của ổ cứng SSD chưa bao giờ làm mọi người thất vọng, với mẫu này, nó có hiệu suất nhanh gấp 2 lần các ổ cứng thông thường khác, đạt các tốc độ đọc ghi vượt trội, trong đó tốc độ ghi rơi vào mức 1000 MB/s còn đọc là 1050 MB/s. ',2990000,2,'Trung Quốc/ Malaysia','upload\\images\\o-cung-di-ong-ssd-500gb-wd-my-passport-bagf5000.png','Ổ cứng di động SSD 500GB WD My Passport BAGF5000',30,3490000,'10 x 5.5 x 0.9 cm','ON_BUSINESS',6,'BY_MONTH',457,'G'),(12,'anonymousUser','2024-05-06 00:55:52.000000','anonymousUser','2024-05-06 00:55:52.000000',0,1,23,'7042830460596','Camera IP 360 Độ 2MP Ezviz C6N có kích cỡ nhỏ nhắn, cầm nắm thoải mái chỉ bằng 1 bàn tay, cho bạn dễ dàng trong việc di chuyển, bố trí trong không gian cần sử dụng camera.',490000,9,'Trung Quốc','upload\\images\\camera-ip-360-o-2mp-ezviz-c6n.png','Camera IP 360 Độ 2MP Ezviz C6N',30,690000,' 90 x 90 x 90 mm','ON_BUSINESS',1,'BY_YEAR',250,'G'),(13,'anonymousUser','2024-05-06 00:59:55.000000','anonymousUser','2024-05-06 00:59:55.000000',0,2,23,'8543827058912','Camera IP Ngoài Trời 360 độ 4MP EZVIZ H8C sở hữu kiểu dáng đẹp mắt, thiết kế hiện đại cùng nhiều tính năng tích hợp như đèn chớp và còi báo động, theo dõi chuyển động, tự động thu phóng',1090000,9,'Trung Quốc','upload\\images\\camera-ip-ngoai-troi-360-o-4mp-ezviz-h8c-.png','Camera IP Ngoài Trời 360 độ 4MP EZVIZ H8C ',29,1290000,' 90 x 90 x 90 mm','ON_BUSINESS',1,'BY_YEAR',250,'G'),(14,'anonymousUser','2024-05-06 14:54:07.000000','anonymousUser','2024-05-06 14:54:07.000000',0,14,21,'1117049190384','Tần số\n\n40 HZ~15 kHz',1790000,1,'Việt Nam','upload\\images\\micro-khong-day-pasion-echo.png','Micro không dây Pasion Echo',29,1990000,'25 x 5 x 5 cm','ON_BUSINESS',6,'BY_MONTH',50,'G'),(15,'anonymousUser','2024-05-06 15:51:37.000000','anonymousUser','2024-05-06 15:51:37.000000',0,14,24,'2659182894686','Thiết kế nhỏ gọn, chân đế dễ điều chỉnh thay đổi góc chiếu.\nCho âm thanh sống động nhờ tích hợp loa JBL.\nHình ảnh sắc nét với độ phân giải 854 x 480, công nghệ hình ảnh DLP, Cinema SuperColor+.\nCung cấp màn hình chiếu tối đa 100 inch.',5275000,1,'Việt Nam','upload\\images\\may-chieu-led-viewsonic-hd-m1-mini-plus--.png','Máy Chiếu Led Viewsonic HD M1 Mini Plus  ',20,6990000,'40 x 50 x 7 cm','ON_BUSINESS',6,'BY_MONTH',400,'G'),(16,'anonymousUser','2024-05-06 15:53:23.000000','anonymousUser','2024-05-06 15:53:23.000000',0,4,24,'3136809701200','Máy chiếu Wanbo Full HD TT Max sở hữu kích thước khá gọn nhẹ, gam màu sang trọng, phù hợp với mọi không gian từ phòng khách đến phòng ngủ, khả năng hiển thị chân thật, tối ưu trải nghiệm trải trí của bạn ngay tại nhà.',5275000,1,'Việt Nam','upload\\images\\may-chieu-wanbo-full-hd-tt-max.png','Máy chiếu Wanbo Full HD TT Max',20,6990000,'40 x 50 x 7 cm','ON_BUSINESS',6,'BY_MONTH',400,'G'),(17,'anonymousUser','2024-05-06 15:56:09.000000','anonymousUser','2024-05-06 15:56:09.000000',0,14,5,'3002660615942','Loa Bluetooth AVA+ MiniPod Y23 sở hữu thiết kế nhỏ nhắn vừa vặn tay cầm, trang bị âm thanh sống động và mạnh mẽ, hỗ trợ chống nước IPX7, tiện lợi sử dụng cho không gian phòng ngủ hay không gian nhỏ.',5275000,1,'Việt Nam','upload\\images\\loa-bluetooth-ava-minipod-y23.png','Loa Bluetooth AVA+ MiniPod Y23',30,6990000,'6.5 x 6.5 x 7.5 cm','ON_BUSINESS',6,'BY_MONTH',100,'G'),(18,'anonymousUser','2024-05-06 16:01:47.000000','anonymousUser','2024-05-06 16:01:47.000000',0,14,25,'2874289619646','Thiết kế độc đáo với chất liệu hợp kim sang trọng\nĐế điện thoại xe hơi OSMIA CK-CH10/CK-CH11 có thiết kế chắc chắn với hợp kim sáng bóng, có khả năng xoay 360 độ cho người dùng linh hoạt điều chỉnh góc nhìn phù hợp.',100000,1,'Trung Quốc','upload\\images\\e-ien-thoai-xe-hoi-osmia-ck-ch10ck-ch11-.png','Đế điện thoại xe hơi OSMIA CK-CH10/CK-CH11 ',30,129000,'15 x 15 x 15 cm','ON_BUSINESS',6,'BY_MONTH',50,'G'),(19,'anonymousUser','2024-05-06 16:10:31.000000','anonymousUser','2024-05-06 16:10:31.000000',0,12,25,'9406255058741','Màu sắc đẹp, tươi tắn.\nGiữ điện thoại chắc chắn và an toàn, không dễ bị rơi khi có va chạm.\nCó thể linh hoạt xoay 360 độ, ngang hoặc dọc.',21000,1,'Trung Quốc','upload\\images\\e-ien-thoai-xe-hoi-esaver-jhd-96.png','Đế điện thoại xe hơi eSaver JHD-96',30,43000,'20 x 10 x  15 cm','ON_BUSINESS',6,'BY_MONTH',50,'G'),(20,'anonymousUser','2024-05-06 16:17:28.000000','anonymousUser','2024-05-06 16:17:28.000000',0,12,21,'5342414845170','Micro có dây Shure MV7 S là chiếc micro chuyên dụng để làm Podcast, livestream, thu tiếng nói rõ ràng, sắc nét, điều khiển bằng điện thoại linh hoạt qua app ShurePlus MOTIV, ghi âm được qua cổng XLR và micro USB.',6500000,4,'Trung Quốc','upload\\images\\micro-co-day-shure-mv7-s.png','Micro có dây Shure MV7 S',30,6990000,'20 x 20 x 7.5 cm','ON_BUSINESS',1,'BY_YEAR',100,'G');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_color`
--

DROP TABLE IF EXISTS `product_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_color` (
  `product_id` bigint NOT NULL,
  `color_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`color_id`),
  KEY `FK3iys6jgmsdkw7w5ncgm55wgj3` (`color_id`),
  CONSTRAINT `FK3iys6jgmsdkw7w5ncgm55wgj3` FOREIGN KEY (`color_id`) REFERENCES `color` (`Id`),
  CONSTRAINT `FKqb6lncpndi0w5po3rr5r9up5e` FOREIGN KEY (`product_id`) REFERENCES `product` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_color`
--

LOCK TABLES `product_color` WRITE;
/*!40000 ALTER TABLE `product_color` DISABLE KEYS */;
INSERT INTO `product_color` VALUES (1,1),(2,1),(3,1),(5,1),(6,1),(7,1),(11,1),(14,1),(16,1),(19,1),(20,1),(4,2),(8,2),(9,2),(10,2),(12,2),(13,2),(15,2),(17,2),(18,2),(20,2),(7,3),(20,3),(12,4),(13,4),(20,4),(19,5),(15,7),(17,7),(17,9),(18,11);
/*!40000 ALTER TABLE `product_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_feature`
--

DROP TABLE IF EXISTS `product_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_feature` (
  `product_id` bigint NOT NULL,
  `feature_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`feature_id`),
  KEY `FKgv1xq970xwg0q5k3jn9i23cc1` (`feature_id`),
  CONSTRAINT `FKgv1xq970xwg0q5k3jn9i23cc1` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`Id`),
  CONSTRAINT `FKp5iv62sge9f7yw66e5w2i2rhx` FOREIGN KEY (`product_id`) REFERENCES `product` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_feature`
--

LOCK TABLES `product_feature` WRITE;
/*!40000 ALTER TABLE `product_feature` DISABLE KEYS */;
INSERT INTO `product_feature` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(14,1),(15,1),(16,1),(17,1),(12,2),(13,2),(17,2),(18,2),(19,2),(20,2),(11,3),(19,3),(20,3),(7,5),(8,5),(9,5),(10,5);
/*!40000 ALTER TABLE `product_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'anonymousUser','2024-04-26 17:47:09.000000','anonymousUser','2024-04-26 17:47:09.000000',0,'Admin'),(2,'anonymousUser','2024-04-26 17:47:10.000000','anonymousUser','2024-04-26 17:47:10.000000',0,'Staff');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `sex` enum('MALE','FEMALE') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_association_staff_role` (`role_id`),
  CONSTRAINT `fk_association_staff_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'anonymousUser','2024-05-04 20:25:03.000000','anonymousUser','2024-05-04 20:25:03.000000',0,'2002-12-23','nv01@gmail.com','Nguyễn Đức Thành','$2a$12$X1lQiZTfSbAjRZQgrSuMLeQnhu/iN7QhvfHIQVSwcwrcyuOJBHoQ6','0342293128',1,'MALE','ACTIVE','nv01'),(2,'anonymousUser','2024-05-04 20:25:04.000000','anonymousUser','2024-05-04 20:25:04.000000',0,'2002-02-05','nv02@gmail.com','Mai Bảo Huy','$2a$12$spRhMR1rbgZQ1U3onYbVqu2.xJGFcHFVnLi1.CYSk.yQPlyPzb2pS','0342293122',1,'MALE','ACTIVE','nv02');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-09 16:20:41
