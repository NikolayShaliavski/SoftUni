using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _05.SumUnlimitedAmountOfCoins
{
    class SumUnlimitedAmountOfCoins
    {
        private static int allSums;
        private static int sum;

        static void Main(string[] args)
        {
            var targetSum = 5;
            int[] coins = { 1, 2, 5 };
            allSums = 0;
            sum = 0;

            GenSum(targetSum, coins);
            Console.WriteLine(allSums);
        }

        private static void GenSum(int targetSum, int[] coins)
        {
            if (sum > targetSum)
            {
                return;
            }
            if (sum == targetSum)
            {
                allSums++;
                //sum = 0;
                return;
            }
            for (int i = 0; i < coins.Length; i++)
            {
                sum += coins[i];
                GenSum(targetSum, coins);
                sum -= coins[i];
            }
        }
    }
}
