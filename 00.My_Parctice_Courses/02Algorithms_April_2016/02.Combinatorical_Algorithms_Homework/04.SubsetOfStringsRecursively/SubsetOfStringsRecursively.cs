using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04.SubsetOfStringsRecursively
{
    class SubsetOfStringsRecursively
    {
        static void Main(string[] args)
        {
            string[] set = { "test", "rock", "fun" };
            int k = 2;

            string[] subset = new string[k];

            GenerateComb(set, subset, 0, 0);
        }

        private static void GenerateComb(string[] set, string[] subset, int startIndex, int index)
        {
            if (index >= subset.Length)
            {
                PrintComb(subset);
                return;
            }
            
            for (int i = startIndex; i < set.Length; i++)
            {
                subset[index] = set[i];
                GenerateComb(set, subset, i + 1, index + 1);
            }
        }

        private static void PrintComb(string[] subset)
        {
            Console.WriteLine(string.Join(" ", subset));
        }
    }
}
