using System;


namespace _04.CombinationsWithRepetitions
{
    class CombinationsWithRepetitions
    {
        private static int[] resultArray;

        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int k = int.Parse(Console.ReadLine());

            resultArray = new int[k];
            GenerateCombinations(0, 1, n);
        }

        private static void GenerateCombinations(int index, int start, int end)
        {
            if (index == resultArray.Length)
            {
                PrintResult();
                return;
            }
            for (int i = start; i <= end; i++)
            {
                resultArray[index] = i;
                GenerateCombinations(index + 1, i, end);
            }
        }

        private static void PrintResult()
        {
            Console.WriteLine(string.Join(" ", resultArray));
            Console.WriteLine();
        }
    }
}
