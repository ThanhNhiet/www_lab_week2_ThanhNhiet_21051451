-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mydb2
CREATE DATABASE IF NOT EXISTS `mydb2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `mydb2`;

-- Dumping structure for table mydb2.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.customer: ~0 rows (approximately)
INSERT INTO `customer` (`ID`, `address`, `email`, `name`, `phone`) VALUES
	(1, 'Binh Tan, HCM', 'tung@gmail.com', 'Thanh Tung', '1111-1111-1111');

-- Dumping structure for table mydb2.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.employee: ~1 rows (approximately)
INSERT INTO `employee` (`ID`, `address`, `dob`, `email`, `fullname`, `phone`, `status`) VALUES
	(1, 'Binh Chanh, hCM', '2003-05-13 13:11:47.000000', 'bcii@gmail.com', 'Bochi', '2222-2222-2222', 'ACTIVE');

-- Dumping structure for table mydb2.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `ORDERID` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderDate` datetime(6) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ORDERID`),
  KEY `FK_orders_customer_id` (`customer_id`),
  KEY `FK_orders_employee_id` (`employee_id`),
  CONSTRAINT `FK_orders_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`),
  CONSTRAINT `FK_orders_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.orders: ~8 rows (approximately)
INSERT INTO `orders` (`ORDERID`, `orderDate`, `customer_id`, `employee_id`) VALUES
	(8, '2024-09-27 22:41:52.756745', 1, 1),
	(9, '2024-09-27 22:44:44.085283', 1, 1),
	(10, '2024-09-27 22:56:52.659557', 1, 1),
	(11, '2024-09-27 22:58:21.349509', 1, 1),
	(12, '2024-09-27 23:07:36.860130', 1, 1),
	(13, '2024-09-27 23:09:01.115810', 1, 1),
	(14, '2024-09-27 23:10:37.537706', 1, 1),
	(15, '2024-09-27 23:12:17.514250', 1, 1),
	(16, '2024-09-27 23:12:31.031239', 1, 1),
	(17, '2024-09-27 23:15:46.078025', 1, 1);

-- Dumping structure for table mydb2.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `note` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FK_order_detail_product_id` (`product_id`),
  CONSTRAINT `FK_order_detail_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ORDERID`),
  CONSTRAINT `FK_order_detail_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`PRODUCTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.order_detail: ~11 rows (approximately)
INSERT INTO `order_detail` (`note`, `price`, `quantity`, `order_id`, `product_id`) VALUES
	('none', 30000, 1, 11, 1),
	('none', 4000, 2, 11, 2),
	('none', 30000, 1, 12, 1),
	('none', 4000, 3, 12, 2),
	('none', 30000, 1, 13, 1),
	('none', 4000, 1, 13, 2),
	('none', 30000, 1, 14, 1),
	('none', 4000, 3, 14, 2),
	('none', 30000, 1, 16, 1),
	('none', 30000, 1, 17, 1),
	('none', 4000, 1, 17, 2);

-- Dumping structure for table mydb2.product
CREATE TABLE IF NOT EXISTS `product` (
  `PRODUCTID` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PRODUCTID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.product: ~3 rows (approximately)
INSERT INTO `product` (`PRODUCTID`, `description`, `manufacturer`, `name`, `status`, `unit`) VALUES
	(1, 'first product', 'SaiGon dink', 'beer SaiGon', 'ACTIVE', 'can'),
	(2, 'second p', 'Acecook', 'Hao hao', 'ACTIVE', 'pack'),
	(9, 'can open mouth', 'CN', 'Doll18', 'INACTIVE', 'body');

-- Dumping structure for table mydb2.product_price
CREATE TABLE IF NOT EXISTS `product_price` (
  `note` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_date_time` datetime(6) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `FK_product_price_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`PRODUCTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table mydb2.product_price: ~2 rows (approximately)
INSERT INTO `product_price` (`note`, `price`, `price_date_time`, `product_id`) VALUES
	('none', 30000, '2024-09-20 15:49:01.000000', 1),
	('none', 4000, '2024-08-20 15:49:43.000000', 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
