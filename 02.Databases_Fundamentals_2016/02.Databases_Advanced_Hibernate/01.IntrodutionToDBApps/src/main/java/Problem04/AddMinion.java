package Problem04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class AddMinion {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] minionData = reader.readLine().split("[\\s+]");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String town = minionData[3];

        String[] villainData = reader.readLine().split("[\\s+]");
        String villainName = villainData[1];

        String sqlDropProcedureIfExists = "DROP PROCEDURE IF EXISTS udp_add_minion ";

        String sqlCreateProcedure = "CREATE PROCEDURE udp_add_minion(minion_name VARCHAR(10),\n" +
                                    "\t\t\t\t\t\t\t\t\t\t\tminion_age INT,\n" +
                                    "\t\t\t\t\t\t\t\t\t\t\ttown VARCHAR(10),\n" +
                                    "\t\t\t\t\t\t\t\t\t\t\tvillain_name VARCHAR(10))\n" +
                                    "BEGIN\n" +
                                    "DECLARE first_report VARCHAR(50) DEFAULT '';\n" +
                                    "DECLARE second_report VARCHAR(50) DEFAULT '';\n" +
                                    "DECLARE third_report VARCHAR(50) DEFAULT '';\n" +
                                    "START TRANSACTION;\n" +
                                    "\n" +
                                    "IF ((SELECT t.town_name FROM towns AS t\n" +
                                    "\t\tWHERE t.town_name = town) IS NULL) THEN\n" +
                                    "\tINSERT INTO towns\n" +
                                    "\t\t(town_name)\n" +
                                    "\tVALUES\n" +
                                    "\t\t(town);\n" +
                                    "\tSET first_report = (SELECT CONCAT('Town ', town, ' was added to the database.'));\n" +
                                    "END IF;\n" +
                                    "\n" +
                                    "IF ((SELECT v.name FROM villains AS v\n" +
                                    "\t\tWHERE v.name = villain_name) IS NULL) THEN\n" +
                                    "\tINSERT INTO villains\n" +
                                    "\t\t(name, evilness)\n" +
                                    "\tVALUES\n" +
                                    "\t\t(villain_name, 'evil');\n" +
                                    "\tSET second_report = (SELECT CONCAT('Villain ', villain_name, ' was added to the database.'));\n" +
                                    "\tEND IF;\n" +
                                    "\n" +
                                    "INSERT INTO minions\n" +
                                    "\t(name, age, town_id, villain_id)\n" +
                                    "VALUES\n" +
                                    "\t(minion_name, \n" +
                                    "\tminion_age, \n" +
                                    "\t(SELECT t.id FROM towns AS t WHERE t.town_name = town), \n" +
                                    "\t(SELECT v.id FROM villains AS v WHERE v.name = villain_name));\n" +
                                    "\n" +
                                    "\n" +
                                    "IF (minion_name IS NULL \n" +
                                    "OR minion_name = ''\n" +
                                    "OR minion_age IS NULL\n" +
                                    "OR minion_age <= 0\n" +
                                    "OR town IS NULL\n" +
                                    "OR town  = ''\n" +
                                    "OR villain_name IS NULL\n" +
                                    "OR villain_name = '') THEN\n" +
                                    "\tROLLBACK;\n" +
                                    "\tSIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invlid parameters passsed to procedure.';\n" +
                                    "ELSE\n" +
                                    "\tCOMMIT;\n" +
                                    "\tSET third_report = (SELECT CONCAT('Successfully added ', minion_name, ' to be minion of ', villain_name));\n" +
                                    "END IF;\t\n" +
                                    "-- SELECT GROUP_CONCAT(first_report, '\\n', second_report, '\\n', third_report) AS report;\n" +
                                    "SELECT first_report AS report, second_report AS report, third_report AS report;\n" +
                                    "END";

        String callProcedure =
                String.format("CALL udp_add_minion('%s', %d, '%s', '%s')", minionName, minionAge, town, villainName);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(sqlDropProcedureIfExists);
            statement.execute(sqlCreateProcedure);
            ResultSet resultSet = statement.executeQuery(callProcedure);
            while (resultSet.next()) {
                String report1 = resultSet.getString(1);
                String report2 = resultSet.getString(2);
                String report3 = resultSet.getString(3);

                if (report1 != "") {
                    System.out.println(report1);
                }
                if (report2 != "") {
                    System.out.println(report2);
                }
                System.out.println(report3);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
