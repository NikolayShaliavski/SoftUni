import connection.Connector;
import entities.User;
import orm.EntityManager;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException,
            ParseException {
        Connector.initConnection("mysql", "root", "1234", "localhost", "3306", "school");
        try (EntityManager entityManager = new EntityManager(Connector.getConnection())) {

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date date = df.parse("2013/10/01");
        User testUser = new User("Ivan", 25, date);
        //testUser.setid(1);
        System.out.println(entityManager.persist(testUser));

        Iterable<User> entities = entityManager.find(User.class);

        for (User entity : entities) {
            System.out.println(entity.toString());
        }

//        Book testBook = new Book("TestBook3",
//                "Unknown", publishedOn, "English", 4);
//
//        testBook.setId(5);
//        entityManager.persist(testBook);

//            Iterable<Book> books = entityManager.find(Book.class, "rating < 5");
//
//            for (Book book : books) {
//                entityManager.deleteById(Book.class, book.getId());
//            }
//
//            books = entityManager.find(Book.class);
//
//            for (Book book : books) {
//                System.out.println(book);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
