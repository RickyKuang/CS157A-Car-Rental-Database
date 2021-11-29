DROP DATABASE IF EXISTS CAR_RENTAL;
CREATE DATABASE CAR_RENTAL;
USE CAR_RENTAL;

DROP TABLE IF EXISTS AGENT;
DROP TABLE IF EXISTS BOOKING;
DROP TABLE IF EXISTS CARS;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS REVIEWS;

CREATE TABLE AGENT
(
agentID INT PRIMARY KEY AUTO_INCREMENT,
agentName VARCHAR(50),
totalClients INT DEFAULT 0
);
ALTER table AGENT AUTO_INCREMENT = 1;

CREATE TABLE BOOKING
(
bookingID INT PRIMARY KEY,
bookedCarID INT REFERENCES CARS(carID),
rentDate date,
dueDate date,
overdue BOOLEAN DEFAULT FALSE
);

CREATE TABLE CARS
(
carID INT PRIMARY KEY AUTO_INCREMENT,
brand VARCHAR(50),
year INT CHECK (year >= 2000),
color VARCHAR(10),
type VARCHAR(10),
rentPrice INT,
rented BOOLEAN DEFAULT FALSE,
updatedAt TIMESTAMP,
UNIQUE KEY(carID, brand, year)
);
ALTER table CARS AUTO_INCREMENT = 101;

CREATE TABLE CUSTOMER
(
customerID INT PRIMARY KEY,
customerName VARCHAR(50),
assignedAgent VARCHAR(50) REFERENCES AGENTS(agentID),
assignedCar INT REFERENCES CARS(carID)
);

CREATE TABLE REVIEWS
(
reviewID INT PRIMARY KEY,
stars INT CHECK (stars >= 1 AND stars <= 5),
reviewedAgentID INT REFERENCES AGENTS(agentID),
reviewer INT REFERENCES CUSTOMER(customerID),
updatedAt TIMESTAMP,
UNIQUE KEY(reviewer, reviewedAgentID)
);

CREATE TABLE ARCHIVECARS
(
carID INT PRIMARY KEY,
brand VARCHAR(50),
year INT,
color VARCHAR(10),
type VARCHAR(10),
rentPrice INT,
rented BOOLEAN,
timeStamp DATE
);

CREATE TABLE ARCHIVEREVIEWS
(
reviewID INT PRIMARY KEY,
stars INT,
reviewedAgentID INT,
reviewer INT,
timeStamp DATE
);

/* STORED PROCEDURES */
DROP PROCEDURE IF EXISTS getCarIDsByBrand;
DELIMITER //
CREATE PROCEDURE getCarIDsByBrand (IN carBrand VARCHAR(50))
BEGIN
SELECT carID FROM Car
WHERE brand = carBrand;
END //
DELIMITER ;

/* TRIGGERS */
/* Expensive Cars */
/*
DROP TRIGGER expensiveCars;
CREATE TRIGGER expensiveCars
BEFORE INSERT ON Car
FOR EACH ROW
BEGIN
IF RentPrice = 5000
PRINT 'THIS IS AN EXPENSIVE CAR'
END;
*/
/* Rental Period Done */ /*
DROP TRIGGER rentalDone;
CREATE TRIGGER RentalDone
BEFORE DELETE ON Customer, Booking
FOR EACH ROW
BEGIN
IF dueDate = ? AND Overdue = 0;
DELETE FROM Customer WHERE CustomerID = ?;
END;*/

insert into AGENT values (1, 'Charles', 0);
insert into AGENT values (2, 'Alex', 0);
insert into AGENT values (3, 'Jung', 0);
insert into AGENT values (4, 'Janet', 0);
insert into AGENT values (5, 'Gerald', 0);

insert into BOOKING values (10, 101, '2021-10-01', '2021-11-01', 0);
insert into BOOKING values (11, 103, '2021-11-02', '2021-12-02', 0);
insert into BOOKING values (12, 105, '2021-11-15', '2021-11-30', 0);

insert into CARS values (101, 'Toyota', 2018, 'red', 'hybrid', 30, 1);
insert into CARS values (102, 'Toyota', 2020, 'green', 'gas', 20, 1);
insert into CARS values (103, 'Chevrolet', 2021, 'yellow', 'gas', 40, 1);
insert into CARS values (103, 'Tesla', 2016, 'white', 'electronic', 50, 1);
insert into CARS values (103, 'Lexus', 2019, 'black', 'hybrid', 40, 1);

insert into CUSTOMER values (1000, 'Dan', 'Jung', 101);
insert into CUSTOMER values (1001, 'Nancy', 'Charles', 103);
insert into CUSTOMER values (1002, 'John', 'Alex', 105);

insert into REVIEWS values (1, 3, 3, 1000);
insert into REVIEWS values (2, 5, 1, 1001);
insert into REVIEWS values (3, 1, 5, 1001);
insert into REVIEWS values (4, 4, 2, 1002);