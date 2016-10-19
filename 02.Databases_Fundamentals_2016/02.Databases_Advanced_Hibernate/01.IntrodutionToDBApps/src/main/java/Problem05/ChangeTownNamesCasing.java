package Problem05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class ChangeTownNamesCasing {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String country = reader.readLine();

        String sqlUpdate = "UPDATE towns AS t\n" +
                "\tSET t.town_name = UPPER(t.town_name)\n" +
                "WHERE t.country_name = '" + country + "'";
        String sqlSelectLastUpdated =
                "SELECT t.town_name FROM towns AS t WHERE t.country_name = '" + country + "'";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            if (statement.executeUpdate(sqlUpdate) == 0) {
                System.out.println("No town names were affected.");
                connection.close();
                statement.close();
                return;
            }

            ResultSet resultSet = statement.executeQuery(sqlSelectLastUpdated);
                int affectedRows = 0;
                StringBuilder builder = new StringBuilder();
                builder.append("[");

                while (resultSet.next()) {
                builder.append(resultSet.getString(1)).
                        append(", ");
                affectedRows++;
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("]");

            System.out.printf("%d town names were affected.%n", affectedRows);
            System.out.println(builder);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
