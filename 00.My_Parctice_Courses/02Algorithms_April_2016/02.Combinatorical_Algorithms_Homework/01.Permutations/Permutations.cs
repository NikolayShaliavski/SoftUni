using System;
using System.Linq;

namespace _01.Permutations
{
    class Permutations
    {
        private static int permutationsCounter = 0;

        static void Main(string[] args)
        {
            int n = 3;
            int[] array = Enumerable.Range(1, n).ToArray();
            Permute(array);
            Console.WriteLine(permutationsCounter);
        }

        private static void Permute(int[] array, int startIndex = 0)
        {
            if (startIndex == array.Length - 1)
            {
                permutationsCounter++;
                PrintPermutation(array);
                return;
            }
            for (int i = startIndex; i < array.Length; i++)
            {
                Swap(ref array[i], ref array[startIndex]);
                Permute(array, startIndex + 1);
                Swap(ref array[i], ref array[startIndex]);
            }
        }

        private static void PrintPermutation(int[] array)
        {
            Console.WriteLine("({0})", string.Join(", ", array));
        }

        private static void Swap(ref int i, ref int j)
        {
            if (i == j)
            {
                return;
            }
            i ^= j;
            j ^= i;
            i ^= j;
        }
    }
}
