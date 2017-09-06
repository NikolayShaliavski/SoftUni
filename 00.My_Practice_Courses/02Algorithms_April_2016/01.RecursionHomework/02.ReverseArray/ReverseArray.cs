using System;

namespace _02.ReverseArray
{
    class ReverseArray
    {
        private static int[] arrayToReverse = { 1, 2, 3, 4, 5, 6 };
        private static int[] reversedArray = new int[arrayToReverse.Length];

        static void Main(string[] args)
        {
            ReverseElement(0);
        }

        private static void ReverseElement(int oldIndex)
        {
            //bottom of the recursion - we have reached the end if the array
            if (oldIndex == arrayToReverse.Length)
            {
                PrintReversedArray();
                return;
            }

            int currentElement = arrayToReverse[oldIndex];
            reversedArray[arrayToReverse.Length - oldIndex - 1] = currentElement;
            ReverseElement(oldIndex + 1);
        }

        private static void PrintReversedArray()
        {
            Console.WriteLine(string.Join(" ", reversedArray));
        }
    }
}
