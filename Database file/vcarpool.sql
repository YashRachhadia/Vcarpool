-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2020 at 03:34 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vcarpool`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(20) UNSIGNED NOT NULL,
  `fname_mname` varchar(150) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `father_name` varchar(250) NOT NULL,
  `dept_id` int(20) UNSIGNED NOT NULL,
  `email` varchar(150) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `carpool_service` varchar(5) NOT NULL DEFAULT 'NO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `fname_mname`, `lname`, `dob`, `father_name`, `dept_id`, `email`, `mobile`, `carpool_service`) VALUES
(1, 'dolorem', 'numquam', '2018-05-14', 'voluptas', 2, 'osinski.lucas@example.org', '287.442.68', 'NO'),
(2, 'beatae', 'ut', '2000-02-03', 'et', 8, 'kuphal.alexa@example.org', '1-443-592-', 'NO'),
(3, 'minima', 'amet', '2019-10-19', 'nesciunt', 3, 'florian61@example.net', '(434)861-7', 'NO'),
(4, 'magnam', 'laborum', '1983-09-21', 'cupiditate', 7, 'maggio.cathryn@example.net', '(820)025-0', 'NO'),
(5, 'aut', 'in', '1995-03-03', 'maxime', 6, 'kenton32@example.com', '314-946-08', 'NO'),
(6, 'quae', 'reiciendis', '1977-02-12', 'voluptas', 6, 'tmcclure@example.net', '478.150.36', 'NO'),
(7, 'omnis', 'omnis', '1977-07-30', 'soluta', 1, 'gdurgan@example.com', '581.288.25', 'NO'),
(8, 'officiis', 'quibusdam', '1992-03-21', 'esse', 3, 'demetrius67@example.net', '255-666-51', 'NO'),
(9, 'atque', 'illum', '1976-03-12', 'explicabo', 4, 'langosh.rick@example.net', '(443)672-2', 'NO'),
(10, 'veritatis', 'quisquam', '2014-01-09', 'voluptatem', 5, 'welch.samara@example.org', '1-329-307-', 'NO'),
(11, 'repellendus', 'exercitationem', '1971-06-21', 'asperiores', 4, 'adelbert40@example.com', '990-951-32', 'NO'),
(12, 'dolor', 'aut', '2015-01-24', 'natus', 9, 'leonardo.brakus@example.net', '160.590.93', 'NO'),
(13, 'velit', 'corrupti', '2009-11-16', 'illum', 2, 'ebert.leda@example.org', '(095)541-0', 'NO'),
(14, 'sint', 'maiores', '2015-12-06', 'officiis', 4, 'hoeger.isadore@example.org', '1-474-975-', 'NO'),
(15, 'similique', 'rem', '1995-04-08', 'totam', 8, 'mann.cortez@example.com', '1-177-362-', 'NO'),
(16, 'sint', 'tempore', '1983-02-06', 'et', 6, 'tobin.kessler@example.net', '0824625372', 'NO'),
(17, 'voluptas', 'aut', '2001-07-03', 'atque', 6, 'wiegand.sasha@example.com', '(048)541-8', 'NO'),
(18, 'sunt', 'placeat', '1979-10-30', 'facilis', 8, 'dnader@example.net', '586.904.79', 'NO'),
(19, 'eum', 'sit', '2007-12-26', 'omnis', 1, 'xo\'kon@example.net', '566.773.12', 'NO'),
(20, 'ut', 'voluptatem', '2011-08-23', 'quae', 5, 'crona.kiana@example.org', '(073)475-9', 'NO'),
(21, 'tempore', 'explicabo', '2002-08-24', 'similique', 9, 'quigley.bryce@example.net', '(190)689-5', 'NO'),
(22, 'alias', 'reprehenderit', '1986-11-08', 'ipsa', 3, 'reta69@example.org', '798.795.65', 'NO'),
(23, 'quia', 'rerum', '1999-09-12', 'facilis', 2, 'slemke@example.org', '1-830-388-', 'NO'),
(24, 'est', 'quibusdam', '2002-05-23', 'enim', 3, 'tatum.rippin@example.com', '1-433-171-', 'NO'),
(25, 'expedita', 'eum', '2002-10-30', 'non', 5, 'lillian98@example.org', '214.382.86', 'NO'),
(26, 'doloribus', 'autem', '2017-09-04', 'repudiandae', 7, 'eliane45@example.com', '023.672.55', 'NO'),
(27, 'beatae', 'quam', '1981-06-06', 'dolore', 4, 'abigail35@example.com', '(757)876-5', 'NO'),
(28, 'consequatur', 'enim', '1973-05-29', 'est', 1, 'hilpert.enola@example.org', '047.410.27', 'NO'),
(29, 'consequuntur', 'fugiat', '2012-11-06', 'vel', 6, 'nschneider@example.org', '+07(1)5044', 'NO'),
(30, 'autem', 'nulla', '1978-09-29', 'eos', 8, 'zkunze@example.com', '973-663-35', 'NO'),
(31, 'possimus', 'totam', '2009-02-01', 'quis', 5, 'jrunolfsdottir@example.net', '1-500-242-', 'NO'),
(32, 'nihil', 'est', '1999-04-16', 'qui', 8, 'reynolds.cortney@example.org', '0414146952', 'NO'),
(33, 'sit', 'quis', '1981-07-26', 'cumque', 5, 'theo.crona@example.org', '1-474-231-', 'NO'),
(34, 'rerum', 'officia', '1983-01-06', 'hic', 7, 'vhamill@example.com', '(848)951-5', 'NO'),
(35, 'pariatur', 'quis', '1998-03-22', 'illum', 7, 'dooley.lorena@example.com', '397-917-61', 'NO'),
(36, 'voluptatem', 'unde', '2005-07-10', 'optio', 5, 'yprohaska@example.org', '0838080975', 'NO'),
(37, 'eveniet', 'consectetur', '1991-12-13', 'odit', 4, 'parisian.santina@example.net', '0718200478', 'NO'),
(38, 'magnam', 'veritatis', '2002-10-09', 'velit', 7, 'sincere.mclaughlin@example.com', '970.277.52', 'NO'),
(39, 'error', 'minima', '1986-01-16', 'esse', 5, 'kayden48@example.net', '190.072.17', 'NO'),
(40, 'minima', 'harum', '2012-10-16', 'et', 3, 'ssipes@example.org', '1-858-221-', 'NO'),
(41, 'consequuntur', 'quia', '1982-04-07', 'est', 6, 'fvon@example.net', '(511)427-3', 'NO'),
(42, 'ea', 'qui', '1976-01-09', 'inventore', 2, 'cielo.d\'amore@example.org', '439.759.67', 'NO'),
(43, 'sit', 'id', '2006-06-04', 'possimus', 7, 'candice14@example.com', '0235003014', 'NO'),
(44, 'sunt', 'officia', '1980-09-22', 'repellat', 4, 'frunte@example.com', '1-585-223-', 'NO'),
(45, 'assumenda', 'doloremque', '1989-01-13', 'nulla', 7, 'hills.amie@example.net', '0914915802', 'NO'),
(46, 'minus', 'itaque', '2017-12-04', 'aut', 2, 'hackett.gonzalo@example.org', '891-250-03', 'NO'),
(47, 'eum', 'dolores', '2010-09-03', 'sunt', 7, 'rosenbaum.augustus@example.org', '590-823-19', 'NO'),
(48, 'earum', 'molestiae', '2013-01-27', 'quae', 4, 'ben23@example.org', '(461)298-4', 'NO'),
(49, 'mollitia', 'ut', '2016-08-10', 'consequatur', 5, 'natalia14@example.net', '259-602-99', 'NO'),
(50, 'sed', 'aut', '1979-03-31', 'aut', 8, 'iabshire@example.org', '1-252-605-', 'NO'),
(51, 'itaque', 'est', '1971-07-31', 'odit', 6, 'rippin.alessia@example.org', '(829)238-5', 'NO'),
(52, 'aliquid', 'molestias', '1972-03-11', 'ex', 2, 'louvenia.krajcik@example.net', '582.569.49', 'NO'),
(53, 'quo', 'voluptatem', '1985-01-19', 'mollitia', 6, 'khalil54@example.com', '1-173-445-', 'NO'),
(54, 'cum', 'officiis', '2005-06-04', 'iusto', 4, 'wjacobson@example.net', '+61(9)4196', 'NO'),
(55, 'minima', 'in', '1999-12-17', 'labore', 9, 'isaiah.bradtke@example.com', '1-868-479-', 'NO'),
(56, 'nostrum', 'laudantium', '2015-12-15', 'quo', 3, 'wblick@example.com', '377-625-36', 'NO'),
(57, 'optio', 'consequuntur', '2002-06-01', 'et', 1, 'vcronin@example.com', '352.011.98', 'NO'),
(58, 'ut', 'aut', '2014-01-31', 'quos', 8, 'cynthia.koelpin@example.com', '(822)757-0', 'NO'),
(59, 'molestias', 'consectetur', '2015-10-14', 'voluptas', 6, 'frank48@example.org', '009.160.85', 'NO'),
(60, 'animi', 'reiciendis', '1971-10-18', 'consectetur', 3, 'schumm.jakob@example.org', '131-700-78', 'NO'),
(61, 'veniam', 'quasi', '2011-06-22', 'quaerat', 6, 'acorwin@example.org', '0276995748', 'NO'),
(62, 'corporis', 'quis', '1978-01-18', 'et', 3, 'richie.rowe@example.net', '(900)369-5', 'NO'),
(63, 'enim', 'voluptate', '1989-04-06', 'aut', 2, 'marcelle68@example.org', '011-768-48', 'NO'),
(64, 'corporis', 'est', '1982-12-29', 'at', 5, 'carolyne19@example.com', '0858136671', 'NO'),
(65, 'sed', 'sit', '1977-05-13', 'exercitationem', 5, 'gabriel.bradtke@example.org', '+58(7)9250', 'NO'),
(66, 'hic', 'ex', '1987-01-22', 'ipsum', 8, 'chelsea13@example.net', '1-510-724-', 'NO'),
(67, 'rerum', 'aut', '2011-12-12', 'accusantium', 4, 'claude.runolfsson@example.net', '+03(6)5744', 'NO'),
(68, 'dolorum', 'illo', '2002-01-18', 'delectus', 9, 'obartoletti@example.net', '0515556759', 'NO'),
(69, 'vel', 'reiciendis', '1994-12-27', 'magnam', 9, 'okemmer@example.net', '(836)039-0', 'NO'),
(70, 'possimus', 'at', '1980-01-04', 'sint', 7, 'lavern.fay@example.net', '(483)385-4', 'NO'),
(71, 'enim', 'veritatis', '1995-06-21', 'eum', 4, 'dfadel@example.net', '1-669-627-', 'NO'),
(72, 'corporis', 'voluptatem', '1978-01-25', 'fuga', 4, 'wilkinson.timmy@example.com', '118-672-11', 'NO'),
(73, 'omnis', 'et', '1999-07-04', 'dicta', 9, 'alexanne.wuckert@example.com', '0029496289', 'NO'),
(74, 'possimus', 'tempora', '2014-01-16', 'sed', 5, 'maida30@example.org', '(814)582-6', 'NO'),
(75, 'est', 'animi', '2019-08-30', 'hic', 7, 'dreichel@example.com', '1-953-329-', 'NO'),
(76, 'maiores', 'asperiores', '2012-10-23', 'dolorum', 2, 'krowe@example.com', '1-642-023-', 'NO'),
(77, 'amet', 'id', '1987-04-23', 'omnis', 2, 'rtremblay@example.org', '0089245041', 'NO'),
(78, 'iusto', 'odit', '2005-08-18', 'repudiandae', 9, 'dena79@example.org', '1-172-599-', 'NO'),
(79, 'beatae', 'tenetur', '1988-04-30', 'deleniti', 2, 'awehner@example.org', '1-032-715-', 'NO'),
(80, 'corporis', 'iure', '1982-09-04', 'aut', 2, 'johathan43@example.org', '1-001-840-', 'NO'),
(81, 'ut', 'cupiditate', '1981-01-31', 'fuga', 1, 'yost.sharon@example.com', '+27(1)9732', 'NO'),
(82, 'nihil', 'omnis', '1999-11-08', 'est', 8, 'mstark@example.com', '1-868-585-', 'NO'),
(83, 'iste', 'sunt', '1971-02-23', 'distinctio', 1, 'purdy.winston@example.com', '496.742.16', 'NO'),
(84, 'assumenda', 'libero', '1971-01-13', 'totam', 4, 'estevan.bernier@example.net', '031.363.65', 'NO'),
(85, 'quisquam', 'aut', '2013-10-16', 'accusantium', 9, 'corwin.columbus@example.net', '627-458-13', 'NO'),
(86, 'quidem', 'provident', '1978-03-27', 'voluptas', 7, 'hazel.wolff@example.org', '(405)315-0', 'NO'),
(87, 'sit', 'officiis', '1972-09-07', 'iste', 4, 'cronin.zelda@example.net', '431.785.65', 'NO'),
(88, 'nesciunt', 'voluptatibus', '2013-01-25', 'totam', 4, 'tressie.volkman@example.org', '+29(8)1233', 'NO'),
(89, 'cum', 'minima', '1998-09-27', 'quia', 6, 'weber.eladio@example.com', '1-605-108-', 'NO'),
(90, 'rerum', 'aut', '2010-09-30', 'laborum', 5, 'wbrown@example.com', '(971)940-1', 'NO'),
(91, 'dolorum', 'et', '1977-05-12', 'sit', 1, 'jabari06@example.net', '0259923856', 'NO'),
(92, 'quos', 'nisi', '1987-04-27', 'ipsam', 2, 'lydia94@example.net', '326.877.58', 'NO'),
(93, 'voluptas', 'et', '1978-11-19', 'illo', 2, 'broberts@example.com', '(771)859-3', 'NO'),
(94, 'repellat', 'aut', '2020-03-02', 'nihil', 5, 'dicki.creola@example.net', '0339645574', 'NO'),
(95, 'esse', 'reprehenderit', '1981-07-15', 'illo', 7, 'angeline12@example.com', '1-706-916-', 'NO'),
(96, 'iste', 'ea', '1995-12-12', 'omnis', 5, 'blick.grady@example.com', '497-664-69', 'NO'),
(97, 'quibusdam', 'autem', '1972-05-12', 'aut', 5, 'jones.coralie@example.com', '(729)584-7', 'NO'),
(98, 'quia', 'hic', '1992-09-18', 'et', 4, 'rmetz@example.net', '+23(6)4777', 'NO'),
(99, 'non', 'vitae', '2003-08-25', 'quidem', 2, 'kovacek.delia@example.org', '(565)890-8', 'NO'),
(100, 'laudantium', 'quia', '1980-02-25', 'quod', 3, 'dstroman@example.net', '502-622-49', 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `tran_id` int(20) NOT NULL,
  `payment_status` varchar(20) NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mode_of_payment` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`tran_id`, `payment_status`, `last_update`, `mode_of_payment`) VALUES
(1, 'PAID', '2020-06-19 08:22:27', 'CASH');

-- --------------------------------------------------------

--
-- Table structure for table `registered_users`
--

CREATE TABLE `registered_users` (
  `emp_id` int(20) UNSIGNED NOT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rider_details`
--

CREATE TABLE `rider_details` (
  `emp_id` int(20) NOT NULL,
  `ride_id` int(20) NOT NULL,
  `ride_status` varchar(20) NOT NULL,
  `transaction_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rider_details`
--

INSERT INTO `rider_details` (`emp_id`, `ride_id`, `ride_status`, `transaction_id`) VALUES
(2, 2, 'COMPLETED', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ride_details`
--

CREATE TABLE `ride_details` (
  `ride_id` int(20) UNSIGNED NOT NULL,
  `destination` varchar(250) NOT NULL,
  `start_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `emp_id` int(20) NOT NULL,
  `end_datetime` timestamp NULL DEFAULT NULL,
  `car_type` varchar(45) NOT NULL,
  `car_model` varchar(45) NOT NULL,
  `capacity` int(11) NOT NULL,
  `vehicle_no` varchar(45) NOT NULL,
  `fare` int(11) NOT NULL,
  `ride_status` varchar(30) NOT NULL DEFAULT 'TO BE COMPLETED'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ride_details`
--

INSERT INTO `ride_details` (`ride_id`, `destination`, `start_datetime`, `emp_id`, `end_datetime`, `car_type`, `car_model`, `capacity`, `vehicle_no`, `fare`, `ride_status`) VALUES
(1, 'DEL', '2020-06-26 18:30:00', 1, NULL, 'HATCHBACK', 'AZX', 2, 'FDHDFH', 121, 'CANCELLED'),
(2, 'DEL', '2020-07-02 18:30:00', 1, '2020-06-19 08:23:00', 'HATCHBACK', 'AZS', 2, 'RGDRS', 121, 'COMPLETED');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`tran_id`);

--
-- Indexes for table `registered_users`
--
ALTER TABLE `registered_users`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `rider_details`
--
ALTER TABLE `rider_details`
  ADD PRIMARY KEY (`emp_id`,`ride_id`);

--
-- Indexes for table `ride_details`
--
ALTER TABLE `ride_details`
  ADD PRIMARY KEY (`ride_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `tran_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `registered_users`
--
ALTER TABLE `registered_users`
  MODIFY `emp_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ride_details`
--
ALTER TABLE `ride_details`
  MODIFY `ride_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
