DROP DATABASE IF EXISTS CAR_RENTAL;
CREATE DATABASE CAR_RENTAL;
USE CAR_RENTAL;

DROP TABLE IF EXISTS AGENT;
DROP TABLE IF EXISTS BOOKING;
DROP TABLE IF EXISTS CARS;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS REVIEWS;
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE AGENT
(
agentID INT PRIMARY KEY AUTO_INCREMENT,
agentName VARCHAR(50),
totalClients INT DEFAULT 0
);
ALTER table AGENT AUTO_INCREMENT = 1;

CREATE TABLE CARS
(
carID INT PRIMARY KEY AUTO_INCREMENT,
brand VARCHAR(50),
year INT CHECK (year >= 2000),
color VARCHAR(10),
type VARCHAR(10),
rentPrice INT,
rented BOOLEAN DEFAULT FALSE,
updatedAt TIMESTAMP DEFAULT current_timestamp not null ON UPDATE current_timestamp
);
ALTER table CARS AUTO_INCREMENT = 101;

CREATE TABLE BOOKING
(
bookingID INT PRIMARY KEY,
bookedCarID INT UNIQUE KEY,
rentDate date,
dueDate date,
overdue BOOLEAN DEFAULT FALSE,
updatedAt TIMESTAMP DEFAULT current_timestamp not null ON UPDATE current_timestamp,
FOREIGN KEY (bookedCarID) REFERENCES CARS(carID)
);

CREATE TABLE CUSTOMER
(
customerID INT PRIMARY KEY,
customerName VARCHAR(50),
assignedAgentID INT,
assignedCar INT,
updatedAt TIMESTAMP DEFAULT current_timestamp not null ON UPDATE current_timestamp,
FOREIGN KEY (assignedAgentID) REFERENCES AGENT(agentID),
FOREIGN KEY (assignedCar) REFERENCES CARS(carID)
);

CREATE TABLE REVIEWS
(
reviewID INT PRIMARY KEY,
reviewedAgentID INT,
reviewer INT,
stars INT CHECK (stars >= 1 AND stars <= 5),
UNIQUE KEY(reviewer, reviewedAgentID),
FOREIGN KEY (reviewedAgentID) REFERENCES AGENT(agentID),
FOREIGN KEY (reviewer) REFERENCES CUSTOMER(customerID)
);

CREATE TABLE ARCHIVE_CUSTOMER
(
customerID INT PRIMARY KEY,
customerName VARCHAR(50),
assignedAgentID INT,
assignedCar INT,
updatedAt TIMESTAMP
);

CREATE TABLE ARCHIVE_BOOKING
(
bookingID INT PRIMARY KEY,
bookedCarID INT UNIQUE KEY,
rentDate date,
dueDate date,
overdue BOOLEAN,
updatedAt TIMESTAMP
);

/* STORED PROCEDURES */
/* Getting Car IDs by Brand */
DROP PROCEDURE IF EXISTS getCarIDsByBrand;
DELIMITER //
CREATE PROCEDURE getCarIDsByBrand (IN carBrand VARCHAR(50))
BEGIN
SELECT carID FROM Car
WHERE brand = carBrand;
END //
DELIMITER ;

/* Archiving Customer */
DROP PROCEDURE IF EXISTS archiveCustomer;
DELIMITER //
CREATE PROCEDURE archiveCustomer (IN cutoff TIMESTAMP)
BEGIN
	INSERT INTO ARCHIVE_CUSTOMER
    SELECT * FROM CUSTOMER WHERE cutoff > CUSTOMER.updatedAt;
END //
DELIMITER ;

/* Archiving Booking */
DROP PROCEDURE IF EXISTS archiveBooking;
DELIMITER //
CREATE PROCEDURE archiveBooking (IN cutoff TIMESTAMP)
BEGIN
	INSERT INTO ARCHIVE_BOOKING
    SELECT * FROM BOOKING WHERE cutoff > BOOKING.updatedAt;
END //
DELIMITER ;

/* TRIGGERS */
/* Update Total Clients of Agent When Inserting */
DROP TRIGGER IF EXISTS increaseAgentClients;
DELIMITER //
CREATE TRIGGER increaseAgentClients
AFTER INSERT ON CUSTOMER
FOR EACH ROW
BEGIN
	IF NEW.assignedAgentID IS NOT NULL THEN
	UPDATE AGENT SET totalClients = totalClients + 1 WHERE agentID = NEW.assignedAgentID;
    END IF;
END;
//
DELIMITER ;

/* Update Total Clients of Agent After Switching */
DROP TRIGGER IF EXISTS switchAgents;
DELIMITER //
CREATE TRIGGER switchAgents
AFTER UPDATE ON CUSTOMER
FOR EACH ROW
BEGIN
	IF OLD.assignedAgentID <> NEW.assignedAgentID THEN
	UPDATE AGENT SET totalClients = totalClients - 1 WHERE agentID = OLD.assignedAgentID;
    UPDATE AGENT SET totalClients = totalClients + 1 WHERE agentID = NEW.assignedAgentID;
    END IF;
END;
//
DELIMITER ;

/* Update rent status of Car */
DROP TRIGGER IF EXISTS updateRentStatus;
DELIMITER //
CREATE TRIGGER updateRentStatus
AFTER INSERT ON CUSTOMER
FOR EACH ROW
BEGIN
	IF NEW.assignedCar IS NOT NULL THEN
	UPDATE CARS SET rented = TRUE WHERE carID = NEW.assignedCar;
    END IF;
END;
//
DELIMITER ;

/* Booking Period Too Long, Limit to 30 days */
DROP TRIGGER IF EXISTS shortenBookingPeriod;
DELIMITER //
CREATE TRIGGER shortenBookingPeriod
BEFORE INSERT ON BOOKING
FOR EACH ROW
BEGIN
	IF NEW.dueDate - NEW.rentDate > 30 THEN
    SET NEW.dueDate = date_add(NEW.rentDate, INTERVAL 30 DAY);
    END IF;
END;
//
DELIMITER ;

/* Populate AGENT */
insert into AGENT(agentName) values ('Charles');
insert into AGENT(agentName) values ('Alex');
insert into AGENT(agentName) values ('Jung');
insert into AGENT(agentName) values ('Janet');
insert into AGENT(agentName) values ('Gerald');
insert into AGENT(agentName) values ('Nina');

/* Populate CARS */
insert into CARS(brand, year, color, type, rentPrice) values ('Toyota', 2018, 'red', 'hybrid', 30);
insert into CARS(brand, year, color, type, rentPrice) values ('Toyota', 2020, 'green', 'gas', 20);
insert into CARS(brand, year, color, type, rentPrice) values ('Chevrolet', 2021, 'yellow', 'gas', 40);
insert into CARS(brand, year, color, type, rentPrice) values ('Tesla', 2016, 'white', 'electric', 50);
insert into CARS(brand, year, color, type, rentPrice) values ('Lexus', 2019, 'black', 'hybrid', 40);
insert into CARS(brand, year, color, type, rentPrice) values ('Tesla', 2016, 'black', 'electric', 50);

/* Populate BOOKING */
insert into BOOKING(bookingID, bookedCarID, rentDate, dueDate) values (10, 101, '2021-10-01', '2021-12-02');
insert into BOOKING(bookingID, bookedCarID, rentDate, dueDate) values (11, 103, '2021-11-02', '2021-12-02');
insert into BOOKING(bookingID, bookedCarID, rentDate, dueDate) values (12, 105, '2021-11-15', '2021-11-30');
insert into BOOKING(bookingID, bookedCarID, rentDate, dueDate) values (13, 104, '2021-02-01', '2021-11-30');

/* Populate CUSTOMER */
insert into CUSTOMER(customerID, customerName, assignedAgentID, assignedCar) values (1000, 'Dan', 3, 101);
insert into CUSTOMER(customerID, customerName, assignedAgentID, assignedCar) values (1001, 'Nancy', 1, 103);
insert into CUSTOMER(customerID, customerName, assignedAgentID, assignedCar) values (1002, 'John', 2, 105);

/* Populate REVIEWS */
insert into REVIEWS(reviewID, stars, reviewedAgentID, reviewer) values (1, 3, 3, 1000);
insert into REVIEWS(reviewID, stars, reviewedAgentID, reviewer) values (2, 5, 1, 1001);
insert into REVIEWS(reviewID, stars, reviewedAgentID, reviewer) values (3, 1, 5, 1001);
insert into REVIEWS(reviewID, stars, reviewedAgentID, reviewer) values (4, 4, 2, 1002);