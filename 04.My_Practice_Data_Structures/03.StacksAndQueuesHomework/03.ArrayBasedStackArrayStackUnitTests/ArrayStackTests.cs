using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests
{
    [TestClass]
    public class ArrayStackTests
    {
        [TestMethod]
        public void TestConstructorWithNoCapacity_ShouldCreateStackWithDefaultCapacity()
        {
            //Arrange
            ArrayStack<int> testStack = new ArrayStack<int>();
            int expectedCapacity = 16;

            //Act
            var actualCapacity = testStack.Capacity();

            //Assert
            Assert.AreEqual(expectedCapacity, actualCapacity);
        }

        [TestMethod]
        public void TestConstructorWithCapacity_ShouldCreateStackWithGivenCapacity()
        {
            //Arrange
            ArrayStack<int> testStack = new ArrayStack<int>(100);
            int expectedCapacity = 100;

            //Act
            var actualCapacity = testStack.Capacity();

            //Assert
            Assert.AreEqual(expectedCapacity, actualCapacity);
        }

        [TestMethod]
        public void PushOneElement_CountOfElementsShouldIncrease()
        {
            //Arrange
            ArrayStack<int> testStack = new ArrayStack<int>();
            int expectedCountOfElements = 1;

            //Act
            testStack.Push(1);
            var actualCount = testStack.Count;

            //Assert
            Assert.AreEqual(expectedCountOfElements, actualCount);
        }

        [TestMethod]
        public void PushPop_ShouldWorkCorrectly()
        {
            // Arrange
            var testStack = new ArrayStack<string>();
            var element = "some value";

            // Act
            testStack.Push(element);
            var elementFromQueue = testStack.Pop();

            // Assert
            Assert.AreEqual(0, testStack.Count);
            Assert.AreEqual(element, elementFromQueue);
        }

        [TestMethod]
        [ExpectedException(typeof(InvalidOperationException))]
        public void Pop_EmptyStack_ThrowsException()
        {
            // Arrange
            var testStack = new ArrayStack<string>();

            // Act
            testStack.Pop();

            // Assert: expect and exception
        }

        [TestMethod]
        public void PushPop1000Elements_ShouldWorkCorrectly()
        {
            // Arrange
            var testStack = new ArrayStack<int>();
            int numberOfElements = 10;

            // Act
            for (int i = 0; i < numberOfElements; i++)
            {
                testStack.Push(i);
            }

            // Assert
            for (int i = numberOfElements; i > 0; i--)
            {
                Assert.AreEqual(i, testStack.Count);
                var element = testStack.Pop();
                Assert.AreEqual(i - 1, element);
                Assert.AreEqual(i - 1, testStack.Count);
            }
        }

        [TestMethod]
        public void PeekTest()
        {
            //Arrange
            var testStack = new ArrayStack<int>();
            var expected = 1;

            //Act
            testStack.Push(1);
            var actual = testStack.Peek();

            //Assert
            Assert.AreEqual(expected, actual);
        }

        [TestMethod]
        public void ToArrayTest()
        {
            //Arrange
            var testStack = new ArrayStack<int>();
            int[] expected = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

            //Act
            for (int i = 0; i < 10; i++)
            {
                testStack.Push(i);
            }
            var actual = testStack.ToArray();

            //Assert
            CollectionAssert.AreEqual(expected, actual);
        }
    }
}