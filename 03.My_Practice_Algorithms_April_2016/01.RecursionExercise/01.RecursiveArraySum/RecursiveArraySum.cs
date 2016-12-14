using System;

namespace _01.RecursiveArraySum
{
    class RecursiveArraySum
    {
        static void Main(string[] args)
        {
            int[] numbers = { 1, 2, 3, 4 };
            int index = int.Parse(Console.ReadLine());

            if (index >= numbers.Length)
            {
                Console.WriteLine("Index is out of bounds of the array!");
                return;
            }
            long result = FindSum(numbers, index);
            Console.WriteLine(result);
        }

        private static long FindSum(int[] arr, int index)
        {
            if (index == arr.Length - 1)
            {
                return arr[index];
            }
            return arr[index] + FindSum(arr, index + 1);
        }
    }
}
