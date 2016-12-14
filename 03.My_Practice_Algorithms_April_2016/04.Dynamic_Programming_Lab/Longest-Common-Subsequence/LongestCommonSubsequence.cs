namespace Longest_Common_Subsequence
{
    using System;
    using System.Collections.Generic;

    public class LongestCommonSubsequence
    {
        public static void Main()
        {
            Console.Write("First string = ");
            var firstStr = Console.ReadLine();
            Console.Write("Second string = ");
            var secondStr = Console.ReadLine();

            var lcs = FindLongestCommonSubsequence(firstStr, secondStr);

            Console.WriteLine("Longest common subsequence:");
            Console.WriteLine("  first  = {0}", firstStr);
            Console.WriteLine("  second = {0}", secondStr);
            Console.WriteLine("  lcs    = {0}", lcs);
        }

        public static string FindLongestCommonSubsequence(string firstStr, string secondStr)
        {
            var firstLen = firstStr.Length + 1;
            var secondLen = secondStr.Length + 1;
            var lcs = new int[firstLen, secondLen];

            for (int row = 1; row < lcs.GetLength(0); row++)
            {
                for (int col = 1; col < lcs.GetLength(1); col++)
                {
                    if (firstStr[row - 1] == secondStr[col - 1])
                    {
                        lcs[row, col] = lcs[row - 1, col - 1] + 1;
                    }
                    else
                    {
                        lcs[row, col] = Math.Max(lcs[row - 1, col], lcs[row, col - 1]);
                    }
                }
            }
            string sequence = RetrieveLCS(firstStr, secondStr, lcs);
            return sequence;
        }

        private static string RetrieveLCS(string firstStr, string secondStr, int[,] lcs)
        {
            int row = firstStr.Length;
            int col = secondStr.Length;
            var sequence = new List<char>();

            while (row > 0 && col > 0)
            {
                if (firstStr[row - 1] == secondStr[col - 1])
                {
                    sequence.Add(firstStr[row - 1]);
                    row--;
                    col--;
                }
                else if (lcs[row, col] == lcs[row - 1, col])
                {
                    row--;
                }
                else
                {
                    col--;
                }
            }
            sequence.Reverse();
            return new string(sequence.ToArray());
        }
    }
}
