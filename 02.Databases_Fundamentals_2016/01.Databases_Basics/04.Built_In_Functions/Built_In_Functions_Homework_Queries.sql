-- Problem 1_Find Names of All Employees by First Name

SELECT first_name, last_name FROM employees
  WHERE first_name LIKE 'SA%'
-----------------------------------------------------------------------------------------------------


-- Problem 2_Find Names of All employees by Last Name 

SELECT first_name, last_name FROM employees
  WHERE last_name LIKE '%ei%'
-----------------------------------------------------------------------------------------------------


-- Problem 3_Find First Names of All Employees By Hire DATE

SELECT first_name FROM employees
  WHERE (department_id = 3 OR 
        department_id = 10)
  AND DATE_FORMAT(hire_date, '%Y') BETWEEN '1995' AND '2005'
  
  SELECT first_name FROM employees
  WHERE (department_id = 3 OR 
        department_id = 10)
  AND YEAR(hire_date) BETWEEN '1995' AND '2005'-- this produces the same result
-----------------------------------------------------------------------------------------------------


-- Problem 4_Find All Employees Except Engineers

SELECT first_name, last_name FROM employees
  WHERE job_title NOT LIKE '%engineer%'
-----------------------------------------------------------------------------------------------------


-- Problem 5_Find Towns with Name Length

SELECT name FROM towns
  WHERE CHAR_LENGTH(name) = 5 OR CHAR_LENGTH(name) = 6
  ORDER BY name
-----------------------------------------------------------------------------------------------------


-- Problem 6_Find Towns Starting With

SELECT * FROM towns
  WHERE (name LIKE 'M%' OR
         name LIKE 'K%' OR
         name LIKE 'B%' OR
         name LIKE 'E%')
  ORDER BY name
-----------------------------------------------------------------------------------------------------


-- Problem 7_Find Towns Not Starting With

SELECT * FROM towns
  WHERE (name NOT LIKE 'R%' AND
         name NOT LIKE 'B%' AND
         name NOT LIKE 'D%')
  ORDER BY name
-----------------------------------------------------------------------------------------------------


-- Problem 8_Create View Employees Hired After 2000 Year

CREATE VIEW v_employees_hired_after_2000 (first_name, last_name)
  AS SELECT first_name, last_name FROM employees
  WHERE YEAR(hire_date) > '2000'
  
-- SELECT * FROM v_employees_hired_after_2000 -> selecting all columns from cretaed view
-----------------------------------------------------------------------------------------------------


-- Problem 9_Length of Last Name

SELECT first_name, last_name FROM employees
  WHERE CHAR_LENGTH(last_name) = 5
-----------------------------------------------------------------------------------------------------


-- Problem 10_Countries Holding ‘A’ 3 or More Times

SELECT country_name, iso_code FROM countries
  WHERE country_name LIKE '%a%a%a%'
  ORDER BY iso_code
-----------------------------------------------------------------------------------------------------


-- Problem 11_Mix of Peak and River Names

SELECT peaks.peak_name, rivers.river_name, CONCAT(LOWER(peaks.peak_name), SUBSTRING(LOWER(rivers.river_name), 2)) 
AS mix FROM peaks, rivers 
  WHERE SUBSTR(peaks.peak_name, CHAR_LENGTH(peaks.peak_name)) = SUBSTR(rivers.river_name, 1, 1)
  ORDER BY mix
-----------------------------------------------------------------------------------------------------


-- Problem 12_Games from 2011 and 2012 year

SELECT name, DATE_FORMAT(start,'%Y-%m-%d') FROM games
  WHERE (YEAR(start) = '2011' OR
         YEAR(start) = '2012')
  ORDER BY start, name LIMIT 50
-----------------------------------------------------------------------------------------------------


-- Problem 13_User Email Providers

SELECT user_name, SUBSTR(email,POSITION('@' IN email) + 1) FROM users
  ORDER BY SUBSTR(email,POSITION('@' IN email) + 1), user_name
-----------------------------------------------------------------------------------------------------


-- Problem 14_Get Users with IPAdress Like Pattern

SELECT user_name, ip_address FROM users
  WHERE ip_address LIKE '___.1%.%.___'
  ORDER BY user_name
-----------------------------------------------------------------------------------------------------


-- Problem 15_Show All Games with Duration and Part of the Day

SELECT name,
  CASE WHEN HOUR(start) >= 0 AND HOUR(start) < 12 THEN 'Morning'
  WHEN HOUR(start) >=  12 AND HOUR(start) < 18 THEN 'Afternoon'
  ELSE 'Evening' END AS part_of_the_day,
  CASE WHEN duration <= 3 THEN 'Extra Short'
  WHEN duration BETWEEN 4 AND 6 THEN 'Short'
  WHEN duration > 6 THEN 'Long'
  ELSE 'Extra Long' END AS duration
  FROM games
  ORDER BY name, start, duration
 ----------------------------------------------------------------------------------------------------- 


 -- Problem 16_Orders Table
 
 CREATE TABLE orders(
id INT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(50),
order_date DATETIME,
PRIMARY KEY(id)
);
INSERT INTO orders
  (product_name, order_date)
  VALUES
  ('Butter', '2016-09-19 00:00:00'),
  ('Milk', '2016-09-30 00:00:00'),
  ('Chese', '2016-09-04 00:00:00'),
  ('Bread', '015-12-20 00:00:00'),
  ('Tomatoes', '2015-12-30 00:00:00');
  
SELECT product_name, order_date,-- this is solution for judge
  DATE_ADD(order_date, INTERVAL 3 DAY) AS pay_due,
  DATE_ADD(order_date, INTERVAL 1 MONTH) AS deliver_due
  FROM orders
-----------------------------------------------------------------------------------------------------