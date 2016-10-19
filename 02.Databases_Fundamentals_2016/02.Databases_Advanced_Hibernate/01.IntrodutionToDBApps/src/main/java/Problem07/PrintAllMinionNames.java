package Problem07;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        String sqlSelectAllMinions = "SELECT m.name FROM minions AS m";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlSelectAllMinions);
            List<String> allMinions = new ArrayList<>();
            while (resultSet.next()) {
                allMinions.add(resultSet.getString(1));
            }


            int mid = (allMinions.size() / 2) + (allMinions.size() % 2);

            for (int i = 0, j = allMinions.size() - 1; i < mid; i++, j--) {
                if (i == j) {
                    System.out.println(allMinions.get(i));
                } else {
                    System.out.println(allMinions.get(i));
                    System.out.println(allMinions.get(j));
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
