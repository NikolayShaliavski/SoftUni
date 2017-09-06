using System;
using System.Linq;

namespace _03.CombinationsWithIteration
{
    class CombinationsWithIteration
    {
        static void Main(string[] args)
        {
            var n = 5;
            var set = Enumerable.Range(1, n).ToArray();
            var k = 3;

            var indexes = new int[k];
            for (int i = 0; i < k; i++)
            {
                indexes[i] = i;
            }

            while (indexes[0] < (n - k) + 1)
            {
                PrintCom(set, indexes);
                indexes[indexes.Length - 1]++;

                for (int i = k - 1; i > 0; i--)
                {
                    if (indexes[i] >= (n - k) + i + 1)
                    {
                        indexes[i - 1]++;
                        for (int innerI = i - 1; innerI < k - 1; innerI++)
                        {                     
                            indexes[innerI + 1] = indexes[innerI] + 1;
                        }
                    }
                }   
            }
        }

        private static void PrintCom(int[] set, int[] indexes)
        {
            for (int i = 0; i < indexes.Length; i++)
            {
                Console.Write(set[indexes[i]] + " ");
            }
            Console.WriteLine();
        }
    }
}
