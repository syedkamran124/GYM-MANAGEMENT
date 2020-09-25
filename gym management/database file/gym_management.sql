/*
SQLyog Ultimate v8.82 
MySQL - 5.1.45-community : Database - gym_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gym_management` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `gym_management`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pswd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`username`,`email`,`pswd`) values ('Admin','admin@gmail.com','123');

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `msg` varchar(250) DEFAULT NULL,
  `reply` varchar(250) DEFAULT NULL,
  `status` varchar(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `contact` */

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `mob` varchar(13) DEFAULT NULL,
  `gen` varchar(20) DEFAULT NULL,
  `dob` varchar(50) DEFAULT NULL,
  `pswd` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `exercise` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `equipment` */

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mob` varchar(20) DEFAULT NULL,
  `gen` varchar(20) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `pack` varchar(20) DEFAULT NULL,
  `duration` varchar(20) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  `joining` varchar(50) DEFAULT NULL,
  `expire` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `member` */

/*Table structure for table `package` */

DROP TABLE IF EXISTS `package`;

CREATE TABLE `package` (
  `package` varchar(100) DEFAULT NULL,
  `duration` int(10) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `package` */

insert  into `package`(`package`,`duration`,`amount`) values ('Silver',1,'1200'),('Gold',3,'3000'),('Diamond',6,'5000'),('Platinum',12,'8000');

/*Table structure for table `trainer` */

DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `aadhar` varchar(12) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `mob` varchar(10) DEFAULT NULL,
  `gen` varchar(20) DEFAULT NULL,
  `dob` varchar(10) DEFAULT NULL,
  `experience` varchar(10) DEFAULT NULL,
  `speciality` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `trainer` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_uname` varchar(100) DEFAULT NULL,
  `cardtype` varchar(30) DEFAULT NULL,
  `namecard` varchar(100) DEFAULT NULL,
  `cardno` varchar(16) DEFAULT NULL,
  `expiry` varchar(50) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `transaction` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
