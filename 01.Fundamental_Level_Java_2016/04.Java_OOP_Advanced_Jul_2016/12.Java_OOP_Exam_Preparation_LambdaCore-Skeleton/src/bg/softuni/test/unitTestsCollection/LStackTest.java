package bg.softuni.test.unitTestsCollection;

import bg.softuni.main.contracts.collectionContracts.LinkedStack;
import bg.softuni.main.models.collection.LStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LStackTest {

    private LinkedStack<Integer> testStack;

    @Before
    public void initStack() {
        this.testStack = new LStack<Integer>();
    }

    //region Constructor
    @Test
    public void constructorTest_initLStack_shouldNotBeNull() {
        //Assert
        Assert.assertNotNull("Initialized LStack should not be null!", this.testStack);
    }
    //endregion

    //region size() method
    @Test
    public void sizeMethodTest_initEmptyLStack_sizeShouldBeZero() {
        //Arrange
        int expected = 0;
        //Act
        int actual = this.testStack.size();
        //Assert
        Assert.assertEquals("Empty LStack's size should be zero!", expected, actual);
    }

    @Test
    public void sizeMethodTest_addElementsToLStack_sizeShouldNotBeZero() {
        final int numberOfElements = 5;
        //Arrange
        for (int i = 0; i < numberOfElements; i++) {
            this.testStack.push(i);
        }
        int expected = numberOfElements;

        //Act
        int actual = this.testStack.size();
        //Assert
        Assert.assertEquals("LStack's size should not be zero!", expected, actual);
    }
    //endregion

    //region push() method
    @Test
    public void pushMethodTest_pushManyElementsToLStack_sizeShouldNotBeZero() {
        final int numberOfPushedElements = 100;
        //Arrange
        for (int i = 0; i < numberOfPushedElements; i++) {
            this.testStack.push(i);
        }
        int expected = numberOfPushedElements;
        //Act
        int actual = this.testStack.size();
        //Assert
        Assert.assertEquals(
                "LStack's size after pushing an element should not be zero!", expected, actual);
    }

    @Test
    public void pushMethodTest_pushElementToLStack_methodShouldReturnPushedElement() {
        final int element = 5;
        //Arrange
        int expected = element;
        //Act
        int actual = this.testStack.push(element);
        //Assert
        Assert.assertEquals(
                "Returned element should be equals to pushed!", expected, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void pushMethodTest_pushNullToLStack_shouldThrowException() {
        this.testStack.push(null);
    }
    //endregion

    //region pop() method
    @Test
    public void popMethodTest_popElementFromLStack_stackSizeShouldDecrease() {
        final int numberOfElements = 5;
        //Arrange
        for (int i = 0; i < numberOfElements; i++) {
            this.testStack.push(i);
        }
        int unexpected = numberOfElements;
        this.testStack.pop();
        //Act
        int actual = this.testStack.size();
        //Assert
        Assert.assertNotEquals(
                "LStack's size should be different after popping!", unexpected, actual);
    }

    @Test
    public void popMethodTest_popElementFromLStack_shouldReturnLastPushedElement() {
        final int lastPushedElement = 3;
        //Arrange
        this.testStack.push(1);
        this.testStack.push(2);
        this.testStack.push(lastPushedElement);
        int expected = lastPushedElement;
        //Act
        int actual = this.testStack.pop();
        //Assert
        Assert.assertEquals(
                "Popped element should be equals with last pushed!", expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void popMethodTest_popElementFromEmptyStack_shouldThrowException() {
        this.testStack.pop();
    }
    //endregion

    //region peek() method
    @Test
    public void peekMethodTest_peekElementFromLStack_shouldReturnLastPushedElement() {
        final int lastPushedElement = 5;
        //Arrange
        this.testStack.push(1);
        this.testStack.push(2);
        this.testStack.push(lastPushedElement);
        int expected = lastPushedElement;
        //Act
        int actual = this.testStack.peek();
        //Assert
        Assert.assertEquals(
                "Peeked element should be equals to last pushed!", expected, actual);
    }

    @Test
    public void peekMethodTest_peekElementFromLStack_sizeShouldNotChange() {
        final int numberOfPushedElements = 5;
        //Arrange
        for (int i = 0; i < numberOfPushedElements; i++) {
            this.testStack.push(i);
        }
        int expected = this.testStack.size();
        for (int i = 0; i < numberOfPushedElements; i++) {
            this.testStack.peek();
        }
        //Act
        int actual = this.testStack.size();
        //Assert
        Assert.assertEquals(
                "LStack's size should not change!", expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void peekMethodTest_peekElementFromEmptyStack_shouldThrowException() {
        this.testStack.peek();
    }
    //endregion

    //region isEmpty() method
    @Test
    public void isEmptyMethodTest_callItWhenStackIsEmpty_ShouldReturnTrue() {
        //Assert
        Assert.assertTrue(
                "When LStack has no elements in it should return true!", this.testStack.isEmpty());
    }

    @Test
    public void isEmptyMethodTest_callItWhenStackIsNotEmpty_ShouldReturnFalse() {
        //Arrange
        this.testStack.push(1);
        this.testStack.push(1);
        this.testStack.push(1);
        //Assert
        Assert.assertFalse(
                "When LStack has elements in it should return false!", this.testStack.isEmpty());
    }
    //endregion

    //region iterator() method
    /**
     * when we call LStack's iterator() it should return
     * LinkedList's iterator, because LStack's inner collection
     * is LinkedList
     */
    @Test
    public void iteratorTest_callLStackIterator_ShouldReturnLinkedListIterator() {
        //Arrange
        Iterator<Integer> expectedIterator = new LinkedList<Integer>().iterator();
        //Act
        Iterator<Integer> actualIterator = this.testStack.iterator();
        //Assert
        Assert.assertEquals(
                "LStack's iterator's class should be equals to LinkedList's iterator's class",
                expectedIterator.getClass(), actualIterator.getClass());
    }
    //endregion
}
