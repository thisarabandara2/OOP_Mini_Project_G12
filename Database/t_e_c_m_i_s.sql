-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 14, 2023 at 12:57 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `t e c m i s`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `admin id` varchar(15) NOT NULL,
  `name` varchar(225) NOT NULL,
  `contact number` int(10) NOT NULL,
  PRIMARY KEY (`admin id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `attendance id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `technical officer id` varchar(15) NOT NULL,
  `course id` varchar(15) NOT NULL,
  `student id` varchar(10) NOT NULL,
  PRIMARY KEY (`attendance id`),
  KEY `add attendance` (`technical officer id`),
  KEY `Eligibility` (`course id`),
  KEY `takes attendance` (`student id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `course id` varchar(15) NOT NULL,
  `course name` varchar(225) NOT NULL,
  `course credit` int(10) NOT NULL,
  `department` varchar(225) NOT NULL,
  `theory` varchar(225) NOT NULL,
  `practical` varchar(225) NOT NULL,
  `department id` varchar(15) NOT NULL,
  `admin id` varchar(15) NOT NULL,
  PRIMARY KEY (`course id`),
  KEY `department id` (`department id`),
  KEY `admin id` (`admin id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course timetable`
--

DROP TABLE IF EXISTS `course timetable`;
CREATE TABLE IF NOT EXISTS `course timetable` (
  `ct id` varchar(15) NOT NULL,
  `lecture hall` int(10) NOT NULL,
  `course id` varchar(15) NOT NULL,
  PRIMARY KEY (`ct id`),
  KEY `course id` (`course id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `department id` varchar(15) NOT NULL,
  `department name` varchar(225) NOT NULL,
  `department head` varchar(225) NOT NULL,
  PRIMARY KEY (`department id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
  `exam id` varchar(15) NOT NULL,
  `exam name` varchar(225) NOT NULL,
  `exam hall` int(10) NOT NULL,
  `exam type` varchar(225) NOT NULL,
  `course id` varchar(15) NOT NULL,
  `department id` varchar(15) NOT NULL,
  PRIMARY KEY (`exam id`),
  KEY `course id` (`course id`),
  KEY `department id` (`department id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `exam time table`
--

DROP TABLE IF EXISTS `exam time table`;
CREATE TABLE IF NOT EXISTS `exam time table` (
  `et id` varchar(15) NOT NULL,
  `exam hall` int(10) NOT NULL,
  `exam id` varchar(15) NOT NULL,
  PRIMARY KEY (`et id`),
  KEY `exam id` (`exam id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
CREATE TABLE IF NOT EXISTS `lecture` (
  `lecture id` varchar(5) NOT NULL,
  `lecture post` varchar(225) NOT NULL,
  `department name` varchar(50) NOT NULL,
  `user id` varchar(15) NOT NULL,
  `course id` varchar(15) NOT NULL,
  `department id` varchar(15) NOT NULL,
  PRIMARY KEY (`lecture id`),
  KEY `user type` (`user id`),
  KEY `course id` (`course id`),
  KEY `department id` (`department id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
CREATE TABLE IF NOT EXISTS `marks` (
  `marks id` varchar(15) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `quizzes` int(10) NOT NULL,
  `assessment` int(10) NOT NULL,
  `mid exam` int(10) NOT NULL,
  `final exam` int(10) NOT NULL,
  `course id` varchar(15) NOT NULL,
  `student id` varchar(10) NOT NULL,
  `lecture id` varchar(5) NOT NULL,
  PRIMARY KEY (`marks id`),
  KEY `course id` (`course id`),
  KEY `student id` (`student id`),
  KEY `lecture id` (`lecture id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `medical`
--

DROP TABLE IF EXISTS `medical`;
CREATE TABLE IF NOT EXISTS `medical` (
  `medical id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `details` varchar(225) NOT NULL,
  `student id` varchar(10) NOT NULL,
  `technical officer id` varchar(15) NOT NULL,
  PRIMARY KEY (`medical id`),
  KEY `student id` (`student id`),
  KEY `technical officer id` (`technical officer id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `notice id` varchar(15) NOT NULL,
  `title` varchar(225) NOT NULL,
  `description` varchar(225) NOT NULL,
  `date` date NOT NULL,
  `user id` varchar(15) NOT NULL,
  `admin id` varchar(15) NOT NULL,
  PRIMARY KEY (`notice id`),
  KEY `see_notice` (`user id`),
  KEY `create_notice` (`admin id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student id` varchar(10) NOT NULL,
  `department name` int(11) NOT NULL,
  `user id` varchar(15) NOT NULL,
  `course id` varchar(15) NOT NULL,
  `exam id` varchar(15) NOT NULL,
  `department id` varchar(15) NOT NULL,
  PRIMARY KEY (`student id`),
  KEY `user id` (`user id`),
  KEY `student_ibfk_2` (`course id`),
  KEY `exam id` (`exam id`),
  KEY `department id` (`department id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `technical officer`
--

DROP TABLE IF EXISTS `technical officer`;
CREATE TABLE IF NOT EXISTS `technical officer` (
  `technical officer id` varchar(15) NOT NULL,
  `department` varchar(225) NOT NULL,
  `user id` varchar(15) NOT NULL,
  `department id` varchar(15) NOT NULL,
  PRIMARY KEY (`technical officer id`),
  KEY `user id` (`user id`),
  KEY `department` (`department id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `time table`
--

DROP TABLE IF EXISTS `time table`;
CREATE TABLE IF NOT EXISTS `time table` (
  `time table id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `admin id` varchar(15) NOT NULL,
  `et id` varchar(15) NOT NULL,
  `ct id` varchar(15) NOT NULL,
  PRIMARY KEY (`time table id`),
  KEY `ct id` (`ct id`),
  KEY `exam time` (`et id`),
  KEY `create time table` (`admin id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user id` varchar(15) NOT NULL,
  `fname` varchar(225) NOT NULL,
  `lname` varchar(225) NOT NULL,
  `address` varchar(225) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birth day` int(15) NOT NULL,
  `contact number` varchar(11) NOT NULL,
  `user type` varchar(20) NOT NULL,
  `admin id` varchar(15) NOT NULL,
  PRIMARY KEY (`user id`),
  KEY `user_ibfk_1` (`admin id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `Eligibility` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `add attendance` FOREIGN KEY (`technical officer id`) REFERENCES `technical officer` (`technical officer id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `takes attendance` FOREIGN KEY (`student id`) REFERENCES `student` (`student id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`department id`) REFERENCES `department` (`department id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `course_ibfk_2` FOREIGN KEY (`admin id`) REFERENCES `admin` (`admin id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course timetable`
--
ALTER TABLE `course timetable`
  ADD CONSTRAINT `course timetable_ibfk_1` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`department id`) REFERENCES `department` (`department id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam time table`
--
ALTER TABLE `exam time table`
  ADD CONSTRAINT `exam time table_ibfk_1` FOREIGN KEY (`exam id`) REFERENCES `exam` (`exam id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lecture`
--
ALTER TABLE `lecture`
  ADD CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lecture_ibfk_2` FOREIGN KEY (`department id`) REFERENCES `department` (`department id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user type` FOREIGN KEY (`user id`) REFERENCES `user` (`user id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`student id`) REFERENCES `student` (`student id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_3` FOREIGN KEY (`lecture id`) REFERENCES `lecture` (`lecture id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `medical`
--
ALTER TABLE `medical`
  ADD CONSTRAINT `medical_ibfk_1` FOREIGN KEY (`student id`) REFERENCES `student` (`student id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `medical_ibfk_2` FOREIGN KEY (`technical officer id`) REFERENCES `technical officer` (`technical officer id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notice`
--
ALTER TABLE `notice`
  ADD CONSTRAINT `create_notice` FOREIGN KEY (`admin id`) REFERENCES `admin` (`admin id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `see_notice` FOREIGN KEY (`user id`) REFERENCES `user` (`user id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user id`) REFERENCES `user` (`user id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`course id`) REFERENCES `course` (`course id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`exam id`) REFERENCES `exam` (`exam id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_4` FOREIGN KEY (`department id`) REFERENCES `department` (`department id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `technical officer`
--
ALTER TABLE `technical officer`
  ADD CONSTRAINT `department` FOREIGN KEY (`department id`) REFERENCES `department` (`department id`),
  ADD CONSTRAINT `technical officer_ibfk_1` FOREIGN KEY (`user id`) REFERENCES `user` (`user id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `time table`
--
ALTER TABLE `time table`
  ADD CONSTRAINT `create time table` FOREIGN KEY (`admin id`) REFERENCES `admin` (`admin id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `exam time` FOREIGN KEY (`et id`) REFERENCES `exam time table` (`et id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `time table_ibfk_1` FOREIGN KEY (`ct id`) REFERENCES `course timetable` (`ct id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`admin id`) REFERENCES `admin` (`admin id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
