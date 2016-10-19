package Problem06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class RemoveVillain {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.valueOf(reader.readLine());

        String sqlFindVillainById = "SELECT v.name FROM villains AS v " +
                                    "WHERE v.id = " + villainId;
        String sqlReleaseMinions = "UPDATE minions AS m\n" +
                                    "\tSET m.villain_id = NULL\n" +
                                    "WHERE m.villain_id = " + villainId;
        String sqlDeleteVillain = "DELETE FROM villains WHERE id = " + villainId;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String villainName = null;
            ResultSet resultSet = statement.executeQuery(sqlFindVillainById);
            if (resultSet.next()) {
                villainName = resultSet.getString(1);
            }


            if (villainName == null) {
                System.out.println("No such villain was found.");
                connection.close();
                statement.close();
                resultSet.close();
                return;
            }

            int countOfReleasedMinions = statement.executeUpdate(sqlReleaseMinions);
            statement.execute(sqlDeleteVillain);

            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released", countOfReleasedMinions);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
