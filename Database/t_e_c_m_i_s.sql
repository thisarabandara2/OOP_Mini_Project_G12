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
create database tecmis;
USE tecmis;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `attendance_id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `technical_officer_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `student_id` varchar(10) NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `addattendance` (`technical_officer_id`),
  KEY `Eligibility` (`course_id`),
  KEY `takes_attendance` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `course_id` varchar(15) NOT NULL,
  `course_name` varchar(225) NOT NULL,
  `course_credit` int(10) NOT NULL,
  `department` varchar(225) NOT NULL,
  `theory` varchar(225) NOT NULL,
  `practical` varchar(225) NOT NULL,
  `department_id` varchar(15) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course timetable`
--

DROP TABLE IF EXISTS `course_timetable`;
CREATE TABLE IF NOT EXISTS `course_timetable` (
  `ct_id` varchar(15) NOT NULL,
  `lecture_hall` int(10) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  PRIMARY KEY (`ct_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `department_id` varchar(15) NOT NULL,
  `department_name` varchar(225) NOT NULL,
  `department_head` varchar(225) NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
  `exam_id` varchar(15) NOT NULL,
  `exam_name` varchar(225) NOT NULL,
  `exam_hall` int(10) NOT NULL,
  `exam_type` varchar(225) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `course_id` (`course_id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `exam time table`
--

DROP TABLE IF EXISTS `exam_time_table`;
CREATE TABLE IF NOT EXISTS `exam_time_table` (
  `et_id` varchar(15) NOT NULL,
  `exam_hall` int(10) NOT NULL,
  `exam_id` varchar(15) NOT NULL,
  PRIMARY KEY (`et_id`),
  KEY `exam_id` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
CREATE TABLE IF NOT EXISTS `lecture` (
  `lecture_id` varchar(5) NOT NULL,
  `lecture_post` varchar(225) NOT NULL,
  `department_name` varchar(50) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL,
  PRIMARY KEY (`lecture_id`),
  KEY `user_type` (`user_id`),
  KEY `course_id` (`course_id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
CREATE TABLE IF NOT EXISTS `marks` (
  `marks_id` varchar(15) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `quizzes` int(10) NOT NULL,
  `assessment` int(10) NOT NULL,
  `mid_exam` int(10) NOT NULL,
  `final_exam` int(10) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `student_id` varchar(10) NOT NULL,
  `lecture_id` varchar(5) NOT NULL,
  PRIMARY KEY (`marks_id`),
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  KEY `lecture_id` (`lecture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `medical`
--

DROP TABLE IF EXISTS `medical`;
CREATE TABLE IF NOT EXISTS `medical` (
  `medical_id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `details` varchar(225) NOT NULL,
  `student_id` varchar(10) NOT NULL,
  `technical_officer_id` varchar(15) NOT NULL,
  PRIMARY KEY (`medical_id`),
  KEY `student_id` (`student_id`),
  KEY `technical_officer_id` (`technical_officer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` varchar(15) NOT NULL,
  `title` varchar(225) NOT NULL,
  `description` varchar(225) NOT NULL,
  `date` date NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`notice_id`),
  KEY `see_notice` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(10) NOT NULL,
  `department_name` int(11) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `exam_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `user_id` (`user_id`),
  KEY `student_ibfk_2` (`course_id`),
  KEY `exam_id` (`exam_id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `technical officer`
--

DROP TABLE IF EXISTS `technical_officer`;
CREATE TABLE IF NOT EXISTS `technical_officer` (
  `technical_officer_id` varchar(15) NOT NULL,
  `department` varchar(225) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL,
  PRIMARY KEY (`technical_officer_id`),
  KEY `user_id` (`user_id`),
  KEY `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `time table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `time_table_id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `et_id` varchar(15) NOT NULL,
  `ct_id` varchar(15) NOT NULL,
  PRIMARY KEY (`time_table_id`),
  KEY `ct_id` (`ct_id`),
  KEY `exam_time` (`et_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(15) NOT NULL,
  `fname` varchar(225) NOT NULL,
  `lname` varchar(225) NOT NULL,
  `address` varchar(225) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birth_day` int(15) NOT NULL,
  `contact_number` varchar(11) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `Eligibility` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `add_attendance` FOREIGN KEY (`technical_officer_id`) REFERENCES `technical_officer` (`technical_officer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `takes_attendance` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course timetable`
--
ALTER TABLE `course_timetable`
  ADD CONSTRAINT `course timetable_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exam time table`
--
ALTER TABLE `exam_time_table`
  ADD CONSTRAINT `exam time table_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lecture`
--
ALTER TABLE `lecture`
  ADD CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lecture_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user type` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_3` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `medical`
--
ALTER TABLE `medical`
  ADD CONSTRAINT `medical_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `medical_ibfk_2` FOREIGN KEY (`technical_officer_id`) REFERENCES `technical_officer` (`technical_officer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notice`
--
ALTER TABLE `notice`
  ADD CONSTRAINT `see_notice` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_4` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `technical officer`
--
ALTER TABLE `technical_officer`
  ADD CONSTRAINT `department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  ADD CONSTRAINT `technical officer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `time table`
--
ALTER TABLE `time_table`
  ADD CONSTRAINT `exam_time` FOREIGN KEY (`et_id`) REFERENCES `exam_time_table` (`et_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `time table_ibfk_1` FOREIGN KEY (`ct_id`) REFERENCES `course_timetable` (`ct_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;




