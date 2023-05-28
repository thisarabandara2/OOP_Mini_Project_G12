-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2023 at 06:51 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tecmis`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `technical_officer_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `student_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` varchar(15) NOT NULL,
  `course_name` varchar(225) NOT NULL,
  `course_credit` int(10) NOT NULL,
  `department` varchar(225) NOT NULL,
  `course_type` varchar(225) NOT NULL,
  `semester` varchar(225) NOT NULL,
  `level` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_name`, `course_credit`, `department`, `course_type`, `semester`, `level`) VALUES
('hjjjjjjjj', 'jjjjjjjjjjjj', 5, 'ET', 'Practical', 'Semester 1', 2),
('ICT1212', 'PPP', 2, 'ICT', 'Practical', 'Semester 2', 2),
('ICT23', 'OOAD', 5, 'ICT', 'Theory', 'Semester 1', 1),
('sasa', 'sasa', 3, 'ICT', 'Theory', 'Semester 1', 1),
('sasaasa', 'asasasasa', 2, 'BST', 'Theory', 'Semester 2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `exam_id` varchar(15) NOT NULL,
  `exam_name` varchar(225) NOT NULL,
  `exam_hall` int(10) NOT NULL,
  `exam_type` varchar(225) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lecture`
--

CREATE TABLE `lecture` (
  `lecture_id` varchar(5) NOT NULL,
  `lecture_post` varchar(225) NOT NULL,
  `department_name` varchar(50) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `marks_id` varchar(15) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `quizzes` int(10) NOT NULL,
  `assessment` int(10) NOT NULL,
  `mid_exam` int(10) NOT NULL,
  `final_exam` int(10) NOT NULL,
  `course_id` varchar(15) NOT NULL,
  `student_id` varchar(10) NOT NULL,
  `lecture_id` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medical`
--

CREATE TABLE `medical` (
  `tg` varchar(10) NOT NULL,
  `level` int(10) NOT NULL,
  `semester` int(10) NOT NULL,
  `start_date` varchar(100) NOT NULL,
  `end_date` varchar(100) NOT NULL,
  `subjects` varchar(200) NOT NULL,
  `medFile` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `no` int(10) NOT NULL,
  `title` varchar(225) NOT NULL,
  `date` varchar(40) NOT NULL,
  `noticeFile` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  `course_credit` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `technical_officer`
--

CREATE TABLE `technical_officer` (
  `technical_officer_id` varchar(15) NOT NULL,
  `department` varchar(225) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `department_id` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `refNo` varchar(50) NOT NULL,
  `department` varchar(20) NOT NULL,
  `level` int(15) NOT NULL,
  `semester` int(15) NOT NULL,
  `day` varchar(40) NOT NULL,
  `time` varchar(40) NOT NULL,
  `subject` varchar(40) NOT NULL,
  `file` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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

CREATE TABLE `user` (
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
  `password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `lname`, `address`, `email`, `birthday`, `contactnumber`, `usertype`, `department`, `adminid`, `password`) VALUES
('tg731', 'Thisara', 'Bandara', 'Kandy', 'thisarabandara2@gmail.com', '2000.08.22', '0758228254', 'Student', 'ICT', '1', '123'),
('tg739', 'cc', 'cccc', 'ccc', 'ccc', 'cc', 'ccc55', 'Student', 'BST', '1', '12345'),
('tg750', 'asas', 'asasas', 'asasas', 'asasa', 'sasas', 'sasasas', 'Student', 'ET', 'a', 'javax.swing.plaf.basic.BasicTextUI$BasicCursor[Text Cursor]');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`),
  ADD KEY `addattendance` (`technical_officer_id`),
  ADD KEY `Eligibility` (`course_id`),
  ADD KEY `takes_attendance` (`student_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`exam_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `lecture`
--
ALTER TABLE `lecture`
  ADD PRIMARY KEY (`lecture_id`),
  ADD KEY `user_type` (`user_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `department_id` (`department_id`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`marks_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `lecture_id` (`lecture_id`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `student_ibfk_2` (`course_id`);

--
-- Indexes for table `technical_officer`
--
ALTER TABLE `technical_officer`
  ADD PRIMARY KEY (`technical_officer_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `department` (`department_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notice`
--
ALTER TABLE `notice`
  MODIFY `no` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
--
-- Constraints for table `technical_officer`
--
ALTER TABLE `technical_officer`
  ADD CONSTRAINT `technical officer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
