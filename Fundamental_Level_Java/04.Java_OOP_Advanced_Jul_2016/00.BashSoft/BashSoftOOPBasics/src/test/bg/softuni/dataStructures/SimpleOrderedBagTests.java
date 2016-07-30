package test.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;
import main.bg.softuni.dataStructures.SimpleSortedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SimpleOrderedBagTests {

    private static final int DEFAULT_LIST_CAPACITY = 16;

    private SimpleOrderedBag<String> names;

    @Before
    public void setUp() {
        this.names = new SimpleSortedList<>(String.class);
    }

    //region Constructors
    @Test
    public void testEmptyCtor() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        int expectedCapacity = DEFAULT_LIST_CAPACITY;
        int expectedSize = 0;
        //Act
        int actualCapacity = this.names.capacity();
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCtorWithInitialCapacity() {
        //Arrange
        int testInitialCapacity = 20;
        this.names = new SimpleSortedList<String>(String.class, testInitialCapacity);
        int expectedCapacity = testInitialCapacity;
        int expectedSize = 0;
        //Act
        int actualCapacity = this.names.capacity();
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCtorWithInitialComparer() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class,
                String.CASE_INSENSITIVE_ORDER);
        int expectedCapacity = DEFAULT_LIST_CAPACITY;
        int expectedSize = 0;
        //Act
        int actualCapacity = this.names.capacity();
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCtorWithAllParams() {
        //Arrange
        int testInitialCapacity = 30;
        this.names = new SimpleSortedList<String>(String.class,
                String.CASE_INSENSITIVE_ORDER,
                testInitialCapacity);
        int expectedCapacity = testInitialCapacity;
        int expectedSize = 0;
        //Act
        int actualCapacity = this.names.capacity();
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedSize, actualSize);
    }
    //endregion

    //region Add method
    @Test
    public void testAddMethodIncreasesSize() {
        //Arrange
        this.names.add("Nasko");
        int expectedSize = 1;
        //Act
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testAddMethodWithNullParamThrowsException() {
        this.names.add(null);
    }

    /*
    add unsorted data to customList
    list must sort elements immediately
     */
    @Test
    public void testAddUnsortedData() {
        //Arrange
        this.names.add("Rosen");
        this.names.add("Boyan");
        this.names.add("Nasko");
        this.names.add("Atanas");
        this.names.add("Qvor");
        String expected = "Atanas Boyan Nasko Qvor Rosen";
        //Act
        StringBuilder testBuilder = new StringBuilder();
        for (String name : this.names) {
            testBuilder.append(name).append(" ");
        }
        String actual = testBuilder.toString().trim();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /*
    add more elements than initial capacity
    capacity must be different than default init capacity - 16
    here is used add() to check if customList will resize after adding the 17'th element
     */
    @Test
    public void testAddMoreElementsThanInitialCapacity() {
        //Arrange
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        this.names.add("Toshko");
        int expectedSize = 17;
        int unexpectedCapacity = DEFAULT_LIST_CAPACITY;
        //Act
        int actualSize = this.names.size();
        int actualCapacity = this.names.capacity();
        //Assert
        Assert.assertEquals(expectedSize, actualSize);
        Assert.assertNotEquals(unexpectedCapacity, actualCapacity);
    }
    //endregion

    //region AddAll method
    @Test
    public void testAddAllMethodListShouldIncreaseSize() {
        //Arrange
        this.names.addAll(Arrays.asList("Qvor", "Nasko", "Boyan", "Atanas", "Rosen"));
        int expectedSize = 5;
        //Act
        int actualSize = this.names.size();
        //Assert
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllWithNullElementThrowsException() {
        this.names.addAll(Arrays.asList("Qvor", null, "Boyan", "Atanas", "Rosen"));
    }

    @Test
    public void testAddAllListShouldSortElements() {
        //Arrange
        this.names.addAll(Arrays.asList("Qvor", "Asen", "Boyan", "Atanas", "Rosen"));
        String expected = "Asen Atanas Boyan Qvor Rosen";
        //Act
        StringBuilder testBuilder = new StringBuilder();
        for (String name : this.names) {
            testBuilder.append(name).append(" ");
        }
        String actual = testBuilder.toString().trim();
        //Assert
        Assert.assertEquals(expected, actual);
    }
    //endregion

    //region Remove method
    @Test
    public void removeValidElementSizeShouldBeDecreased() {
        //Arrange
        int expected = this.names.size();
        this.names.add("Nikolay");
        this.names.remove("Nikolay");
        //Act
        int actual = this.names.size();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeElementShouldNotMoreContainsInList() {
        String nameToRemove = "Nasko";
        //Arrange
        this.names.add("Nasko");
        this.names.add("Asen");
        this.names.remove(nameToRemove);
        //Act
        boolean contains = false;
        for (String name : this.names) {
            if (name.equals(nameToRemove)) {
                contains = true;
                break;
            }
        }
        //Assert
        Assert.assertFalse(contains);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullElementThrowsException() {
        this.names.remove(null);
    }
    //endregion

    //region joinWith method
    @Test(expected = IllegalArgumentException.class)
    public void joinWithNullParamThrowsException() {
        //Arrange
        this.names.add("Nikolay");
        this.names.joinWith(null);
    }

    @Test
    public void joinWithShouldJoinElementsInString() {
        //Arrange
        this.names.addAll(Arrays.asList("Vency", "Atanas", "Bobi"));
        String expected = "Atanas, Bobi, Vency";
        //Act
        String actual = this.names.joinWith(", ");
        //Assert
        Assert.assertEquals(expected, actual);
    }
    //endregion
}
