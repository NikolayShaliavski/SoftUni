03
ALTER TABLE towns
  ADD PRIMARY KEY(id)
  
04
ALTER TABLE minions
	ADD COLUMN town_id VARCHAR(50)

INSERT INTO towns VALUES (1, 'Sofia');
INSERT INTO towns VALUES (2, 'Plovdiv');
INSERT INTO towns VALUES (3, 'Varna');
INSERT INTO minions VALUES (1, 'Kevin', 22, 1);
INSERT INTO minions VALUES (2, 'Bob', 15, 2);
INSERT INTO minions VALUES (3, 'Steward', NULL, 3);
-----------------------------------------------------------------------------------------------------


CREATE TABLE minions
(
id INT NOT NULL PRIMARY KEY,
name VARCHAR(50),
age INT,
town_id INT,
FOREIGN KEY(town_id) REFERENCES towns(id)
)
-----------------------------------------------------------------------------------------------------

-- Problem_04_InsertIntoTables
INSERT INTO towns 
(id, name)
VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');
INSERT INTO minions
(id, name, age, town_id)
VALUES
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 2),
(3, 'Steward', NULL, 3)
-----------------------------------------------------------------------------------------------------


-- Problem_07_People
CREATE TABLE people(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(200) NOT NULL,
picture BLOB,
height DECIMAL(10, 2),
weight DECIMAL(10, 2),
gender ENUM('m', 'f') NOT NULL,
birthdate DATE NOT NULL,
biography LONGTEXT,
PRIMARY KEY(id)
);
INSERT INTO people
  (name, picture, height, weight, gender, birthdate, biography)
VALUES
  ('Nick', NULL, 188.12, 85.50, 'm', '1990/01/23', NULL),
  ('Mike', NULL, 168.12, 60.50, 'm', '1995/01/23', NULL),
  ('Alex', NULL, 155.20, 48.50, 'f', '1992/01/23', NULL),
  ('Petya', NULL, 160.12, 60.50, 'f', '1988/08/23', NULL),
  ('Ivan', NULL, 190.52, 86.00, 'm', '1987/01/23', NULL);
-----------------------------------------------------------------------------------------------------
 
  
  -- Problem_08_Users
CREATE TABLE users(
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(30) NOT NULL,
pasword VARCHAR(26) NOT NULL,
profile_picture BLOB,
last_login_time DATE,
is_deleted BIT,
PRIMARY KEY(id)
);
INSERT INTO users
  (username, pasword, profile_picture, last_login_time, is_deleted)
VALUES
  ('Nick659', 'asd', NULL, '2016/09/22', 1),
  ('Mike000', 'asd', NULL, '2016/09/22', 0),
  ('Alex_0987', 'asd', NULL, '2016/09/22', 0),
  ('Petya_*_', 'asd', NULL, '2016/09/22', 1),
  ('Ivan----', 'asd', NULL, '2016/09/22', 0);
-----------------------------------------------------------------------------------------------------
  
  
  -- Prolem_10_Change_PRIMARY_KEY
ALTER TABLE users
  MODIFY id INT NOT NULL;-- remove AUTO_INCREMENT
ALTER TABLE users
  DROP PRIMARY KEY;-- remove PRIMARY KEY
ALTER TABLE users
ADD PRIMARY KEY(id, username)
-----------------------------------------------------------------------------------------------------


-- Prolem_12_Default_Login_Time
ALTER TABLE users
	MODIFY last_login_time DATETIME DEFAULT CURRENT_TIMESTAMP 
INSERT INTO users
  (username, pasword, profile_picture, is_deleted)
VALUES
  ('Nick659', 'asd', NULL, 1)
-----------------------------------------------------------------------------------------------------
  
  
-- Problem_14_Movies_DB
CREATE DATABASE movies;

CREATE TABLE directors(
id INT NOT NULL,
director_name VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id)
);

CREATE TABLE genres(
id INT NOT NULL,
genre_name VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id)
);

CREATE TABLE categories(
id INT NOT NULL,
category_name VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id)
);

CREATE TABLE movies(
id INT NOT NULL,
title VARCHAR(50) NOT NULL,
director_id INT NOT NULL,
copyright_year VARCHAR(4) NOT NULL,
duration INT NOT NULL,
genre_id INT NOT NULL,
category_id INT NOT NULL,
rating INT NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes',
FOREIGN KEY(director_id) REFERENCES directors(id),
FOREIGN KEY(genre_id) REFERENCES genres(id),
FOREIGN KEY(category_id) REFERENCES categories(id),
PRIMARY KEY(id)
);

INSERT INTO directors
  (id, director_name)
  VALUES
  (1, 'John'),
  (2, 'John'),
  (3, 'John'),
  (4, 'John'),
  (5, 'John');
INSERT INTO genres
  (id, genre_name)
  VALUES
  (1, 'Crime'),
  (2, 'Comedy'),
  (3, 'Psyho'),
  (4, 'History'),
  (5, 'Thriller');
INSERT INTO categories
  (id, category_name)
  VALUES
  (1, 'Crime'),
  (2, 'Comedy'),
  (3, 'Psyho'),
  (4, 'History'),
  (5, 'Thriller');
INSERT INTO movies
  (id, title, director_id, copyright_year, duration, genre_id, category_id, rating)
  VALUES
  (1, 'Scary Movie', 2, '2010', 160, 2, 2, 10),
  (2, '300', 1, '2012', 160, 4, 4, 10),
  (3, 'Scary Movie', 2, '2010', 160, 2, 2, 10),
  (4, 'Scary Movie', 2, '2010', 160, 2, 2, 10),
  (5, 'Scary Movie', 2, '2010', 160, 2, 2, 10)
-----------------------------------------------------------------------------------------------------


-- Problem15_Car_Rental_Database

-- CREATE DATABASE car_rental

CREATE TABLE categories(
id INT NOT NULL AUTO_INCREMENT,
category INT NOT NULL,
daily_rate INT NOT NULL,
weekly_rate INT NOT NULL,
monthly_rate INT NOT NULL,
weekend_rate INT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE cars(
id INT NOT NULL AUTO_INCREMENT,
plate_number INT NOT NULL,
make VARCHAR(50) NOT NULL,
model VARCHAR(50) NOT NULL,
car_year INT NOT NULL,
category_id INT NOT NULL,
doors INT NOT NULL,
picture BLOB,
conditione VARCHAR(50) NOT NULL,
available BIT,
FOREIGN KEY(category_id) REFERENCES categories(id),
PRIMARY KEY(id)
);

CREATE TABLE employees(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
title VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id)
);

CREATE TABLE customers(
id INT NOT NULL AUTO_INCREMENT,
driver_licence_number INT NOT NULL,
full_name VARCHAR(50) NOT NULL,
adress VARCHAR(50) DEFAULT 'No adress',
city VARCHAR(50) DEFAULT 'No city',
zipcode VARCHAR(50) DEFAULT 'No zip code',
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id)
);

CREATE TABLE rental_orders(
id INT NOT NULL AUTO_INCREMENT,
employee_id INT NOT NULL,
customer_id INT NOT NULL,
car_id INT NOT NULL,
car_condition VARCHAR(50) NOT NULL,
tank_level INT DEFAULT 50,
kilometrage_start INT DEFAULT NULL,
kilometrage_end INT DEFAULT NULL,
total_kilometrage INT DEFAULT NULL,
start_date DATE,
end_date DATE,
total_days INT,
rate_applied VARCHAR(50) DEFAULT 'Weekly',
tax_rate INT DEFAULT 100,
order_status ENUM('free', 'rented'),
notes VARCHAR(200) DEFAULT 'No notes',
PRIMARY KEY(id),
FOREIGN KEY(employee_id) REFERENCES employees(id),
FOREIGN KEY(customer_id) REFERENCES customers(id),
FOREIGN KEY(car_id) REFERENCES cars(id)
);

INSERT INTO categories
  (category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
  VALUES
  (2, 10, 50, 150, 40),
  (2, 10, 50, 150, 40),
  (2, 10, 50, 150, 40);

INSERT INTO cars
  (plate_number, make, model, car_year, category_id, doors, picture, conditione, available)
  VALUES
  (2, 'France', 'Peugeot', 2001, 1, 4, NULL, 'OK', 1),
  (3, 'Germany', 'VW', 1999, 2, 4, NULL, 'OK', 0),
  (4, 'France', 'Renault', 2001, 1, 4, NULL, 'OK', 1);

INSERT INTO employees
  (first_name, last_name, title)
  VALUES
  ('Ivan', 'Ivanov', 'Director'),
  ('Nikolay', 'Stoichev', 'Office support'),
  ('Asen', 'Grigorov', 'Driver');

INSERT INTO customers
  (driver_licence_number, full_name)
  VALUES
  (1002365, 'Ivan Ivanov'),
  (1002548, 'Anatoli Stoichev'),
  (1003236, 'Kaloqn Grigorov');

INSERT INTO rental_orders
  (employee_id, customer_id, car_id, car_condition, start_date, end_date, total_days,order_status)
  VALUES
  (1, 2, 3, 'OK', '2016/02/22', '2016/02/25', 3, 'free'),
  (2, 1, 3, 'OK', '2016/02/22', '2016/02/25', 3, 'free'),
  (3, 1, 2, 'OK', '2016/02/22', '2016/02/25', 3, 'free')
-----------------------------------------------------------------------------------------------------


-- Problem15_Hotel_Database

-- CREATE DATABASE hotel

CREATE TABLE employees(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50),
last_name VARCHAR(50),
title VARCHAR(50) DEFAULT 'No title',
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(id)
);

CREATE TABLE customers(
account_number INT NOT NULL,
first_name VARCHAR(50),
last_name VARCHAR(50),
phone_number VARCHAR(50) DEFAULT 'No phone number.',
emergency_name VARCHAR(50) DEFAULT 'No emergency name.',
emergency_number INT DEFAULT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(account_number)
);

CREATE TABLE room_status(
room_status VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(room_status)
);

CREATE TABLE room_types(
room_type VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(room_type)
);

CREATE TABLE bed_types(
bed_type VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(bed_type)
);

CREATE TABLE rooms(
room_number INT NOT NULL,
room_type VARCHAR(50) NOT NULL,
bed_type VARCHAR(50) NOT NULL,
rate INT DEFAULT 1,
room_status VARCHAR(50) NOT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(room_number),
FOREIGN KEY(room_type) REFERENCES room_types(room_type),
FOREIGN KEY(bed_type) REFERENCES bed_types(bed_type),
FOREIGN KEY(room_status) REFERENCES room_status(room_status)
);

CREATE TABLE payments(
id INT NOT NULL AUTO_INCREMENT,
employee_id INT NOT NULL,
payment_date DATE DEFAULT NULL,
account_number INT NOT NULL,
first_date_occupied DATE DEFAULT NULL,
last_date_occupied DATE DEFAULT NULL,
total_days INT DEFAULT 0,
amount_charged INT DEFAULT 0,
tax_rate INT DEFAULT 10,
tax_amount INT DEFAULT 100,
payment_total INT DEFAULT NULL,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(id),
FOREIGN KEY(employee_id) REFERENCES employees(id),
FOREIGN KEY(account_number) REFERENCES customers(account_number)
);

CREATE TABLE occupancies(
id INT NOT NULL AUTO_INCREMENT,
employee_id INT NOT NULL,
date_occupied DATE DEFAULT NULL,
account_number INT NOT NULL,
room_number INT NOT NULL,
rate_applied INT DEFAULT NULL,
phone_charge INT DEFAULT 0,
notes VARCHAR(200) DEFAULT 'No notes.',
PRIMARY KEY(id),
FOREIGN KEY(employee_id) REFERENCES employees(id),
FOREIGN KEY(account_number) REFERENCES customers(account_number)
);

INSERT INTO employees
  (first_name, last_name)
  VALUES
  ('Ivan', 'Ivanov'), ('Todor', 'Ivanov'), ('Ignat', 'Ignatov');

INSERT INTO customers
  (account_number, first_name, last_name)
  VALUES
  (123, 'Georgi', 'Ivanov'), (456, 'Todor', 'Georgiev'), (789, 'Veselin', 'Stoqnov');

INSERT INTO room_status
  (room_status)
  VALUES
  ('free'), ('reserved'), ('renovation');

INSERT INTO room_types
  (room_type)
  VALUES
  ('studio'), ('apartment'), ('penthouse');

INSERT INTO bed_types
  (bed_type)
  VALUES
  ('single'), ('double'), ('bedchamber');

INSERT INTO rooms
  (room_number, room_type, bed_type, room_status)
  VALUES
  (101, 'studio', 'single', 'free'), 
  (202, 'apartment', 'double', 'free'), 
  (303, 'penthouse', 'bedchamber', 'reserved');

INSERT INTO payments
  (employee_id, account_number)
  VALUES
  (1, 123), 
  (2, 456), 
  (3, 789);

INSERT INTO occupancies
  (employee_id, account_number, room_number)
  VALUES
  (1, 123, 101), 
  (2, 456, 202), 
  (3, 789, 303)
----------------------------------------------------------------------------------------------------- 
  
  
-- Problem17_Create_SoftUni_Database

-- CREATE DATABASE softuni

CREATE TABLE towns(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE adresses(
id INT NOT NULL AUTO_INCREMENT,
adress_text VARCHAR(100) NOT NULL,
town_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(town_id) REFERENCES towns(id)
); 

CREATE TABLE departments(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE employees(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50),
middle_name VARCHAR(50),
last_name VARCHAR(50),
job_title VARCHAR(50),
department_id INT NOT NULL,
hire_date DATE,
salary INT,
adress_id INT DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(department_id) REFERENCES departments(id),
FOREIGN KEY(adress_id) REFERENCES adresses(id)
);
-----------------------------------------------------------------------------------------------------


-- Problem19_Basic_Insert_In_Softuni_Database

INSERT INTO towns (name)
  VALUES
  ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');
  
INSERT INTO departments (name)
  VALUES 
  ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');
  
INSERT INTO employees
  (first_name, middle_name, last_name, job_title, hire_date, salary, department_id)
  SELECT 'Ivan', 'Ivanov', 'Ivanov', '.Net Developer', '2013/02/01', 3500.00, id -- department_id is a foreign key, referenced to id 
  FROM departments WHERE name = 'Software Development'; -- here we took this value from deparmnets table and insert it istead of hardcoding
  
INSERT INTO employees
  (first_name, middle_name, last_name, job_title, hire_date, salary, department_id)
  SELECT 'Petar', 'Petrov', 'Petrov', 'Senior Engineer', '2004/03/02', 4000.00, id
  FROM departments WHERE name = 'Engineering';
  
INSERT INTO employees
  (first_name, middle_name, last_name, job_title, hire_date, salary, department_id)
  SELECT 'Maria', 'Petrova', 'Ivanova', 'Intern', '2016/08/28', 525.25, id
  FROM departments WHERE name = 'Quality Assurance';
  
INSERT INTO employees
  (first_name, middle_name, last_name, job_title, hire_date, salary, department_id)
  SELECT 'Georgi', 'Terziev', 'Ivanov', 'CEO', '2007/12/09', 3000.00, id
  FROM departments WHERE name = 'Sales';
  
INSERT INTO employees
  (first_name, middle_name, last_name, job_title, hire_date, salary, department_id)
  SELECT 'Peter', 'Pan', 'Pan', 'Intern', '2016/08/28', 599.88, id
  FROM departments WHERE name = 'Marketing';
-----------------------------------------------------------------------------------------------------
  
  
  
-- Problem20_Basic_Select_All_Fields

SELECT * FROM towns;

SELECT * FROM departments;

SELECT * FROM employees
-----------------------------------------------------------------------------------------------------



-- Problem21_Basic_Select_All_Fields_And_Order_Them

SELECT * FROM towns
  ORDER BY name;
  
SELECT * FROM departments 
  ORDER BY name;
  
SELECT* FROM employees
  ORDER BY salary DESC
-----------------------------------------------------------------------------------------------------


-- Problem22_Basic_Select_Some_Fields_And_Order_Them

SELECT name FROM towns
  ORDER BY name;
  
SELECT name FROM departments 
  ORDER BY name;
  
SELECT first_name, middle_name, last_name, job_title, salary FROM employees
  ORDER BY salary DESC
-----------------------------------------------------------------------------------------------------


-- Problem23_Increase_Employees_Salary

UPDATE employees
  SET salary = salary + (salary * 0.10);
  SELECT salary FROM employees
-----------------------------------------------------------------------------------------------------