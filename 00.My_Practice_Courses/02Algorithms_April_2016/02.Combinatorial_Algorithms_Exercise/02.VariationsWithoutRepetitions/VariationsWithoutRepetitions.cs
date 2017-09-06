using System;

namespace _02.VariationsWithoutRepetitions
{
    class VariationsWithoutRepetitions
    {
        private static int[] array;
        private static bool[] used;

        static void Main(string[] args)
        {
            int n = 4;
            int k = 4;

            array = new int[k];
            used = new bool[n + 1];
            GenerateVariationsWithoutRepetitions(n);
        }

        private static void GenerateVariationsWithoutRepetitions(int sizeOfSet, int index = 0)
        {
            if (index >= array.Length)
            {
                PrintVariation();
            }
            else
            {
                for (int i = 1; i <= sizeOfSet; i++)
                {
                    if (!used[i])
                    {
                        used[i] = true;
                        array[index] = i;
                        GenerateVariationsWithoutRepetitions(sizeOfSet, index + 1);
                        used[i] = false;
                    }
                }
            }
        }

        private static void PrintVariation()
        {
            Console.WriteLine("({0})", string.Join(", ", array));
        }
    }
}
