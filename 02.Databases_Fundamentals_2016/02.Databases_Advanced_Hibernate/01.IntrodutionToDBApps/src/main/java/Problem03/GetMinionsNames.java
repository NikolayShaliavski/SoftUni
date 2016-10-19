package Problem03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class GetMinionsNames {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainsId = Integer.parseInt(reader.readLine());

        String sqlGetVillainsName = "SELECT v.name FROM villains AS v\n" +
                                        "\tWHERE v.id = " + villainsId;
        String sqlGetMinionsNames = "SELECT m.name, m.age FROM minions AS m\n" +
                                    "\tINNER JOIN villains AS v\n" +
                                    "\tON m.villain_id = v.id\n" +
                                    "WHERE v.id = " + villainsId;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet1 = statement.executeQuery(sqlGetVillainsName);

            if (!resultSet1.next()) {
                System.out.println("No villain with ID 10 exists in the database.");
                return;
            }
            else {
                System.out.printf("Villain: %s%n", resultSet1.getString("name"));
            }
            ResultSet resultSet2 = statement.executeQuery(sqlGetMinionsNames);
            int minionsCounter = 1;
            while (resultSet2.next()) {
                String minionName = resultSet2.getString("name");
                int minionAge = resultSet2.getInt("age");

                System.out.printf("%d. %s %d%n", minionsCounter, minionName, minionAge);
                minionsCounter++;
            }
            if (minionsCounter == 1) {
                System.out.println("(no minions)");
            }
            resultSet1.close();
            resultSet2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
