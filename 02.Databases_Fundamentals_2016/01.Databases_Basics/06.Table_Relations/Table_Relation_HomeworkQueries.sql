-- Problem 2_One-To-One Relationship

CREATE TABLE passports(
passport_id INT NOT NULL,
passport_number VARCHAR(20),
PRIMARY KEY(passport_id)
);

CREATE TABLE persons(
person_id INT NOT NULL,
first_name VARCHAR(50),
salary DECIMAL,
passport_id INT NOT NULL,
PRIMARY KEY(person_id),
FOREIGN KEY(passport_id) REFERENCES passports(passport_id)
);

INSERT INTO passports
  (passport_id, passport_number)
VALUES
  (101, 'N34FG21B'),
  (102, 'K65LO4R7'),
  (103, 'ZE657QP2');
  
INSERT INTO persons
  (person_id, first_name, salary, passport_id)
VALUES
  (1, 'Roberto', 43330.00, 102),
  (2, 'Tom', 56100.00, 103),
  (3, 'Yana', 60200.00, 101);
--------------------------------------------------------------------------------------------------------


-- Problem 3_One-To-Many Relationship

CREATE TABLE manufacturers(
manufacturer_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
established_on DATE NOT NULL,
PRIMARY KEY(manufacturer_id)
);	

CREATE TABLE models(
model_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
manufacturer_id INT,
PRIMARY KEY(model_id),
FOREIGN KEY(manufacturer_id) REFERENCES manufacturers(manufacturer_id)
);
-- work in judge without inserts
INSERT INTO manifacturers
  (manifacturer_id, name, established_on)
VALUES
  (1, 'BMW', '1916-07-03'),
  (2, 'Tesla', '2003-01-01'),
  (3, 'Lada', '1966-01-05');
  
INSERT INTO models
  (model_id, name, manifacturer_id)
VALUES
  (101, 'X1', 1),
  (102, 'i6', 1),
  (103, 'Model S', 2),
  (104, 'Model X', 2),
  (105, 'Model 3', 2),
  (106, 'Nova', 3);
--------------------------------------------------------------------------------------------------------


-- Problem 4_Many-To-Many Relationship

CREATE TABLE students(
student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL);

CREATE TABLE exams(
exam_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL);

CREATE TABLE students_exams(
student_id INT NOT NULL,
exam_id INT NOT NULL,
PRIMARY KEY(student_id, exam_id),
FOREIGN KEY (student_id) REFERENCES students(student_id),
FOREIGN KEY (exam_id) REFERENCES exams(exam_id));

INSERT INTO students
(student_id, name)
VALUES
(1, 'Mila'),
(2, 'Toni'),
(3, 'Ron');

INSERT INTO exams
(exam_id, name)
VALUES
(101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

INSERT INTO students_exams
(student_id, exam_id)
VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103)
--------------------------------------------------------------------------------------------------------


-- Problem 5_Self-Referencing

CREATE TABLE teachers(
teacher_id INT NOT NULL,
name VARCHAR(50) NOT NULL,
manager_id INT,
PRIMARY KEY(teacher_id),
FOREIGN KEY(manager_id) REFERENCES teachers(teacher_id)
);
--------------------------------------------------------------------------------------------------------


-- Problem 6_Online_store_database - 33 points in judge

CREATE TABLE item_types(
item_type_id INT NOT NULL,
name VARCHAR(50) NOT NULL,
PRIMARY KEY(item_type_id)
);

CREATE TABLE items(
item_id INT NOT NULL,
name VARCHAR(50),
item_type_id INT NOT NULL,
PRIMARY KEY(item_id),
FOREIGN KEY(item_type_id) REFERENCES item_types(item_type_id)
);

CREATE TABLE order_items(
order_id INT NOT NULL,
item_id INT NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(item_id) REFERENCES items(item_id)
);

CREATE TABLE cities(
city_id INT NOT NULL,
name VARCHAR(50),
PRIMARY KEY(city_id)
);

CREATE TABLE customers(
customer_id INT NOT NULL,
name VARCHAR(50),
birthdate DATE,
city_id INT NOT NULL,
PRIMARY KEY(customer_id),
FOREIGN KEY(city_id) REFERENCES cities(city_id)
);

CREATE TABLE orders(
order_id INT NOT NULL,
customer_id INT NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
FOREIGN KEY(order_id) REFERENCES order_items(order_id)
);
--------------------------------------------------------------------------------------------------------


-- Problem 7_University Database

CREATE TABLE subjects(
subject_id INT NOT NULL AUTO_INCREMENT, 
subject_name VARCHAR(50) NOT NULL,
PRIMARY KEY(subject_id)
);

CREATE TABLE agenda(
student_id INT NOT NULL AUTO_INCREMENT,
subject_id INT NOT NULL,
PRIMARY KEY(student_id, subject_id),
FOREIGN KEY(subject_id) REFERENCES subjects(subject_id)
);	

CREATE TABLE majors(
major_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY(major_id)
);	

CREATE TABLE students(
student_id INT NOT NULL AUTO_INCREMENT,
student_number VARCHAR(12) NOT NULL,
student_name VARCHAR(50) NOT NULL,
major_id INT NOT NULL,
PRIMARY KEY(student_id),
FOREIGN KEY(major_id) REFERENCES majors(major_id),
FOREIGN KEY(student_id) REFERENCES agenda(student_id)
);	

CREATE TABLE payments(
payment_id INT NOT NULL AUTO_INCREMENT,
payment_date DATE NOT NULL,
payment_amount DECIMAL(8,2) NOT NULL,
student_id INT NOT NULL,
PRIMARY KEY(payment_id),
FOREIGN KEY(student_id) REFERENCES students(student_id)
);
--------------------------------------------------------------------------------------------------------



-- Problem 10_Employee Address

SELECT e.employee_id, e.job_title, e.address_id, a.address_text
  FROM employees AS e
  INNER JOIN (SELECT a.address_id, a.address_text FROM addresses AS a) AS a
  ON e.address_id = a.address_id LIMIT 5
--------------------------------------------------------------------------------------------------------



-- Problem 11_Employee Departments

SELECT e.employee_id, e.first_name, e.salary, d.name AS department_name
  FROM employees AS e
  JOIN (SELECT d.department_id, d.name FROM departments AS d) AS d
  ON e.department_id = d.department_id
  AND e.salary > 15000
  ORDER BY e.department_id LIMIT 5
--------------------------------------------------------------------------------------------------------



-- Problem 12_Employees Without Project

SELECT e.employee_id, e.first_name
  FROM employees AS e
  LEFT OUTER JOIN (SELECT ep.employee_id FROM employees_projects AS ep) AS ep
  ON e.employee_id = ep.employee_id
  WHERE ep.employee_id IS NULL
  ORDER BY e.employee_id LIMIT 3
--------------------------------------------------------------------------------------------------------



-- Problem 13_Employees with Project

SELECT e.employee_id, e.first_name, p.name
  FROM employees AS e
  INNER JOIN (SELECT ep.employee_id, ep.project_id FROM employees_projects AS ep) AS ep
  ON e.employee_id = ep.employee_id
  INNER JOIN (SELECT p.name, p.project_id, p.start_date, p.end_date FROM projects AS p) AS p
  ON p.project_id = ep.project_id
  AND p.start_date > '2002-08-13'
  AND p.end_date IS NULL
  ORDER BY e.employee_id LIMIT 5
  
  SELECT e.employee_id
		,e.first_name
		,p.name AS project_name
FROM employees AS e
	LEFT OUTER JOIN employees_projects AS ep ON e.employee_id = ep.employee_id
	LEFT OUTER JOIN projects AS p ON ep.project_id = p.project_id AND YEAR(p.start_date) < 2005
WHERE e.employee_id = 24
--------------------------------------------------------------------------------------------------------



-- Problem 14_Employee 24 - doesn't work in judge

SELECT e.employee_id, e.first_name, IF(p.start_date > '2005-01-01', NULL, p.name) AS project_name
  FROM employees AS e
  INNER JOIN (SELECT ep.employee_id, ep.project_id FROM employees_projects AS ep) AS ep
  ON e.employee_id = ep.employee_id
  AND e.employee_id = 24
  INNER JOIN (SELECT p.name, p.project_id, p.start_date, p.end_date FROM projects AS p) AS p
  ON p.project_id = ep.project_id
--------------------------------------------------------------------------------------------------------



-- Problem 15_Employee Manager

SELECT e.employee_id, e.first_name, e.manager_id, me.first_name
  FROM employees AS e
  INNER JOIN (SELECT me.employee_id, me.first_name FROM employees AS me) AS me
  ON e.manager_id = me.employee_id
  AND e.manager_id IN (3, 7)
  ORDER BY e.employee_id
--------------------------------------------------------------------------------------------------------



-- Problem 16_Highest Peak in Bulgaria

SELECT mc.country_code, m.mountain_range, p.peak_name, p.elevation FROM mountains_countries AS mc
	 INNER JOIN(SELECT m.mountain_range, m.id FROM mountains AS m) AS m
	ON mc.mountain_id = m.id
	AND mc.country_code = 'BG'
	   INNER JOIN(SELECT p.peak_name, p.elevation, p.mountain_id FROM peaks AS p) AS p
     ON m.id = p.mountain_id
	 AND p.elevation > 2835
  ORDER BY p.elevation DESC
--------------------------------------------------------------------------------------------------------



-- Problem 17_Count Mountain Ranges

SELECT mc.country_code AS country_code, COUNT(*) AS mountain_ranges FROM mountains_countries AS mc
  WHERE mc.country_code IN('BG', 'US', 'RU')
  GROUP BY mc.country_code
 --------------------------------------------------------------------------------------------------------
 
 
 
 -- Problem 18_Countries with Rivers
 
 SELECT c.country_name, r.river_name FROM countries AS c
  LEFT OUTER JOIN(SELECT cr.country_code, cr.river_id FROM countries_rivers AS cr) AS cr
  ON c.country_code = cr.country_code
    LEFT OUTER JOIN(SELECT r.river_name, r.id FROM rivers AS r) AS r
    ON cr.river_id = r.id
  WHERE c.continent_code = 'AF'
  ORDER BY c.country_name LIMIT 5
--------------------------------------------------------------------------------------------------------



-- Problem 19_*Continents and Currencies

-- my solution
SELECT c.continent_code, c.currency_code, c.count FROM	
	(SELECT c.continent_code, c.currency_code, COUNT(*) AS count FROM countries AS c	
		GROUP BY c.continent_code, c.currency_code
		HAVING COUNT(*) > 1
		ORDER BY c.continent_code, COUNT(*) DESC) AS c
INNER JOIN(SELECT co.continent_code, co.currency_code, MAX(co.currency_count) AS currency_usage FROM countries AS c
				INNER JOIN(SELECT co.continent_code, co.currency_code, COUNT(*) AS currency_count FROM countries AS co
								GROUP BY co.continent_code, co.currency_code) AS co
				ON c.continent_code = co.continent_code
			   AND c.currency_code = co.currency_code
			   AND co.currency_count > 1
				GROUP BY c.continent_code) AS max_count
ON c.continent_code = max_count.continent_code
AND c.count = max_count.currency_usage
	
	
-- Kristiqn solution
SELECT co.continent_code
		,co.currency_code
		,(SELECT COUNT(*) FROM countries AS c
		  WHERE c.continent_code = co.continent_code
		  GROUP BY c.continent_code, c.currency_code
	     ORDER BY COUNT(*) DESC LIMIT 1) AS count
FROM countries AS co
WHERE co.currency_code IN (SELECT c1.currency_code FROM countries AS c1
								   WHERE c1.continent_code = co.continent_code
								   GROUP BY c1.continent_code, c1.currency_code
								   HAVING  COUNT(*) > 1 AND COUNT(*) = (SELECT COUNT(*) FROM countries AS c2
																					 WHERE c2.continent_code = c1.continent_code
																					 GROUP BY c2.continent_code, c2.currency_code
																				    ORDER BY COUNT(*) DESC LIMIT 1)
							      ORDER BY COUNT(*) DESC)
GROUP BY co.continent_code, co.currency_code
ORDER BY co.continent_code, co.currency_code DESC
--------------------------------------------------------------------------------------------------------



-- Problem 20_Countries Without any Mountains

SELECT COUNT(*) AS country_code FROM countries AS c
  LEFT OUTER JOIN(SELECT mc.country_code FROM mountains_countries AS mc) AS mc
  ON c.country_code = mc.country_code
  WHERE mc.country_code IS NULL
--------------------------------------------------------------------------------------------------------



-- Problem 21_Highest Peak and Longest River by Country

SELECT c.country_name, p.highest_peak_elevation, r.longest_river_length FROM countries AS c
  LEFT OUTER JOIN(SELECT mc.country_code, mc.mountain_id FROM mountains_countries AS mc) AS mc
  ON c.country_code = mc.country_code
     LEFT OUTER JOIN(SELECT MAX(p.elevation) AS highest_peak_elevation, p.mountain_id FROM peaks AS p
	 GROUP BY mountain_id) AS p
    ON mc.mountain_id = p.mountain_id
       LEFT OUTER JOIN(SELECT cr.country_code, cr.river_id FROM countries_rivers AS cr) AS cr
       ON c.country_code = cr.country_code 
		   LEFT OUTER JOIN(SELECT MAX(r.length) AS longest_river_length, r.id FROM rivers AS r
	     GROUP BY id) AS r
        ON cr.river_id = r.id 
   GROUP BY c.country_name
   ORDER BY p.highest_peak_elevation DESC, r.longest_river_length DESC, c.country_name LIMIT 5
--------------------------------------------------------------------------------------------------------



-- Problem 22._**Highest Peak Name and Elevation by Country

SELECT c.country_name AS 'country', 
		 IF(mc.mountain_id IS NULL, '(no highest peak)', mc.peak_name) AS 'highest_peak_name',
       IF(mc.mountain_id IS NULL, 0, mc.max_elevation) AS 'highest_peak_elevation',
		 IF(mc.mountain_id IS NULL, '(no mountain)', moun.mountain_range) AS 'mountain' 
		 FROM countries AS c
	LEFT OUTER JOIN(SELECT mc.country_code, mc.mountain_id, 
	 							  p.elevation AS max_elevation, p.peak_name FROM mountains_countries AS mc
		INNER JOIN(SELECT p.elevation, p.peak_name, p.mountain_id FROM peaks AS p
		          	INNER JOIN(SELECT MAX(pe.elevation) AS max_el, pe.mountain_id FROM peaks AS pe
						           GROUP BY pe.mountain_id) AS pe
		          	ON p.elevation = pe.max_el) AS p
		 ON mc.mountain_id = p.mountain_id
		 AND p.elevation IN(SELECT MAX(p.elevation) AS max_elevation FROM mountains_countries AS mc
									INNER JOIN(SELECT p.elevation, p.peak_name, p.mountain_id FROM peaks AS p
		          					INNER JOIN(SELECT MAX(pe.elevation) AS max_el, pe.mountain_id FROM peaks AS pe
						            GROUP BY pe.mountain_id) AS pe
		          					ON p.elevation = pe.max_el) AS p
		 							ON mc.mountain_id = p.mountain_id
		 							GROUP BY mc.country_code)
		 GROUP BY mc.country_code) AS mc
	    ON c.country_code = mc.country_code
		LEFT OUTER JOIN(SELECT moun.id, moun.mountain_range FROM mountains AS moun) AS moun
		ON mc.mountain_id = moun.id
GROUP BY c.country_name
ORDER BY c.country_name, mc.peak_name LIMIT 5
--------------------------------------------------------------------------------------------------------