-- Problem08_Find_Email_Adresses

SELECT CONCAT (first_name, '.', last_name, '@softuni.bg') AS full_email_address
 FROM employees
--------------------------------------------------------------------------------------


-- Problem09_Find_Different_Employee’s_Salaries

SELECT DISTINCT salary FROM employees
--------------------------------------------------------------------------------------


-- Problem10_Find_All_Info_About_Employees

SELECT * FROM employees
  WHERE job_title = 'Sales Representative'
--------------------------------------------------------------------------------------


-- Problem11_Find Names of All Employees by salary in Range

SELECT first_name, last_name, job_title FROM employees
  WHERE salary BETWEEN 20000 AND 30000
--------------------------------------------------------------------------------------


-- Problem 12_Find Names of All Employees

SELECT CONCAT (first_name, ' ', middle_name, ' ', last_name) AS full_name
FROM employees
  WHERE 
    (salary = 25000 OR
    salary = 14000 OR
    salary = 12500 OR 
    salary = 23600)
--------------------------------------------------------------------------------------


-- Problem 13_Find All Employees Without Manager

SELECT first_name, last_name FROM employees
  WHERE manager_id IS NULL
--------------------------------------------------------------------------------------


-- Problem 14_Find All Employees with salary More Than 50000

SELECT first_name, last_name, salary FROM employees
  WHERE salary > 50000
  ORDER BY salary DESC
--------------------------------------------------------------------------------------


-- Problem 15_Find 5 Best Paid Employees

SELECT first_name, last_name FROM employees
  ORDER BY salary DESC LIMIT 5
--------------------------------------------------------------------------------------


-- Problem 16_Find All Employees Except Marketing

SELECT first_name, last_name FROM employees
  WHERE department_id <> 4
--------------------------------------------------------------------------------------


-- Problem 17_Sort Employees Table

SELECT * FROM employees
  ORDER BY salary DESC, first_name, last_name DESC, middle_name DESC
--------------------------------------------------------------------------------------


-- Problem 18_Create View Employees with Salaries

CREATE VIEW v_employees_salaries (first_name, last_name, salary)
  AS SELECT first_name, last_name, salary FROM employees
--------------------------------------------------------------------------------------


-- Problem 19_Create View Employees with Job Titles

CREATE VIEW v_employees_job_titles (full_name, job_title)
  AS SELECT CONCAT (first_name, ' ', IFNULL (middle_name, ''), ' ', last_name), job_title FROM employees
--------------------------------------------------------------------------------------


-- Problem 20_Distinct Job Titles

SELECT DISTINCT job_title FROM employee
--------------------------------------------------------------------------------------


-- Problem 21_Find First 10 Started Projects

SELECT * FROM projects
  ORDER BY start_date, name LIMIT 10
--------------------------------------------------------------------------------------


-- Problem 22_Last 7 Hired Employees

SELECT first_name, last_name, hire_date FROM employees
  ORDER BY hire_date DESC LIMIT 7
--------------------------------------------------------------------------------------


-- Problem 23_Increase Salaries

UPDATE employees 
  SET salary = salary + (salary * 0.12)
  WHERE (department_id = 1 OR
        department_id = 2 OR
		  department_id = 4 OR 
		  department_id = 11);
SELECT salary FROM employees
--------------------------------------------------------------------------------------


-- Problem 25_All Mountain Peaks

SELECT peak_name FROM peaks
  ORDER BY peak_name
--------------------------------------------------------------------------------------


-- Problem 26_Biggest Countries by Population

SELECT country_name, population FROM countries
  WHERE continent_code = 'EU'
  ORDER BY population DESC, country_name LIMIT 30
--------------------------------------------------------------------------------------


-- Problem 27_Countries and Currency (Euro / Not Euro)

SELECT country_name, country_code, IF(currency_code = 'EUR', 'Euro', 'Not Euro') FROM countries
  ORDER BY country_name
--------------------------------------------------------------------------------------


-- Problem 29_All Diablo Characters

SELECT name FROM characters 
  ORDER BY name
--------------------------------------------------------------------------------------