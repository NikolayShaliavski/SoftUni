package Problem02;

import java.sql.*;

public class GetVillainsNames {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        String sqlSelectVillainsNames = "SELECT v.name, COUNT(m.id) AS minions_count FROM villains AS v\n" +
                                        "\tINNER JOIN minions AS m\n" +
                                        "\tON v.id = m.villain_id\n" +
                                        "GROUP BY v.id\n" +
                                        "HAVING minions_count > 3\n" +
                                        "ORDER BY minions_count DESC";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelectVillainsNames)) {
            while (resultSet.next()) {
                String villainName = resultSet.getString("name");
                int minionsCount = resultSet.getInt("minions_count");

                System.out.println(String.format("%s %d", villainName, minionsCount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
