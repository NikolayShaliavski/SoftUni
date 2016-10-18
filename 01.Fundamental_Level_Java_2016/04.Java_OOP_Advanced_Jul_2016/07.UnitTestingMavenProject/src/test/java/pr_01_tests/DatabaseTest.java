package pr_01_tests;

import org.junit.Assert;
import org.junit.Test;
import problem_01.Database;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Nikolay Shalyavski on 27.7.2016 г..
 */
public class DatabaseTest {

    private Database database;

    @Test
    public void constructorWithNoArgsShouldPass() {
        //Arrange
        this.database = new Database();
        Integer expected = null;
        //Act
        Integer actual = null;
        for (Integer value : this.database.getData()) {
            if (value != null) {
                actual = value;
            }
        }
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void constructorWithArgsShouldPass() {
        //Arrange
        this.database = new Database(1, 2, 3);
        String expected = "123";
        //Act
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(this.database.getData()[i]);
        }
        String actual = sb.toString();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void databaseLengthAfterInitializing() {
        //Arrange
        this.database = new Database();
        int expectedLength = 16;
        //Act
        int actualLength = this.database.getData().length;
        //Assert
        Assert.assertEquals(expectedLength, actualLength);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addNullElementToDataThrowsException() throws Exception {
        this.database = new Database();
        this.database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeElementFromDatabaseWhenItIsEmpty() throws Exception {
        this.database = new Database();
        this.database.remove();
    }

    @Test
    public void removeLastElementFromDatabase() throws OperationNotSupportedException {
        //Arrange
        this.database = new Database(1, 2, 3);
        int expected = 3;
        //Act
        int actual = this.database.remove();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fetchShouldReturnElementsInDatabase() throws Exception {
        //Arrange
        this.database = new Database(1, 2, 3);
        Integer[] expected = {1, 2, 3};
        //Act
        Integer[] actual = this.database.fetch();
        //Assert
        Assert.assertArrayEquals(expected, actual);
    }
}