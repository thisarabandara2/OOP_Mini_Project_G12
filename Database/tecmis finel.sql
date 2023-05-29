-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 28, 2023 at 06:06 PM
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
-- Database: `tecmis`
--

--
-- Table structure for table `gpa`
--

DROP TABLE IF EXISTS `gpa`;
CREATE TABLE IF NOT EXISTS `gpa` (
  `stuid` varchar(10) NOT NULL,
  `sgpa` varchar(10)  NOT NULL,
  `cgpa` varchar(6) NOT NULL,
  `subid` varchar(6) NOT NULL
);
-- --------------------------------------------------------
--
-- Table structure for table `final_marks`
--

DROP TABLE IF EXISTS `final_marks`;
CREATE TABLE IF NOT EXISTS `final_marks` (
  `stuid` varchar(10) NOT NULL,
  `subid` varchar(10) NOT NULL,
  `finalMarks` varchar(6) NOT NULL,
  `Theory` varchar(6) NOT NULL,
  `Total` float NOT NULL,
  `Grade` varchar(1) NOT NULL
);

--
-- Dumping data for table `final_marks`
--

INSERT INTO `final_marks` (`stuid`, `subid`, `finalMarks`, `Theory`, `Total`, `Grade`) VALUES
('678', 'Java', '80', '90', 0, ''),
('', '', '0', '0', 0, ''),
('671', 'Java', '80', '90', 0, ''),
('672', 'Java', '80', '90', 0, ''),
('673', 'Java', '60', '70', 0, ''),
('674', 'Java', '0', '60', 0, ''),
('675', 'Java', '70', '0', 0, ''),
('675', 'Java', '70', '0', 0, ''),
('672', 'Java', '80', '90', 0, ''),
('tg671', 'Java', 'A/N', '60', 0, ''),
('tg671', 'Java', '70', 'A/N', 0, '');
COMMIT;
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
);

-- --------------------------------------------------------
--
-- Table structure for table `ca_marks`
--

DROP TABLE IF EXISTS `ca_marks`;
CREATE TABLE IF NOT EXISTS `ca_marks` (
  `subject_id` varchar(10) NOT NULL,
  `student_id` varchar(10) NOT NULL,
  `quiz` varchar(4) NOT NULL,
  `assignment` varchar(4)  NOT NULL,
  `mid` varchar(5) NOT NULL,
  `Total` varchar(4)  NOT NULL,
  `Elegibaly` varchar(15) NOT NULL
);

--
-- Dumping data for table `ca_marks`
--

INSERT INTO `ca_marks` (`subject_id`, `student_id`, `quiz`, `assignment`, `mid`, `Total`, `Elegibaly`) VALUES
('Java', '004', '70', '80', '70', '0', ''),
('Java', '009', '73.', '110', '98', '0', ''),
('Java', 'tgb675', '70', '95', 'N/A', '50', ''),
('Java', 'tg675', '60', 'N/A', 'N/A', '50', '');
COMMIT;
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
  `material_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_name`, `course_credit`, `department`, `course_type`, `semester`, `level`, `material_name`) VALUES
('hjjjjjjjj', 'jjjjjjjjjjjj', 5, 'ET', 'Practical', 'Semester 1', 2, NULL),
('ICT1212', 'PPP', 2, 'ICT', 'Practical', 'Semester 2', 2, NULL),
('ICT23', 'OOAD', 5, 'ICT', 'Theory', 'Semester 1', 1, NULL),
('sasa', 'sasa', 3, 'ICT', 'Theory', 'Semester 1', 1, NULL),
('sasaasa', 'asasasasa', 2, 'BST', 'Theory', 'Semester 2', 1, NULL);

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
  KEY `lecture_id` (`lecture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `medical`
--

DROP TABLE IF EXISTS `medical`;
CREATE TABLE IF NOT EXISTS `medical` (
  `tg` varchar(10) NOT NULL,
  `level` int(10) NOT NULL,
  `semester` int(10) NOT NULL,
  `start_date` varchar(100) NOT NULL,
  `end_date` varchar(100) NOT NULL,
  `subjects` varchar(200) NOT NULL,
  `medFile` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medical`
--

INSERT INTO `medical` (`tg`, `level`, `semester`, `start_date`, `end_date`, `subjects`, `medFile`) VALUES
('tg731', 1, 2, 'Mon May 01 23:28:39 IST 2023', 'Thu May 11 23:28:46 IST 2023', 'jouihuijhnj,,,jhihhnj', 'medicals/Doc1.docx');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `no` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(225) NOT NULL,
  `date` varchar(40) NOT NULL,
  `noticeFile` varchar(200) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notice`
--

INSERT INTO `notice` (`no`, `title`, `date`, `noticeFile`) VALUES
(8, 'huihkjnjknjk', '2023-05-28 02:04:00', 'notices/Muruthawela.pdf'),
(11, 'ede', '2023-05-28 10:07:14', 'sxe');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(10) NOT NULL,
  `department_name` varchar(20) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `course_credit` int(20) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `student_ibfk_2` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `department_name`, `user_id`, `course_id`, `course_name`, `course_credit`) VALUES
('TG739', '1', 'shah', 'sacSS', 'gttyyu', 2),
('TG739', '1', 'shah', 'sacSS', 'gttyyu', 2);

-- --------------------------------------------------------

--
-- Table structure for table `technical_officer`
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
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
CREATE TABLE IF NOT EXISTS `timetable` (
  `refNo` varchar(50) NOT NULL,
  `department` varchar(20) NOT NULL,
  `level` int(15) NOT NULL,
  `semester` int(15) NOT NULL,
  `day` varchar(40) NOT NULL,
  `time` varchar(40) NOT NULL,
  `subject` varchar(40) NOT NULL,
  `file` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`refNo`, `department`, `level`, `semester`, `day`, `time`, `subject`, `file`) VALUES
('', 'ICT', 2, 1, 'Select', '', '', 'uploads/Muruthawela.pdf'),
('', 'ICT', 1, 2, 'Monday', '12-2', 'english', ''),
('', 'ICT', 1, 2, 'Monday', '12-2', 'english', ''),
('ONLY FOR PDF', 'ICT', 1, 2, 'Monday', '12-2', 'english', ''),
('ONLY FOR PDF', 'ICT', 1, 2, 'Monday', '12-2', 'englishIII', ''),
('ONLY FOR PDF', 'ICT', 1, 2, 'Monday', '12-2', 'englishIIIIII', ''),
('L1 SEM 2', 'ICT', 2, 2, 'Tuesday', '', '', 'wt4rtg4tf4t');

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
  `password` varchar(500) NOT NULL,
  `profile_picture` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `lname`, `address`, `email`, `birthday`, `contactnumber`, `usertype`, `department`, `adminid`, `password`, `profile_picture`) VALUES
('lec1', 'nuwan', 'laksiri', 'gdfgd', 'gdfg', '1999.02.02', '0754215', 'Lecturer', 'ICT', '1', 'nuwan', NULL),
('tec1', 'chathu', 'dil', 'asasas', 'asas', 'asasas', '695416952', 'Technical Officer', 'ET', '1', 'tec01', NULL),
('tg731', 'Thisara', 'Bandara', 'jhoih', 'pkijoihi', '2000.08.22', 'xnlxk', 'Student', 'ICT', '1', '123', 'profile/IMG_8395.jpg'),
('tg739', 'cc', 'cccc', 'ccc', 'ccc', 'cc', 'ccc55', 'Student', 'BST', '1', '12345', ''),
('tg750', 'asas', 'asasas', 'asasas', 'asasa', 'sasas', 'sasasas', 'Student', 'ET', 'a', 'javax.swing.plaf.basic.BasicTextUI$BasicCursor[Text Cursor]', '');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `Eligibility` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `add_attendance` FOREIGN KEY (`technical_officer_id`) REFERENCES `technical_officer` (`technical_officer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `marks_ibfk_3` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `technical_officer`
--
ALTER TABLE `technical_officer`
  ADD CONSTRAINT `technical officer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
