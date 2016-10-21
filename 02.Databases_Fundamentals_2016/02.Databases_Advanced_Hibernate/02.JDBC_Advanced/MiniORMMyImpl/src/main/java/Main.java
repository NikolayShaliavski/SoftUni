import connection.Connector;
import entities.User;
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.initConnection("mysql", "root", "1234", "localhost", "3306", "school");
        Connection connection = Connector.getConnection();
        EntityManager entityManager = new EntityManager(connection);

        User testUser = new User(2, "Gosho", 25, new Date());

        System.out.println(entityManager.persist(testUser));
    }
}
