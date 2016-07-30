import problem_01.Database;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Nikolay Shalyavski on 27.7.2016 г..
 */
public class Main {

    public static void main(String[] args) throws OperationNotSupportedException {

        Database database = new Database();
        database.add(Integer.MAX_VALUE + 1);
        System.out.println();
    }
}
