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
--

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
  `course_type` varchar(225) NOT NULL,
  `semester` varchar(225) NOT NULL,
  `level` int(10) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course timetable`
--

DROP TABLE IF EXISTS `timetable`;
CREATE TABLE IF NOT EXISTS `timetable` (
  `fname` varchar(50) NOT NULL,
  `department` varchar(20) NOT NULL,
  `level` int(15) NOT NULL,
  `semester` int(15) NOT NULL,
  `file` varchar(100) NOT NULL,
  PRIMARY KEY (`fname`)
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
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--

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
  `medical_id` int(10) NOT NULL auto_increment,
  `tg` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `level` int(10) NOT NULL,
  `medFile` varchar(100) NOT NULL,
  `ICT1` varchar(10) NOT NULL,
  `ICT2` varchar(10) NOT NULL,
  `ICT3` varchar(10) NOT NULL,
  `ICT4` varchar(10) NOT NULL,
  PRIMARY KEY (`medical_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `title` varchar(225) NOT NULL,
  `date` varchar(15) NOT NULL,
  `noticeFile` varchar(50) NOT NULL
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(15) NOT NULL,
  `fname` varchar(225) NOT NULL,
  `lname` varchar(225) NOT NULL,
  `address` varchar(225) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthday` varchar(15) NOT NULL,
  `contactnumber` varchar(11) NOT NULL,
  `usertype` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `adminid` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `confirmpassword` varchar(30) NOT NULL,
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
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lecture`
--
ALTER TABLE `lecture`
  ADD CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user type` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_3` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE;
 

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `technical officer`
--
ALTER TABLE `technical_officer`
  ADD CONSTRAINT `technical officer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;


-- Constraints for table `user`
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



