-- Section 1 - Data Definition 
CREATE DATABASE airport_management_system;

CREATE TABLE flights(
flight_id INT,
departure_time DATETIME NOT NULL,
arrival_time DATETIME NOT NULL,
status VARCHAR(9) NOT NULL CHECK 
(status='Departing' OR status='Delayed'OR status='Arrived'OR status='Cancelled'),
origin_airport_id INT,
destination_airport_id INT,
airline_id INT,
CONSTRAINT pk_flights PRIMARY KEY (flight_id),
CONSTRAINT fk01_flights_airports FOREIGN KEY 
(origin_airport_id) REFERENCES airports(airport_id),
CONSTRAINT fk02_flights_airports FOREIGN KEY 
(destination_airport_id) REFERENCES airports(airport_id),
CONSTRAINT fk_flights_airlines FOREIGN KEY 
(airline_id) REFERENCES airlines(airline_id)
);

CREATE TABLE tickets(
ticket_id INT,
price DECIMAL(8, 2) NOT NULL,
class VARCHAR(6) NOT NULL CHECK 
(class='First' OR class='Second'OR class='Third'),
seat VARCHAR(5) NOT NULL,
customer_id INT,
flight_id INT,
CONSTRAINT pk_tickets PRIMARY KEY (ticket_id),
CONSTRAINT fk_tickets_customers FOREIGN KEY 
(customer_id) REFERENCES customers(customer_id),
CONSTRAINT fk_tickets_flights FOREIGN KEY 
(flight_id) REFERENCES flights(flight_id)
);

-----------------------------------------------------------------------------------------------


-- Section 2 - Database Manipulations 

-- 1
INSERT INTO flights
	(flight_id, departure_time, arrival_time, status, origin_airport_id, destination_airport_id, airline_id)
VALUES 
	(1, STR_TO_DATE('2016-10-13 06:00 AM', '%Y-%m-%d %h:%i %p'),
		 STR_TO_DATE('2016-10-13 10:00 AM', '%Y-%m-%d %h:%i %p'), 'Delayed', 1, 4, 1),
	(2, STR_TO_DATE('2016-10-12 12:00 PM', '%Y-%m-%d %h:%i %p'),
			STR_TO_DATE('2016-10-12 12:01 PM', '%Y-%m-%d %h:%i %p'),	'Departing', 1, 3, 2),
	(3, STR_TO_DATE('2016-10-14 03:00 PM', '%Y-%m-%d %h:%i %p'), 
		 STR_TO_DATE('2016-10-20 04:00 AM', '%Y-%m-%d %h:%i %p'), 'Delayed', 4, 2,	4),
	(4, STR_TO_DATE('2016-10-12 01:24 PM', '%Y-%m-%d %h:%i %p'), 
		 STR_TO_DATE('2016-10-12 4:31 PM', '%Y-%m-%d %h:%i %p'), 'Departing', 3, 1, 3),
	(5, STR_TO_DATE('2016-10-12 08:11 AM', '%Y-%m-%d %h:%i %p'),
		 STR_TO_DATE('2016-10-12 11:22 PM', '%Y-%m-%d %h:%i %p'), 'Departing',	4,	1,	1),
	(6, STR_TO_DATE('1995-06-21 12:30 PM', '%Y-%m-%d %h:%i %p'),
	    STR_TO_DATE('1995-06-22 08:30 PM', '%Y-%m-%d %h:%i %p'), 'Arrived',	2,	3,	5),
	(7, STR_TO_DATE('2016-10-12 11:34 PM', '%Y-%m-%d %h:%i %p'),
		 STR_TO_DATE('2016-10-13 03:00 AM', '%Y-%m-%d %h:%i %p'), 'Departing', 2, 4, 2),
	(8, STR_TO_DATE('2016-11-11 01:00 PM', '%Y-%m-%d %h:%i %p'),
		 STR_TO_DATE('2016-11-12 10:00 PM', '%Y-%m-%d %h:%i %p'), 'Delayed',	4,	3,	1),
	(9, STR_TO_DATE('2015-10-01 12:00 PM', '%Y-%m-%d %h:%i %p'),
		 STR_TO_DATE('2015-12-01 01:00 AM', '%Y-%m-%d %h:%i %p'), 'Arrived',	1,	2,	1),
	(10, STR_TO_DATE('2016-10-12 07:30 PM', '%Y-%m-%d %h:%i %p'), 
		  STR_TO_DATE('2016-10-13 12:30 PM', '%Y-%m-%d %h:%i %p'), 'Departing',	2,	1,	7);


INSERT INTO tickets
	(ticket_id, price, class, seat, customer_id, flight_id)
VALUES
	(1,	3000.00,	'First', '233-A',	3,	8),
	(2,	1799.90,	'Second', '123-D', 1, 1),
	(3,	1200.50,	'Second', '12-Z',	2,	5),
	(4,	410.68,	'Third',	'45-Q',	2,	8),
	(5,	560.00,	'Third',	'201-R',	4,	6),
	(6,	2100.00,	'Second', '13-T',	1,	9),
	(7, 5500.00, 'First', '98-O',	2,	7);

	
-- 2
UPDATE flights AS fl
SET fl.airline_id = 1
WHERE fl.`status` = 'Arrived'
	
	
-- 3
UPDATE tickets AS t
	SET t.price = t.price + (t.price * 0.5)
	WHERE t.flight_id IN(SELECT fl.flight_id FROM flights AS fl
									INNER JOIN airlines AS a
									ON fl.airline_id = a.airline_id
									AND a.airline_id = (SELECT a.airline_id FROM airlines AS a
																	WHERE a.rating = (SELECT MAX(rating)FROM airlines)))



-- 4
CREATE TABLE customer_reviews(
review_id INT,
review_content VARCHAR(255) NOT NULL,
review_grade INT NOT NULL CHECK (review_grade >= 0 AND review_grade <= 10),
airline_id INT,
customer_id INT,
CONSTRAINT pk_customer_reviews PRIMARY KEY (review_id),
CONSTRAINT fk_customer_reviews_airlines FOREIGN KEY 
(airline_id) REFERENCES airlines(airline_id),
CONSTRAINT fk_customer_reviews_customers FOREIGN KEY 
(customer_id) REFERENCES customers(customer_id)
);


CREATE TABLE customer_bank_accounts(
account_id INT,
account_number VARCHAR(10) NOT NULL UNIQUE,
balance DECIMAL(10, 2) NOT NULL,
customer_id INT,
CONSTRAINT pk_customer_bank_accounts PRIMARY KEY (account_id),
CONSTRAINT fk_customer_bank_accounts_customers FOREIGN KEY 
(customer_id) REFERENCES customers(customer_id)
);


-- 5
INSERT INTO customer_reviews
	(review_id, review_content, review_grade, airline_id, customer_id)
VALUES
	(1,	'Me is very happy. Me likey this airline. Me good.',	10,	1,	1),
	(2,	'Ja, Ja, Ja... Ja, Gut, Gut, Ja Gut! Sehr Gut!',	10,	1,	4),
	(3, 'Meh...',	5,	4,	3),
	(4,	'Well Ive seen better, but Ive certainly seen a lot worse...',	7,	3,	5);


INSERT INTO customer_bank_accounts
	(account_id, account_number, balance, customer_id)
VALUES
	(1, '123456790', 2569.23, 1),
	(2, '18ABC23672',	14004568.23, 2),
	(3, 'F0RG0100N3',	19345.20, 5);


-- Section 3 - Querring Section

-- 1
SELECT t.ticket_id, t.price, t.class, t.seat FROM tickets AS t
	ORDER BY t.ticket_id

-- 2
SELECT c.customer_id, 
		CONCAT(c.first_name, ' ', c.last_name) AS full_name,
		c.gender FROM customers AS c
ORDER BY full_name, c.customer_id

-- 3
SELECT fl.flight_id, fl.departure_time, fl.arrival_time FROM flights AS fl
	WHERE fl.`status` = 'Delayed'
ORDER BY fl.flight_id

-- 4
SELECT a.airline_id,	a.airline_name, a.nationality, a.rating FROM airlines AS a
	INNER JOIN flights AS fl
	ON a.airline_id = fl.airline_id
GROUP BY a.airline_id
ORDER BY a.rating DESC, a.airline_id LIMIT 5


-- 5
SELECT t.ticket_id, 
		air.airport_name AS destination, 
		CONCAT(c.first_name, ' ', c.last_name) AS customer_name FROM tickets AS t
	INNER JOIN customers AS c
	ON t.customer_id = c.customer_id
		INNER JOIN flights AS fl
		ON t.flight_id = fl.flight_id
			 INNER JOIN airports AS air
			 ON fl.destination_airport_id = air.airport_id
WHERE t.price < 5000
AND t.class = 'First'
ORDER BY t.ticket_id ASC

-- 6
SELECT c.customer_id, 
		CONCAT(c.first_name, ' ', c.last_name) AS full_name,
	   t.town_name FROM customers AS c
	INNER JOIN tickets AS tic
	ON c.customer_id = tic.customer_id
		INNER JOIN flights AS fl
		ON tic.flight_id = fl.flight_id
			INNER JOIN airports AS ap
			ON fl.destination_airport_id = ap.airport_id
				INNER JOIN towns AS t
				ON ap.town_id = t.town_id
				AND c.home_town_id = t.town_id
				
-- 7
SELECT c.customer_id, 
		CONCAT(c.first_name, ' ', c.last_name) AS full_name,
		2016 - YEAR(c.date_of_birth) AS age
		FROM customers AS c
	INNER JOIN tickets AS tic
	ON c.customer_id = tic.customer_id
		INNER JOIN flights AS fl
		ON tic.flight_id = fl.flight_id
WHERE fl.`status` = 'Departing'
GROUP BY c.customer_id
ORDER BY age ASC, c.customer_id ASC

-- 8
SELECT c.customer_id, 
		CONCAT(c.first_name, ' ', c.last_name) AS full_name ,
		tic.price,
		air.airport_name
		FROM customers AS c
	INNER JOIN tickets AS tic
	ON c.customer_id = tic.customer_id
		INNER JOIN flights AS fl
		ON tic.flight_id = fl.flight_id
			INNER JOIN airports AS air
			ON fl.destination_airport_id = air.airport_id
WHERE fl.`status` = 'Delayed'
GROUP BY c.customer_id
ORDER BY tic.price DESC, c.customer_id ASC LIMIT 3


-- 9
SELECT fl.flight_id,	
		fl.departure_time,
		fl.arrival_time,
		air.airport_name AS origin,
		air2.airport_name AS destination FROM flights AS fl
	INNER JOIN airports AS air
	ON fl.origin_airport_id = air.airport_id
		INNER JOIN airports AS air2
		ON fl.destination_airport_id = air2.airport_id
WHERE fl.`status` = 'Departing'
ORDER BY fl.departure_time ASC, fl.flight_id ASC LIMIT 5


-- 10	
SELECT c.customer_id, 
		CONCAT(c.first_name, ' ', c.last_name) AS full_name,
		2016 - YEAR(c.date_of_birth) AS age 
		FROM customers AS c		
	INNER JOIN tickets AS tic
	ON c.customer_id = tic.customer_id
		INNER JOIN flights AS fl
		ON tic.flight_id = fl.flight_id
WHERE 2016 - YEAR(c.date_of_birth) < 21
AND fl.`status` = 'Arrived'
ORDER BY age DESC, c.customer_id ASC


-- 11	
SELECT air.airport_id, 
		air.airport_name,
		 COUNT(tic.customer_id) AS passengers 
		FROM airports AS air
	INNER JOIN flights AS fl
	ON air.airport_id = fl.origin_airport_id
	-- OR air.airport_id = fl.origin_airport_id
		INNER JOIN tickets AS tic
		ON fl.flight_id = tic.flight_id
WHERE fl.`status` = 'Departing'
GROUP BY air.airport_id-- , tic.flight_id
ORDER BY air.airport_id ASC


-- Section 4 - Programmability

-- 1	
DELIMITER $$ 

CREATE PROCEDURE usp_submit_review(customer_id INT, review_content VARCHAR(255), review_grade INT, airline_name VARCHAR(30))
BEGIN
DECLARE airline_id INT;
DECLARE new_review_id INT;

SET airline_id := (SELECT a.airline_id FROM airlines AS a WHERE a.airline_name = airline_name);
SET new_review_id := (SELECT cr.review_id FROM customer_reviews AS cr
								WHERE cr.review_id = (SELECT MAX(review_id) FROM customer_reviews)) + 1;

START TRANSACTION;

	INSERT INTO customer_reviews
		(review_id, review_content, review_grade, airline_id, customer_id)
	VALUES
		(new_review_id, review_content, review_grade, airline_id, customer_id);
		
	IF (airline_id IS NULL) THEN
		ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Airline does not exist.';
	ELSE
		COMMIT;
	END IF;
	
END$$

DELIMITER ;


--- 2
DELIMITER $$ 

CREATE PROCEDURE usp_purchase_ticket(customer_id INT, 
													flight_id INT, 
													ticket_price DECIMAL(8, 2), 
													class VARCHAR(6),
													seat VARCHAR(5))
BEGIN
DECLARE customer_balance DECIMAL(10, 2);
DECLARE new_ticket_id INT;
SET new_ticket_id := (SELECT t.ticket_id FROM tickets AS t
								WHERE t.ticket_id = (SELECT MAX(ticket_id) FROM tickets)) + 1;
SET customer_balance := (SELECT cba.balance FROM customer_bank_accounts AS cba
									WHERE cba.customer_id = customer_id);
START TRANSACTION;	

	INSERT INTO tickets
		(ticket_id, price, class, seat, customer_id, flight_id)
	VALUES
		(new_ticket_id, ticket_price, class, seat, customer_id, flight_id);
		
	UPDATE customer_bank_accounts AS cba
		SET cba.balance = cba.balance - ticket_price
	WHERE cba.customer_id = customer_id;
	
	IF (customer_balance < ticket_price) THEN
		ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient bank account balance for ticket purchase.';
	ELSE 
		COMMIT;
	END IF;
	
END$$

DELIMITER ;


-- Bonus Section

CREATE TABLE arrived_flights(
flight_id INT,
arrival_time TIMESTAMP NOT NULL,
origin VARCHAR(50) NOT NULL,
destination VARCHAR(50) NOT NULL,
passengers INT NOT NULL,
CONSTRAINT pk_arrived_flights PRIMARY KEY (flight_id)
);

DELIMITER $$

CREATE TRIGGER tr_arrive_flight
AFTER UPDATE
ON flights
FOR EACH ROW
BEGIN
									
	IF (NEW.status = 'Arrived') THEN
		
		INSERT INTO arrived_flights
			(flight_id, arrival_time, origin, destination, passengers)
		VALUES
			(NEW.flight_id, 
			NEW.arrival_time, 
			NEW.origin_airport_id, 
			NEW.destination_airport_id, 
			(SELECT COUNT(tic.customer_id) AS pass_count FROM tickets AS tic
										INNER JOIN flights AS fl
										ON tic.flight_id = fl.flight_id
									WHERE fl.flight_id = NEW.flight_id));
	END IF;
	
END $$
