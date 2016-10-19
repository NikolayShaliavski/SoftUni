package Problem01;

import java.sql.*;

public class InitialSetup {

    private static final String URL = "jdbc:mysql://localhost:3306?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        String sqlCreateDatabase = "CREATE DATABASE minions_db ";

        String sqlUseDatabase = "USE minions_db ";

        String sqlCreateTableTowns = "CREATE TABLE towns(" +
                                    "id INT AUTO_INCREMENT," +
                                    "town_name VARCHAR(10)," +
                                    "country_name VARCHAR(10)," +
                                    "CONSTRAINT pk_towns PRIMARY KEY (id))";

        String sqlCreateTableVillains = "CREATE TABLE villains(" +
                                        "id INT AUTO_INCREMENT," +
                                        "name VARCHAR(10)," +
                                        "evilness VARCHAR(10) NOT NULL CHECK " +
                                        "(evilness='good' OR evilness='bad' OR evilness='evil' OR evilness='super evil')," +
                                        "CONSTRAINT pk_villains PRIMARY KEY (id))";

        String sqlCreateTableMinions = "CREATE TABLE minions(" +
                                       "id INT AUTO_INCREMENT," +
                                        "name VARCHAR(20)," +
                                        "age INT," +
                                        "town_id INT," +
                                        "villain_id INT," +
                                        "CONSTRAINT pk_minions PRIMARY KEY (id)," +
                                        "CONSTRAINT fk_minions_towns " +
                                        "FOREIGN KEY (town_id) REFERENCES towns(id)," +
                                        "CONSTRAINT fk_minions_villains " +
                                        "FOREIGN KEY (villain_id) REFERENCES villains(id))";

        String sqlInsertDataIntoTowns = "INSERT INTO towns" +
                                        "(town_name, country_name)" +
                                        "VALUES" +
                                        "('Sofia', 'Bulgaria')," +
                                        "('Moscow', 'Russia')," +
                                        "('New York', 'USA')," +
                                        "('Varna', 'Bulgaria')," +
                                        "('Burgas', 'Bulgaria')";

        String sqlInsertDataIntoMinions = "INSERT INTO minions" +
                                            "(name, age, town_id ,villain_id)" +
                                            "VALUES" +
                                            "('Bob', 13, 1, 1)," +
                                            "('Steward', 19, 2, 1)," +
                                            "('Kevin', 14, 5, 1)," +
                                            "('Simon', 22, 3, 1)," +
                                            "('Jimmy', 20, 4, 1)," +
                                            "('Vicky', 16, 4, 1)," +
                                            "('Pesho', 17, 1, 2)," +
                                            "('Cecko', 12, 3, 2)," +
                                            "('Ivan', 30, 2, 2)," +
                                            "('Rado', 80, 1, 2)," +
                                            "('Alex', 23, 4, 3)," +
                                            "('Pencho', 18, 1, 3)," +
                                            "('Asen', 17, 4, 3)," +
                                            "('Andrei', 19, 5, 3)," +
                                            "('Mariika', 40, 5, 4)," +
                                            "('Gosho', 16, 3, 5)";

        String sqlInsertDataIntoVillains = "INSERT INTO villains" +
                                            "(name, evilness)" +
                                            "VALUES\n" +
                                            "('Gru', 'good')," +
                                            "('Victor', 'bad')," +
                                            "('Victor Jr.', 'super evil')," +
                                            "('Jilly', 'evil')," +
                                            "('Jimmy', 'bad')";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlCreateDatabase);
            statement.execute(sqlUseDatabase);

            statement.execute(sqlCreateTableTowns);
            statement.execute(sqlCreateTableVillains);
            statement.execute(sqlCreateTableMinions);

            statement.execute(sqlInsertDataIntoTowns);
            statement.execute(sqlInsertDataIntoVillains);
            statement.execute(sqlInsertDataIntoMinions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
