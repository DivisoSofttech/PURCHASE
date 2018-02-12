Microsoft Windows [Version 10.0.10240]
(c) 2015 Microsoft Corporation. All rights reserved.

C:\Users\User>mysqldump
'mysqldump' is not recognized as an internal or external command,
operable program or batch file.

C:\Users\User>"c:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump.exe" -u root -p root purchase > purchase.sql
Enter password: ****
mysqldump: Got error: 1049: Unknown database 'root' when selecting the database

C:\Users\User>"c:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump.exe" -u root  purchase > purchase.sql
mysqldump: Got error: 1045: Access denied for user 'root'@'localhost' (using password: NO) when trying to connect

C:\Users\User>"c:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump.exe" -u root  -p purchase > purchase.sql
Enter password: ****

C:\Users\User>type purchase.sql
-- MySQL dump 10.13  Distrib 5.5.23, for Win64 (x86)
--
-- Host: localhost    Database: purchase
-- ------------------------------------------------------
-- Server version       5.5.23

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `place` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `pin_code` int(11) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_supplier_id` (`supplier_id`),
  CONSTRAINT `fk_address_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `delivery_note_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_delivery_note_id` (`delivery_note_id`),
  CONSTRAINT `fk_comment_delivery_note_id` FOREIGN KEY (`delivery_note_id`) REFERENCES `delivery_note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail_id` varchar(255) DEFAULT NULL,
  `phone_number_1` bigint(20) DEFAULT NULL,
  `phone_number_2` bigint(20) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-01-31 14:39:53',1,'EXECUTED','7:9a5a4c89406d5d1db3a173ec117d1a81','createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, referencedTableName=jhi_authorit...','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040652-1','jhipster','config/liquibase/changelog/20180131040652_added_entity_Supplier.xml','2018-01-31 14:39:53',2,'EXECUTED','7:4987b8498261f2a9dc4f724e56ea67b9','createTable tableName=supplier','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040653-1','jhipster','config/liquibase/changelog/20180131040653_added_entity_Address.xml','2018-01-31 14:39:53',3,'EXECUTED','7:c60d04e42ee7a096d37743d079d839f4','createTable tableName=address','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040654-1','jhipster','config/liquibase/changelog/20180131040654_added_entity_Contact.xml','2018-01-31 14:39:53',4,'EXECUTED','7:5d89c55ae3f7267cf2c1671a1a7f15bf','createTable tableName=contact','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040655-1','jhipster','config/liquibase/changelog/20180131040655_added_entity_Quotation.xml','2018-01-31 14:39:53',5,'EXECUTED','7:9c172e5a0706f19c9fc816d4f3aef19d','createTable tableName=quotation','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040656-1','jhipster','config/liquibase/changelog/20180131040656_added_entity_QuotationLine.xml','2018-01-31 14:39:53',6,'EXECUTED','7:a4f086a3aa557492f1052f29626d1170','createTable tableName=quotation_line','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040657-1','jhipster','config/liquibase/changelog/20180131040657_added_entity_QuotationStatus.xml','2018-01-31 14:39:54',7,'EXECUTED','7:72ea00431fe0f95c356d86e97873e5f4','createTable tableName=quotation_status','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131050044-1','jhipster','config/liquibase/changelog/20180131050044_added_entity_PurchaseOrder.xml','2018-01-31 14:39:54',8,'EXECUTED','7:b535418556ff0a52804bd387f622003a','createTable tableName=purchase_order','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131050045-1','jhipster','config/liquibase/changelog/20180131050045_added_entity_PurchaseLine.xml','2018-01-31 14:39:54',9,'EXECUTED','7:7daa87b1fab285d79d4c5ab14c930375','createTable tableName=purchase_line','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131052434-1','jhipster','config/liquibase/changelog/20180131052434_added_entity_DeliveryNote.xml','2018-01-31 14:39:54',10,'EXECUTED','7:caa85ad7759819dcc45447cc0e73d050','createTable tableName=delivery_note','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131052435-1','jhipster','config/liquibase/changelog/20180131052435_added_entity_Rating.xml','2018-01-31 14:39:54',11,'EXECUTED','7:6e330527bebc99b6b313d33a64a0068e','createTable tableName=rating','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131052436-1','jhipster','config/liquibase/changelog/20180131052436_added_entity_Comment.xml','2018-01-31 14:39:54',12,'EXECUTED','7:e76eb132450d4f58cac5a50bbde2f107','createTable tableName=comment','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040652-2','jhipster','config/liquibase/changelog/20180131040652_added_entity_constraints_Supplier.xml','2018-01-31 14:39:55',13,'EXECUTED','7:764ca691d451f7bd8b0aa694012e2630','addForeignKeyConstraint baseTableName=supplier, constraintName=fk_supplier_permanent_address_id, referencedTableName=address; addForeignKeyConstraint baseTableName=supplier, constraintName=fk_supplier_contact_id, referencedTableName=contact','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040653-2','jhipster','config/liquibase/changelog/20180131040653_added_entity_constraints_Address.xml','2018-01-31 14:39:55',14,'EXECUTED','7:7e3745645e2d765c03c2335a6e47c9e0','addForeignKeyConstraint baseTableName=address, constraintName=fk_address_supplier_id, referencedTableName=supplier','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040655-2','jhipster','config/liquibase/changelog/20180131040655_added_entity_constraints_Quotation.xml','2018-01-31 14:39:56',15,'EXECUTED','7:e5ce6825f78e34115ee5e657e491d0aa','addForeignKeyConstraint baseTableName=quotation, constraintName=fk_quotation_supplier_id, referencedTableName=supplier; addForeignKeyConstraint baseTableName=quotation, constraintName=fk_quotation_quotation_status_id, referencedTableName=quotation...','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131040656-2','jhipster','config/liquibase/changelog/20180131040656_added_entity_constraints_QuotationLine.xml','2018-01-31 14:39:56',16,'EXECUTED','7:727cb989b99598181ebe52d92c76f6c8','addForeignKeyConstraint baseTableName=quotation_line, constraintName=fk_quotation_line_quotation_id, referencedTableName=quotation','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131050045-2','jhipster','config/liquibase/changelog/20180131050045_added_entity_constraints_PurchaseLine.xml','2018-01-31 14:39:56',17,'EXECUTED','7:47627da00ac9c1b9e4d53acd625b7cfa','addForeignKeyConstraint baseTableName=purchase_line, constraintName=fk_purchase_line_purchase_order_id, referencedTableName=purchase_order','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131052434-2','jhipster','config/liquibase/changelog/20180131052434_added_entity_constraints_DeliveryNote.xml','2018-01-31 14:39:57',18,'EXECUTED','7:5a21346ffb3a672bd89604c4a8f883d2','addForeignKeyConstraint baseTableName=delivery_note, constraintName=fk_delivery_note_purchase_order_id, referencedTableName=purchase_order; addForeignKeyConstraint baseTableName=delivery_note, constraintName=fk_delivery_note_rating_id, referencedT...','',NULL,'3.5.3',NULL,NULL,'7389791922'),('20180131052436-2','jhipster','config/liquibase/changelog/20180131052436_added_entity_constraints_Comment.xml','2018-01-31 14:39:57',19,'EXECUTED','7:2eb49631964110bd5ec2873157242261','addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_delivery_note_id, referencedTableName=delivery_note','',NULL,'3.5.3',NULL,NULL,'7389791922');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'','2018-02-01 10:54:46','DESKTOP-7PI9FGC (192.168.1.107)');
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` varchar(255) DEFAULT NULL,
  `order_reference` varchar(255) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `quotation_id` bigint(20) DEFAULT NULL,
  `purchase_order_id` bigint(20) DEFAULT NULL,
  `rating_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rating_id` (`rating_id`),
  UNIQUE KEY `purchase_order_id` (`purchase_order_id`),
  CONSTRAINT `fk_delivery_note_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`),
  CONSTRAINT `fk_delivery_note_rating_id` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note`
--

LOCK TABLES `delivery_note` WRITE;
/*!40000 ALTER TABLE `delivery_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_line`
--

DROP TABLE IF EXISTS `purchase_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_reference` varchar(255) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_tax` float DEFAULT NULL,
  `available_quantity` int(11) DEFAULT NULL,
  `is_select` bit(1) DEFAULT NULL,
  `purchase_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchase_line_purchase_order_id` (`purchase_order_id`),
  CONSTRAINT `fk_purchase_line_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_line`
--

LOCK TABLES `purchase_line` WRITE;
/*!40000 ALTER TABLE `purchase_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) DEFAULT NULL,
  `quotation_id` bigint(20) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quotation`
--

DROP TABLE IF EXISTS `quotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quotation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` varchar(255) DEFAULT NULL,
  `issued_date` date DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `quotation_status_id` bigint(20) DEFAULT NULL,
  `purchase_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `quotation_status_id` (`quotation_status_id`),
  KEY `fk_quotation_supplier_id` (`supplier_id`),
  KEY `fk_quotation_purchase_order_id` (`purchase_order_id`),
  CONSTRAINT `fk_quotation_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`),
  CONSTRAINT `fk_quotation_quotation_status_id` FOREIGN KEY (`quotation_status_id`) REFERENCES `quotation_status` (`id`),
  CONSTRAINT `fk_quotation_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quotation`
--

LOCK TABLES `quotation` WRITE;
/*!40000 ALTER TABLE `quotation` DISABLE KEYS */;
/*!40000 ALTER TABLE `quotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quotation_line`
--

DROP TABLE IF EXISTS `quotation_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quotation_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_reference` varchar(255) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_tax` float DEFAULT NULL,
  `available_quantity` int(11) DEFAULT NULL,
  `is_select` bit(1) DEFAULT NULL,
  `quotation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quotation_line_quotation_id` (`quotation_id`),
  CONSTRAINT `fk_quotation_line_quotation_id` FOREIGN KEY (`quotation_id`) REFERENCES `quotation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quotation_line`
--

LOCK TABLES `quotation_line` WRITE;
/*!40000 ALTER TABLE `quotation_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `quotation_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quotation_status`
--

DROP TABLE IF EXISTS `quotation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quotation_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_id` int(11) DEFAULT NULL,
  `status_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quotation_status`
--

LOCK TABLES `quotation_status` WRITE;
/*!40000 ALTER TABLE `quotation_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `quotation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` varchar(255) DEFAULT NULL,
  `ratings` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `permanent_address_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permanent_address_id` (`permanent_address_id`),
  UNIQUE KEY `contact_id` (`contact_id`),
  CONSTRAINT `fk_supplier_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_supplier_permanent_address_id` FOREIGN KEY (`permanent_address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (2,2,'Uvaiz','Umer',NULL,NULL),(7,2,'Uvaiz','Umer',NULL,NULL),(8,5,'Fahad','Leoms',NULL,NULL);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-09 16:48:03
