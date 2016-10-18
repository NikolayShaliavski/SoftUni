-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.15-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for bank
CREATE DATABASE IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bank`;


-- Dumping structure for table bank.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` int(11) NOT NULL,
  `account_number` char(12) NOT NULL,
  `start_date` date NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `fk_accounts_customers` (`customer_id`),
  CONSTRAINT `fk_accounts_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.accounts: ~40 rows (approximately)
DELETE FROM `accounts`;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`account_id`, `account_number`, `start_date`, `customer_id`) VALUES
	(1, '355074144476', '2016-04-22', 40),
	(2, '510014316471', '2015-10-21', 13),
	(3, '504837572679', '2012-05-17', 5),
	(4, '589360969376', '2016-07-23', 37),
	(5, '357737883238', '2015-11-09', 5),
	(6, '404137269591', '2011-08-07', 40),
	(7, '356665682743', '2014-03-23', 30),
	(8, '353336775123', '2011-04-16', 24),
	(9, '529906064937', '2014-11-29', 22),
	(10, '357300377233', '2011-04-02', 37),
	(11, '501845589067', '2016-08-12', 16),
	(12, '356698459732', '2013-08-04', 31),
	(13, '354165074950', '2014-12-27', 26),
	(14, '358964814991', '2012-05-11', 30),
	(15, '676702537059', '2016-01-25', 16),
	(16, '637847380672', '2014-11-15', 19),
	(17, '501012430845', '2015-10-31', 2),
	(18, '355030411164', '2012-07-05', 5),
	(19, '353551869441', '2013-06-12', 13),
	(20, '358186999628', '2013-01-20', 36),
	(21, '633110998329', '2012-12-06', 19),
	(22, '633110652265', '2010-12-03', 30),
	(23, '357865854277', '2011-06-03', 19),
	(24, '354587535088', '2013-04-21', 28),
	(25, '500766743677', '2013-10-11', 8),
	(26, '670618072040', '2015-08-29', 33),
	(27, '354413268319', '2016-04-11', 23),
	(28, '201924781142', '2011-12-13', 7),
	(29, '353792155017', '2013-07-09', 31),
	(30, '374288725696', '2012-06-14', 26),
	(31, '352806149112', '2016-08-05', 4),
	(32, '355708116263', '2014-05-12', 37),
	(33, '560222129740', '2011-04-30', 5),
	(34, '491305918331', '2010-10-13', 27),
	(35, '676226671786', '2012-01-03', 26),
	(36, '358415634989', '2013-05-20', 23),
	(37, '402677818660', '2015-09-09', 1),
	(38, '375374823869', '2011-04-27', 6),
	(39, '355945632328', '2016-08-09', 39),
	(40, '305517744547', '2012-01-25', 20);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


-- Dumping structure for table bank.branches
CREATE TABLE IF NOT EXISTS `branches` (
  `branch_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `fk_branches_cities` (`city_id`),
  CONSTRAINT `fk_branches_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.branches: ~20 rows (approximately)
DELETE FROM `branches`;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` (`branch_id`, `name`, `city_id`) VALUES
	(1, 'Roy', 13),
	(2, 'Roy', 8),
	(3, 'Eugene', 8),
	(4, 'Virginia', 15),
	(5, 'Aaron', 14),
	(6, 'Lois', 17),
	(7, 'Marilyn', 13),
	(8, 'Joseph', 9),
	(9, 'Andrew', 18),
	(10, 'Angela', 12),
	(11, 'Nicole', 19),
	(12, 'Jesse', 15),
	(13, 'Louis', 15),
	(14, 'Pamela', 6),
	(15, 'Nicholas', 18),
	(16, 'Judy', 5),
	(17, 'Lois', 15),
	(18, 'Lois', 7),
	(19, 'Mildred', 4),
	(20, 'Laura', 16);
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;


-- Dumping structure for table bank.cities
CREATE TABLE IF NOT EXISTS `cities` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.cities: ~20 rows (approximately)
DELETE FROM `cities`;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` (`city_id`, `city_name`) VALUES
	(1, 'Povorino'),
	(2, 'Tanumshede'),
	(3, 'Santa Mara'),
	(4, 'Uppsala'),
	(5, 'Nanpu'),
	(6, 'Char BhadrДЃsan'),
	(7, 'Darungan Lor'),
	(8, 'Orlu'),
	(9, 'Leiden'),
	(10, 'Rio do Sul'),
	(11, 'Pinhais'),
	(12, 'Budapest'),
	(13, 'Skrunda'),
	(14, 'Tumaco'),
	(15, 'Jiangmen'),
	(16, 'Belyy Yar'),
	(17, 'Nevers'),
	(18, 'Boje'),
	(19, 'Sungaibengkal'),
	(20, 'Nueva Guadalupe');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;


-- Dumping structure for table bank.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `gender` char(1) NOT NULL,
  `date_of_birth` date NOT NULL,
  `height` float DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `fk_customers_cities` (`city_id`),
  CONSTRAINT `fk_customers_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.customers: ~40 rows (approximately)
DELETE FROM `customers`;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `gender`, `date_of_birth`, `height`, `city_id`) VALUES
	(1, 'Bruce', 'Armstrong', 'M', '1970-09-17', 2.11, 10),
	(2, 'Carolyn', 'Wells', 'F', '1940-06-23', 2.11, 18),
	(3, 'Rachel', 'White', 'F', '2015-06-11', 1.65, 9),
	(4, 'Brenda', 'Boyd', 'F', '1945-11-25', 2.05, 17),
	(5, 'Aaron', 'Campbell', 'M', '1978-08-06', 2.14, 7),
	(6, 'Mary', 'Gordon', 'F', '1939-05-10', 1.66, 14),
	(7, 'Amy', 'Allen', 'F', '2007-04-25', 1.7, 11),
	(8, 'Frank', 'Armstrong', 'M', '1980-05-20', 1.95, 20),
	(9, 'Bobby', 'Hughes', 'M', '1967-10-12', 1.72, 8),
	(10, 'Gregory', 'Hansen', 'M', '1977-08-16', 1.37, 4),
	(11, 'Russell', 'Lawrence', 'M', '1994-11-17', 1.78, 3),
	(12, 'Henry', 'Henry', 'M', '1945-10-13', 1.85, 8),
	(13, 'Christina', 'Little', 'F', '1954-01-02', 1.14, 12),
	(14, 'George', 'Bennett', 'M', '1956-04-16', 2.12, 14),
	(15, 'Carolyn', 'Pierce', 'F', '2004-03-10', 1.89, 10),
	(16, 'Tammy', 'Crawford', 'F', '1993-11-28', 1.46, 6),
	(17, 'Samuel', 'Cooper', 'M', '2000-05-13', 1.22, 8),
	(18, 'Patrick', 'Mills', 'M', '1974-08-04', 2.1, 14),
	(19, 'Matthew', 'Davis', 'M', '1941-09-01', 1.82, 8),
	(20, 'Clarence', 'Meyer', 'M', '2016-01-23', 1.18, 12),
	(21, 'Karen', 'Mason', 'F', '1957-03-12', 2.15, 19),
	(22, 'Lawrence', 'Diaz', 'M', '1980-02-17', 1.78, 1),
	(23, 'Deborah', 'Taylor', 'F', '1946-11-06', 1.5, 12),
	(24, 'Robert', 'Fuller', 'M', '2003-01-05', 2.11, 13),
	(25, 'Debra', 'Crawford', 'F', '1989-05-24', 2.16, 1),
	(26, 'Albert', 'Flores', 'M', '2005-03-03', 1.9, 6),
	(27, 'Howard', 'Wood', 'M', '1965-12-16', 1.11, 16),
	(28, 'Sarah', 'Wheeler', 'F', '2005-11-03', 1.76, 11),
	(29, 'Roy', 'Rogers', 'M', '1985-12-18', 2.14, 16),
	(30, 'John', 'Pierce', 'M', '1999-05-16', 1.01, 12),
	(31, 'Justin', 'Dixon', 'M', '1956-08-30', 1.6, 17),
	(32, 'Karen', 'Cook', 'F', '1967-03-21', 1.93, 16),
	(33, 'Shirley', 'Williamson', 'F', '1984-10-06', 1.62, 16),
	(34, 'Joe', 'Sanders', 'M', '1972-06-29', 1.78, 1),
	(35, 'Mildred', 'Ferguson', 'F', '1958-06-07', 1.34, 15),
	(36, 'Mary', 'Harper', 'F', '1991-03-30', 1.44, 15),
	(37, 'George', 'Ryan', 'M', '1955-08-09', 1.91, 13),
	(38, 'Carl', 'Turner', 'M', '1975-01-28', 1.51, 17),
	(39, 'mark', 'Wheeler', 'M', '1954-02-02', 1.14, 1),
	(40, 'William', 'Fox', 'M', '1994-08-12', 1.92, 3);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


-- Dumping structure for table bank.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `hire_date` date NOT NULL,
  `salary` decimal(8,2) DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `fk_employees_branches` (`branch_id`),
  CONSTRAINT `fk_employees_branches` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.employees: ~30 rows (approximately)
DELETE FROM `employees`;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`employee_id`, `first_name`, `hire_date`, `salary`, `branch_id`) VALUES
	(1, 'Jacqueline', '2009-01-14', 2263.35, 9),
	(2, 'Kathryn', '2008-03-15', 1952.32, 13),
	(3, 'Raymond', '2006-04-13', 2246.48, 6),
	(4, 'Sarah', '2006-05-18', 2447.09, 19),
	(5, 'Gregory', '2009-12-23', 2295.88, 12),
	(6, 'Christine', '2007-02-18', 1554.37, 9),
	(7, 'Laura', '2008-04-09', 1985.56, 15),
	(8, 'Harry', '2006-03-19', 2884.21, 19),
	(9, 'Shawn', '2009-02-28', 2996.61, 4),
	(10, 'Margaret', '2006-07-17', 2514.07, 18),
	(11, 'Debra', '2006-10-08', 2569.80, 8),
	(12, 'Gerald', '2006-06-13', 2107.17, 11),
	(13, 'Walter', '2007-01-04', 2152.34, 3),
	(14, 'Harold', '2008-03-12', 2429.60, 7),
	(15, 'Jeremy', '2009-05-27', 2968.82, 12),
	(16, 'Jessica', '2008-08-19', 1731.33, 13),
	(17, 'Barbara', '2010-07-04', 2240.41, 10),
	(18, 'Angela', '2007-02-07', 2653.56, 5),
	(19, 'Terry', '2007-04-09', 2530.49, 10),
	(20, 'Ryan', '2007-07-12', 2401.28, 19),
	(21, 'Michael', '2006-07-08', 1109.21, 19),
	(22, 'Susan', '2009-01-13', 2296.28, 8),
	(23, 'Randy', '2008-10-14', 2925.68, 3),
	(24, 'Anthony', '2008-05-26', 2416.49, 10),
	(25, 'Deborah', '2010-01-05', 1902.53, 2),
	(26, 'William', '2009-11-21', 1639.97, 14),
	(27, 'Jose', '2006-03-08', 2500.32, 17),
	(28, 'Shawn', '2009-04-08', 2348.14, 13),
	(29, 'Frances', '2008-06-01', 2872.45, 19),
	(30, 'Diane', '2006-03-18', 2574.01, 6);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;


-- Dumping structure for table bank.employees_accounts
CREATE TABLE IF NOT EXISTS `employees_accounts` (
  `employee_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`,`account_id`),
  KEY `fk_employees_accounts_Account` (`account_id`),
  CONSTRAINT `fk_employees_accounts_Account` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `fk_employees_accounts_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.employees_accounts: ~50 rows (approximately)
DELETE FROM `employees_accounts`;
/*!40000 ALTER TABLE `employees_accounts` DISABLE KEYS */;
INSERT INTO `employees_accounts` (`employee_id`, `account_id`) VALUES
	(2, 1),
	(21, 2),
	(22, 2),
	(12, 4),
	(14, 5),
	(16, 5),
	(18, 5),
	(22, 6),
	(20, 7),
	(2, 8),
	(7, 9),
	(15, 9),
	(16, 11),
	(16, 12),
	(12, 13),
	(25, 13),
	(21, 14),
	(28, 14),
	(23, 17),
	(26, 17),
	(11, 18),
	(14, 18),
	(16, 19),
	(19, 19),
	(27, 19),
	(19, 22),
	(26, 23),
	(21, 24),
	(25, 26),
	(6, 27),
	(19, 27),
	(6, 28),
	(29, 28),
	(8, 30),
	(19, 30),
	(18, 31),
	(21, 31),
	(25, 31),
	(7, 33),
	(8, 33),
	(28, 33),
	(3, 34),
	(17, 34),
	(14, 35),
	(30, 35),
	(6, 37),
	(21, 37),
	(5, 38),
	(26, 38),
	(24, 39);
/*!40000 ALTER TABLE `employees_accounts` ENABLE KEYS */;


-- Dumping structure for table bank.employees_loans
CREATE TABLE IF NOT EXISTS `employees_loans` (
  `employee_id` int(11) NOT NULL,
  `loan_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`,`loan_id`),
  KEY `fk_employees_loans_loans` (`loan_id`),
  CONSTRAINT `fk_employees_loans_customers_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `fk_employees_loans_loans` FOREIGN KEY (`loan_id`) REFERENCES `loans` (`loan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.employees_loans: ~30 rows (approximately)
DELETE FROM `employees_loans`;
/*!40000 ALTER TABLE `employees_loans` DISABLE KEYS */;
INSERT INTO `employees_loans` (`employee_id`, `loan_id`) VALUES
	(3, 4),
	(13, 5),
	(7, 6),
	(30, 7),
	(1, 8),
	(7, 9),
	(17, 9),
	(12, 11),
	(20, 11),
	(1, 14),
	(3, 14),
	(4, 14),
	(18, 15),
	(19, 16),
	(22, 17),
	(23, 19),
	(24, 24),
	(4, 26),
	(11, 30),
	(27, 30),
	(5, 31),
	(22, 33),
	(1, 34),
	(16, 34),
	(18, 34),
	(18, 35),
	(17, 36),
	(26, 39),
	(4, 45),
	(26, 49);
/*!40000 ALTER TABLE `employees_loans` ENABLE KEYS */;


-- Dumping structure for table bank.loans
CREATE TABLE IF NOT EXISTS `loans` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `amount` decimal(18,2) NOT NULL,
  `interest` decimal(4,2) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `fk_loans_customers` (`customer_id`),
  CONSTRAINT `fk_loans_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- Dumping data for table bank.loans: ~50 rows (approximately)
DELETE FROM `loans`;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` (`loan_id`, `start_date`, `amount`, `interest`, `expiration_date`, `customer_id`) VALUES
	(1, '2012-07-19', 39688.89, 0.64, NULL, 36),
	(2, '2012-10-24', 27458.56, 0.75, '2014-07-17', 39),
	(3, '2011-05-22', 86354.31, 0.96, NULL, 40),
	(4, '2012-04-15', 43669.45, 0.84, NULL, 15),
	(5, '2010-11-03', 87303.90, 0.19, '2015-08-12', 17),
	(6, '2011-01-10', 21608.36, 0.07, '2015-09-13', 24),
	(7, '2011-04-21', 51176.01, 0.37, NULL, 34),
	(8, '2011-06-23', 6906.34, 0.59, NULL, 27),
	(9, '2013-06-01', 60755.86, 0.16, '2013-11-17', 2),
	(10, '2012-05-28', 10153.59, 0.74, '2015-11-07', 38),
	(11, '2012-04-20', 33182.13, 0.39, '2016-03-20', 39),
	(12, '2012-04-04', 94793.58, 0.11, '2015-08-18', 30),
	(13, '2013-05-13', 38042.37, 0.12, '2015-11-10', 23),
	(14, '2013-06-12', 64682.27, 0.31, '2014-04-03', 37),
	(15, '2010-10-19', 68002.42, 0.48, '2016-02-28', 2),
	(16, '2013-07-04', 52386.52, 0.09, '2015-12-14', 18),
	(17, '2011-05-25', 51732.28, 0.13, '2015-04-08', 32),
	(18, '2013-07-23', 49265.91, 0.33, '2015-07-08', 37),
	(19, '2012-12-24', 88067.24, 0.23, '2014-02-02', 4),
	(20, '2012-03-05', 30679.99, 0.30, NULL, 20),
	(21, '2011-08-19', 72139.46, 0.89, NULL, 32),
	(22, '2013-06-09', 9544.91, 0.93, NULL, 37),
	(23, '2011-12-26', 50728.08, 0.80, NULL, 9),
	(24, '2010-12-12', 3267.54, 0.13, '2016-05-09', 19),
	(25, '2011-11-04', 46492.12, 0.36, NULL, 28),
	(26, '2011-12-06', 16837.25, 0.55, NULL, 10),
	(27, '2012-07-09', 91980.57, 0.67, '2016-08-03', 34),
	(28, '2010-10-10', 55077.79, 0.84, '2014-04-05', 24),
	(29, '2013-03-15', 89733.18, 0.19, NULL, 40),
	(30, '2011-02-14', 58872.88, 0.53, '2013-10-03', 27),
	(31, '2013-03-13', 45145.99, 0.03, '2013-10-31', 27),
	(32, '2012-10-11', 12684.23, 0.08, '2014-01-12', 16),
	(33, '2011-03-10', 92276.06, 0.12, '2016-08-06', 5),
	(34, '2012-06-18', 36767.29, 0.64, '2014-12-25', 29),
	(35, '2011-10-04', 4114.00, 0.06, '2016-03-10', 27),
	(36, '2013-03-20', 27258.64, 0.16, '2016-04-30', 21),
	(37, '2012-04-04', 91384.30, 0.88, '2015-08-06', 26),
	(38, '2012-05-12', 19019.01, 0.96, '2016-01-15', 26),
	(39, '2011-05-28', 86027.03, 0.18, '2013-10-29', 29),
	(40, '2011-06-15', 2163.08, 0.67, '2014-05-05', 38),
	(41, '2013-05-10', 9409.06, 0.18, '2014-09-27', 5),
	(42, '2012-04-13', 47901.56, 0.88, '2013-11-17', 2),
	(43, '2011-06-09', 16449.92, 0.04, NULL, 26),
	(44, '2013-03-04', 85025.95, 0.56, NULL, 28),
	(45, '2011-06-14', 42992.17, 0.91, '2016-05-28', 39),
	(46, '2011-04-14', 57635.73, 0.60, '2013-11-22', 6),
	(47, '2011-02-21', 1209.53, 0.38, NULL, 38),
	(48, '2012-07-23', 88950.67, 0.89, '2016-05-07', 21),
	(49, '2012-12-30', 70819.68, 0.20, '2015-03-14', 32),
	(50, '2012-04-15', 12017.63, 0.86, '2013-10-06', 21);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
