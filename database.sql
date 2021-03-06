
CREATE TABLE lesson (
        lessonID INT NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(30) NOT NULL,
	NUMBER_LESSONS INT NOT NULL,
        PRIMARY KEY (lessonID)
    );

CREATE TABLE students (
        studentID INT NOT NULL,
        username VARCHAR(30) NOT NULL,
	status VARCHAR(30) NOT NULL,
	chefID INT,
	lessons_attended INT NOT NULL,
        message varchar(50),
        PRIMARY KEY (studentID)
    );
CREATE TABLE completed_courses (
ID INT NOT NULL AUTO_INCREMENT,
studentID INT NOT NULL ,
name VARCHAR(30) NOT NULL,
status VARCHAR(30) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY(studentID) REFERENCES students(studentID)
);

CREATE TABLE chefs (
        chefID INT NOT NULL,
        username VARCHAR(30) NOT NULL,
	studentID INT,
	current_course VARCHAR(30) NOT NULL,
        message VARCHAR(50),
        PRIMARY KEY (chefID)
    );
CREATE TABLE available_chefs(
	lessonID INT NOT NULL,
	name VARCHAR(30) NOT NULL,
	ID INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(ID),
 	FOREIGN KEY(lessonID) REFERENCES lesson(lessonID)
);

CREATE TABLE users (
username VARCHAR(30) NOT NULL,
password VARCHAR(30) NOT NULL,
role VARCHAR(30) NOT NULL,
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY(id)
);

INSERT INTO `lesson`(NAME, NUMBER_LESSONS) VALUES ("CAKE", 3);
INSERT INTO `lesson`(NAME, NUMBER_LESSONS) VALUES ("PIZZA", 2);
INSERT INTO `lesson`(NAME, NUMBER_LESSONS) VALUES ("PIE", 4);
INSERT INTO `lesson`(NAME, NUMBER_LESSONS) VALUES ("MUFFIN", 1);
