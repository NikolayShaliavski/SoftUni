using System;
using System.Linq;

namespace _02.PermutationsWithIteration
{
    class PermutationsWithItertaion
    {
        static void Main(string[] args)
        {
            int n = 4;
            int[] array = Enumerable.Range(1, n).ToArray();
            int counter = 1;

            int[] p = new int[n];
            int i = 1;

            PrintPerm(array);
            while (i < n)
            {
                if (p[i] < i)
                {
                    int j = (i % 2) == 0 ? 0 : p[i];
                    Swap(array, j, i);
                    counter++;
                    PrintPerm(array);
                    p[i]++;
                    i = 1;
                }
                else
                {
                    p[i] = 0;
                    i++;
                }
            }
            Console.WriteLine("All permutations are: {0}", counter);
        }

        private static void PrintPerm(int[] array)
        {
            Console.WriteLine(string.Join(" ", array));
        }

        private static void Swap(int[] array, int i, int j)
        {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
