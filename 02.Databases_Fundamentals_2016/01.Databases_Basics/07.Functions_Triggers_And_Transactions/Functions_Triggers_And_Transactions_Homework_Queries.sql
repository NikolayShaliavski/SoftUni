-- Problem 1_Employees with Salary Above 35000

DELIMITER $$
-- in judge only create statement
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN

SELECT e.first_name, e.last_name FROM employees AS e
			WHERE e.salary > 35000;
END $$
DELIMITER ;
CALL usp_get_employees_salary_above_35000
------------------------------------------------------------------------------------------------


-- Problem 2. Employees with Salary Above Number

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(number DOUBLE)
BEGIN

SELECT e.first_name, e.last_name FROM employees AS e
			WHERE e.salary >= number;
END $$
DELIMITER ;
CALL usp_get_employees_salary_above(48100.00)
------------------------------------------------------------------------------------------------


-- Problem 3. Town Names Starting With

DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(start_str VARCHAR(50))
BEGIN

SELECT t.name FROM towns AS t
			WHERE t.name LIKE CONCAT(start_str, '%');
END $$
DELIMITER ;
CALL usp_get_towns_starting_with('b')
------------------------------------------------------------------------------------------------


-- Problem 4. Employees from Town

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN

SELECT e.first_name, e.last_name FROM employees AS e
	INNER JOIN(SELECT a.town_id, a.address_id FROM addresses AS a) AS a
	ON e.address_id = a.address_id
		INNER JOIN(SELECT t.name, t.town_id FROM towns AS t) AS t
		ON a.town_id = t.town_id
		AND t.name = town_name;
END $$
DELIMITER ;
CALL usp_get_employees_from_town('Sofia')
------------------------------------------------------------------------------------------------


-- Problem 5. Salary Level Function

DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL)
RETURNS VARCHAR(50)
BEGIN

DECLARE salaryLevel VARCHAR(50);
	IF (salary < 30000) THEN
		SET salaryLevel = 'Low';
	ELSEIF (salary <= 50000) THEN
		SET salaryLevel = 'Average';
	ELSE
		SET salaryLevel = 'High';
	END IF;
	RETURN salaryLevel;
END $$
DELIMITER ;
SELECT e.salary, ufn_get_salary_level(e.salary) AS salary_level FROM employees AS e
------------------------------------------------------------------------------------------------


-- Problem 6. Employees by Salary Level

DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salaryLevel VARCHAR(50))
BEGIN

	IF (salaryLevel = 'Low') THEN
		SELECT e.first_name, e.last_name FROM employees AS e
		WHERE e.salary < 30000;
	ELSEIF (salaryLevel = 'Average') THEN
		SELECT e.first_name, e.last_name FROM employees AS e
		WHERE e.salary <= 50000;
	ELSE
		SELECT e.first_name, e.last_name FROM employees AS e
		WHERE e.salary > 50000;
	END IF;

END $$
DELIMITER ;
CALL usp_get_employees_by_salary_level('High')
------------------------------------------------------------------------------------------------


-- Problem 7_Define Function

DELIMITER $$

DROP FUNCTION IF EXISTS ufn_is_word_comprised$$

CREATE FUNCTION ufn_is_word_comprised(letters VARCHAR(50), word VARCHAR(50))
RETURNS TINYINT
BEGIN

DECLARE letter_index INT DEFAULT 1;
DECLARE word_length INT;
DECLARE letter VARCHAR(1);
SET word_length = CHAR_LENGTH(word);

IF (word IS NULL OR letters IS NULL) THEN
	RETURN 0;
END IF;

WHILE (letter_index < word_length) DO
	SET letter = SUBSTRING(word, letter_index, 1);
	IF (LOCATE(letter, letters) = 0) THEN
		RETURN 0;
	END IF;
	SET letter_index = letter_index + 1;
END WHILE;

RETURN 1;
END $$

DELIMITER ;

SELECT ufn_is_word_comprised(1, '1') AS result
------------------------------------------------------------------------------------------------


-- Problem 9_Find Full Name

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN

	SELECT CONCAT(ac.first_name, ' ', ac.last_name) AS full_name FROM account_holders AS ac;

END
------------------------------------------------------------------------------------------------


-- Problem 10_People with Balance Higher Than

DELIMITER $$

DROP PROCEDURE IF EXISTS usp_get_holders_with_balance_higher_than$$

CREATE PROCEDURE usp_get_holders_with_balance_higher_than(sum DOUBLE)
BEGIN

SELECT ac.first_name, ac.last_name FROM account_holders AS ac
	INNER JOIN(SELECT a.account_holder_id, SUM(a.balance) AS balance FROM accounts AS a
					GROUP BY a.account_holder_id) AS sum_holder
	ON ac.id = sum_holder.account_holder_id
	AND sum_holder.balance > sum
	GROUP BY ac.first_name;
END$$

DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(300)
------------------------------------------------------------------------------------------------


-- Problem 11_Future Value Function

DELIMITER $$

DROP FUNCTION IF EXISTS ufn_calculate_future_value$$

CREATE FUNCTION ufn_calculate_future_value(sum DOUBLE, rate DOUBLE, years INT)
RETURNS DOUBLE
BEGIN
DECLARE future_sum DOUBLE;
DECLARE iterator INT;
SET future_sum = sum;
SET iterator = 1;


WHILE (iterator <= years) DO

	SET future_sum = future_sum + (future_sum * rate);
	SET iterator = iterator + 1;
	
END WHILE;
RETURN future_sum;
END $$

SELECT ufn_calculate_future_value(1000, 0.1, 5)
------------------------------------------------------------------------------------------------


-- Problem 12_Calculating Interest

DELIMITER $$

DROP PROCEDURE IF EXISTS usp_calculate_future_value_for_account$$

CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, rate DOUBLE)
BEGIN

SELECT acc.id, 
		 ac.first_name, 
		 ac.last_name, 
		 acc.balance AS current_balance, 
		 ufn_calculate_future_value(acc.balance, rate, 5) AS balance_in_5_years FROM account_holders AS ac
	INNER JOIN(SELECT a.id, a.account_holder_id, a.balance FROM accounts AS a) AS acc
	ON acc.id = account_id
	AND ac.id = acc.account_holder_id;
END $$


CALL usp_calculate_future_value_for_account(1, 0.1)
------------------------------------------------------------------------------------------------


-- Problem 13_Deposit Money

USE bank_database;
DELIMITER $$

DROP PROCEDURE IF EXISTS usp_deposit_money$$

CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DOUBLE)
BEGIN

START TRANSACTION;

UPDATE accounts
	SET balance = balance + money_amount
	WHERE id = account_id;
-- works in judge without any checks  && with DECIMAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
IF (money_amount < 0 
	 OR money_amount IS NULL) THEN
	ROLLBACK;
ELSE
	COMMIT;
END IF;
	
END $$

DELIMITER ;
CALL usp_deposit_money(2, 100);

SELECT * FROM accounts
------------------------------------------------------------------------------------------------


-- Problem 14_Withdraw Money

USE bank_database;
DELIMITER $$

DROP PROCEDURE IF EXISTS usp_withdraw_money$$

CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DOUBLE)
BEGIN
DECLARE temp_balance DOUBLE;
SET temp_balance = (SELECT a.balance FROM accounts AS a 
							WHERE a.id = account_id);

START TRANSACTION;

UPDATE accounts AS a
	SET a.balance = a.balance - money_amount
	WHERE a.id = account_id;
-- works in judge without any checks && with DECIMAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
IF (money_amount > temp_balance
	 OR money_amount < 0
	 OR money_amount IS NULL) THEN
	ROLLBACK;
END IF;

COMMIT;
	
END $$

DELIMITER ;

CALL usp_withdraw_money(2, 1100);

SELECT * FROM accounts
------------------------------------------------------------------------------------------------


-- Problem 15_Money Transfer

DELIMITER $$

DROP PROCEDURE IF EXISTS usp_transfer_money;

CREATE PROCEDURE usp_transfer_money(account_from INT, account_to INT, sum DOUBLE)
BEGIN

DECLARE sender_balance DOUBLE;
SET sender_balance = (SELECT a.balance FROM accounts AS a WHERE a.id = account_from);

START TRANSACTION;

UPDATE accounts AS a
	SET a.balance = a.balance - sum
WHERE a.id = account_from;
	
UPDATE accounts AS a
	SET a.balance = a.balance + sum
WHERE a.id = account_to;
	
IF (account_from NOT IN (SELECT id FROM accounts)
	 OR account_to NOT IN (SELECT id FROM accounts)
	 OR sum > sender_balance
	 OR sum <= 0) THEN
	ROLLBACK;
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot accomplish transfer!!!';
ELSE 
	COMMIT;
END IF;

END $$

DELIMITER ;

CALL usp_transfer_money(1, 1, 123.12);

SELECT * FROM accounts
------------------------------------------------------------------------------------------------


-- Problem 16_Create Table Logs

DELIMITER $$

DROP TRIGGER IF EXISTS tr_update_accounts_balance;

CREATE TRIGGER tr_update_accounts_balance
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN

	INSERT INTO `logs`
	  (account_id, old_sum, new_sum)
	VALUES
	  (old.id, old.balance, new.balance);
	
END $$

DELIMITER ;

UPDATE accounts 
	SET balance = 300
	WHERE id = 2
------------------------------------------------------------------------------------------------


-- Problem 17_Create Table Emails

DELIMITER $$

CREATE TRIGGER tr_insert_logs -- uses trigger from previous problem
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
DECLARE inserted_id INT;
DECLARE subject VARCHAR(50);
DECLARE body VARCHAR(100);
SET inserted_id = new.account_id;
SET subject = CONCAT('Balance changed for account : ', inserted_id);
SET body = CONCAT('On ', NOW(), ' your balance was changed from ', new.old_sum, ' to ', new.new_sum, '.');

	INSERT INTO notification_emails
		(recipient, subject, body)
	VALUES
		(inserted_id, subject, body);
		
END $$

DELIMITER ;
------------------------------------------------------------------------------------------------


-- Problem 20_Number of Users for Email Provider

SELECT SUBSTRING_INDEX(u.email, '@', -1) AS email_provider, COUNT(*) AS number_of_users FROM users AS u
	GROUP BY email_provider 
	ORDER BY number_of_users DESC, email_provider 
------------------------------------------------------------------------------------------------


-- Problem 21_All User in Games

SELECT users_games_characters.game_name,
		users_games_characters.type_name,
		u.user_name,
		users_games_characters.`level`,
		users_games_characters.cash,
		users_games_characters.character_name
		FROM users AS u
INNER JOIN(SELECT games_users.user_id, 
	 games_users.`level`, 
	 games_users.cash, 
	 games_users.name AS game_name, 
	 games_users.type_name, 
	 ch.name AS character_name FROM characters AS ch
	INNER JOIN(
	SELECT ug.user_id, 
			 ug.`level`, 
			 ug.cash, 
			 ug.character_id,
			 games_name_type.name, 
			 games_name_type.type_name 
			 FROM users_games AS ug
		INNER JOIN(
		SELECT g.id, 
				g.name, 
				gt.name AS type_name 
				FROM games AS g
			INNER JOIN(
			SELECT gt.id, 
					gt.name 
					FROM game_types AS gt) AS gt
			ON g.game_type_id = gt.id) AS games_name_type
		ON ug.game_id = games_name_type.id) AS games_users
	ON ch.id = games_users.character_id) AS users_games_characters
ON u.id = users_games_characters.user_id
ORDER BY users_games_characters.`level` DESC, 
			u.user_name, 
			users_games_characters.game_name
------------------------------------------------------------------------------------------------



-- Problem 22_Users in Games with Their Items

SELECT games_count_prices.user_name AS username, 
		g.name AS game, 
		games_count_prices.items_count AS items_count, 
		games_count_prices.price AS items_price FROM games AS g	
INNER JOIN(SELECT all_games.user_name, 
				all_games.game_id, 
				SUM(i.price) AS price, 
				COUNT(*) AS items_count FROM items AS i
	INNER JOIN(SELECT ugi.user_game_id, 
					user_games.user_name, 
					user_games.game_id, 
					ugi.item_id FROM user_game_items AS ugi	
		INNER JOIN(SELECT u.user_name, 
						ug.game_id, 
						ug.id FROM users AS u
			INNER JOIN(SELECT ug.id, 
							ug.user_id, 
							ug.game_id FROM users_games AS ug) AS ug
			ON u.id = ug.user_id) AS user_games
		ON ugi.user_game_id = user_games.id
		GROUP BY ugi.item_id, ugi.user_game_id) AS all_games
	ON i.id = all_games.item_id
	GROUP BY all_games.user_name, all_games.game_id
	HAVING items_count >= 10) AS games_count_prices
ON g.id = games_count_prices.game_id
ORDER BY games_count_prices.items_count DESC, 
			games_count_prices.price DESC, 
			games_count_prices.user_name ASC
------------------------------------------------------------------------------------------------


-- Problem 23_*User in Games with Their Statistics

SELECT u.user_name, 
		 g.name AS game, 
		 MAX(ch.name) AS character_name,
		 MAX(st.strength) + MAX(st_ch.strength) + SUM(st_i.strength) AS strength,
		 MAX(st.defence) + MAX(st_ch.defence) + SUM(st_i.defence) AS defence,
		 MAX(st.speed) + MAX(st_ch.speed) + SUM(st_i.speed) AS speed,
		 MAX(st.mind) + MAX(st_ch.mind) + SUM(st_i.mind) AS mind,
		 MAX(st.luck)  + MAX(st_ch.luck) + SUM(st_i.luck) AS luck FROM users AS u
	INNER JOIN users_games AS ug
	ON u.id = ug.user_id
		INNER JOIN games AS g
		ON ug.game_id = g.id
			INNER JOIN game_types AS gt
			ON g.game_type_id = gt.id
				INNER JOIN statistics AS st
				ON gt.bonus_stats_id = st.id
					INNER JOIN characters AS ch
					ON ug.character_id = ch.id
						INNER JOIN statistics AS st_ch
						ON ch.statistics_id = st_ch.id
							INNER JOIN user_game_items AS ugi
							ON ug.id = ugi.user_game_id
								INNER JOIN items AS i
								ON ugi.item_id = i.id
									INNER JOIN statistics AS st_i
									ON i.statistics_id = st_i.id
GROUP BY u.user_name, g.name
ORDER BY strength DESC,
			defence DESC,
			speed DESC,
			mind DESC,
			luck DESC					
------------------------------------------------------------------------------------------------


-- Problem 24_All Items with Greater than Average Statistics - doesn't work in judge

SELECT i.name, 
		 i.price, 
		 i.min_level,
		 st.strength,
		 st.defence,
		 st.speed,
		 st.luck,
		 st.mind FROM items AS i
	INNER JOIN statistics AS st
	ON i.statistics_id = st.id
WHERE st.mind >= (SELECT AVG(st.mind) FROM statistics AS st
								INNER JOIN items AS i
								ON st.id = i.statistics_id)
AND st.luck >= (SELECT AVG(st.luck) FROM statistics AS st
								INNER JOIN items AS i
								ON st.id = i.statistics_id)
AND st.speed >= (SELECT AVG(st.speed) FROM statistics AS st
								INNER JOIN items AS i
								ON st.id = i.statistics_id)
ORDER BY i.name																
------------------------------------------------------------------------------------------------


-- Problem 25_Display All Items with information about Forbidden Game Type - doesn't work in judge, but zero test OK

SELECT i.name AS item, i.price AS price, i.min_level, gt.name AS forbidden_game_type FROM items AS i	
	LEFT OUTER JOIN game_type_forbidden_items AS gtfi
	ON i.id = gtfi.item_id				
		LEFT OUTER JOIN game_types AS gt
		ON gtfi.game_type_id = gt.id	
ORDER BY gt.name DESC, i.name ASC
------------------------------------------------------------------------------------------------


-- Problem 26. Buy Items for User in Game - DOESN'T COMPILE IN JUDGE

DELIMITER $$
CREATE PROCEDURE usp_buy_items_for_alex()
BEGIN
DECLARE user_game_id INT;
DECLARE sum_of_new_items DECIMAL(19, 4);

SET user_game_id = (SELECT ug.id FROM users AS u
						  INNER JOIN users_games AS ug
						  ON u.id = ug.user_id
								INNER JOIN games AS g
								ON ug.game_id = g.id
						  WHERE u.user_name = 'Alex'
						  AND g.name = 'Edinburgh');

SET sum_of_new_items = (SELECT SUM(i.price) FROM items AS i 
								WHERE i.name IN('Blackguard', 'Bottomless Potion of Amplification', 
								'Eye of Etlich (Diablo III)', 'Gem of Efficacious Toxin', 
								'Golden Gorget of Leoric', 'Hellfire Amulet'));
								
UPDATE users_games AS ug
	SET ug.cash = ug.cash - sum_of_new_items
WHERE ug.id = user_game_id;

INSERT INTO user_game_items (item_id, user_game_id)
	SELECT i.id, 235 FROM items AS i 
		WHERE i.name IN('Blackguard', 'Bottomless Potion of Amplification', 
		'Eye of Etlich (Diablo III)', 'Gem of Efficacious Toxin', 
		'Golden Gorget of Leoric', 'Hellfire Amulet');

SELECT u.user_name, g.name, ug.cash, i.name FROM users AS u
	INNER JOIN users_games AS ug
	ON u.id = ug.user_id
		INNER JOIN games AS g
		ON g.name = 'Edinburgh'
		AND ug.game_id = g.id
			INNER JOIN user_game_items AS ugi
			ON ug.id = ugi.user_game_id
				INNER JOIN items AS i
				ON ugi.item_id = i.id
ORDER BY i.name;

END $$
DELIMITER ;
CALL usp_buy_items_for_alex();
------------------------------------------------------------------------------------------------



-- Problem 27. Peaks and Mountains

SELECT p.peak_name, m.mountain_range AS mountain, p.elevation  FROM peaks AS p		
	INNER JOIN mountains AS m
	ON p.mountain_id = m.id
ORDER BY p.elevation DESC, p.peak_name
------------------------------------------------------------------------------------------------


-- Problem 28. Peaks with Their Mountain, Country and Continent

SELECT p.peak_name, 
		 m.mountain_range AS mountain_name,
		 c.country_name,
		 con.continent_name FROM peaks AS p		
	INNER JOIN mountains AS m
	ON p.mountain_id = m.id
		INNER JOIN mountains_countries AS mc
		ON m.id = mc.mountain_id
			INNER JOIN countries AS c
			ON mc.country_code = c.country_code
				INNER JOIN continents AS con
				ON c.continent_code = con.continent_code
ORDER BY p.peak_name, c.country_name
------------------------------------------------------------------------------------------------


-- Problem 29. Rivers by Country

SELECT c.country_name,
		 con.continent_name,
		 IF(cr.river_id IS NULL, 0, COUNT(*)) AS rivers_count,
		 IF(cr.river_id IS NULL, 0, SUM(r.length)) AS total_length FROM continents AS con
	INNER JOIN countries AS c
	ON c.continent_code = con.continent_code
		LEFT OUTER JOIN countries_rivers AS cr
		ON c.country_code = cr.country_code
			LEFT OUTER JOIN rivers AS r
			ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY rivers_count DESC, total_length DESC, c.country_name
------------------------------------------------------------------------------------------------


-- Problem 30. Count of Countries by Currency - doesn't work in judge

SELECT curr.currency_code, 
		 curr.description AS curency_description, 
		 COUNT(c.country_name) AS number_of_countries FROM currencies AS curr
	LEFT OUTER JOIN countries AS c
	ON curr.currency_code = c.currency_code
GROUP BY curr.currency_code
ORDER BY number_of_countries DESC, curency_description
------------------------------------------------------------------------------------------------


-- Problem 31. Population and Area by Continent

SELECT con.continent_name,
		 SUM(c.area_in_sq_km) AS countries_area,
		 SUM(c.population) AS countries_population  FROM continents AS con
	INNER JOIN countries AS c
	ON con.continent_code = c.continent_code
GROUP BY con.continent_name
ORDER BY countries_population DESC
------------------------------------------------------------------------------------------------


-- Problem 32. Monasteries by Country

CREATE TABLE monasteries(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
country_code CHAR(2),
CONSTRAINT pk_monasteries PRIMARY KEY (id),
CONSTRAINT fk_monasteries_countries 
FOREIGN KEY (country_code) REFERENCES countries(country_code)
);

INSERT INTO monasteries(name, country_code) VALUES
('Rila Monastery “St. Ivan of Rila”', 'BG'), 
('Bachkovo Monastery “Virgin Mary”', 'BG'),
('Troyan Monastery “Holy Mother''s Assumption”', 'BG'),
('Kopan Monastery', 'NP'),
('Thrangu Tashi Yangtse Monastery', 'NP'),
('Shechen Tennyi Dargyeling Monastery', 'NP'),
('Benchen Monastery', 'NP'),
('Southern Shaolin Monastery', 'CN'),
('Dabei Monastery', 'CN'),
('Wa Sau Toi', 'CN'),
('Lhunshigyia Monastery', 'CN'),
('Rakya Monastery', 'CN'),
('Monasteries of Meteora', 'GR'),
('The Holy Monastery of Stavronikita', 'GR'),
('Taung Kalat Monastery', 'MM'),
('Pa-Auk Forest Monastery', 'MM'),
('Taktsang Palphug Monastery', 'BT'),
('Sümela Monastery', 'TR');

-- in judge without this query
ALTER TABLE countries
	ADD is_deleted TINYINT(1) DEFAULT 0;
	
UPDATE countries AS c
	SET is_deleted = 1
WHERE (SELECT COUNT(*) FROM countries_rivers AS cr
			WHERE c.country_code = cr.country_code
		 GROUP BY cr.country_code) > 3;
		 
SELECT m.name AS monastery, c.country_name AS country FROM monasteries AS m
	INNER JOIN countries AS c
	ON m.country_code = c.country_code
	AND c.is_deleted = 0
ORDER BY m.name;
------------------------------------------------------------------------------------------------


-- Problem 33. Monasteries by Continents and Countries

UPDATE countries AS c
	SET c.country_name = 'Burma'
	WHERE c.country_name = 'Myanmar';
	
INSERT INTO monasteries
	(name, country_code) 
VALUES
	('Hanga Abbey', 
	(SELECT c.country_code FROM countries AS c
	WHERE c.country_name = 'Tanzania'));
	
INSERT INTO monasteries
	(name, country_code) 
VALUES
	('Myin-Tin-Daik', 
	(SELECT c.country_code FROM countries AS c
	WHERE c.country_name = 'Myanmar'));
	
SELECT con.continent_name, 
		 c.country_name,
		 IF(m.name IS NULL, 0, COUNT(*)) AS monasteries_count FROM continents AS con
	INNER JOIN countries AS c
	ON con.continent_code = c.continent_code
	AND c.is_deleted = 0
		LEFT OUTER JOIN monasteries AS m
		ON c.country_code = m.country_code
GROUP BY c.country_name
ORDER BY monasteries_count DESC, c.country_name;
------------------------------------------------------------------------------------------------


-- Cursor Demo
DELIMITER $$
CREATE PROCEDURE udp_cursor_demo ()
BEGIN
DECLARE employee_id INT;
DECLARE project_id INT;
DECLARE cursor_over INT DEFAULT 0;

DECLARE employee_projects_cursor CURSOR
FOR
SELECT ep.employee_id, ep.project_id 
	FROM employees_projects AS ep 
	LIMIT 5;
	
DECLARE CONTINUE HANDLER
FOR NOT FOUND SET cursor_over = 1;

OPEN employee_projects_cursor;

cursorLoop : LOOP
FETCH employee_projects_cursor INTO employee_id, project_id;

IF (cursor_over = 1) THEN
LEAVE cursorLoop;
END IF;

SELECT employee_id, project_id;

END LOOP cursorLoop;

CLOSE employee_projects_cursor;
END $$
DELIMITER ;

CALL udp_cursor_demo