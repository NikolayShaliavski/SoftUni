package Problem08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;

public class IncreaseMinionsAge {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] minionsIds =
                Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        String sqlSelectAllMinions = "SELECT m.name, m.age FROM minions AS m";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String updateMinions = null;

            for (int i = 0; i < minionsIds.length; i++) {
                updateMinions = "UPDATE minions AS m\n" +
                        "\tSET m.name = CONCAT(UPPER(SUBSTRING(m.name, 1, 1)) , LOWER(SUBSTRING(m.name FROM 2))),\n" +
                        "\tm.age = m.age + 1\n" +
                        "WHERE m.id = " + minionsIds[i];
                statement.executeUpdate(updateMinions);
            }
            ResultSet resultSet = statement.executeQuery(sqlSelectAllMinions);

            while (resultSet.next()) {
                System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
