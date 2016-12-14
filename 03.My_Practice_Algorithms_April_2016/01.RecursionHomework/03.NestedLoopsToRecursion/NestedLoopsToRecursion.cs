using System;

namespace _03.NestedLoopsToRecursion
{
    class NestedLoopsToRecursion
    {
        private static int[] resultArray;

        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            resultArray = new int[n];
            SimulateLoop(0, n);
        }

        private static void SimulateLoop(int index, int end)
        {
            //bottom - if we are out of bounds of the array
            if (index == resultArray.Length)
            {
                PrintResult();
                return;
            }
            for (int i = 1; i <= end; i++)
            {
                resultArray[index] = i;
                //recursive call
                SimulateLoop(index + 1, end);
            }
        }

        private static void PrintResult()
        {
            for (int i = 0; i < resultArray.Length; i++)
            {
                Console.Write(resultArray[i] + " ");
            }
            Console.WriteLine();
        }
    }
}
