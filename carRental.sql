DROP TABLE IF EXISTS AGENT;
DROP TABLE IF EXISTS BOOKING;
DROP TABLE IF EXISTS CARS;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS REVIEWS;

CREATE TABLE AGENT
(
	agentID INT PRIMARY KEY,
    agentName VARCHAR(50),
    totalClients INT DEFAULT 0
);

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
	carID INT PRIMARY KEY,
	brand VARCHAR(50),
    year INT,
    color VARCHAR(10),
    type VARCHAR(10),
    rentPrice INT,
    rented BOOLEAN DEFAULT FALSE,
    updatedAt TIMESTAMP
);

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
    stars INT,
    reviewedAgentID INT REFERENCES AGENTS(agentID),
    reviewer INT REFERENCES CUSTOMER(customerID),
    updatedAt TIMESTAMP
);

delete from reviews where reviewID=04;


SELECT
DATEDIFF(dueDate, rentDate) AS days
FROM
BOOKING;

Select reviewedAgentID
From reviews 
Where
stars>2;


Order cars by price
Select carID
From Cars
Order by rentprice;


Select min(rentPrice)
From cars, booking
Where carId not in
(Select bookedcarid from booking);


select bookingid
from booking 
where overdue='false';


SELECT * 
FROM CARS, REVIEWS
WHERE COLOR = ‘white’ AND stars >3;


Drop trigger ins_carInfo;
Create trigger ins_carInfo
After update
On reviews
FOR EACH ROW 
BEGIN
Update reviews set stars=stars+1 where reviewID= new.reviewID;
END;

