CREATE TABLE `tasklist`.`task` (
  `taskid` INT NOT NULL AUTO_INCREMENT,
  `taskname` VARCHAR(200) NOT NULL,
  `category` VARCHAR(45) NULL,
  `duedate` DATETIME NULL,
  `priority` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`taskid`),
  UNIQUE INDEX `taskid_UNIQUE` (`taskid` ASC));

INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Adding Priority information to Task list and new Task form', 'Work', '2017-11-20', 'High', 'stanley', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Creating GIT repository and assign each brand to each member', 'Personal', '2017-11-20', 'Medium', 'stanley', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Delete task''s data from MySQL DB', 'Work', '2017-11-20', 'Low', 'stanley', 'In Progress');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Sorting task by clicking on due date', 'Work', '2017-11-20', 'Medium', 'samson', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Sorting task by clicking on Priority', 'Work', '2017-11-20', 'Low', 'samson', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Update and Store task''s data to MySQL DB', 'Work', '2017-11-21', 'High', 'samson', 'In Progress');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Update the TaskServlet to alLow system to pull date from Database', 'Work', '2017-11-20', 'Low', 'luat', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Make the pom.xml to understand mySql driver', 'Personal', '2017-11-20', 'Medium', 'luat', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Setup and create example data for testing on mysql db', 'Work', '2017-11-20', 'High', 'luat', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Set up Amazon WS environment to for Tomcat instance and MySQL server', 'Personal', '2017-11-19', 'Low', 'luat', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Create the TaskList project structure and put to GIT repository', 'Work', '2017-11-20', 'Medium', 'luat', 'Completed');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Add and Store task''s data to MySQL DB', 'Personal', '2017-11-20', 'High', 'luat', 'In Progress');
INSERT INTO `tasklist`.`task` (`taskname`, `category`, `duedate`, `priority`, `userid`, `status`) VALUES ('Filter task list by user', 'Work', '2017-11-21', 'High', 'luat', 'In Progress');