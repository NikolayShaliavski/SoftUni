package Problem09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class IncreaseAgeProcedure {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.valueOf(reader.readLine());

        String sqlCreateProcedure = "CREATE PROCEDURE usp_get_older(minion_id INT)\n" +
                                    "BEGIN\n" +
                                    "\n" +
                                    "START TRANSACTION;\n" +
                                    "\n" +
                                    "UPDATE minions AS m\n" +
                                    "\tSET m.age = m.age + 1\n" +
                                    "WHERE m.id = minion_id;\n" +
                                    "\n" +
                                    "IF (minion_id NOT IN (SELECT m.id FROM minions AS m)) THEN\n" +
                                    "\tROLLBACK;\n" +
                                    "\tSIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Minion with such id do not exist.';\n" +
                                    "ELSE \n" +
                                    "\tCOMMIT;\n" +
                                    "END IF;\n" +
                                    "\n" +
                                    "END";

        String sqlDropProcedure = "DROP PROCEDURE IF EXISTS usp_get_older";

        String sqlCallProcedure = "CALL usp_get_older(" + minionId + ")";

        String sqlSelectMinion = "SELECT m.name, m.age FROM minions AS m " +
                                    "WHERE m.id = " + minionId;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(sqlDropProcedure);
            statement.execute(sqlCreateProcedure);

            statement.executeUpdate(sqlCallProcedure);
            ResultSet resultSet = statement.executeQuery(sqlSelectMinion);

            while (resultSet.next()) {
                String minionName  = resultSet.getString(1);
                int minionAge = resultSet.getInt(2);

                System.out.printf("%s %d%n", minionName, minionAge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
