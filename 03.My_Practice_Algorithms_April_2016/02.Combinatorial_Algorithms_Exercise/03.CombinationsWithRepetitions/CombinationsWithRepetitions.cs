using System;

namespace _03.CombinationsWithRepetitions
{
    class CombinationsWithRepetitions
    {
        private static int[] array;

        static void Main(string[] args)
        {
            int n = 3;
            int k = 2;

            array = new int[k];

            GenerateCombinations(n);
        }

        private static void GenerateCombinations(int sizeOfSet, int start = 1, int index = 0)
        {
            if (index >= array.Length)
            {
                PrintCombination();
                return;
            }
            for (int i = start; i <= sizeOfSet; i++)
            {
                array[index] = i;
                //if we want to allow repetitions we call recursion with start param == i
                //for n == 3 and k == 2 will produce (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 3)
                //but if we pass as param i + 1 the repetitions are NOT allowed
                //so programm will produce (1, 2), (1, 3), (2, 3)
                GenerateCombinations(sizeOfSet, i + 1, index + 1);
            }
        }

        private static void PrintCombination()
        {
            Console.WriteLine("({0})", string.Join(", ", array));
        }
    }
}
