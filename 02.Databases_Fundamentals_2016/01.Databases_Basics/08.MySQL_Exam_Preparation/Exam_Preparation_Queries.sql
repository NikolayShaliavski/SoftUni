-- FIRST SECTION 

-- Creating tables

CREATE TABLE deposit_types(
deposit_type_id INT PRIMARY KEY,
name VARCHAR(20)
);

CREATE TABLE deposits(
deposit_id INT NOT NULL AUTO_INCREMENT,
amount DECIMAL(10, 2),
start_date DATE,
end_date DATE,
deposit_type_id INT,
customer_id INT,
CONSTRAINT pk_deposits PRIMARY KEY (deposit_id),
CONSTRAINT fk_deposits_deposit_types 
FOREIGN KEY (deposit_type_id) REFERENCES deposit_types(deposit_type_id),
CONSTRAINT fk_deposits_customers 
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE employees_deposits(
employee_id INT,
deposit_id INT,
PRIMARY KEY (employee_id, deposit_id),
CONSTRAINT fk_employees_deposits_employees 
FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
CONSTRAINT fk_employees_deposits_deposits 
FOREIGN KEY (deposit_id) REFERENCES deposits(deposit_id)
);

CREATE TABLE credit_history(
credit_history_id INT PRIMARY KEY,
mark CHAR(1),
start_date DATE,
end_date DATE,
customer_id INT,
CONSTRAINT fk_credit_history_customers 
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE payments(
payement_id INT PRIMARY KEY,
date DATE,
amount DECIMAL(10, 2),
loan_id INT,
CONSTRAINT fk_payments_loans
FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
); 


CREATE TABLE users(
user_id INT PRIMARY KEY,
user_name VARCHAR(20),
password VARCHAR(20),
customer_id INT UNIQUE,
CONSTRAINT fk_users_customers
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Modifying tables

ALTER TABLE employees
	ADD manager_id INT;
	
ALTER TABLE employees
	ADD CONSTRAINT fk_employees_employees
	FOREIGN KEY (manager_id) REFERENCES employees(employee_id);
------------------------------------------------------------------------------------------------------------------------


-- SECOND SECTION 

-- Inserts

INSERT INTO deposit_types
	VALUES
	(1, 'Time Deposit'),
	(2, 'Call Deposit'),
	(3, 'Free Deposit');
	
INSERT INTO deposits(amount, start_date, end_date, deposit_type_id, customer_id)
SELECT CASE
			WHEN c.date_of_birth > '1980-01-01' THEN
				1000
			ELSE 
				1500
			END
			+
		 CASE
			WHEN c.gender = 'M' THEN 
				100
			ELSE 
				200
			END,
		 NOW(),
		 NULL AS end_date,
		 CASE
		 	WHEN c.customer_id > 15 THEN
		 	3
		 WHEN c.customer_id % 2 = 0 THEN
		 	2
		 ELSE
		 	1
		 END,
		 c.customer_id
  FROM customers AS c
  WHERE c.customer_id < 20;
  
INSERT INTO employees_deposits
	VALUES
	(15, 4),
	(20, 15),
	(8, 7),
	(4, 8),
	(3, 13),
	(3, 8),
	(4, 10),
	(10, 1),
	(13, 4),
	(14, 9);

-- Updates

UPDATE employees AS e
	SET manager_id = 
	CASE
		WHEN (e.employee_id BETWEEN 2 AND 10) THEN 1
		WHEN (e.employee_id BETWEEN 12 AND 20) THEN 11
		WHEN (e.employee_id BETWEEN 22 AND 30)  THEN 21
		WHEN (e.employee_id IN (11, 21)) THEN 1
	END;

-- Deletes

DELETE FROM employees_deposits 
	WHERE deposit_id = 9 OR employee_id = 3;
------------------------------------------------------------------------------------------------------------------------	


-- THIRD SECTION - Queries

SELECT e.employee_id, e.hire_date, e.salary, e.branch_id FROM employees AS e 
	WHERE e.salary > 2000 AND e.hire_date > '2009-06-15'
------------------------------------------------------------------------------------------------------------------------	

SELECT c.first_name, 
		 c.date_of_birth, 
		 FLOOR(DATEDIFF('2016-10-01', c.date_of_birth) / 360) AS age
FROM customers AS c
WHERE DATEDIFF('2016-10-01', c.date_of_birth) / 360 BETWEEN 40 AND 50
------------------------------------------------------------------------------------------------------------------------

SELECT c.customer_id, c.first_name, c.last_name, c.gender, cit.city_name FROM customers AS c
	INNER JOIN cities AS cit
	ON c.city_id = cit.city_id
WHERE (c.last_name LIKE 'Bu%' -- can use LEFT(c.last_name, 2) = 'Bu'
	   OR RIGHT(c.first_name, 1) = 'a')
	   AND CHAR_LENGTH(cit.city_name) >= 8
------------------------------------------------------------------------------------------------------------------------

SELECT e.employee_id, e.first_name, ac.account_number FROM employees AS e
	INNER JOIN employees_accounts AS ea
	ON e.employee_id = ea.employee_id
		INNER JOIN accounts AS ac
		ON ea.account_id = ac.account_id
WHERE YEAR(ac.start_date) > 2012
ORDER BY e.first_name DESC LIMIT 5
------------------------------------------------------------------------------------------------------------------------

SELECT c.city_name, b.name, COUNT(*) AS employees_count FROM cities AS c
	INNER JOIN branches AS b
	ON c.city_id = b.city_id
		INNER JOIN employees AS e
		ON b.branch_id = e.branch_id	
WHERE c.city_id NOT IN (4, 5)
GROUP BY c.city_name, b.name
HAVING COUNT(*) >= 3
------------------------------------------------------------------------------------------------------------------------

SELECT SUM(l.amount) AS total_loan_amount, 
		MAX(l.interest) AS max_interest, 
		MIN(e.salary) AS min_employee_salary FROM loans AS l
	INNER JOIN employees_loans AS el
	ON l.loan_id = el.loan_id
		INNER JOIN employees AS e
		ON el.employee_id = e.employee_id
------------------------------------------------------------------------------------------------------------------------
-- UNION syntax
(SELECT e.first_name, c.city_name FROM employees AS e
	INNER JOIN branches AS br
	ON e.branch_id = br.branch_id
		INNER JOIN cities AS c
		ON br.city_id = c.city_id LIMIT 3)		
		UNION 
(SELECT cu.first_name, c.city_name FROM customers AS cu
	INNER JOIN cities AS c
	ON c.city_id = cu.city_id LIMIT 3)
------------------------------------------------------------------------------------------------------------------------

SELECT cu.customer_id, cu.height FROM customers AS cu
	LEFT OUTER JOIN accounts AS a
	ON cu.customer_id = a.customer_id
WHERE a.customer_id IS NULL
AND cu.height BETWEEN 1.74 AND 2.04
------------------------------------------------------------------------------------------------------------------------
-- SUBQUERY
SELECT cu.customer_id, l.amount FROM customers AS cu
	INNER JOIN loans AS l
	ON cu.customer_id = l.customer_id
WHERE l.amount > (SELECT AVG(l.amount) FROM loans AS l
							INNER JOIN customers AS cu
							ON l.customer_id = cu.customer_id
						WHERE cu.gender = 'M')
ORDER BY cu.last_name LIMIT 5
------------------------------------------------------------------------------------------------------------------------

SELECT cu.customer_id, cu.first_name, ac.start_date FROM customers AS cu
	INNER JOIN accounts AS ac
	ON cu.customer_id = ac.customer_id
WHERE ac.start_date = (SELECT MIN(a.start_date) FROM customers AS c
									INNER JOIN accounts AS a
									ON c.customer_id = a.customer_id)
------------------------------------------------------------------------------------------------------------------------


-- FORTH SECTION -- Programmability

-- 1_String Joiner
DELIMITER $$
CREATE FUNCTION udf_concat_string(first_str VARCHAR(50), second_str VARCHAR(50))
RETURNS VARCHAR(255)
BEGIN
	DECLARE first_rev VARCHAR(50);
	DECLARE sec_rev VARCHAR(50);
	SET first_rev = REVERSE(first_str);
	SET sec_rev = REVERSE(second_str);
	RETURN CONCAT(first_rev, sec_rev);
END $$
DELIMITER ;

SELECT udf_concat_string('Nick', 'kciN')									

-- 2_Unexpired Loans Procedure
DELIMITER $$
CREATE PROCEDURE usp_customers_with_unexpired_loans(customer_id INT)
BEGIN

	SELECT cu.customer_id, cu.first_name , l.loan_id FROM customers AS cu
		INNER JOIN loans AS l
		ON cu.customer_id = l.customer_id
	WHERE cu.customer_id = customer_id
	AND l.expiration_date IS NULL;

END $$

DELIMITER ;
CALL usp_customers_with_unexpired_loans(39)

-- 3_Take Loan Procedure
DELIMITER $$
CREATE PROCEDURE usp_take_loan(customer_id INT, loan_amount DECIMAL(18, 2), interest DECIMAL(4, 2), start_date DATE)
BEGIN

START TRANSACTION;
	
	INSERT INTO loans
	(start_date, amount, interest, expiration_date, customer_id)
	VALUES
	(start_date, loan_amount, interest, NULL, customer_id);
	
IF (loan_amount < 0.01 AND loan_amount > 100000) THEN
	ROLLBACK;
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid Loan Amount.';
ELSE 
	COMMIT;
END IF;

END $$
DELIMITER ;

CALL usp_take_loan(1, 0.02, 1,'20160915')

-- 4_Trigger Hire Employee
DELIMITER $$

CREATE TRIGGER tr_hire_employee
AFTER INSERT
ON employees
FOR EACH ROW
BEGIN

	UPDATE employees_loans AS el
		SET el.employee_id = new.employee_id
		WHERE el.employee_id = new.employee_id - 1;
	
END $$

DELIMITER ;

INSERT INTO employees
	VALUES 
	(31, 'Jake', '20161212', 500, 2, 1)

	
-- FIFTH SECTION BONUS

-- 1_Delete Trigger

-- creating table for deleted data
CREATE TABLE account_logs(
account_id INT NOT NULL,
account_number CHAR(12),
start_date DATE,
customer_id INT,
CONSTRAINT pk_account_logs PRIMARY KEY (account_id),
CONSTRAINT fk_account_logs_customers 
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- creating trigger
DELIMITER $$
CREATE TRIGGER tr_delete_account
BEFORE DELETE
ON accounts
FOR EACH ROW
BEGIN

	DELETE FROM employees_accounts -- first delete value in table wich references account_id -> foreign key in table employees_accounts
		WHERE account_id = old.account_id;
		
	INSERT INTO account_logs -- after that insert data into new table 
		VALUES
		(old.account_id, old.account_number, old.start_date, old.customer_id);
	-- only after these actions the record can be deleted
END $$
DELIMITER ;

DELETE FROM accounts -- first trigger done's his job
WHERE customer_id = 40