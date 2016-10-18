-- Problem 2_Records’ Count

SELECT COUNT(id) FROM wizzard_deposits
------------------------------------------------------------------------------------------------


-- Problem 3_Longest Magic Wand

SELECT MAX(magic_wand_size) FROM wizzard_deposits 
  AS longest_magic_wand
------------------------------------------------------------------------------------------------


-- Problem 4_Longest Magic Wand per Deposit Groups

SELECT deposit_group AS deposit_group, 
       MAX(magic_wand_size) AS longest_magic_wand 
		 FROM wizzard_deposits
  GROUP BY deposit_group
------------------------------------------------------------------------------------------------


-- Problem 5_*Smallest Deposit Group per Magic Wand Size

SELECT deposit_group FROM wizzard_deposits
  GROUP BY deposit_group 
  ORDER BY AVG(magic_wand_size) LIMIT 1
------------------------------------------------------------------------------------------------


-- Problem 6_Deposits Sum

SELECT deposit_group,
  SUM(deposit_amount) AS total_sum FROM wizzard_deposits
  GROUP BY deposit_group 
------------------------------------------------------------------------------------------------


-- Problem 7_Deposits Sum for Ollivander family

SELECT deposit_group,
  SUM(deposit_amount) AS total_sum FROM wizzard_deposits
  WHERE magic_wand_creator = 'Ollivander family'
  GROUP BY deposit_group 
------------------------------------------------------------------------------------------------


-- Problem 8_Deposits Filter

SELECT deposit_group,
  SUM(deposit_amount) AS total_sum FROM wizzard_deposits
  WHERE magic_wand_creator = 'Ollivander family'
  GROUP BY deposit_group HAVING total_sum < 150000
  ORDER BY total_sum DESC
------------------------------------------------------------------------------------------------


-- Problem 9_Deposit charge

SELECT deposit_group, magic_wand_creator, MIN(deposit_charge) FROM wizzard_deposits
  GROUP BY deposit_group, magic_wand_creator
  ORDER BY magic_wand_creator, deposit_group
------------------------------------------------------------------------------------------------


-- Problem 10_Age Groups

SELECT CASE
       WHEN age BETWEEN 0 AND 10 THEN '[0-10]'
  		 WHEN age BETWEEN 11 AND 20 THEN '[11-20]'
  		 WHEN age BETWEEN 21 AND 30 THEN '[21-30]'
  		 WHEN age BETWEEN 31 AND 40 THEN '[31-40]'
  		 WHEN age BETWEEN 41 AND 50 THEN '[41-50]'
  		 WHEN age BETWEEN 51 AND 60 THEN '[51-60]'
  		 ELSE '[61+]'
       END AS age_group,
		 COUNT(*) AS wizzard_count FROM wizzard_deposits
		 GROUP BY age_group
-----------------------------------------------------------------------------------------------------


-- Problem 11_First Letter

SELECT SUBSTR(first_name, 1, 1) AS first_letter FROM wizzard_deposits
  WHERE deposit_group = 'Troll Chest'
  GROUP BY first_letter
  ORDER BY first_letter
-----------------------------------------------------------------------------------------------------


-- Problem 12_Average Interest

SELECT deposit_group, is_deposit_expired, AVG(deposit_interest) AS deposit_interest FROM wizzard_deposits
  WHERE deposit_start_date > '1985-01-01'
  GROUP BY deposit_group, is_deposit_expired
  ORDER BY deposit_group DESC, is_deposit_expired
-----------------------------------------------------------------------------------------------------


-- Problem 13_Rich Wizard, Poor Wizard

SELECT SUM(w1.deposit_amount - w2.deposit_amount) AS sum_difference FROM wizzard_deposits AS w1
  JOIN (SELECT id, deposit_amount FROM wizzard_deposits) AS w2
  ON (w1.id +1 = w2.id)
-----------------------------------------------------------------------------------------------------



-- Problem 14_Employees Minimum Salaries

SELECT department_id, MIN(salary) AS minimum_salary FROM employees
  WHERE (department_id IN (2, 5, 7) AND
		 hire_date > '2000-01-01') 
  GROUP BY department_id
-----------------------------------------------------------------------------------------------------


-- Problem 15_Employees Average Salaries - again doesn't work in judge

CREATE TABLE employees_test(
employee_id INT NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
middle_name VARCHAR(50),
job_title VARCHAR(50) NOT NULL,
department_id INT NOT NULL,
manager_id INT,
hire_date TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
salary DECIMAL NOT NULL,
address_id INT,
PRIMARY KEY(employee_id)
-- FOREIGN KEY(department_id) REFERENCES departments(department_id),
-- FOREIGN KEY(manager_id) REFERENCES employees(employee_id),
-- FOREIGN KEY(address_id) REFERENCES addresses(address_id)
); 

INSERT INTO employees_test
  (employee_id, first_name, last_name, middle_name, job_title, department_id, manager_id, hire_date, salary, address_id)
  SELECT * FROM employees
  WHERE salary > 30000;
  
DELETE FROM employees_test
  WHERE manager_id = 42;
  
UPDATE employees_test
  SET salary = salary + 5000
  WHERE department_id = 1;
  
SELECT department_id, AVG(salary) AS manager_id FROM employees_test
  GROUP BY department_id
  
-----------------------------------------------this work in judge 
CREATE TABLE employees_test
  SELECT * FROM employees
  WHERE salary > 30000;
  
DELETE FROM employees_test
  WHERE manager_id = 42;
  
UPDATE employees_test
  SET salary = salary + 5000
  WHERE department_id = 1;
  
SELECT department_id, AVG(salary) AS manager_id FROM employees_test
  GROUP BY department_id
------------------------------------------------------------------------------------------------


-- Problem 16_Employees Maximum Salaries

SELECT department_id, MAX(salary) AS max_salary FROM employees
  GROUP BY department_id
  HAVING max_salary < 30000 OR max_salary > 70000
------------------------------------------------------------------------------------------------


-- Problem 17_Employees Count Salaries

SELECT COUNT(*) AS count FROM employees
  WHERE manager_id IS NULL
------------------------------------------------------------------------------------------------


-- Problem 18_*3rd Highest Salary

SELECT e.department_id, MAX(e.salary) AS third_max_salary FROM employees AS e
	JOIN (SELECT e.department_id, MAX(e.salary) AS second_max_salary FROM employees AS e
		JOIN (SELECT e.department_id, MAX(e.salary) AS first_max_salary FROM employees AS e
			  	GROUP BY department_id) AS first_max_salary
		 ON e.department_id = first_max_salary.department_id
		 AND e.salary < first_max_salary.first_max_salary
		   GROUP BY department_id) AS second_max_salary
   ON e.department_id = second_max_salary.department_id
   AND e.salary < second_max_salary.second_max_salary
GROUP BY department_id		
------------------------------------------------------------------------------------------------


-- Problem 19_**Salary Challenge

SELECT e.first_name, e.last_name, e.department_id FROM employees AS e
	JOIN (SELECT e.department_id, AVG(e.salary) AS average_salary FROM employees AS e
			GROUP BY department_id) AS average_salaries
	ON e.department_id = average_salaries.department_id
	AND e.salary > average_salaries.average_salary 
	ORDER BY department_id LIMIT 10