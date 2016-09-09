using System;

namespace _01.BinomialCoeficients
{
    class BinomialCoeficients
    {
        static void Main(string[] args)
        {
            var n = 6;
            var k = 2;

            var firstArr = new int[n + 1];//even
            var secondArr = new int[n + 1];//odd

            firstArr[0] = 1;
            secondArr[0] = 1;
            secondArr[1] = 1;

            for (int i = 2; i <= n; i++)
            {
                if (i % 2 == 0)
                {
                    for (int j = 1; j < i; j++)
                    {
                        firstArr[j] = secondArr[j - 1] + secondArr[j];
                    }
                    firstArr[i] = 1;
                }
                else
                {
                    for (int j = 1; j < i; j++)
                    {
                        secondArr[j] = firstArr[j - 1] + firstArr[j];
                    }
                    secondArr[i] = 1;
                }
            }
            var coeficient = n % 2 == 0 ? firstArr[k] : secondArr[k];
            Console.WriteLine(coeficient);
        }
    }
}
