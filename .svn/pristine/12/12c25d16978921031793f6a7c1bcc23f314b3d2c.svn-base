-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.65-community-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-01-16 17:26:15
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for crm_db
CREATE DATABASE IF NOT EXISTS `crm_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `crm_db`;


-- Dumping structure for table crm_db.accinfo
CREATE TABLE IF NOT EXISTS `accinfo` (
  `acc_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `login` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `user_id_fk` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.accinfo: ~2 rows (approximately)
/*!40000 ALTER TABLE `accinfo` DISABLE KEYS */;
INSERT INTO `accinfo` (`acc_id`, `user_id`, `login`, `password`) VALUES
	(1, 1, 'login1', '61e2489741fdff3ad62ea7554bc9dfc4'),
	(3, 2, 'login2', 'bf59423304fc3a43bd1215ca8d307693');
/*!40000 ALTER TABLE `accinfo` ENABLE KEYS */;


-- Dumping structure for table crm_db.agreements
CREATE TABLE IF NOT EXISTS `agreements` (
  `agr_id` int(10) NOT NULL AUTO_INCREMENT,
  `agr_number` varchar(64) NOT NULL,
  `date_written` date NOT NULL,
  `date_begin` date NOT NULL,
  `date_end` date NOT NULL,
  `date_pay` date NOT NULL,
  `sum_pay` bigint(20) NOT NULL,
  `client_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `payment_id` int(10) DEFAULT NULL,
  `agr_state` tinyint(4) NOT NULL,
  PRIMARY KEY (`agr_id`),
  KEY `payment_id_fk_agreements` (`payment_id`),
  KEY `client_id_fk_agreements` (`client_id`),
  KEY `user_id_fk_agreements` (`user_id`),
  CONSTRAINT `client_id_fk_agreements` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON UPDATE CASCADE,
  CONSTRAINT `payment_id_fk_agreements` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk_agreements` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.agreements: ~5 rows (approximately)
/*!40000 ALTER TABLE `agreements` DISABLE KEYS */;
INSERT INTO `agreements` (`agr_id`, `agr_number`, `date_written`, `date_begin`, `date_end`, `date_pay`, `sum_pay`, `client_id`, `user_id`, `payment_id`, `agr_state`) VALUES
	(2, '123456-78', '2011-11-11', '2011-11-11', '2012-11-10', '2011-11-11', 10000, 1, 2, 3, 1),
	(3, '123456-79', '2011-11-12', '2011-11-12', '2012-11-11', '2011-11-12', 20000, 1, 2, 4, 1),
	(4, '123456-80', '2011-11-13', '2011-11-13', '2012-11-12', '2011-11-13', 12300, 1, 2, 5, 1),
	(5, '123456-82', '2011-11-15', '2011-11-15', '2012-11-14', '2011-11-15', 50000, 1, 2, NULL, 2),
	(7, '123456-90', '2011-11-11', '2011-11-11', '2011-11-12', '2011-11-11', 19900, 1, 2, NULL, 2);
/*!40000 ALTER TABLE `agreements` ENABLE KEYS */;


-- Dumping structure for table crm_db.banks
CREATE TABLE IF NOT EXISTS `banks` (
  `bank_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `mfo` int(6) NOT NULL,
  `edrpou` int(10) NOT NULL,
  `address` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `telephone` varchar(128) NOT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.banks: ~3 rows (approximately)
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
INSERT INTO `banks` (`bank_id`, `name`, `mfo`, `edrpou`, `address`, `email`, `telephone`) VALUES
	(1, 'JSC "Bank1"', 123456, 12345678, 'address1', '1@1', 'telephone1'),
	(2, 'JSC "Bank2"', 234567, 23456789, 'address2', '2@2', 'telephone2'),
	(3, 'JSC "Bank3"', 334567, 33456789, 'address3', '3@3', 'telephone3');
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;


-- Dumping structure for table crm_db.banks_accounts
CREATE TABLE IF NOT EXISTS `banks_accounts` (
  `bank_acc_id` int(10) NOT NULL AUTO_INCREMENT,
  `bank_id` int(10) NOT NULL,
  `acc_number` varchar(64) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `acc_state` tinyint(1) NOT NULL,
  PRIMARY KEY (`bank_acc_id`),
  KEY `bank_id` (`bank_id`),
  CONSTRAINT `bank_id` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`bank_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.banks_accounts: ~5 rows (approximately)
/*!40000 ALTER TABLE `banks_accounts` DISABLE KEYS */;
INSERT INTO `banks_accounts` (`bank_acc_id`, `bank_id`, `acc_number`, `currency`, `acc_state`) VALUES
	(1, 1, '1acc_number1', 'UAH', 1),
	(2, 1, '1acc_number2', 'UAH', 1),
	(3, 2, '2acc_number1', 'USD', 1),
	(5, 2, '2accnumber2', 'UAH', 1),
	(6, 1, '1acc_number1', 'UAH', 1);
/*!40000 ALTER TABLE `banks_accounts` ENABLE KEYS */;


-- Dumping structure for table crm_db.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `middlename` varchar(32) NOT NULL,
  `birthday` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  `address` varchar(128) NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`client_id`),
  KEY `user_id_fk_clients` (`user_id`),
  CONSTRAINT `user_id_fk_clients` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.clients: ~4 rows (approximately)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`client_id`, `login`, `password`, `email`, `surname`, `name`, `middlename`, `birthday`, `sex`, `address`, `telephone`, `user_id`) VALUES
	(1, 'login1', '61e2489741fdff3ad62ea7554bc9dfc4', '1@1', 'Ivanov', 'Ivan', 'Ivanovich', '2011-11-11', 'm', 'address1', 'telephone1', 2),
	(2, 'login2', 'bf59423304fc3a43bd1215ca8d307693', 'mail-mail1@mail1.com', 'Sidorov', 'Ivan', 'Ivanovich', '2011-11-12', 'm', 'city Kiev, Osennyaya str. 33, flat 401, post code 03164', '+38-099-3434709', 2),
	(4, 'login3', '0ae4c2aed893072bccda00757ac9b17d', 'mail3@mail3.com', 'Petrov', 'Ivan', 'Ivanovich', '2011-11-13', 'm', 'address 3, address 3, address 3, address 3, address 3', '+33-333-3333333', 2),
	(7, 'login4', '4a331dc5683a0ebb00864cce3882dba6', 'mail4@mail4.com', 'Popov', 'Ivan', 'Ivanovich', '2011-11-14', 'm', 'address 4, address 4, address 4, address 4, address 4', '+44-444-4444444', 2);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;


-- Dumping structure for table crm_db.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `payment_id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` int(10) NOT NULL,
  `client_id` int(10) DEFAULT NULL,
  `paym_sum` bigint(20) NOT NULL,
  `paym_purpose` varchar(512) NOT NULL,
  `paym_date` date NOT NULL,
  `paym_state` tinyint(1) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `bank_acc_id_fk` (`account_id`),
  KEY `paym_client_id_fk` (`client_id`),
  CONSTRAINT `bank_acc_id_fk` FOREIGN KEY (`account_id`) REFERENCES `banks_accounts` (`bank_acc_id`) ON UPDATE CASCADE,
  CONSTRAINT `paym_client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.payments: ~5 rows (approximately)
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` (`payment_id`, `account_id`, `client_id`, `paym_sum`, `paym_purpose`, `paym_date`, `paym_state`) VALUES
	(3, 1, 1, 10000, 'payed 100', '2011-11-14', 1),
	(4, 5, 1, 20000, 'payed 200', '2011-11-12', 1),
	(5, 5, 1, 12300, 'payed 123', '2011-11-13', 1),
	(6, 1, NULL, 50000, 'paid 500', '2011-11-15', 2),
	(7, 1, NULL, 19900, 'paid 199', '2011-11-11', 2);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;


-- Dumping structure for table crm_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `middlename` varchar(32) NOT NULL,
  `birthday` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  `address` varchar(128) NOT NULL,
  `position` varchar(128) NOT NULL,
  `department` varchar(128) NOT NULL,
  `email` varchar(64) NOT NULL,
  `user_state` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table crm_db.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `name`, `surname`, `middlename`, `birthday`, `sex`, `address`, `position`, `department`, `email`, `user_state`) VALUES
	(1, 'name1', 'surname1', 'middlename1', '2011-11-11', 'm', 'address1', 'position1', 'department1', '1@1', 1),
	(2, 'name2', 'surname2', 'middlename2', '2011-11-12', 'm', 'address2', 'position2', 'department2', '2@2', 2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;