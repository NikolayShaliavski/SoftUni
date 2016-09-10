using System;
using System.Collections.Generic;

namespace _05.SumUnlimitedAmountOfCoins
{
    class SumUnlimitedAmountOfCoins
    {
        private static int allSumsCount;
        private static int sum;
        private static List<int> currentCom;
        private static List<string> allSums;

        static void Main(string[] args)
        {
            var targetSum = 100;
            int[] coins = { 1, 2, 5, 10, 20, 50, 100 };
            allSumsCount = 0;
            sum = 0;
            currentCom = new List<int>();
            allSums = new List<string>();

            GenSum(targetSum, coins);
            Console.WriteLine(allSumsCount);

            //foreach (var sum in allSums)
            //{
            //    Console.WriteLine(sum);
            //}
        }

        private static void GenSum(int targetSum, int[] coins)
        {
            if (sum > targetSum)
            {
                return;
            }
            if (sum == targetSum)
            {
                currentCom.Sort();
                string sum = string.Join("", currentCom);
                if (!allSums.Contains(sum))
                {
                    allSumsCount++;
                    allSums.Add(sum);
                }
                return;
            }
            for (int i = 0; i < coins.Length; i++)
            {
                sum += coins[i];
                currentCom.Add(coins[i]);
                GenSum(targetSum, coins);
                sum -= coins[i];
                currentCom.Remove(coins[i]);
            }
        }
    }
}
